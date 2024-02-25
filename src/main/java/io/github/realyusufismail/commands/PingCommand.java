package io.github.realyusufismail.commands;

import io.github.realyusufismail.slash.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand implements ISlashCommand {


    @Override
    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        long restPing = event.getJDA().getRestPing().complete();
        long gatewayPing = event.getJDA().getGatewayPing();

        event.reply("Rest Ping: " + restPing + "ms\nGateway Ping: " + gatewayPing + "ms").queue();
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Shows the bot's ping";
    }
}
