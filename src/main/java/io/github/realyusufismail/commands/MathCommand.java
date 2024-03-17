package io.github.realyusufismail.commands;

import io.github.realyusufismail.slash.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

@SuppressWarnings("unused")
public class MathCommand implements ISlashCommand {
    @Override
    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        String operation = event.getOption("operation", OptionMapping::getAsString);
        Double first = event.getOption("first", OptionMapping::getAsDouble);
        Double second = event.getOption("second", OptionMapping::getAsDouble);

        if (operation == null || first == null || second == null) {
            event.reply("Invalid input").setEphemeral(true).queue();
            return;
        }

        double result;
        switch (operation) {
            case "add":
                result = first + second;
                break;
            case "subtract":
                result = first - second;
                break;
            case "multiply":
                result = first * second;
                break;
            case "divide":
                if (second == 0) {
                    event.reply("Cannot divide by zero").setEphemeral(true).queue();
                    return;
                }
                result = first / second;
                break;
            default:
                event.reply("Invalid operation").setEphemeral(true).queue();
                return;
        }

        event.reply("Result: " + result)
                .setEphemeral(true)
                .queue();
    }

    @Override
    public String getName() {
        return "math";
    }

    @Override
    public String getDescription() {
        return "Performs a math operation on two numbers";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.STRING, "operation", "The operation to perform", true)
                        .addChoice("Addition", "add")
                        .addChoice("Subtraction", "subtract")
                        .addChoice("Multiplication", "multiply")
                        .addChoice("Division", "divide"),
                new OptionData(OptionType.NUMBER, "first", "The first number", true),
                new OptionData(OptionType.NUMBER, "second", "The second number", true)
        );
    }
}
