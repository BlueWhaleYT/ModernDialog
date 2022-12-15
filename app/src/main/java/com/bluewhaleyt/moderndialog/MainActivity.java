package com.bluewhaleyt.moderndialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ModernDialog library created by me
        ModernDialog.init(this).create().setAnimation("https://assets10.lottiefiles.com/packages/lf20_uu0x8lqv.json")
                .setTitle("Your account is now verified!")
                .setMessage("Go to gain daily rewards.")
                .setPositiveButtonText("Explore")
                .setNegativeButtonText("Close")
                .setPositiveButtonBackgroundColor(0xFF42A5F5)
                .setAnimationLoop(ModernDialog.ANIMATION_INFINITE)
        ;

    }

    private void test2() {



    }
}