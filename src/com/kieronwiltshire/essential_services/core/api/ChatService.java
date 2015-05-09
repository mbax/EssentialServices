package com.kieronwiltshire.essential_services.core.api;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.chat.Channel;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Collection;
import java.util.UUID;

public interface ChatService {

    /**
     * Send a message to a channel
     *
     * @param channel The channel
     * @param message The message
     */
    void send(Channel channel, Text message);

    /**
     * Add a player to a specific channel
     *
     * @param channel The channel
     * @param player The player
     */
    void join(Channel channel, Player player);

    /**
     * Remove a player from a specific channel
     *
     * @param channel The channel
     * @param player The player
     */
    void leave(Channel channel, Player player);

    /**
     * Get a channel
     *
     * @param id The id
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
     * @param player The player
     * @return All of the channels that the player is in
     */
    Collection<Channel> getChannels(Player player);

    /**
     * Get all players
     *
     * @return Get all of the players within a channel
     */
    Collection<Player> getPlayers();

    /**
     * Get all players
     *
     * @param channel The channel
     * @return Get all of the players within the specified channel
     */
    Collection<Player> getPlayers(Channel channel);

}
