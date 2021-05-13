package me.hsgamer.serverrestarter;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.command.CheckCommand;
import me.hsgamer.serverrestarter.command.ForceRestartCommand;
import me.hsgamer.serverrestarter.command.ReloadCommand;
import me.hsgamer.serverrestarter.command.SkipCommand;
import me.hsgamer.serverrestarter.config.MainConfig;
import me.hsgamer.serverrestarter.config.MessageConfig;
import me.hsgamer.serverrestarter.manager.RestartManager;

public final class ServerRestarter extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);
    private final RestartManager restartManager = new RestartManager(this);

    @Override
    public void load() {
        mainConfig.setup();
        messageConfig.setup();
        MessageUtils.setPrefix(MessageConfig.PREFIX::getValue);
    }

    @Override
    public void enable() {
        registerCommand(new ReloadCommand(this));
        registerCommand(new ForceRestartCommand(this));
        registerCommand(new SkipCommand(this));
        registerCommand(new CheckCommand(this));
    }

    @Override
    public void postEnable() {
        restartManager.nextSchedule();
    }

    @Override
    public void disable() {
        restartManager.stopSchedule();
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public RestartManager getRestartManager() {
        return restartManager;
    }
}
