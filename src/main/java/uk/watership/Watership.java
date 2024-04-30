package uk.watership;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.will88.commands.CommandManager;
import net.will88.watership.listeners.Counting;
import net.will88.watership.listeners.ExpSystem;
import net.will88.watership.listeners.MemberJoinRemoveListener;
import net.will88.watership.listeners.MessageReceivedListener;

public class Watership {

    private static String BOT_TOKEN;
    private final ShardManager shardManager;

    public Watership() throws LoginException {

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createLight(BOT_TOKEN,
                GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("The Honeycomb"));
        shardManager = builder.build();

    }

    public static void main(String[] args) {
        Watership watership = null;

        FileManager botTokenFile = new FileManager();

        BOT_TOKEN = botTokenFile.getBotToken();

        try {
            watership = new Watership();
        }
        catch (LoginException e) {
            // e.printStackTrace();
            System.out.println("[Watership] ERROR: Provided bot token is invalid");
        }
        System.out.println("[Watership] The bot is now online!");

        watership.registerListeners();
        watership.runExpSystem();

//		shardManager.updateCommands().addCommands(
//		        Commands.slash("echo", "Repeats messages back to you.")
//		            .addOption(OptionType.STRING, "message", "The message to repeat.")
//		            .addOption(OptionType.INTEGER, "times", "The number of times to repeat the message.")
//		            .addOption(OptionType.BOOLEAN, "ephemeral", "Whether or not the message should be sent as an ephemeral message."),
//		        Commands.slash("animal", "Finds a random animal")
//		             .addOptions(
//		                 new OptionData(OptionType.STRING, "type", "The type of animal to find")
//		                     .addChoice("Bird", "bird")
//		                     .addChoice("Big Cat", "bigcat")
//		                     .addChoice("Canine", "canine")
//		                     .addChoice("Fish", "fish")
//		             )
//		).queue();

    }

    private void runExpSystem() {
        ExpSystem system = new ExpSystem();
        system.startTimer();
    }

    private void registerListeners() {

        shardManager.addEventListener(new MessageReceivedListener());
        shardManager.addEventListener(new MemberJoinRemoveListener(shardManager));
        shardManager.addEventListener(new ExpSystem());
        shardManager.addEventListener(new Counting());
        shardManager.addEventListener(new CommandManager());
    }

    /**
     * @return the shardManager
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

}
