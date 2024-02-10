package io.github.realyusufismail;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.jconfig.JConfig;
import io.github.realyusufismail.slash.AutoSlashAdder;
import io.github.realyusufismail.slash.SlashCommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDA5Bot {
    public static JConfig config = JConfig.builder().build();
    public static Logger logger = LoggerFactory.getLogger(JDA5Bot.class);
    private static SlashCommandHandler slashCommandHandler;

    public static void main(String[] args) throws InterruptedException {
        JsonNode token = config.get("token");

        if (token == null) {
            throw new RuntimeException("Token not found in config file");
        }

        JDA jda = JDABuilder.createDefault(token.asText())
                .build();

        jda.awaitReady();

        logger.info("Logged in as: {}", jda.getSelfUser().getName());

        try {
            slashCommandHandler = new AutoSlashAdder(jda);
        } catch (Exception e) {
            logger.error("Failed to initialize slash command handler", e);
        }

        jda.addEventListener(new ListenerAdapter() {
            @Override
            public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
                slashCommandHandler.onSlashCommandInteractionEvent(event);
            }
        });
    }
}