package com.bluewhaleyt.moderndialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bluewhaleyt.moderndialog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // initialize this dialog class can call its methods everywhere.
    ModernDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn1.setOnClickListener(view -> dialogDefault());
        binding.btn2.setOnClickListener(view -> dialogBottomSheet());
        binding.btn3.setOnClickListener(view -> gotoGithub());

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                binding.switchTheme.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                binding.switchTheme.setChecked(false);
                break;
        }

        binding.switchTheme.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

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
                .setPositiveButtonBackgroundColor(0xFF69F0AE)
                .setPositiveButtonTextColor(0xFF000000)
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