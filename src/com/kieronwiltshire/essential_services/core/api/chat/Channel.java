package com.kieronwiltshire.essential_services.core.api.chat;

import com.google.common.base.Optional;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Identifiable;

import java.util.Collection;

public interface Channel extends Identifiable {

    /**
     * Get the capacity
     *
     * @return The capacity of the channel
     */
    int capacity();

}
