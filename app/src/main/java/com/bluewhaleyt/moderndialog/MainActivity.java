package com.bluewhaleyt.moderndialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // initialize this dialog class can call its methods everywhere.
    ModernDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        btn1.setOnClickListener(view -> dialogDefault());
        btn2.setOnClickListener(view -> dialogBottomSheet());
        btn3.setOnClickListener(view -> gotoGithub());

    }

    private void dialogDefault() {

        // here's the sample of dialog configuration
        dialog = new ModernDialog.Builder(this)
                .setTitle("Dialog")
                .setMessage("This is a simple alert dialog using ModernDialog library. The animation is provided by Lottie Animations.")
                .setPositiveButton("ok", v -> clickEvent())
                .setPositiveButtonBackgroundColor(0xFF536DFE)
                .setNegativeButton("close", null)
                .setCancelable(true, false)
                .setAnimation("https://assets10.lottiefiles.com/packages/lf20_uu0x8lqv.json")
                .setAnimationColorOverlayForAllLayers(0xFF536DFE)
                // the layerName named according to the animation JSON files
                .setAnimationColorOverlayForSpecificLayer("Shape Layer 3", 0xFFFFFFFF)
                .show();

    }

    private void dialogBottomSheet() {

        // here's the sample of dialog configuration
        dialog = new ModernDialog.Builder(this)
                .setDialogStyle(ModernDialog.DIALOG_STYLE_BOTTOM_SHEET)
                .setTitle("Bottom Sheet Dialog")
                .setMessage("This is a simple bottom sheet dialog using ModernDialog library. The animation is provided by Lottie Animations.")
                .setPositiveButton("ok", v -> clickEvent())
                .setPositiveButtonBackgroundColor(0xFF00E676)
                .setNegativeButton("close", null)
                .setCancelable(true, false)
                .setAnimation("https://assets7.lottiefiles.com/packages/lf20_iIq3IA.json")
                .setAnimationLoop(true)
                // the layerName named according to the animation JSON files
                .show();

    }

    private void clickEvent() {
        Toast.makeText(this, "made by BlueWhaleYT", Toast.LENGTH_SHORT).show();
    }

    private void gotoGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BlueWhaleYT/ModernDialog")));
    }

}