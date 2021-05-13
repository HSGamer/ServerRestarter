package me.hsgamer.serverrestarter.action;

import me.hsgamer.serverrestarter.api.Action;
import org.bukkit.Bukkit;

public class ConsoleAction extends Action {
    public ConsoleAction(String data) {
        super(data);
    }

    @Override
    public void execute() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), data);
    }
}
