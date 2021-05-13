package me.hsgamer.serverrestarter.value;

import me.hsgamer.hscore.common.CachedValue;
import me.hsgamer.serverrestarter.api.Action;
import me.hsgamer.serverrestarter.builder.ActionBuilder;
import me.hsgamer.serverrestarter.config.MainConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionMap extends CachedValue<Map<Integer, List<Action>>> {
    @Override
    public Map<Integer, List<Action>> generate() {
        Map<Integer, List<Action>> map = new HashMap<>();
        MainConfig.ACTION_MAP.getValue().forEach(((integer, strings) -> map.put(integer, ActionBuilder.INSTANCE.build(strings))));
        return map;
    }
}
