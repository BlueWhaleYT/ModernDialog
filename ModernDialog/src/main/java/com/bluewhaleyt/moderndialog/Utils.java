package com.bluewhaleyt.moderndialog;

import android.os.Handler;

import java.util.concurrent.Callable;

public class Utils {

    // these modules will soon be used ...

    /**
     * Description:		<b>waitForTimeThenDo()</b> is to execute programs after the time delay.
     * @param time		The time does program need to wait
     * @param callable 	The program to be executed after the time
     *
     * Example:			<pre></pre><code></code>waitForTimeThenDo(time, () -> {
     *                  	// code here ..
     *                  	return null;
     * 					})</code></pre>
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

}
