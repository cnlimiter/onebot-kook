package cn.evolvefield.kook.onebot;

import snw.jkook.plugin.BasePlugin;

/**
 * Project: kook
 * Author: cnlimiter
 * Date: 2023/2/6 0:33
 * Description:
 */
public class OneBotKooK extends BasePlugin {
    private static OneBotKooK instance;
    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();

        saveConfig();
    }

    public static OneBotKooK getInstance() {
        return instance;
    }
}

