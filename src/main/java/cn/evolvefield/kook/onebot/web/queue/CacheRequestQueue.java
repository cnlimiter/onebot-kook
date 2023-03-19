package cn.evolvefield.kook.onebot.web.queue;

import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import snw.jkook.event.Event;
import snw.jkook.event.user.UserJoinGuildEvent;
import snw.jkook.event.user.UserJoinVoiceChannelEvent;

import java.util.LinkedHashMap;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/2 22:21
 * Version: 1.0
 */
public class CacheRequestQueue extends LinkedHashMap<String, Event>{
    int cacheSize = 512;

    @Override
    public Event get(Object key) {
         return super.get(key);
    }

    @Override
    public Event put(String key, Event value) {
        if (size() > cacheSize) remove(this.entrySet().stream().findFirst().get().getKey());
        return super.put(key, value);
    }

//    public void add(NewFriendRequestEvent event){
//        put(event.getEventId(), event);
//    }
    public void add(UserJoinGuildEvent event){
        put(event.getUser().getId(), event);
    }
    public void add(UserJoinVoiceChannelEvent event){
        put(event.getUser().getId(), event);
    }
}
