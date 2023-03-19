package cn.evolvefield.kook.onebot.utils;

import cn.enaium.kook.spring.boot.starter.model.constant.ChannelType;
import cn.enaium.kook.spring.boot.starter.model.sign.data.EventData;
import cn.enaium.kook.spring.boot.starter.model.sign.data.extra.TextMessageExtra;
import cn.enaium.kook.spring.boot.starter.model.sign.data.extra.event.message.KMarkdownMessage;
import cn.enaium.kook.spring.boot.starter.model.sign.data.extra.event.message.TextMessage;
import cn.evolvefield.kook.onebot.OneBotKooK;
import cn.evolvefield.kook.onebot.dto.response.ActionData;
import cn.evolvefield.mirai.onebot.OneBotMirai;
import com.alibaba.fastjson2.JSONObject;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.*;
import snw.jkook.message.component.BaseComponent;
import snw.jkook.message.component.TextComponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/4 2:43
 * Version: 1.0
 */
public class OnebotMsgParser {


    private static final TextComponent MSG_EMPTY = new TextComponent("");

    public static EventData<?> messageToMiraiMessageChains(Object message, boolean raw){
        if (message instanceof String s){
            var msg = new EventData<TextMessage>();
            msg.type = 1;
            msg.content = s;
            return msg;
        }
        else if (message instanceof JSONObject jsonObject) {
            try {
                var data = jsonObject.getJSONObject("data");
                if (jsonObject.getJSONObject("type") != null){
                    if (data.containsKey("text"))
                    {
                        var msg = new EventData<TextMessage>();
                        msg.type = 1;
                        msg.content = data.getJSONObject("text").toString();
                        return msg;
                    }
                    else return new MessageChainBuilder().append(textToMessageInternal(bot, contact, message)).build();
                }
            } catch (NullPointerException e) {
                OneBotKooK.logger.warn("Got null when parsing CQ message object");
                return null;
            }
        }
        return null;
    }

     public static String toCQString(EventData<?> message){
        var type = message.type;
        if (type == 1) return escape(message.content);

//        else if (type instanceof At at) return "[CQ:at,qq=" +at.getTarget() + "]";
//
//        else if (type instanceof Face face) return "[CQ:face,id=" + face.getId() + "]";
//
//        else if (type instanceof VipFace vipFace) return "[CQ:vipface,id="+vipFace.getKind().getId()+",name=" +vipFace.getKind().getName()+ ",count=" +vipFace.getCount()+ "]";
//
//        else if (type instanceof PokeMessage pokeMessage) return "[CQ:poke,id=" +pokeMessage.getId()+ ",type= "+pokeMessage.getPokeType()+" ,name="+pokeMessage.getName()+"]";
//
//        else if (type instanceof AtAll all) return "[CQ:at,qq=all]";

        else if (type == 2) return "[CQ:image,file=,url="+escape(message.content)+"]";

       // else if (type instanceof FlashImage flashImage) return "[CQ:image,file="+flashImage.getImage().getImageId()+",url="+escape(Image.queryUrl(flashImage.getImage()))+",type=flash]";

//        else if (type instanceof ServiceMessage serviceMessage){
//            if (serviceMessage.getContent().contains("xml version")) return "[CQ:xml,data="+escape(serviceMessage.getContent())+"]";
//            else return "[CQ:json,data="+escape(serviceMessage.getContent())+"]";
//        }
//        else if (type instanceof LightApp app) return "[CQ:json,data="+escape(app.getContent())+"]";
//
//        else if (type instanceof MessageSource) return "";
//
//        else if (type instanceof QuoteReply quoteReply) return "[CQ:reply,id="+DataBaseUtils.toMessageId(quoteReply.getSource().getInternalIds(), quoteReply.getSource().getBotId(), quoteReply.getSource().getFromId())+"]";
//
//        else if (type instanceof OnlineAudio audio) return "[CQ:record,url="+escape(audio.getUrlForDownload())+",file="+ Arrays.toString(audio.getFileMd5()) +"]";
//
//        else if (type instanceof Audio audio) return "[CQ:record,url=,file="+ Arrays.toString(audio.getFileMd5()) +"]";

        else return "此处消息的转义尚未被插件支持";

    }


    private static String escape(String msg){
        return msg.replace("&", "&amp;")
                .replace("[", "&#91;")
                .replace("]", "&#93;")
                .replace(",", "&#44;");
    }

    private static String unescape(String msg){
        return msg.replace("&amp;", "&")
                .replace("&#91;", "[")
                .replace("&#93;", "]")
                .replace("&#44;", ",");
    }

    private static HashMap<String, String> toMap(String msg){
        var map = new HashMap<String, String>();
        Arrays.stream(msg.split(",")).forEach(
                s -> {
                    var parts = s.split("=",  2);
                    map.put(parts[0].trim(), unescape(parts[1]));
                }

        );
        return map;
    }

