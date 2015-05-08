package com.kieronwiltshire.essentials.core.request;

import org.spongepowered.api.util.command.CommandSource;

public interface IRequestService {

    /**
     * Send a request
     *
     * @param source The recipient of the request
     * @param request The request to send
     */
    void send(CommandSource source, Request request);

}
