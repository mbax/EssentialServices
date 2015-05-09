package com.kieronwiltshire.essential_services.core.services;

import com.google.common.base.*;
import com.google.common.base.Optional;
import com.google.common.eventbus.Subscribe;
import com.kieronwiltshire.essential_services.core.api.ChatService;
import com.kieronwiltshire.essential_services.core.api.chat.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;

import java.util.*;

public class EssentialsChatService implements ChatService {

    private HashMap<Channel, Collection<Player>> channels;

    /**
     * EssentialsChatService constructor
     */
    public EssentialsChatService() {
        this.channels = new HashMap<Channel, Collection<Player>>();
    }

    @Override
    public void send(Channel channel, Text message) throws NullPointerException {
        for (Player p : this.getPlayers(channel)) {
            p.sendMessage(ChatTypes.CHAT, message);
        }
    }

    @Override
    public void join(Channel channel, Player player) {
        if (this.channels.containsKey(channel)) {
            Collection<Player> collection = this.channels.get(channel);
            if (!collection.contains(player)) {
                if (collection.size() < channel.capacity() || channel.capacity() < 0) {
                    collection.add(player);
                }
            }
            return;
        }

        List<Player> collection = new ArrayList<Player>();
        collection.add(player);

        this.channels.put(channel, collection);
    }

    @Override
    public void leave(Channel channel, Player player) {
        if (this.channels.containsKey(channel)) {
            Collection<Player> collection = this.channels.get(channel);
            if (collection.contains(player)) {
                collection.remove(player);
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
    public Collection<Channel> getChannels(Player player) {
        List<Channel> collection = new ArrayList<Channel>();
        for(Channel c : this.getChannels()) {
            if (this.channels.get(c).contains(player)) {
                collection.add(c);
            }
        }
        return collection;
    }

    @Override
    public Collection<Player> getPlayers() {
        Set<Player> collection = new HashSet<Player>();
        for (Channel c : this.getChannels()) {
            collection.addAll(this.channels.get(c));
        }
        return collection;
    }

    @Override
    public Collection<Player> getPlayers(Channel channel) throws NullPointerException {
        return this.channels.get(channel);
    }

}
