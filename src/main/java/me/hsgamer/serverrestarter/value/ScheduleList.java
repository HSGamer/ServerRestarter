package me.hsgamer.serverrestarter.value;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import me.hsgamer.hscore.common.CachedValue;
import me.hsgamer.serverrestarter.config.MainConfig;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class ScheduleList extends CachedValue<List<Cron>> {
    @Override
    public List<Cron> generate() {
        List<Cron> cronList = new ArrayList<>();
        CronDefinition definition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        for (String cronTime : MainConfig.SCHEDULE_LIST.getValue()) {
            try {
                Cron cron = new CronParser(definition).parse(cronTime);
                cronList.add(cron);
            } catch (Exception ex) {
                Bukkit.getLogger().warning(String.format("[ServerRestarter] Cron time is invalid: `%s`.", cronTime));
            }
        }
        return cronList;
    }
}