    private static EventData<?> textToMessageInternal(Object message){
        if (message instanceof String msg){
            if (msg.startsWith("[CQ:") && msg.endsWith("]")) {
                var parts = msg.substring(4, msg.length() - 1).split(",",  2);

                HashMap<String, String> args;
                if (parts.length == 2) {
                    args = toMap(parts[1]);
                } else {
                   args = new HashMap<>();
                }
                return convertToMiraiMessage(parts[0], args);
            }
            return new PlainText(unescape(msg));
        }
        else if (message instanceof JSONObject jsonObject){
            var type = jsonObject.getJSONObject("type").toString();
            JSONObject data = jsonObject.getJSONObject("data");
            Map<String, String> args = new HashMap<>();
            data.forEach((s, o) -> args.put(s, (String) o));
            return convertToMiraiMessage(type, args);
        }
        else return MSG_EMPTY;
    }


    private static EventData<?> convertToMiraiMessage(String type, Map<String, String> args){
        switch (type) {
            case "at" -> {
                if ("all".equals(args.get("qq"))) {
                    var msg = new EventData<KMarkdownMessage>();
                    msg.channel_type = ChannelType.BROADCAST;
                    return msg;
                } else {
                    if (contact instanceof Group) {
                        OneBotMirai.logger.debug("不能在私聊中发送 At。");
                        return MSG_EMPTY;
                    } else {
                        var member = contact.getBot().getFriend(Long.parseLong(args.get("qq")));
                        if (member == null) {
                            OneBotMirai.logger.debug(String.format("无法找到群员：%s", args.get("qq")));
                            return MSG_EMPTY;
                        } else {
                           return new At(member.getId());
                        }
                    }
                }
            }
            case "face" -> {
                return new Face(Integer.parseInt(args.get("id")));
            }
            case "emoji" -> {
                return new PlainText(new String(Character.toChars(Integer.parseInt(args.get("id")))));
            }
            case "image" -> {
               // return tryResolveMedia("image", contact, args);
            }
            case "share" -> {
                return RichMessage.share(
                        args.get("url"),
                        args.get("title"),
                        args.get("content"),
                        args.get("image")
                );
            }
            case "record" -> {}
            case "contact" -> {
                if ("qq".equals(args.get("type"))) {
                    //return  RichMessageHelper.contactQQ(bot, args["id"]!!.toLong())
                } else {
                    //return RichMessageHelper.contactGroup(bot, args["id"]!!.toLong())
                }

            }
            case "music" -> {
//                switch (args.get("type")){
//                    case "qq" -> { }
//                }
//                return when (args["type"]) {
//                    "qq" -> QQMusic.send(args["id"]!!)
//                    "163" -> NeteaseMusic.send(args["id"]!!)
//                    "custom" -> Music.custom(
//                            args["url"]!!,
//                            args["audio"]!!,
//                            args["title"]!!,
//                            args["content"],
//                            args["image"]
//                )
//                else -> throw IllegalArgumentException("Custom music share not supported anymore")
//                }
            }
            case "shake" -> {return PokeMessage.ChuoYiChuo;}
            case "poke" -> {
                return Arrays.stream(PokeMessage.values).filter(
                        pokeMessage -> pokeMessage.getPokeType() == Integer.parseInt(args.get("type")) && pokeMessage.getId() == Integer.parseInt(args.get("id"))
                ).findFirst().orElseThrow();
            }
            case "nudge" -> {
                var target = Optional.of(args.get("qq")).orElseThrow();
                if (contact instanceof Group c) {
                    Optional.ofNullable(c.get(Long.parseLong(target))).orElseThrow().nudge().sendTo(c);
                } else {
                    Optional.ofNullable(contact).ifPresent( contact1 -> Optional.ofNullable(bot.getFriend(Long.parseLong(target))).orElseThrow().nudge().sendTo(contact) );
                }
                return MSG_EMPTY;
            }
            case "xml" -> {}
            case "json" -> {}
            case "reply" -> {

            }
            default -> {
                OneBotMirai.logger.debug("不支持的 CQ码：${type}");
            }
        }
        return MSG_EMPTY;

    }




//    File getDataFile(String type,String name){
//        arrayOf(
//                File(PluginBase.dataFolder, type).absolutePath + File.separatorChar,
//                "data" + File.separatorChar + type + File.separatorChar,
//                System.getProperty("java.library.path")
//                        .substringBefore(";") + File.separatorChar + "data" + File.separatorChar + type + File.separatorChar,
//                ""
//        ).forEach {
//            var f = File(it + name).absoluteFile;
//            if (f.exists()) {
//                return f;
//            }
//        }
//        return null;
//    }
}
