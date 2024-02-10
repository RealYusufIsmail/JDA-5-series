package io.github.realyusufismail.slash;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.realyusufismail.JDA5Bot;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AutoSlashAdder extends SlashCommandHandler {

    public AutoSlashAdder(JDA jda) {
        super(jda);

        registerSlashCommands(loadSlashCommands()
                .stream()
                .map(clazz -> {
                    try {
                        return clazz.getConstructor().newInstance();
                    } catch (Exception e) {
                        JDA5Bot.logger.error("Failed to instantiate class: " + clazz.getName(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private List<Class<? extends ISlashCommand>> loadSlashCommands() {
        try (ScanResult result = new ClassGraph().enableClassInfo().scan()) {
          return new ArrayList<>(result.getAllClasses()
                  .filter(classInfo -> classInfo.implementsInterface(ISlashCommand.class))
                  .loadClasses(ISlashCommand.class));
        }
    }
}
