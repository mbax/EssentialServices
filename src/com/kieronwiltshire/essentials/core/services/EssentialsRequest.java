package com.kieronwiltshire.essentials.core.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essentials.core.api.RequestService;
import com.kieronwiltshire.essentials.core.services.request.Request;
import org.spongepowered.api.entity.player.Player;

import java.util.*;

public class EssentialsRequest implements RequestService {

    private HashMap<Request, Player> requests;

    /**
     * EssentialsRequest constructor
     */
    public EssentialsRequest() {
        this.requests = new HashMap<Request, Player>();
    }

    @Override
    public void send(Player recipient, Request request) {
        this.requests.put(request, recipient);
    }

    @Override
    public void accept(Request request) {
        if (this.requests.containsKey(request)) {
            request.onAccept(this.requests.get(request));
            this.requests.remove(request);
        }
    }

    @Override
    public void decline(Request request) {
        if (this.requests.containsKey(request)) {
            request.onDecline(this.requests.get(request));
            this.requests.remove(request);
        }
    }

    @Override
    public Optional<Request> getRequest(UUID id) {
        for(Iterator<Request> iter = this.requests.keySet().iterator(); iter.hasNext();) {
            Request req = iter.next();
            if (req.getUniqueId().equals(id)) {
                return Optional.of(req);
            }
        }
        return Optional.<Request>absent();
    }

    @Override
    public Collection<Request> getRequests() {
        return this.requests.keySet();
    }

    @Override
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

    @Override
    public Optional<Player> getRecipient(Request request) {
        if (this.requests.containsKey(request)) {
            return Optional.of(this.requests.get(request));
        }
        return Optional.<Player>absent();
    }

    @Override
    public Collection<Player> getRecipients() {
        return new HashSet<Player>(this.requests.values());
    }

    @Override
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
