package com.kieronwiltshire.essentials;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = "Essentials", name = "Essentials", version = "4.0.0")
public class Essentials {

    private Game game;
    private PluginContainer pluginContainer;
    private Logger logger;

    /**
     * Essentials Constructor
     *
     * @param game The Game instance
     * @param pluginContainer The PluginContainer instance
     * @param logger The Logger instance
     */
    @Inject
    public Essentials(Game game, PluginContainer pluginContainer, Logger logger) {
        this.game = game;
        this.pluginContainer = pluginContainer;
        this.logger = logger;
    }

    /**
     * Get the Game instance
     *
     * @return The server's game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Get the PluginContainer instance
     *
     * @return The plugin's container
     */
    public PluginContainer getPluginContainer() {
        return this.pluginContainer;
    }

    /**
     * Get the Logger instance
     *
     * @return The plugin's assigned logger
     */
    public Logger getLogger() {
        return this.logger;
    }

}
