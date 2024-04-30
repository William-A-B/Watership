package uk.watership.listeners;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ExpSystem extends ListenerAdapter {

    HashMap<Member, Integer> memberExp = new HashMap<>();
    HashMap<Member, Integer> memberTimer = new HashMap<>();

    public ExpSystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        String command = args[0];

        if (command.equalsIgnoreCase("!exp")) {
            event.getChannel().sendMessage("You have " + getMemberExp(event.getMember()) + " exp").queue();
        }

        if (canGetExp(event.getMember())) {
            randExp(event.getMember());
            setMemberTime(event.getMember(), 30);
        }

    }

    private void randExp(Member member) {
        Random r = new Random();

        if (!memberExp.containsKey(member)) {
            setMemberExp(member, 0);
        }
        setMemberExp(member, getMemberExp(member) + r.nextInt(25));
    }

    private boolean canGetExp(Member member) {

        if (memberTimer.containsKey(member)) {
            return true;
        }
        return false;
    }

    public void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                for (Member member : memberTimer.keySet()) {
                    setMemberTime(member, (getMemberTime(member) - 1));

                    if (getMemberTime(member) == 0) {
                        memberTimer.remove(member);
                    }
                }
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    /**
     * @return the memberExp
     */
    public int getMemberExp(Member member) {
        return memberExp.get(member);
    }

    /**
     * @param memberExp the memberExp to set
     */
    public void setMemberExp(Member member, int exp) {
        memberExp.put(member, exp);
    }

    /**
     * @return the memberTimer
     */
    public int getMemberTime(Member member) {
        return memberTimer.get(member);
    }

    /**
     * @param memberTimer the memberTimer to set
     */
    public void setMemberTime(Member member, int time) {
        memberTimer.put(member, time);
    }
}
