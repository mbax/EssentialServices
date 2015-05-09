package com.kieronwiltshire.essential_services.core.api.services.chat;

import org.spongepowered.api.util.Identifiable;

public interface Channel extends Identifiable {

    /**
     * Get the capacity
     *
     * @return The capacity of the channel
     */
    int capacity();

}
