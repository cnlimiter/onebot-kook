package cn.evolvefield.kook.onebot.utils;

import cn.evolvefield.kook.onebot.OneBotKooK;
import cn.evolvefield.kook.onebot.core.ApiMap;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.java_websocket.WebSocket;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/10/9 23:14
 * Version: 1.0
 */
public class ActionUtils {
    public static void handleWebSocketActions(WebSocket session, ApiMap api, JSONObject json){
        try {
            OneBotKooK.logger.info(String.format("WebSocket收到操作请求: %s", JSON.toJSONString(json)));
            var echo = json.getString("echo");
            var action = json.getString("action");

            var responseDTO = api.callKookApi(action, json.getJSONObject("params"), api);

            responseDTO.setEcho(echo);
            var jsonToSend = JSON.toJSONString(responseDTO);
            OneBotKooK.logger.debug(String.format("WebSocket将返回结果: %s" ,jsonToSend));
            session.send(jsonToSend);
        } catch (Exception e) {
            OneBotKooK.logger.error(e.getMessage());
        }
    }
}
