package io.github.realyusufismail.commands;

import io.github.realyusufismail.TestUtils;
import io.github.realyusufismail.slash.ITestSlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestPingCommand implements ITestSlashCommand {

    @Mock
    private SlashCommandInteractionEvent event;

    @Mock
    private JDA jda;

    @Mock
    private User user;

    @Mock
    private RestActionImpl<Long> action;

    @Mock
    private ReplyCallbackAction mockReply;

    private PingCommand pingCommand;

    @Override
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pingCommand = new PingCommand();
        mockReply = mock(ReplyCallbackAction.class);
        TestUtils.setCommonVariables(jda, user, event, mockReply);
    }

    @Override
    @Test
    public void shouldReturnCorrectName() {
        assertEquals("ping", pingCommand.getName(), "Name should be 'ping'");
    }

    @Override
    @Test
    public void shouldReturnCorrectDescription() {
        assertEquals("Shows the bot's ping", pingCommand.getDescription(), "Description should be 'Shows the bot's ping'");
    }

    @Override
    @Test
    public void shouldSendResponse() {
        when(jda.getRestPing()).thenReturn(action);

        when(action.complete()).thenReturn(100L);
        when(jda.getGatewayPing()).thenReturn(200L);

        when(event.reply("Rest Ping: 100ms\nGateway Ping: 200ms")).thenReturn(mockReply);

        pingCommand.onSlashCommandInteractionEvent(event);

        verify(mockReply).queue();

        verify(event).reply(anyString());
        verify(event.getJDA()).getRestPing();
        verify(event.getJDA()).getGatewayPing();
    }
}
