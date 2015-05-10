package com.kieronwiltshire.essential_services.core.api.services;

import org.spongepowered.api.entity.player.User;

public interface CooldownService {
    /**
     * Adds a cooldown for an object.
     *
     * @param object the object to be added
     */
    void cooldown(Object object);

    /**
     * Adds a cooldown for a class.
     *
     * @param clazz the class to be added
     */
    void cooldown(Class<?> clazz);

    /**
     * Adds a user-specific cooldown for an object.
     *
     * @param user the user affected by the cooldown
     * @param object the object to be added
     */
    void cooldown(User user, Object object);

    /**
     * Adds a user-specific cooldown for a class.
     *
     * @param user the user affected by the cooldown
     * @param clazz the class to be added
     */
    void cooldown(User user, Class<?> clazz);

    /**
     * Removes a cooldown for an object.
     *
     * @param object the object for which the cooldown is removed
     */
    void retract(Object object);

    /**
     * Removes a cooldown for a class.
     *
     * @param clazz the class for which the cooldown is removed
     */
    void retract(Class<?> clazz);

    /**
     * Removes a user-specific cooldown for an object.
     *
     * @param user the user affected by the cooldown
     * @param object the object for which the cooldown is removed
     */
    void retract(User user, Object object);

    /**
     * Removes a user-specific cooldown for a class.
     *
     * @param user the user affected by the cooldown
     * @param clazz the class for which the cooldown is removed
     */
    void retract(User user, Class<?> clazz);

    /**
     * Checks if an object is affected currently by a cooldown of a provided
     * time.
     *
     * @param object the object potentially affected by a cooldown
     * @param time milliseconds of cooldown
     * @return true if the object is not affected
     */
    boolean isAvailable(Object object, long time);

    /**
     * Checks if a class is affected currently by a cooldown of a provided
     * time.
     *
     * @param clazz the class potentially affected by a cooldown
     * @param time milliseconds of cooldown
     * @return true if the object is not affected
     */
    boolean isAvailable(Class<?> clazz, long time);

    /**
     * Checks if a user and related object are affected currently by a
     * cooldown of a given time.
     *
     * @param user the user potentially affected by a cooldown
     * @param object the object potentially affected by a cooldown
     * @param time milliseconds of cooldown
     * @return true if the object is not affected
     */
    boolean isAvailable(User user, Object object, long time);

    /**
     * Checks if a user and related class are affected currently by a
     * cooldown of a given time.
     *
     * @param user the user potentially affected by a cooldown
     * @param clazz the class potentially affected by a cooldown
     * @param time milliseconds of cooldown
     * @return true if the object is not affected
     */
    boolean isAvailable(User user, Class<?> clazz, long time);
}