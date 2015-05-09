/**
 * The design of the request service is highly influenced from the
 * DoubleCheck confirmation service that was created by Felix Schmidt.
 */
package com.kieronwiltshire.essentials.core.api.request;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.Identifiable;

public interface Request extends Identifiable {

    /**
     * This method is called upon accepting the request
     *
     * @param recipient The recipient of the request
     */
    void onAccept(Player recipient);

    /**
     * This method is called upon declining the request
     *
     * @param recipient The recipient of the request
     */
    void onDecline(Player recipient);

}
