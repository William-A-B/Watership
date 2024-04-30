package uk.watership.listeners;

import java.lang.reflect.Member;

import javax.management.relation.Role;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.sharding.ShardManager;

public class MemberJoinRemoveListener extends ListenerAdapter {

    private ShardManager shardManager;

    public MemberJoinRemoveListener(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild g = event.getGuild();
        Member m = event.getMember();
        VoiceChannel vc = shardManager.getVoiceChannelById("1147949577236922378");

        Role role = g.getRolesByName("Member", false).get(0);

        vc.getManager().setName("Members: " + g.getMemberCount()).queue();

        g.addRoleToMember(m, role).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Guild g = event.getGuild();
        VoiceChannel vc = shardManager.getVoiceChannelById("1147949577236922378");

        vc.getManager().setName("Members: " + g.getMemberCount()).queue();
    }

}
