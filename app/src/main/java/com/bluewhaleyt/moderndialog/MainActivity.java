package com.bluewhaleyt.moderndialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Initialize this dialog instance can call its methods everywhere.
    ModernDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // here's the sample of dialog configuration
        dialog = new ModernDialog.Builder(this)
                .setTitle("Hello World")
                .setMessage("A whale jumps over the lazy dog.")
                .setPositiveButton("ok", v -> clickEvent())
                .setPositiveButtonBackgroundColor(0xFF536DFE)
                .setNegativeButton("close", Dialog::dismiss) // you can use lambda to call dialog dismiss
                .setCancelable(true, false)
                .setAnimation("https://assets10.lottiefiles.com/packages/lf20_uu0x8lqv.json")
                .setAnimationLoop(true)
                .setAnimationColorOverlayForAllLayers(0xFF536DFE)
                // the layerName named according to the animation JSON files
                .setAnimationColorOverlayForSpecificLayer("Shape Layer 3", 0xFFFFFFFF)
                .show();

    }

    private void clickEvent() {
        dialog.dismiss(); // you can also simply do this to dismiss since you have defined the instance
    }

}