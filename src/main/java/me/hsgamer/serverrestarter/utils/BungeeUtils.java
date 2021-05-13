package me.hsgamer.serverrestarter.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.common.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtils {

    private static final String BUNGEE = "BungeeCord";
    private static JavaPlugin plugin;

    private BungeeUtils() {
        // EMPTY
    }

    public static void setPlugin(JavaPlugin plugin) {
        BungeeUtils.plugin = plugin;
    }

    public static void register() {
        if (!Bukkit.getMessenger().isOutgoingChannelRegistered(plugin, BUNGEE)) {
            Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, BUNGEE);
        }
    }

    public static void unregister() {
        if (Bukkit.getMessenger().isOutgoingChannelRegistered(plugin, BUNGEE)) {
            Bukkit.getMessenger().unregisterOutgoingPluginChannel(plugin, BUNGEE);
        }
    }

    public static void connect(Player player, String server) {
        if (Validate.isNullOrEmpty(server)) {
            return;
        }

        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();

        dataOutput.writeUTF("ConnectOther");
        dataOutput.writeUTF(player.getName());
        dataOutput.writeUTF(server);

        sendToBungee(player, dataOutput.toByteArray());
    }

    public static void alert(Player player, String message) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();

        dataOutput.writeUTF("Message");
        dataOutput.writeUTF("ALL");
        dataOutput.writeUTF(MessageUtils.colorize(message));

        sendToBungee(player, dataOutput.toByteArray());
    }

    private static void sendToBungee(Player player, byte[] byteArray) {
        player.sendPluginMessage(plugin, BUNGEE, byteArray);
    }
}
