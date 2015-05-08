package com.kieronwiltshire.essentials.core.request;

import com.google.common.base.Optional;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.command.CommandSource;

public interface Request extends Identifiable {

    /**
     * The message of the request
     *
     * @return The message the implemented request
     */
    Optional<Text> message();

    /**
     * This method is called upon accepting the request
     *
     * @param recipient The recipient of the request
     */
    void onAccept(CommandSource recipient);

    /**
     * This method is called upon declining the request
     *
     * @param recipient The recipient of the request
     */
    void onDecline(CommandSource recipient);

}
