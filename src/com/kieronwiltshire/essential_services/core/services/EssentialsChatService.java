package com.kieronwiltshire.essential_services.core.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.services.ChatService;
import com.kieronwiltshire.essential_services.core.api.services.chat.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.entity.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

import java.util.*;

public class EssentialsChatService implements ChatService {

    private HashMap<Channel, Collection<User>> channels;

    /**
     * EssentialsChatService constructor
     */
    public EssentialsChatService() {
        this.channels = new HashMap<Channel, Collection<User>>();
    }

    @Override
    public boolean register(Channel channel) {
        if (!this.channels.containsKey(channel)) {
            this.channels.put(channel, new ArrayList<User>());
            return true;
        }
        return false;
    }

    @Override
    public boolean unregister(Channel channel) {
        if (this.channels.containsKey(channel)) {
            this.channels.remove(channel);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregister(UUID id) {
        if (this.getChannel(id).isPresent()) {
            this.unregister(this.getChannel(id).get());
            return true;
        }
        return false;
    }

    @Override
    public void join(Channel channel, User user) {
        if (this.channels.containsKey(channel)) {
            Collection<User> collection = this.channels.get(channel);
            if (!collection.contains(user)) {
                if (collection.size() < channel.capacity() || channel.capacity() < 0) {
                    collection.add(user);
                }
            }
        }
    }

    @Override
    public void leave(Channel channel, User user) {
        if (this.channels.containsKey(channel)) {
            Collection<User> collection = this.channels.get(channel);
            if (collection.contains(user)) {
                collection.remove(user);
            }
        }
    }

    @Override
    public void send(Channel channel, Text message) throws NullPointerException {
        for (User user : this.getUsers(channel)) {
            if (user.isOnline()) {
                if (user.getPlayer().isPresent()) {
                    user.getPlayer().get().sendMessage(ChatTypes.CHAT, message);
                }
            }
        }
    }

    @Override
    public Optional<Channel> getChannel(UUID id) {
        for (Channel c : this.getChannels()) {
            if (c.getUniqueId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.<Channel>absent();
    }

    @Override
    public Collection<Channel> getChannels() {
        return this.channels.keySet();
    }

    @Override
    public Collection<Channel> getChannels(User user) {
        List<Channel> collection = new ArrayList<Channel>();
        for(Channel c : this.getChannels()) {
            if (this.channels.get(c).contains(user)) {
                collection.add(c);
            }
        }
        return collection;
    }

    @Override
    public Collection<User> getUsers() {
        Set<User> collection = new HashSet<User>();
        for (Channel c : this.getChannels()) {
            collection.addAll(this.channels.get(c));
        }
        return collection;
    }

    @Override
    public Collection<User> getUsers(Channel channel) throws NullPointerException {
        return this.channels.get(channel);
    }

}
