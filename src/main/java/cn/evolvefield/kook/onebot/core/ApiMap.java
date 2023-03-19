package cn.evolvefield.kook.onebot.core;

import cn.enaium.kook.spring.boot.starter.api.DirectMessageAPI;
import cn.enaium.kook.spring.boot.starter.api.GuildAPI;
import cn.enaium.kook.spring.boot.starter.model.constant.MessageType;
import cn.enaium.kook.spring.boot.starter.util.HttpUtil;
import cn.evolvefield.kook.onebot.OneBotKooK;
import cn.evolvefield.kook.onebot.dto.response.ActionData;
import cn.evolvefield.kook.onebot.dto.response.common.*;
import cn.evolvefield.kook.onebot.utils.DataBaseUtils;
import cn.evolvefield.kook.onebot.utils.OnebotMsgParser;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Project: kook
 * Author: cnlimiter
 * Date: 2023/2/6 0:39
 * Description:
 */
public class ApiMap {
    @Resource
    public HttpUtil httpUtil;
    @Getter
    private final LinkedHashMap<Long, Long> cachedTempContact = new LinkedHashMap<>();

    public ApiMap(){
    }

    public ActionData<?> callKookApi(String action, JSONObject params, ApiMap api){
        ActionData<?> responseDTO = new PluginFailure();


        try {
            switch (action) {
                case "send_msg" -> {
                    responseDTO = api.sendMessage(params);
                }
                case "send_private_msg" -> {
                    responseDTO = api.sendPrivateMessage(params);
                }
                case "send_group_msg" -> {
                    responseDTO = api.sendGroupMessage(params);
                }
                case "send_discuss_msg" -> {
                    responseDTO = api.sendDiscussMessage(params);
                }
                case "delete_msg" -> {
                    responseDTO = api.deleteMessage(params);
                }
                case "send_like" -> {
                    responseDTO = api.sendLike(params);
                }
                case "set_group_kick" -> {
                    responseDTO = api.setGroupKick(params);
                }
                case "set_group_ban" -> {
                    responseDTO = api.setGroupBan(params);
                }
                case "set_group_anonymous_ban" -> {
                    responseDTO = api.setGroupAnonymousBan(params);
                }
                case "set_group_whole_ban" -> {
                    responseDTO = api.setWholeGroupBan(params);
                }
                case "set_group_admin" -> {
                    responseDTO = api.setGroupAdmin(params);
                }
                case "set_group_anonymous" -> {
                    responseDTO = api.setGroupAnonymous(params);
                }
                case "set_group_card" -> {
                    responseDTO = api.setGroupCard(params);
                }
                case "set_group_leave" -> {
                    responseDTO = api.setGroupLeave(params);
                }
                case "set_group_special_title" -> {
                    responseDTO = api.setGroupSpecialTitle(params);
                }
                case "set_discuss_leave" -> {
                    responseDTO = api.setDiscussLeave(params);
                }
                case "set_friend_add_request" -> {
                    responseDTO = api.setFriendAddRequest(params);
                }
                case "set_group_add_request" -> {
                    responseDTO = api.setGroupAddRequest(params);
                }
                case "get_login_info" -> {
                    responseDTO = api.getLoginInfo(params);
                }
                case "set_qq_profile" -> {
                    responseDTO = api.sendQQProfile(params);
                }
                case "get_stranger_info" -> {
                    responseDTO = api.getStrangerInfo(params);
                }
                case "get_friend_list" -> {
                    responseDTO = api.getFriendList(params);
                }
                case "get_group_list" -> {
                    responseDTO = api.getGroupList(params);
                }
                case "get_group_info" -> {
                    responseDTO = api.getGroupInfo(params);
                }
                case "get_group_member_info" -> {
                    responseDTO = api.getGroupMemberInfo(params);
                }
                case "get_group_member_list" -> {
                    responseDTO = api.getGroupMemberList(params);
                }
                case "get_cookies" -> {
                    responseDTO = api.getCookies(params);
                }
                case "get_csrf_token" -> {
                    responseDTO = api.getCSRFToken(params);
                }
                case "get_credentials" -> {
                    responseDTO = api.getCredentials(params);
                }
                case "get_record" -> {
                    responseDTO = api.sendMessage(params);
                }
                case "get_image" -> {
                    responseDTO = api.sendMessage(params);
                }
                case "can_send_image" -> {
                    responseDTO = api.canSendImage(params);
                }
                case "can_send_record" -> {
                    responseDTO = api.canSendRecord(params);
                }
                case "get_status" -> {
                    responseDTO = api.getStatus(params);
                }
                case "get_version_info" -> {
                    responseDTO = api.getVersionInfo(params);
                }
                case "set_restart_plugin" -> {
                    responseDTO = api.setRestartPlugin(params);
                }
                case "clean_data_dir" -> {
                    responseDTO = api.cleanDataDir(params);
                }
                case "clean_plugin_log" -> {
                    responseDTO = api.cleanPluginLog(params);
                }
                case "set_group_name" -> {
                    responseDTO = api.setGroupName(params);
                }
                case "get_group_honor_info" -> {
                    responseDTO = api.getGroupHonorInfo(params);
                }
                case "get_msg" -> {
                    responseDTO = api.sendMessage(params);
                }
                case "_set_group_notice" -> {
                    responseDTO = api.setGroupNotice(params);
                }
                case ".get_word_slices" -> {
                    responseDTO = api.getWordSlice(params);
                }
                case "set_essence_msg" -> {
                    responseDTO = api.setEssenceMsg(params);
                }
                case "get_group_root_files" -> {
                    responseDTO = api.setEssenceMsg(params);
                }
                default -> OneBotMirai.logger.error(String.format("未知OneBot API: %s", action));
            }
        } catch (IllegalArgumentException e) {
            OneBotKooK.getInstance().getLogger().info(e);
            responseDTO = new InvalidRequest();
        } catch (PermissionDeniedException e) {
            OneBotKooK.getInstance().getLogger().debug(String.format("机器人无操作权限, 调用的API: / %s", action));
            responseDTO = new MiraiFailure();
        } catch (Exception e) {
            OneBotKooK.getInstance().getLogger().error(e);
            responseDTO = new PluginFailure();
        }
        return responseDTO;
    }




