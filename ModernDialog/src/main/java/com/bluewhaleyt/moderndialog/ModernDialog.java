package com.bluewhaleyt.moderndialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.bluewhaleyt.moderndialog.databinding.ModernDialogBinding;

public class ModernDialog {

    private final static int COLOR_BLACK = 0xFF000000;
    private final static int COLOR_GREY = 0xFF626262;
    private final static int COLOR_WHITE = 0xFFFFFFFF;
    private final static int COLOR_LIGHT_WHITE = 0xFFF5F5F5;
    private final static int COLOR_ACCENT = 0xFF6200EE;

    public final static int ALIGNMENT_CENTER = Gravity.CENTER;
    public final static int ALIGNMENT_LEFT = Gravity.LEFT;
    public final static int ALIGNMENT_RIGHT = Gravity.RIGHT;

    public final static int ANIMATION_INFINITE = LottieDrawable.INFINITE;
    public final static int ANIMATION_RESTART = LottieDrawable.RESTART;
    public final static int ANIMATION_REVERSE = LottieDrawable.REVERSE;

    @SuppressLint("StaticFieldLeak")
    private static ModernDialogBinding binding;

    public AlertDialog dialog = null;

    public ModernDialog(final Builder builder) {

        binding = ModernDialogBinding.inflate(LayoutInflater.from(builder.context));
        dialog = new AlertDialog.Builder(builder.context).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setView(binding.getRoot());
        dialog.show();

        // apply configuration
        // set cancelable
        dialog.setCancelable(builder.isCancelable);
        dialog.setCanceledOnTouchOutside(builder.isCancelableTouchOutside);

        // set background
        setBackgroundColor(binding.dialogView, builder.dialogBgColor);
        setBackgroundColor(binding.btnPositive, builder.buttonPositiveBgColor);
        setBackgroundColor(binding.btnNegative, builder.buttonNegativeBgColor);

        // set corner radius
        setCornerRadius(binding.dialogView, builder.dialogCornerRadius);
        setCornerRadius(binding.btnPositive, builder.buttonCornerRadius);
        setCornerRadius(binding.btnNegative, builder.buttonCornerRadius);

        // set text
        binding.tvTitle.setText(builder.title);
        binding.tvMessage.setText(builder.message);
        binding.btnPositive.setText(builder.btnPositiveText);
        binding.btnNegative.setText(builder.btnNegativeText);

        // set text color
        binding.tvTitle.setTextColor(builder.titleTextColor);
        binding.tvMessage.setTextColor(builder.messageTextColor);

        // set text alignment
        binding.tvTitle.setGravity(builder.titleTextAlignment);
        binding.tvMessage.setGravity(builder.messageTextAlignment);

        // set click event listener
        binding.btnPositive.setOnClickListener(v -> builder.onPositiveListener.onPositive(dialog));
        binding.btnNegative.setOnClickListener(v -> builder.onNegativeListener.onNegative(dialog));

        // set view visible
        setViewVisible(binding.tvTitle, builder.isTitleVisible);
        setViewVisible(binding.tvMessage, builder.isMessageVisible);
        setViewVisible(binding.imageView, builder.isImageVisible);
        setViewVisible(binding.btnPositive, builder.isPositiveButtonVisible);
        setViewVisible(binding.btnNegative, builder.isNegativeButtonVisible);

        if (builder.isAnimationVisible) {
            // set animation
            if (!builder.animationJSONUrl.equals("")) {
                setViewVisible(binding.animationView, true);
                binding.animationView.setAnimationFromUrl(builder.animationJSONUrl);
            } else if (builder.animationRawRes != 0) {
                binding.animationView.setAnimation(builder.animationRawRes);
            }

            // set animation color
            binding.animationView.addValueCallback(
                    new KeyPath("**"),
                    LottieProperty.COLOR_FILTER,
                    frameInfo -> new PorterDuffColorFilter(builder.animationColorAllLayers, PorterDuff.Mode.SRC_ATOP)
            );
            binding.animationView.addValueCallback(
                    new KeyPath(builder.animationLayerName, "**"),
                    LottieProperty.COLOR_FILTER,
                    frameInfo -> new PorterDuffColorFilter(builder.animationColorSpecificLayer, PorterDuff.Mode.SRC_ATOP)
            );

            // set animation loop count
            binding.animationView.setRepeatCount(builder.animationLoop);
            if (builder.isAnimationLoop) {
                binding.animationView.setRepeatCount(ANIMATION_INFINITE);
            }

            // set animation mode
            binding.animationView.setRepeatMode(builder.animationMode);
        }

        if (builder.isImageVisible) {
            // set image
            if (builder.imageRes != 0) {
                binding.imageView.setImageResource(builder.imageRes);
            } else if (!builder.imageUri.equals(Uri.EMPTY)) {
                binding.imageView.setImageURI(builder.imageUri);
            } else if (builder.imageDrawable != null) {
                binding.imageView.setImageDrawable(builder.imageDrawable);
            }
        }

    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

    private void setViewVisible(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setGravity(TextView view, int gravity) {
        view.setGravity(gravity);
    }

    private void setBackgroundColor(View view, int color) {
        view.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    private static void setCornerRadius(View view, int radius) {
        view.setBackground(new GradientDrawable() {
            public GradientDrawable apply(int radius) {
                this.setCornerRadius(radius);
                return this;
            }
        }.apply(radius));
    }

    public static class Builder {

        private Context context;

        // Basic settings
        private String title;
        private String message;
        private boolean isCancelable = true;
        private boolean isCancelableTouchOutside = false;

        // Advanced settings
        private int dialogBgColor = COLOR_WHITE;
        private int dialogCornerRadius = 60;

        private int titleTextColor = COLOR_BLACK;
        private int messageTextColor = COLOR_GREY;
        private int titleTextAlignment = ALIGNMENT_CENTER;
        private int messageTextAlignment = ALIGNMENT_CENTER;

        private int buttonCornerRadius = 80;
        private int buttonPositiveBgColor = COLOR_ACCENT;
        private int buttonNegativeBgColor = COLOR_LIGHT_WHITE;

        private String animationJSONUrl = "";
        private String animationLayerName = "";

        private int animationRawRes;
        private int animationColorAllLayers;
        private int animationColorSpecificLayer;
        private int animationLoop;
        private int animationMode;

        private int imageRes;
        private Uri imageUri;
        private Drawable imageDrawable;

        private boolean isTitleVisible = true;
        private boolean isMessageVisible = true;
        private boolean isAnimationVisible = false;
        private boolean isImageVisible = false;
        private boolean isPositiveButtonVisible = true;
        private boolean isNegativeButtonVisible = true;

        private boolean isAnimationLoop = false;

        private String btnPositiveText = String.valueOf(android.R.string.ok);
        private onPositiveListener onPositiveListener = modernDialog -> {};

        private String btnNegativeText = String.valueOf(android.R.string.cancel);
        private onNegativeListener onNegativeListener = modernDialog -> {};

        public Builder(Context context) {
            this.context = context;
        }

        public ModernDialog show() {
            return new ModernDialog(this);
        }

        /* ==== BASIC DIALOG CONFIG ==== */
        public Builder setTitle(String text) {
            this.title = text;
            return this;
        }

        public Builder setMessage(String text) {
            this.message = text;
            return this;
        }

        public Builder setCancelable(boolean isCancelable, boolean isCancelableTouchOutside) {
            this.isCancelable = isCancelable;
            this.isCancelableTouchOutside = isCancelableTouchOutside;
            return this;
        }

        public Builder setPositiveButton(String text, onPositiveListener listener) {
            this.btnPositiveText = text;
            this.onPositiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(String text, onNegativeListener listener) {
            this.btnNegativeText = text;
            this.onNegativeListener = listener;
            return this;
        }

        /* ==== ADVANCED DIALOG CONFIG ==== */
        /* ==== [DIALOG] ==== */
        public Builder setDialogBackgroundColor(int color) {
            this.dialogBgColor = color;
            return this;
        }

        public Builder setDialogCornerRadius(int radius) {
            this.dialogCornerRadius = radius;
            return this;
        }

        /* ==== [TITLE] ==== */
        public Builder setTitleTextColor(int color) {
            this.titleTextColor = color;
            return this;
        }

        public Builder setTitleTextAlignment(int align) {
            this.titleTextAlignment = align;
            return this;
        }

        /* ==== [MESSAGE] ==== */
        public Builder setMessageTextColor(int color) {
            this.messageTextColor = color;
            return this;
        }

        public Builder setMessageTextAlignment(int align) {
            this.messageTextAlignment = align;
            return this;
        }

        /* ==== [ANIMATION] ==== */
        public Builder setAnimation(boolean isVisible) {
            this.isImageVisible = false;
            this.isAnimationVisible = isVisible;
            return this;
        }

        public Builder setAnimation(String url) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationJSONUrl = url;
            return this;
        }

        public Builder setAnimation(int rawRes) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationRawRes = rawRes;
            return this;
        }

        public Builder setAnimationColorOverlayForAllLayers(int color) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationColorAllLayers = color;
            return this;
        }

        public Builder setAnimationColorOverlayForSpecificLayer(String layerName, int color) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationLayerName = layerName;
            this.animationColorSpecificLayer = color;
            return this;
        }

        public Builder setAnimationLoop(int count) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationLoop = count;
            return this;
        }

