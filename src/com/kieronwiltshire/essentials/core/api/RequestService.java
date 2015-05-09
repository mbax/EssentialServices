/**
 * The design of the request service is highly influenced from the
 * DoubleCheck confirmation service that was created by Felix Schmidt.
 */
package com.kieronwiltshire.essentials.core.api;

import com.google.common.base.Optional;
import com.kieronwiltshire.essentials.core.api.request.Request;
import org.spongepowered.api.entity.player.Player;

import java.util.Collection;
import java.util.UUID;

public interface RequestService {

    /**
     * Send a request
     *
     * @param recipient The recipient
     * @param request The request
     */
    void send(Player recipient, Request request);

    /**
     * Accept a request
     *
     * @param request The request
     */
    void accept(Request request);

    /**
     * Decline a request
     *
     * @param request The request
     */
    void decline(Request request);

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
    Collection<Request> getRequests(Player source);

    /**
     * Get the recipient
     *
     * @param request The request
     * @return The recipient of the request specified
     */
    Optional<Player> getRecipient(Request request);

    /**
     * Get the recipients
     *
     * @return The recipients with currently pending requests
     */
    Collection<Player> getRecipients();

    /**
     * Get the recipients
     *
     * @param type The type of request
     * @return The recipients with currently pending requests of the specified type
     */
    Collection<Player> getRecipients(Class<Request> type);

}