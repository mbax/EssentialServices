package com.kieronwiltshire.essential_services.core.api;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.SpawnLocation;
import org.spongepowered.api.entity.player.User;

public interface Warp {

    /**
     * Get the name
     *
     * @return The name of the warp
     */
    String name();

    /**
     * Get the creator
     *
     * @return The person who created the warp
     */
    User creator();

    /**
     * Get the modifier
     *
     * @return The person who last modified the warp
     */
    Optional<User> modifier();

    /**
     * Get the spawn
     *
     * @return The spawn location
     */
    SpawnLocation getSpawn();

}
