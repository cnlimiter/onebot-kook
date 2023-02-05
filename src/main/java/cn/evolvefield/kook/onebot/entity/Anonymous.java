package cn.evolvefield.kook.onebot.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Anonymous {

    @JSONField(name =  "id")
    private long id;

    @JSONField(name =  "name")
    private String name;

    @JSONField(name =  "flag")
    private String flag;

}
