package com.kieronwiltshire.essentials.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kieronwiltshire.essentials.core.request.IRequestService;
import com.kieronwiltshire.essentials.core.request.Request;
import org.spongepowered.api.util.command.CommandSource;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RequestService implements IRequestService {

    private static int expiration = 60;

    private HashMap<Request, CommandSource> requests;

    /**
     * RequestService constructor
     */
    public RequestService() {
        this.requests = new HashMap<Request, CommandSource>();
    }

    @Override
    public void send(CommandSource source, Request request) {
        this.requests.put(request, source);
        if (request.message().isPresent()) {
            source.sendMessage(request.message().get());
        }
    }

}
