package me.hsgamer.serverrestarter.action;

import me.hsgamer.serverrestarter.api.Action;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAction extends Action {
    private Sound sound;

    public SoundAction(String data) {
        super(data);
        try {
            this.sound = Sound.valueOf(data.toUpperCase());
        } catch (Exception ex) {
            valid = false;
        }
    }

    @Override
    public void execute() {
        if (!valid) return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }
}
