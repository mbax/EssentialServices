package com.kieronwiltshire.essential_services.core.services;

import com.kieronwiltshire.essential_services.core.api.services.CooldownService;
import org.spongepowered.api.entity.player.User;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: This may need some work
 */
public class EssentialsCooldownService implements CooldownService {
    private final Map<Object, Long> objectCooldowns;
    private final Map<User, Map<Object, Long>> userCooldowns; // TODO store when user leaves, don't keep in memory

    public EssentialsCooldownService() {
        this.objectCooldowns = new HashMap<>();
        this.userCooldowns = new HashMap<>();
    }

    @Override
    public void cooldown(Object object) {
        synchronized (this.objectCooldowns) {
            this.objectCooldowns.put(object, System.currentTimeMillis());
        }
    }

    @Override
    public void cooldown(Class<?> clazz) {
        this.cooldown((Object) clazz);
    }

    @Override
    public void cooldown(User user, Object object) {
        synchronized (this.userCooldowns) {
            Map<Object, Long> map = this.userCooldowns.get(user);
            if (map == null) {
                map = new HashMap<>();
                this.userCooldowns.put(user, map);
            }
            map.put(object, System.currentTimeMillis());
        }
    }

    @Override
    public void cooldown(User user, Class<?> clazz) {
        this.cooldown(user, (Object) clazz);
    }

    @Override
    public void retract(Object object) {
        synchronized (this.objectCooldowns) {
            this.objectCooldowns.remove(object);
        }
    }

    @Override
    public void retract(Class<?> clazz) {
        this.retract((Object) clazz);
    }

    @Override
    public void retract(User user, Object object) {
        synchronized (this.userCooldowns) {
            Map<Object, Long> map = this.userCooldowns.get(user);
            if (map != null) {
                map.remove(object);
                if (map.isEmpty()) {
                    this.userCooldowns.remove(user);
                }
            }
        }
    }

    @Override
    public void retract(User user, Class<?> clazz) {
        this.retract(user, (Object) clazz);
    }

    @Override
    public boolean isAvailable(Object object, long time) {
        synchronized (this.objectCooldowns) {
            Long lastUsed = this.objectCooldowns.get(object);
            return lastUsed == null || lastUsed + time < System.currentTimeMillis();
        }
    }

    @Override
    public boolean isAvailable(Class<?> clazz, long time) {
        return this.isAvailable((Object) clazz, time);
    }

    @Override
    public boolean isAvailable(User user, Object object, long time) {
        synchronized (this.userCooldowns) {
            Map<Object, Long> map = this.userCooldowns.get(user);
            if (map != null) {
                Long lastUsed = map.get(object);
                return lastUsed == null || lastUsed + time < System.currentTimeMillis();
            }
        }
        return true;
    }

    @Override
    public boolean isAvailable(User user, Class<?> clazz, long time) {
        return this.isAvailable(user, (Object) clazz, time);
    }
}