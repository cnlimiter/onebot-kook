package cn.evolvefield.kook.onebot.dto.event.notice.misc;

import cn.evolvefield.mirai.onebot.dto.event.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created on 2022/7/8.
 *
 * @author cnlimiter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ReceiveOfflineFilesNoticeEvent extends NoticeEvent {

    @JSONField(name = "user_id")
    private long userId;

    @JSONField(name = "file")
    private File file;

    /**
     * 文件对象
     */
    @Data
    public static class File {

        @JSONField(name = "name")
        private String name;

        @JSONField(name = "size")
        private long size;

        @JSONField(name = "url")
        private String url;

    }

}
