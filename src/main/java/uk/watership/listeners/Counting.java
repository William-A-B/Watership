package uk.watership.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Counting extends ListenerAdapter {

    private int currentNumber;

    public Counting() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        final TextChannel channel = (TextChannel) event.getChannel();

        if (channel.getId().equalsIgnoreCase("1155935657793966090")) {
            final Message message = event.getMessage();

            channel.getHistory().retrievePast(2).map(messages -> messages.get(1)).queue(msg -> {

                try {
                    final int a = Integer.parseInt(message.getContentRaw());
                    final int b = Integer.parseInt(msg.getContentRaw());

                    if (message.getAuthor().equals(msg.getAuthor())) {
                        message.delete().queue();
                    }

                    if (a != b + 1) {
                        message.delete().queue();
                    }
                }
                catch (NumberFormatException e) {
                    message.delete().queue();
                    e.fillInStackTrace();
                }
            });
        }
    }
}
