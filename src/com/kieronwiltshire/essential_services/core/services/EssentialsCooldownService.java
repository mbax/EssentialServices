package com.kieronwiltshire.essential_services.core.services;

import com.kieronwiltshire.essential_services.core.api.services.CooldownService;
import org.spongepowered.api.entity.player.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO: This may need some work
 */
public class EssentialsCooldownService implements CooldownService {

    private HashMap<Object, Long> instanceCooldowns;
    private HashMap<Class, Long> objectCooldowns;
    private HashMap<User, HashMap<Object, Long>> userCooldowns;

    /**
     * EssentialsCooldownService constructor
     */
    public EssentialsCooldownService() {
        this.instanceCooldowns = new HashMap<Object, Long>();
        this.objectCooldowns = new HashMap<Class, Long>();
        this.userCooldowns = new HashMap<User, HashMap<Object, Long>>();
    }

    @Override
    public void cooldown(Object object) {
        this.instanceCooldowns.put(object, System.currentTimeMillis());
    }

    @Override
    public void cooldown(Class<?> clazz) {
        this.objectCooldowns.put(clazz, System.currentTimeMillis());
    }

    @Override
    public void cooldown(User user, Object object) {
        if (!this.userCooldowns.containsKey(user)) {
            this.userCooldowns.put(user, new HashMap<Object, Long>());
        }
        this.userCooldowns.get(user).put(object, System.currentTimeMillis());
    }

    @Override
    public void cooldown(User user, Class<?> clazz) {
        this.cooldown(user, clazz);
    }

    @Override
    public void retract(Object object) {
        this.retract(this.instanceCooldowns, object);
    }

    @Override
    public void retract(Class<?> clazz) {
        this.retract(this.objectCooldowns, clazz);
    }

    /**
     * Remove an key value from the map
     *
     * @param map The map
     * @param object The object
     */
    private void retract(HashMap<?, ?> map, Object object) {
        if (map.containsKey(object)) {
            map.remove(object);
        }
    }

    @Override
    public void retract(User user, Object object) {
        if (this.userCooldowns.containsKey(user)) {
            if (this.userCooldowns.get(user).containsKey(object)) {
                this.userCooldowns.get(user).remove(object);
            }
        }
    }

    @Override
    public void retract(User user, Class<?> clazz) {
        if (this.userCooldowns.containsKey(user)) {
            if (this.userCooldowns.get(user).containsKey(clazz)) {
                this.userCooldowns.get(user).remove(clazz);
            }
        }
    }

    @Override
    public boolean isAvailable(Object object, long time) {
        return this.isAvailable(this.instanceCooldowns, object, time);
    }

    @Override
    public boolean isAvailable(Class<?> clazz, long time) {
        return this.isAvailable(this.objectCooldowns, clazz, time);
    }

    /**
     * Check if an object is available
     *
     * @param map The map
     * @param object The object
     * @param time The time in milliseconds
     * @return True if the object is available for use
     */
    private boolean isAvailable(HashMap<?, Long> map, Object object, long time) {
        boolean available = true;
        if (map.containsKey(object)) {
            if (map.get(object) + time <= System.currentTimeMillis()) {
                map.remove(object);
            } else {
                available = false;
            }
        }
        return available;
    }

    @Override
    public boolean isAvailable(User user, Object object, long time) {
        boolean available = false;
        if (this.isAvailable(object, time)) {
            available = true;
            if (this.userCooldowns.containsKey(user)) {
                if (this.userCooldowns.get(user).containsKey(object)) {
                    if (this.userCooldowns.get(user).get(object) + time <= System.currentTimeMillis()) {
                        this.userCooldowns.get(user).remove(object);
                    } else {
                        available = false;
                    }
                }
            }
        }
        return available;
    }

    @Override
    public boolean isAvailable(User user, Class<?> clazz, long time) {
        boolean available = false;
        if (this.isAvailable(clazz, time)) {
            available = true;
            if (this.userCooldowns.containsKey(user)) {
                for (Iterator<Object> iter = this.userCooldowns.get(user).keySet().iterator(); iter.hasNext();) {
                    Object o = iter.next();

                    if (o.equals(clazz) || o.getClass().isInstance(clazz)) {
                        if (this.userCooldowns.get(user).get(o) + time <= System.currentTimeMillis()) {
                            iter.remove();
                        } else {
                            available = false;
                        }
                        break;
                    }
                }
            }
        }
        return available;
    }

}
