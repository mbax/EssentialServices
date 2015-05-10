package com.kieronwiltshire.essential_services.core.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.services.RequestService;
import com.kieronwiltshire.essential_services.core.api.services.request.Request;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.entity.player.User;

import java.util.*;

public class EssentialsRequestService implements RequestService {

    private HashMap<Request, User> requests;

    /**
     * EssentialsRequestService constructor
     */
    public EssentialsRequestService() {
        this.requests = new HashMap<Request, User>();
    }

    @Override
    public void send(User recipient, Request request) {
        this.requests.put(request, recipient);
    }

    @Override
    public void retract(Request request) {
        if (this.requests.containsKey(request)) {
            this.requests.remove(request);
        }
    }

    @Override
    public boolean accept(Request request) {
        if (this.requests.containsKey(request)) {
            User u = this.requests.get(request);
            if (u.getPlayer().isPresent()) {
                request.onAccept(u.getPlayer().get());
                this.requests.remove(request);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean decline(Request request) {
        if (this.requests.containsKey(request)) {
            User u = this.requests.get(request);
            if (u.getPlayer().isPresent()) {
                request.onDecline(u.getPlayer().get());
                this.requests.remove(request);
                return true;
            }
        }
        return false;
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
    public Collection<Request> getRequests(User source) {
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
    public Optional<User> getRecipient(Request request) {
        if (this.requests.containsKey(request)) {
            return Optional.of(this.requests.get(request));
        }
        return Optional.<User>absent();
    }

    @Override
    public Collection<User> getRecipients() {
        return new HashSet<User>(this.requests.values());
    }

    @Override
    public <T extends Request> Collection<User> getRecipients(Class<T> type) {
        Set<User> collection = new HashSet<User>();
        for(Iterator<Request> iter = this.requests.keySet().iterator(); iter.hasNext();) {
            Request req = iter.next();
            if (req.getClass().isInstance(type)) {
                collection.add(this.requests.get(req));
            }
        }
        return collection;
    }

    /*
    TODO: Move this code out of the service, and register it somewhere else.
          Services should not be registered as event listeners!

    @Subscribe(order = Order.LATE)
    private void onCommand(CommandEvent e) {
        if (e.getSource() instanceof Player) {
            if (e.getCommand().equalsIgnoreCase("confirm") || e.getCommand().equalsIgnoreCase("decline")) {
                UUID id = UUID.fromString(e.getArguments().substring(0, e.getArguments().indexOf(' ')));
                if (id != null) {
                    if (this.getRequest(id).isPresent()) {
                        e.setCancelled(true);
                        e.setResult(CommandResult.empty());

                        Request req = this.getRequest(id).get();

                        if (e.getCommand().equalsIgnoreCase("confirm")) req.onAccept((Player) e.getSource());
                        else req.onDecline((Player) e.getSource());
                    }
                }
            }
        }
    }
    */

}
