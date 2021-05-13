package me.hsgamer.serverrestarter.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.Permissions;
import me.hsgamer.serverrestarter.ServerRestarter;
import me.hsgamer.serverrestarter.config.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class SkipCommand extends Command {
    private final ServerRestarter instance;

    public SkipCommand(ServerRestarter instance) {
        super("skiprestart", "Skip the current restart", "/skiprestart", Collections.emptyList());
        setPermission(Permissions.SKIP.getName());
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        instance.getRestartManager().scheduleSkipped();
        MessageUtils.sendMessage(sender, MessageConfig.SUCCESS.getValue());
        return true;
    }
}
