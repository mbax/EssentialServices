package com.kieronwiltshire.essential_services.core.api.spawn_points;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.SpawnPoint;
import org.spongepowered.api.entity.player.User;

public interface Warp extends SpawnPoint {

    /**
     * Get the name
     *
     * @return The name of the warp
     */
    String name();

    /**
     * Get the modifier
     *
     * @return The person who last modified the warp
     */
    Optional<User> modifier();

}