    public ActionData<?> sendPrivateMessage(JSONObject params) {
        var targetQQId = params.getLong("user_id");
        var raw = params.getBooleanValue("auto_escape", false);
        var messages = params.get("message");


        var messageChain = OnebotMsgParser.messageToMiraiMessageChains(messages, raw);
        if (messageChain != null && !messageChain.content.isEmpty()) {
            var send = messageChain.content;
            var back = httpUtil.send(DirectMessageAPI.DIRECT_MESSAGE_CREATE.setBody(
                    Map.of(
                            "type", MessageType.KMARKDOWN,
                            "target_id", targetQQId.toString(),
                            "content", send
                    )
            ));;
            return new MessageResponse(DataBaseUtils.toMessageId(Long.parseLong(back), targetQQId));
        } else {
            return new MessageResponse(-1);
        }

    }


    //delete
    public ActionData<?> deleteMessage(JSONObject params) {
        var messageId = params.getInteger("message_id");
        var back = httpUtil.send(DirectMessageAPI.DIRECT_MESSAGE_DELETE.setBody(
                Map.of(
                        "msg_id", messageId
                )
        ));
        return new GeneralSuccess();
    }


    public ActionData<?> setGroupKick(JSONObject params) {
        var groupId = params.getLong("group_id");
        var memberId = params.getLong("user_id");
        var back = httpUtil.send(GuildAPI.GUILD_KICKOUT.setBody(
                Map.of(
                        "guild_id", groupId,
                        "target_id", memberId
                )
        ));
        return new GeneralSuccess();
    }

    public ActionData<?> setGroupBan(JSONObject params) {
        var groupId = params.getLong("group_id");
        var memberId = params.getLong("user_id");
        var back = httpUtil.send(GuildAPI.GUILD_KICKOUT.setBody(
                Map.of(
                        "guild_id", groupId,
                        "target_id", memberId
                )
        ));
        return new GeneralSuccess();
    }

}
