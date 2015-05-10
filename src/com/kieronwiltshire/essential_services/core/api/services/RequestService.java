package com.kieronwiltshire.essential_services.core.api.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.services.request.Request;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.entity.player.User;

import java.util.Collection;
import java.util.UUID;

public interface RequestService {

    /**
     * Send a request
     *
     * @param recipient The recipient
     * @param request The request
     */
    void send(User recipient, Request request);

    /**
     * Retract a request
     *
     * @param request The request
     */
    void retract(Request request);

    /**
     * Accept a request
     *
     * @param request The request
     */
    boolean accept(Request request);

    /**
     * Decline a request
     *
     * @param request The request
     */
    boolean decline(Request request);

    /**
     * Get a pending request
     *
     * @param id The id of the request
     * @return The request
     */
    Optional<Request> getRequest(UUID id);

    /**
     * Get all requests
     *
     * @return A collection of requests
     */
    Collection<Request> getRequests();

    /**
     * Get all requests
     *
     * @param source The recipient of the requests you wish to retrieve
     * @return A collection of requests sent to the specified source
     */
    Collection<Request> getRequests(User source);

    /**
     * Get the recipient
     *
     * @param request The request
     * @return The recipient of the request specified
     */
    Optional<User> getRecipient(Request request);

    /**
     * Get the recipients
     *
     * @return The recipients with currently pending requests
     */
    Collection<User> getRecipients();

    /**
     * Get the recipients
     *
     * @param type The type of request
     * @return The recipients with currently pending requests of the specified type
     */
    <T extends Request> Collection<User> getRecipients(Class<T> type);

}
