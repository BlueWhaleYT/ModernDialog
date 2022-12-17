package com.bluewhaleyt.moderndialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

import java.util.concurrent.Callable;

public class Utils {

    /**
     * Description:		<b>waitForTimeThenDo()</b> is to execute programs after the time delay.
     * @param time		The time does program need to wait
     * @param callable 	The program to be executed after the time
     */
    public static void waitForTimeThenDo(int time, Callable<Void> callable) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, time);
    }

    /**
     * Description: 	<b>repeatDoing()</b> is to execute programs in a loop.
     * @param time		The time program needs to repeat its execution
     * @param callable	The program to be executed during the loop stage
     */
    public static void repeatDoing(int time, Callable<Void> callable) {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    callable.call();
                    handler.postDelayed(this, time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, time);
    }

    public static boolean isInDarkMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                return true;

            case Configuration.UI_MODE_NIGHT_NO:
                return false;
        }
        return true;
    }

}
