package com.kieronwiltshire.essentials.core.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essentials.core.services.request.Request;
import org.spongepowered.api.entity.player.Player;

import java.util.*;

public class RequestService {

    private HashMap<Request, Player> requests;

    /**
     * RequestService constructor
     */
    public RequestService() {
        this.requests = new HashMap<Request, Player>();
    }

    /**
     * Send a request
     *
     * @param recipient The recipient
     * @param request The request
     */
    public void send(Player recipient, Request request) {
        this.requests.put(request, recipient);
    }

    /**
     * Accept a request
     *
     * @param request The request
     */
    public void accept(Request request) {
        if (this.requests.containsKey(request)) {
            request.onAccept(this.requests.get(request));
            this.requests.remove(request);
        }
    }

    /**
     * Decline a request
     *
     * @param request The request
     */
    public void decline(Request request) {
        if (this.requests.containsKey(request)) {
            request.onDecline(this.requests.get(request));
            this.requests.remove(request);
        }
    }

    /**
     * Get a pending request
     *
     * @param id The id of the request
     * @return The request
     */
    public Optional<Request> getRequest(UUID id) {
        for(Iterator<Request> iter = this.requests.keySet().iterator(); iter.hasNext();) {
            Request req = iter.next();
            if (req.getUniqueId().equals(id)) {
                return Optional.of(req);
            }
        }
        return Optional.<Request>absent();
    }

    /**
     * Get all requests
     *
     * @return A collection of requests
     */
    public Collection<Request> getRequests() {
        return this.requests.keySet();
    }

    /**
     * Get all requests
     *
     * @param source The recipient of the requests you wish to retrieve
     * @return A collection of requests sent to the specified source
     */
    public Collection<Request> getRequests(Player source) {
        List<Request> collection = new ArrayList<Request>();
        for(Iterator<Request> iter = this.requests.keySet().iterator(); iter.hasNext();) {
            Request req = iter.next();
            if (this.requests.get(req).equals(source)) {
                collection.add(req);
            }
        }
        return collection;
    }

    /**
     * Get the recipient
     *
     * @param request The request
     * @return The recipient of the request specified
     */
    public Optional<Player> getRecipient(Request request) {
        if (this.requests.containsKey(request)) {
            return Optional.of(this.requests.get(request));
        }
        return Optional.<Player>absent();
    }

    /**
     * Get the recipients
     *
     * @return The recipients with currently pending requests
     */
    public Collection<Player> getRecipients() {
        return new HashSet<Player>(this.requests.values());
    }

    /**
     * Get the recipients
     *
     * @param type The type of request
     * @return The recipients with currently pending requests of the specified type
     */
    public Collection<Player> getRecipients(Class<Request> type) {
        Set<Player> collection = new HashSet<Player>();
        for(Iterator<Request> iter = this.requests.keySet().iterator(); iter.hasNext();) {
            Request req = iter.next();
            if (req.getClass().isInstance(type)) {
                collection.add(this.requests.get(req));
            }
        }
        return collection;
    }

}
