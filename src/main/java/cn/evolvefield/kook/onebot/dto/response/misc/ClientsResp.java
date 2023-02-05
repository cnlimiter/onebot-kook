package cn.evolvefield.kook.onebot.dto.response.misc;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author cnlimiter
 */
@Data
public class ClientsResp {

    @JSONField(name = "clients")
    private List<Clients> clients;

    @Data
    private static class Clients {

        @JSONField(name = "app_id")
        private long appId;

        @JSONField(name = "device_name")
        private String deviceName;

        @JSONField(name = "device_kind")
        private String deviceKind;

    }

}
