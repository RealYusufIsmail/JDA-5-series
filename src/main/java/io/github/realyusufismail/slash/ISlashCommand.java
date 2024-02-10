package io.github.realyusufismail.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;
import java.util.List;

public interface ISlashCommand {

    void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event);

    String getName();

    String getDescription();

    default List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    /**
     * Whether the command can appear in dm's or not
     *
     * @return true if the command can appear in dm's
     */
    default boolean isGlobal() {
        return true;
    }
}
