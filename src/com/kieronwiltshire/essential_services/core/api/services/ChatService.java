package com.kieronwiltshire.essential_services.core.api.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.services.chat.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.entity.player.User;
import org.spongepowered.api.text.Text;

import java.util.Collection;
import java.util.UUID;

public interface ChatService {

    /**
     * Register a channel
     *
     * @param channel The channel
     * @return True if the specified channel was registered
     */
    boolean register(Channel channel);

    /**
     * Unregister a channel
     *
     * @param channel The channel
     * @return True if the specified channel was unregistered
     */
    boolean unregister(Channel channel);

    /**
     * Unregister a channel
     *
     * @param id The channel identifier
     * @return True if the channel with the specified identifier was unregistered
     */
    boolean unregister(UUID id);

    /**
     * Add a player to a specific channel
     *
     * @param channel The channel
     * @param user The user
     */
    void join(Channel channel, User user);

    /**
     * Remove a player from a specific channel
     *
     * @param channel The channel
     * @param user The user
     */
    void leave(Channel channel, User user);

    /**
     * Send a message to a channel
     *
     * @param channel The channel
     * @param message The message
     */
    void send(Channel channel, Text message);

    /**
     * Get a channel
     *
     * @param id The channel identifier
     * @return Get a channel with the specified identifier
     */
    Optional<Channel> getChannel(UUID id);

    /**
     * Get all of the channels
     *
     * @return All of the channels
     */
    Collection<Channel> getChannels();

    /**
     * Get all of the channels
     *
     * @param user The user
     * @return All of the channels that the player is in
     */
    Collection<Channel> getChannels(User user);

    /**
     * Get all users
     *
     * @return Get all of the users within a channel
     */
    Collection<User> getUsers();

    /**
     * Get all users
     *
     * @param channel The channel
     * @return Get all of the users within the specified channel
     */
    Collection<User> getUsers(Channel channel);

}
