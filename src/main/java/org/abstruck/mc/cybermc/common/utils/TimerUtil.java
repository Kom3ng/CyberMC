package org.abstruck.mc.cybermc.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.jline.utils.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerUtil {
    public static float PERCENT = 20F;
    public static double millisF = 0F;
    public static long millis = 0;
    public static Timer timer;
    public static ScheduledExecutorService service;

    public static void create() {
        if (service == null)
            service = Executors.newSingleThreadScheduledExecutor();

        if (timer == null)
            timer = new Timer();
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    service.scheduleAtFixedRate(TimerUtil::update, 1L, 1L, TimeUnit.MILLISECONDS);
                }
            }, 1L);
        } catch (Exception e) {
            LogManager.getLogger().log(Level.FATAL,"调整时间是出现错误！",e);
        }
    }

    static void update() {
        float p = PERCENT / 20.0F;
        millisF = p + millisF;
        millis = (long) millisF;
    }

    public static void changeAll(float percent) {
        PERCENT = percent;
    }

    public static void jump(int ticks) {
        millisF += ticks * 50L;
    }
}
