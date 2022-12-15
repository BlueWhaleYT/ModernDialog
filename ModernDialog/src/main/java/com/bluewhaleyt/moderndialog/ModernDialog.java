package com.bluewhaleyt.moderndialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.bluewhaleyt.moderndialog.databinding.ModernDialogBinding;

import java.util.concurrent.Callable;

public class ModernDialog {

    public static int COLOR_DIALOG_DEFAULT = android.R.color.white;
    public static int CORNER_RADIUS_DIALOG_DEFAULT = 60;
    public static int CORNER_RADIUS_BUTTON_DEFAULT = 80;

    public static int COLOR_IGNORE = -1;

    public static int ALIGNMENT_CENTER = Gravity.CENTER;
    public static int ALIGNMENT_LEFT = Gravity.LEFT;
    public static int ALIGNMENT_RIGHT = Gravity.RIGHT;

    public static Typeface FONT_STYLE_DEFAULT = Typeface.DEFAULT;
    public static Typeface FONT_STYLE_BOLD = Typeface.defaultFromStyle(Typeface.BOLD);
    public static Typeface FONT_STYLE_ITALIC = Typeface.defaultFromStyle(Typeface.ITALIC);
    public static Typeface FONT_STYLE_BOLD_ITALIC = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);

    public static int ANIMATION_INFINITE = LottieDrawable.INFINITE;
    public static int ANIMATION_RESTART = LottieDrawable.RESTART;
    public static int ANIMATION_REVERSE = LottieDrawable.REVERSE;

    @SuppressLint("StaticFieldLeak")
    private static ModernDialogBinding binding;

    public static ModernDialog modernDialog = new ModernDialog();

    public static ModernDialog init(Context context) {

        // initialize view binding
        binding = ModernDialogBinding.inflate(LayoutInflater.from(context));

        return modernDialog;
    }

    public static ModernDialog create() {

        // define default settings for the dialog
        setDialogBackgroundColor(getColor(binding.getRoot().getContext(), android.R.color.white));
        setDialogCornerRadius(CORNER_RADIUS_DIALOG_DEFAULT);
        setPositiveButtonCornerRadius(CORNER_RADIUS_BUTTON_DEFAULT);
        setNegativeButtonCornerRadius(CORNER_RADIUS_BUTTON_DEFAULT);

        // initialize a dialog
        AlertDialog dialog = new AlertDialog.Builder(binding.getRoot().getContext()).create();
        dialog.setView(binding.getRoot());
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        return modernDialog;
    }

    public static ModernDialog setDialogBackgroundColor(int color) {
        binding.dialogView.setBackgroundTintList(ColorStateList.valueOf(color));
        return modernDialog;
    }

    public static ModernDialog setDialogCornerRadius(int radius) {
        setCornerRadius(binding.dialogView, radius);
        return modernDialog;
    }

    public static ModernDialog setTitle(boolean isEnable) {
        setViewVisible(binding.tvTitle, isEnable);
        return modernDialog;
    }

    public static ModernDialog setTitle(String title) {
        binding.tvTitle.setText(title);
        return modernDialog;
    }

    public static ModernDialog setMessage(boolean isEnable) {
        setViewVisible(binding.tvMessage, isEnable);
        return modernDialog;
    }

    public static ModernDialog setMessage(String message) {
        binding.tvMessage.setText(message);
        return modernDialog;
    }

    public static ModernDialog setTitleAlignment(int alignment) {
        setGravity(binding.tvTitle, alignment);
        return modernDialog;
    }

    public static ModernDialog setMessageAlignment(int alignment) {
        setGravity(binding.tvMessage, alignment);
        return modernDialog;
    }

    public static ModernDialog setPositiveButton(boolean isEnable) {
        setViewVisible(binding.btnPositive, isEnable);
        return modernDialog;
    }

    public static ModernDialog setNegativeButton(boolean isEnable) {
        setViewVisible(binding.btnNegative, isEnable);
        return modernDialog;
    }

    public static ModernDialog setPositiveButton(View.OnClickListener listener, boolean isDismiss) {
        if (isDismiss) {

        }
        binding.btnPositive.setOnClickListener(listener);
        return modernDialog;
    }

    public static ModernDialog setNegativeButton(View.OnClickListener listener, boolean isDismiss) {
        if (isDismiss) {

        }
        binding.btnNegative.setOnClickListener(listener);
        return modernDialog;
    }

    public static ModernDialog setPositiveButtonText(String text) {
        binding.btnPositive.setText(text);
        return modernDialog;
    }

    public static ModernDialog setNegativeButtonText(String text) {
        binding.btnNegative.setText(text);
        return modernDialog;
    }

    public static ModernDialog setPositiveButtonTextColor(int color) {
        binding.btnPositive.setTextColor(color);
        return modernDialog;
    }

    public static ModernDialog setNegativeButtonTextColor(int color) {
        binding.btnNegative.setTextColor(color);
        return modernDialog;
    }

    public static ModernDialog setPositiveButtonBackgroundColor(int color) {
        binding.btnPositive.setBackgroundTintList(ColorStateList.valueOf(color));
        return modernDialog;
    }

    public static ModernDialog setNegativeButtonBackgroundColor(int color) {
        binding.btnNegative.setBackgroundTintList(ColorStateList.valueOf(color));
        return modernDialog;
    }

    public static ModernDialog setPositiveButtonCornerRadius(int cornerRadius) {
        setCornerRadius(binding.btnPositive, cornerRadius);
        return modernDialog;
    }

    public static ModernDialog setNegativeButtonCornerRadius(int cornerRadius) {
        setCornerRadius(binding.btnNegative, cornerRadius);
        return modernDialog;
    }

    public static ModernDialog setImage(int imgRes) {
        setViewVisible(binding.imageView, true);
        setViewVisible(binding.animationView, false);
        binding.imageView.setImageResource(imgRes);
        return modernDialog;
    }

    public static ModernDialog setImage(Uri imageUri) {
        setViewVisible(binding.imageView, true);
        setViewVisible(binding.animationView, false);
        binding.imageView.setImageURI(imageUri);
        return modernDialog;
    }

    public static ModernDialog setImage(Drawable imageDrawable) {
        setViewVisible(binding.imageView, true);
        setViewVisible(binding.animationView, false);
        binding.imageView.setImageDrawable(imageDrawable);
        return modernDialog;
    }

    public static ModernDialog setAnimation(String url) {
        setViewVisible(binding.imageView, false);
        setViewVisible(binding.animationView, true);
        binding.animationView.setAnimationFromUrl(url);
        return modernDialog;
    }

    public static ModernDialog setAnimation(int rawRes) {
        setViewVisible(binding.imageView, false);
        setViewVisible(binding.animationView, true);
        setViewVisible(binding.animationView, true);
        binding.animationView.setAnimation(rawRes);
        return modernDialog;
    }

    public static ModernDialog setAnimationColorOverlayForAllLayers(int color) {
        binding.animationView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        );
        return modernDialog;
    }

    public static ModernDialog setAnimationColorOverlayForSpecificLayer(String layerName, int color) {
        binding.animationView.addValueCallback(
                new KeyPath(layerName, "**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        );
        return modernDialog;
    }

    public static ModernDialog setAnimationLoop(int count) {
        binding.animationView.setRepeatCount(count);
        return modernDialog;
    }

    public static ModernDialog setAnimationMode(int mode) {
        binding.animationView.setRepeatMode(mode);
        return modernDialog;
    }

    /* PRIVATE METHODS */

    private static void setViewVisible(View v, boolean isVisible) {
        if (isVisible) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.GONE);
        }
    }

    private static void setGravity(TextView v, int gravity) {
        v.setGravity(gravity);
    }

    private static void setCornerRadius(View v, int cornerRadius) {
        v.setBackground(new GradientDrawable() {
            public GradientDrawable apply(int cornerRadius) {
                this.setCornerRadius(cornerRadius);
                return this;
            }
        }.apply(cornerRadius));
    }

    private static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }

}
