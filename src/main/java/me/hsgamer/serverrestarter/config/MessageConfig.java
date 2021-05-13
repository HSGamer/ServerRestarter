package me.hsgamer.serverrestarter.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.StringConfigPath;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public static final StringConfigPath PREFIX = new StringConfigPath("prefix", "&f[&eServerRestarter&f] &7");
    public static final StringConfigPath SUCCESS = new StringConfigPath("success", "&aSuccess");
    public static final StringConfigPath CHECK = new StringConfigPath("check", "&eNext Restart: &f{time}");

    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
