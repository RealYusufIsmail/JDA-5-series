package io.github.realyusufismail;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.utils.ImageProxy;

import static org.mockito.Mockito.when;

public class TestUtils {


    public static void setCommonVariables(JDA jda, User user, SlashCommandInteractionEvent event, ReplyCallbackAction mockReply) {
        when(user.getAvatar()).thenReturn(new ImageProxy(""));
        when(user.getName()).thenReturn("TestUser");
        when(event.getUser()).thenReturn(user);

        long mockDiscordId = 123456789L;

        when(event.getUser().getIdLong()).thenReturn(mockDiscordId);
        when(event.getJDA()).thenReturn(jda);

        when(event.deferReply()).thenReturn(mockReply);
    }
}
