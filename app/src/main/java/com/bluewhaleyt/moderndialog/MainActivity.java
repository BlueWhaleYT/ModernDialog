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

    final String versionName = "v1.0.1";
    final String str1 = "Dialog";
    final String str2 = "Bottom Sheet Dialog";
    final String str3 = "This is an alert dialog using ModernDialog library. ";
    final String str4 = "This is a bottom sheet dialog using ModernDialog library. ";

    final int color1 = 0xFFFFEE58;
    final int color2 = 0xFFEC407A;

    private ActivityMainBinding binding;
    private ModernDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.versionName.setText(versionName);
        binding.btnDefaultDialog.setOnClickListener(view -> dialogDefault());
        binding.btnBottomSheetDialog.setOnClickListener(view -> dialogBottomSheet());
        binding.btnDefaultDialogWithAnimation.setOnClickListener(view -> dialogDefaultWithAnimation());
        binding.btnBottomSheetDialogWithAnimation.setOnClickListener(view -> dialogBottomSheetWithAnimation());
        binding.btnGithub.setOnClickListener(view -> gotoGithub());

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

        dialog = new ModernDialog.Builder(this)
                .setTitle(str1)
                .setMessage(str3)
                .setTitleTextAlignment(ModernDialog.ALIGNMENT_LEFT)
                .setMessageTextAlignment(ModernDialog.ALIGNMENT_LEFT)
                .setPositiveButtonBackgroundColor(color1)
                .setPositiveButtonTextColor(0xFF000000)
                .show();

    }

    private void dialogBottomSheet() {

        dialog = new ModernDialog.Builder(this)
                .setDialogStyle(ModernDialog.DIALOG_STYLE_BOTTOM_SHEET)
                .setTitle(str2)
                .setMessage(str4)
                .setTitleTextAlignment(ModernDialog.ALIGNMENT_LEFT)
                .setMessageTextAlignment(ModernDialog.ALIGNMENT_LEFT)
                .setPositiveButton(false)
                .show();

    }

    private void dialogDefaultWithAnimation() {

        // here's the sample of dialog configuration
        dialog = new ModernDialog.Builder(this)
                .setTitle(str1)
                .setMessage(str3 + "The animation is provided by Lottie Animations.")
                .setPositiveButton("ok", v -> clickEvent())
                .setPositiveButtonBackgroundColor(color2)
                .setNegativeButton("close", null)
                .setCancelable(true, false)
                .setAnimation("https://assets10.lottiefiles.com/packages/lf20_uu0x8lqv.json")
                .setAnimationColorOverlayForAllLayers(color2)
                // the layerName named according to the animation JSON files
                .setAnimationColorOverlayForSpecificLayer("Shape Layer 3", 0xFFFFFFFF)
                .show();

    }

    private void dialogBottomSheetWithAnimation() {

        // here's the sample of dialog configuration
        dialog = new ModernDialog.Builder(this)
                .setDialogStyle(ModernDialog.DIALOG_STYLE_BOTTOM_SHEET)
                .setTitle("Bottom Sheet Dialog")
                .setMessage(str4 + "The animation is provided by Lottie Animations.")
                .setButtonsDisabled(true)
                .setCancelable(true, true)
                .setAnimation("https://assets7.lottiefiles.com/packages/lf20_083h7wcs.json")
                .setAnimationLoop(true)
                .show();

    }

    private void clickEvent() {
        Toast.makeText(this, "made by BlueWhaleYT", Toast.LENGTH_SHORT).show();
    }

    private void gotoGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BlueWhaleYT/ModernDialog")));
    }

}