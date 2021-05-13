package me.hsgamer.serverrestarter.action;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.api.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickAllAction extends Action {
    public KickAllAction(String data) {
        super(data);
    }

    @Override
    public void execute() {
        String colored = MessageUtils.colorize(data);
        for (Player player : Bukkit.getOnlinePlayers())
            player.kickPlayer(colored);
    }
}
