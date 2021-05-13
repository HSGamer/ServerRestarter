package me.hsgamer.serverrestarter.manager;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import me.hsgamer.serverrestarter.ServerRestarter;
import me.hsgamer.serverrestarter.api.Action;
import me.hsgamer.serverrestarter.value.ActionMap;
import me.hsgamer.serverrestarter.value.ScheduleList;
import org.bukkit.Bukkit;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RestartManager {
    private final ServerRestarter instance;
    private final ActionMap actionMap = new ActionMap();
    private final ScheduleList scheduleList = new ScheduleList();
    private LocalDateTime nextRestartTime;
    private ScheduledExecutorService executor;

    public RestartManager(ServerRestarter instance) {
        this.instance = instance;
    }

    public ActionMap getActionMap() {
        return actionMap;
    }

    public ScheduleList getScheduleList() {
        return scheduleList;
    }

    public LocalDateTime getNextRestartTime() {
        return nextRestartTime;
    }

    public void schedule(long millisToRestart) {
        nextRestartTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis() + millisToRestart), ZoneId.systemDefault());

        executor = Executors.newSingleThreadScheduledExecutor();
        actionMap.getValue().forEach((seconds, actions) -> {
            long millisToTask = millisToRestart - seconds * 1000;
            if (millisToTask < 0) return;
            executor.schedule(() -> runSync(() -> actions.forEach(Action::execute)), millisToTask, TimeUnit.MILLISECONDS);
        });

        executor.schedule(() -> runSync(this::nextSchedule), millisToRestart, TimeUnit.MILLISECONDS);
    }

    public void scheduleSkipped() {
        stopSchedule();
        if (nextRestartTime != null) {
            ZonedDateTime timeToSkip = nextRestartTime.atZone(ZoneId.systemDefault());
            nextRestartTime = null;
            getNextDelayMillis(timeToSkip).ifPresent(this::schedule);
        }
    }

    public void nextSchedule() {
        stopSchedule();
        nextRestartTime = null;
        getNextDelayMillis().ifPresent(this::schedule);
    }

    public void stopSchedule() {
        if (executor != null)
            executor.shutdownNow();
    }

    private Optional<Long> getNextDelayMillis() {
        return getNextDelayMillis(ZonedDateTime.now());
    }

    private Optional<Long> getNextDelayMillis(ZonedDateTime timeAfter) {
        long currentMillis = System.currentTimeMillis();

        long minDelayMillis = -1;
        for (Cron cron : scheduleList.getValue()) {
            Optional<ZonedDateTime> time = ExecutionTime.forCron(cron).nextExecution(timeAfter);
            if (time.isPresent()) {
                long delayMillis = time.get().toInstant().toEpochMilli() - currentMillis;
                if (delayMillis < minDelayMillis || minDelayMillis == -1) minDelayMillis = delayMillis;
            }
        }

        return minDelayMillis == -1 ? Optional.empty() : Optional.of(minDelayMillis + 1);
    }

    private void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(instance, runnable);
    }
}
