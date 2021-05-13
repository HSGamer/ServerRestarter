package me.hsgamer.serverrestarter.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.Permissions;
import me.hsgamer.serverrestarter.ServerRestarter;
import me.hsgamer.serverrestarter.config.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class CheckCommand extends Command {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private final ServerRestarter instance;

    public CheckCommand(ServerRestarter instance) {
        super("checkrestart", "Check the current restart", "/checkrestart", Collections.emptyList());
        setPermission(Permissions.CHECK.getName());
        this.instance = instance;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        MessageUtils.sendMessage(sender, MessageConfig.CHECK.getValue().replace("{time}", getNextFormattedTime()));
        return true;
    }

    private String getNextFormattedTime() {
        LocalDateTime time = instance.getRestartManager().getNextRestartTime();
        return time == null ? "---" : TIME_FORMATTER.format(time);
    }
}
