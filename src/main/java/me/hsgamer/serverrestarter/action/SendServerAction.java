package me.hsgamer.serverrestarter.action;

import me.hsgamer.serverrestarter.api.Action;
import me.hsgamer.serverrestarter.utils.BungeeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SendServerAction extends Action {
    public SendServerAction(String data) {
        super(data);
    }

    @Override
    public void execute() {
        for (Player player : Bukkit.getOnlinePlayers())
            BungeeUtils.connect(player, data);
    }
}
