package me.hsgamer.serverrestarter.builder;

import me.hsgamer.hscore.builder.Builder;
import me.hsgamer.serverrestarter.action.ChatAction;
import me.hsgamer.serverrestarter.action.ConsoleAction;
import me.hsgamer.serverrestarter.action.SoundAction;
import me.hsgamer.serverrestarter.action.TitleAction;
import me.hsgamer.serverrestarter.api.Action;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActionBuilder extends Builder<String, Action> {
    public static final ActionBuilder INSTANCE = new ActionBuilder();

    private ActionBuilder() {
        register(ConsoleAction::new, "console", "command");
        register(ChatAction::new, "chat");
        register(SoundAction::new, "sound");
        register(TitleAction::new, "title");
    }

    public Optional<Action> build(String string) {
        String[] split = string.split(":", 2);
        String name = split[0];
        String value = split.length > 1 ? split[1] : "";
        return build(name.trim(), value.trim());
    }

    public List<Action> build(List<String> strings) {
        return strings.stream()
                .map(string -> build(string).orElseGet(() -> new ConsoleAction(string.trim())))
                .collect(Collectors.toList());
    }
}
