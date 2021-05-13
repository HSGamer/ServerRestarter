package me.hsgamer.serverrestarter.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.AdvancedConfigPath;
import me.hsgamer.hscore.config.BaseConfigPath;
import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.PathableConfig;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainConfig extends PathableConfig {
    public static final BaseConfigPath<List<String>> SCHEDULE_LIST = new BaseConfigPath<>("schedule-list", Collections.singletonList("0 20 4 * * ?"), o -> CollectionUtils.createStringListFromObject(o, true));
    public static final AdvancedConfigPath<Map<String, Object>, Map<Integer, List<String>>> ACTION_MAP = new AdvancedConfigPath<Map<String, Object>, Map<Integer, List<String>>>("action-map", new HashMap<>()) {
        @Override
        public Map<String, Object> getFromConfig(Config config) {
            return config.getNormalizedValues(getPath(), false);
        }

        @Override
        public Map<Integer, List<String>> convert(Map<String, Object> rawValue) {
            Map<Integer, List<String>> map = new HashMap<>();
            rawValue.forEach((s, o) -> {
                int i;
                try {
                    i = Integer.parseInt(s);
                } catch (Exception e) {
                    return;
                }
                map.put(i, CollectionUtils.createStringListFromObject(o, true));
            });
            return map;
        }

        @Override
        public Map<String, Object> convertToRaw(Map<Integer, List<String>> value) {
            Map<String, Object> map = new HashMap<>();
            value.forEach((i, list) -> map.put(Integer.toString(i), list));
            return map;
        }
    };

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
