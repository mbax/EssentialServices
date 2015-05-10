package com.kieronwiltshire.essential_services.core.api.services;

import org.spongepowered.api.entity.player.User;

public interface CooldownService {

    /**
     * Add an object to the cooldown
     *
     * @param object The object
     */
    void cooldown(Object object);

    /**
     * Add a class to the cooldown
     *
     * @param clazz The class
     */
    void cooldown(Class<?> clazz);

    /**
     * Add an object to the cooldown
     *
     * @param user The user
     * @param object The object
     */
    void cooldown(User user, Object object);

    /**
     * Add a class to the cooldown
     *
     * @param user The user
     * @param clazz The class
     */
    void cooldown(User user, Class<?> clazz);

    /**
     * Retract an object
     *
     * @param object The object
     */
    void retract(Object object);

    /**
     * Retract a class
     *
     * @param clazz The class
     */
    void retract(Class<?> clazz);

    /**
     * Retract an object
     *
     * @param user The user
     * @param object The object
     */
    void retract(User user, Object object);

    /**
     * Retract a class
     *
     * @param user The user
     * @param clazz The class
     */
    void retract(User user, Class<?> clazz);

    /**
     * Check if an object is available
     *
     * @param object The object
     * @param time The time in milliseconds
     * @return True if the object is available for use
     */
    boolean isAvailable(Object object, long time);

    /**
     * Check if a class is available
     *
     * @param clazz The class
     * @param time The time in milliseconds
     * @return True if the class is available for use
     */
    boolean isAvailable(Class<?> clazz, long time);

    /**
     * Check if an object is available
     *
     * @param user The user
     * @param object The object
     * @param time The time in milliseconds
     * @return True if the object is available for use
     */
    boolean isAvailable(User user, Object object, long time);

    /**
     * Check if a class is available
     *
     * @param user The user
     * @param clazz The class
     * @param time The time in milliseconds
     * @return True if the class is available for use
     */
    boolean isAvailable(User user, Class<?> clazz, long time);

}
