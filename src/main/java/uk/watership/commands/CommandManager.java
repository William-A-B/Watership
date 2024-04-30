package uk.watership.commands;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import uk.watership.listeners.Counting;

public class CommandManager extends ListenerAdapter {

    private List<CommandData> commandData = new ArrayList<>();

    public CommandManager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("test")) {
            String userMention = event.getUser().getAsMention();
            event.reply("This is a test, **" + userMention + "**!").queue();
        }
        else if (command.equals("setup counting")) {
            OptionMapping messageOption = event.getOption("channelID");
            String channelID = messageOption.getAsString();
            event.reply("Setting up Counting Channel").setEphemeral(true).queue();

            setupCounting(channelID);
        }

    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {

        commandData.add(Commands.slash("test", "A test slash command"));

        setupCountingCommand();

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private void setupCountingCommand() {

        OptionData option1 = new OptionData(OptionType.STRING, "channelid", "The ID of the channel to count in", true);

        commandData.add(Commands.slash("setup counting", "Setup the counting channel").addOptions(option1));
    }

    private void setupCounting(String channelID) {

        Counting counter = new Counting();

    }
}
