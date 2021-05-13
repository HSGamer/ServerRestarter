package me.hsgamer.serverrestarter.action;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.serverrestarter.api.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TitleAction extends Action {
    private String title;
    private String subtitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleAction(String data) {
        super(data);
        try {
            String[] dataArr = data.split(" :: ");
            this.title = MessageUtils.colorize(dataArr[0]);
            this.subtitle = MessageUtils.colorize(dataArr[1]);

            String[] ticksArr = dataArr[2].split(" ");
            this.fadeIn = Integer.parseInt(ticksArr[0]);
            this.stay = Integer.parseInt(ticksArr[1]);
            this.fadeOut = Integer.parseInt(ticksArr[2]);
        } catch (Exception ex) {
            valid = false;
        }
    }

    @Override
    public void execute() {
        if (!valid) return;
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

}
