package me.hsgamer.serverrestarter.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.Permissions;
import me.hsgamer.serverrestarter.ServerRestarter;
import me.hsgamer.serverrestarter.config.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;

public class ForceRestartCommand extends Command {
    private final ServerRestarter instance;

    public ForceRestartCommand(ServerRestarter instance) {
        super("forcerestart", "Force restart the server", "/forcerestart [seconds]", Collections.emptyList());
        setPermission(Permissions.FORCE_RESTART.getName());
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        int seconds = 0;
        if (args.length > 1) {
            try {
                seconds = Integer.parseInt(args[1]);
                if (seconds < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                MessageUtils.sendMessage(sender, getUsage());
                return false;
            }
        }
        instance.getRestartManager().stopSchedule();
        instance.getRestartManager().schedule(seconds * 1000L);
        MessageUtils.sendMessage(sender, MessageConfig.SUCCESS.getValue());
        return true;
    }
}
