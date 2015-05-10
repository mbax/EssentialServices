package com.kieronwiltshire.essential_services.core.services;

import com.kieronwiltshire.essential_services.core.api.services.CooldownService;
import org.spongepowered.api.entity.player.User;

import java.util.HashMap;

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
    public void cooldown(Class object) {
        this.objectCooldowns.put(object, System.currentTimeMillis());
    }

    @Override
    public void cooldown(User user, Object object) {
        if (!this.userCooldowns.containsKey(user)) {
            this.userCooldowns.put(user, new HashMap<Object, Long>());
        }
        this.userCooldowns.get(user).put(object, System.currentTimeMillis());
    }

    @Override
    public void cooldown(User user, Class object) {
        this.cooldown(user, object);
    }

    @Override
    public void retract(Object object) {
        if (this.instanceCooldowns.containsKey(object)) {
            this.instanceCooldowns.remove(object);
        }
    }

    @Override
    public void retract(Class object) {
        if (this.objectCooldowns.containsKey(object)) {
            this.objectCooldowns.remove(object);
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
    public void retract(User user, Class object) {
        if (this.userCooldowns.containsKey(user)) {
            if (this.userCooldowns.get(user).containsKey(object)) {
                this.userCooldowns.get(user).remove(object);
            }
        }
    }

    @Override
    public boolean isAvailable(Object object, long time) {
        boolean available = true;
        if (this.instanceCooldowns.containsKey(object)) {
            if (this.instanceCooldowns.get(object) + time <= System.currentTimeMillis()) {
                this.instanceCooldowns.remove(object);
            } else {
                available = false;
            }
        }
        return available;
    }

    @Override
    public boolean isAvailable(Class object, long time) {
        boolean available = true;
        if (this.objectCooldowns.containsKey(object)) {
            if (this.objectCooldowns.get(object) + time <= System.currentTimeMillis()) {
                this.objectCooldowns.remove(object);
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
    public boolean isAvailable(User user, Class object, long time) {
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

}