        public Builder setAnimationLoop(boolean isLoop) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.isAnimationLoop = isLoop;
            return this;
        }

        public Builder setAnimationMode(int mode) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationMode = mode;
            return this;
        }

        /* ==== [IMAGE] ==== */
        public Builder setImage(boolean isVisible) {
            this.isAnimationVisible = false;
            this.isImageVisible = isVisible;
            return this;
        }

        public Builder setImage(int imgRes) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageRes = imgRes;
            return this;
        }

        public Builder setImage(Uri imgUri) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageUri = imgUri;
            return this;
        }

        public Builder setImage(Drawable imageDrawable) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageDrawable = imageDrawable;
            return this;
        }

        /* ==== [POSITIVE BUTTON] ==== */
        public Builder setPositiveButton(boolean isVisible) {
            this.isPositiveButtonVisible = isVisible;
            return this;
        }

        public Builder setPositiveButtonBackgroundColor(int color) {
            this.buttonPositiveBgColor = color;
            return this;
        }

        public Builder setPositiveButtonCornerRadius(int radius) {
            this.buttonCornerRadius = radius;
            return this;
        }

        /* ==== [NEGATIVE BUTTON] ==== */
        public Builder setNegativeButton(boolean isVisible) {
            this.isNegativeButtonVisible = isVisible;
            return this;
        }

        public Builder setNegativeButtonBackgroundColor(int color) {
            this.buttonNegativeBgColor = color;
            return this;
        }

        public Builder setNegativeButtonCornerRadius(int radius) {
            this.buttonCornerRadius = radius;
            return this;
        }

    }

    public interface onPositiveListener {
        void onPositive(AlertDialog modernDialog);
    }

    public interface onNegativeListener {
        void onNegative(AlertDialog modernDialog);
    }

}
