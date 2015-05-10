package com.kieronwiltshire.essential_services.core.api;

import com.flowpowered.math.vector.Vector3d;
import com.google.common.base.Optional;
import org.spongepowered.api.entity.player.User;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.world.World;

public interface SpawnLocation {

    /**
     * Get the position
     *
     * @return The position vector
     */
    Vector3d position();

    /**
     * Get the rotation
     *
     * @return The rotation vector
     */
    Vector3d rotation();

    /**
     * Get the world
     *
     * @return The world
     */
    World world();

}
