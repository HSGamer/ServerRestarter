package me.hsgamer.serverrestarter.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.Permissions;
import me.hsgamer.serverrestarter.ServerRestarter;
import me.hsgamer.serverrestarter.config.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class ReloadCommand extends Command {
    private final ServerRestarter instance;

    public ReloadCommand(ServerRestarter instance) {
        super("reloadrestarter", "Reload the plugin", "/reloadrestarter", Collections.emptyList());
        setPermission(Permissions.RELOAD.getName());
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        instance.getMainConfig().reload();
        instance.getMessageConfig().reload();
        instance.getRestartManager().getActionMap().clearCache();
        instance.getRestartManager().getScheduleList().clearCache();
        instance.getRestartManager().nextSchedule();
        MessageUtils.sendMessage(sender, MessageConfig.SUCCESS.getValue());
        return true;
    }
}
