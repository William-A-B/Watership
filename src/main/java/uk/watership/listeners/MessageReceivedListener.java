package uk.watership.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

    public MessageReceivedListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        // Don't respond to other bot accounts, including ourself
        if (event.getAuthor().isBot())
            return;

        Message message = event.getMessage();
        String content = message.getContentRaw();

        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g.
        // console view (strip discord formatting)
        if (content.equals("!ping")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by
                                                  // sendMessage(...)
        }
        else if (content.equals("!Thomas")) {
            event.getChannel().sendMessage("Hi Thomas! :)").queue();
        }

    }

}
