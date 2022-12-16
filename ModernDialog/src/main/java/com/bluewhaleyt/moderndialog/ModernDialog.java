package com.bluewhaleyt.moderndialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ModernDialog {

    // UNAVAILABLE CONSTANTS
    private final static int COLOR_BLACK = 0xFF212121;
    private final static int COLOR_GREY = 0xFF9E9E9E;
    private final static int COLOR_WHITE = 0xFFFFFFFF;
    private final static int COLOR_LIGHT_WHITE = 0xFFF5F5F5;
    private final static int COLOR_ACCENT = 0xFF6200EE;

    // AVAILABLE CONSTANTS
    public final static int CORNER_RADIUS_DIALOG = 60;
    public final static int CORNER_RADIUS_BUTTON = 80;

    public final static int ALIGNMENT_CENTER = Gravity.CENTER;
    public final static int ALIGNMENT_LEFT = Gravity.LEFT;
    public final static int ALIGNMENT_RIGHT = Gravity.RIGHT;

    public final static int ANIMATION_INFINITE = LottieDrawable.INFINITE;
    public final static int ANIMATION_RESTART = LottieDrawable.RESTART;
    public final static int ANIMATION_REVERSE = LottieDrawable.REVERSE;

    public final static int DIALOG_STYLE_DEFAULT = 10;
    public final static int DIALOG_STYLE_BOTTOM_SHEET = 11;

    @SuppressLint("StaticFieldLeak")
    private static ModernDialogBinding binding;

    public AlertDialog dialog;
    public BottomSheetDialog dialogBS;

    public ModernDialog(final Builder builder) {

        binding = ModernDialogBinding.inflate(LayoutInflater.from(builder.context));

        if (builder.dialogStyle == DIALOG_STYLE_DEFAULT) {
            dialog = new AlertDialog.Builder(builder.context).create();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setView(binding.getRoot());
            dialog.show();
            // set cancelable
            dialog.setCancelable(builder.isCancelable);
            dialog.setCanceledOnTouchOutside(builder.isCancelableTouchOutside);
        } else if (builder.dialogStyle == DIALOG_STYLE_BOTTOM_SHEET) {
            dialogBS = new BottomSheetDialog(builder.context, R.style.ThemeOverlay_App_BottomSheetDialog);
            dialogBS.setContentView(binding.getRoot());
            dialogBS.show();
            // set cancelable
            dialogBS.setCancelable(builder.isCancelable);
            dialogBS.setCanceledOnTouchOutside(builder.isCancelableTouchOutside);
        }

        // apply configuration
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

        // set view visible
        setViewVisible(binding.tvTitle, builder.isTitleVisible);
        setViewVisible(binding.tvMessage, builder.isMessageVisible);
        setViewVisible(binding.imageView, builder.isImageVisible);
        setViewVisible(binding.btnPositive, builder.isPositiveButtonVisible);
        setViewVisible(binding.btnNegative, builder.isNegativeButtonVisible);

        // set click event listener
        if (builder.dialogStyle == DIALOG_STYLE_DEFAULT) {
            if (builder.onPositiveListener != null) {
                binding.btnPositive.setOnClickListener(v -> {
                    builder.onPositiveListener.onPositive(dialog);
                    if (builder.isDialogDismiss) dialog.dismiss();
                });
            } else {
                binding.btnPositive.setOnClickListener(v -> dialog.dismiss());
            }
            if (builder.onNegativeListener != null) {
                binding.btnNegative.setOnClickListener(v -> {
                    builder.onNegativeListener.onNegative(dialog);
                    if (builder.isDialogDismiss) dialog.dismiss();
                });
            } else {
                binding.btnNegative.setOnClickListener(v -> dialog.dismiss());
            }
        } else if (builder.dialogStyle == DIALOG_STYLE_BOTTOM_SHEET) {
            if (builder.onPositiveListener != null) {
                binding.btnPositive.setOnClickListener(v -> {
                    builder.onPositiveListener.onPositive(dialog);
                    if (builder.isDialogDismiss) dialogBS.dismiss();
                });
            } else {
                binding.btnPositive.setOnClickListener(v -> dialogBS.dismiss());
            }
            if (builder.onNegativeListener != null) {
                binding.btnNegative.setOnClickListener(v -> {
                    builder.onNegativeListener.onNegative(dialog);
                    if (builder.isDialogDismiss) dialogBS.dismiss();
                });
            } else {
                binding.btnNegative.setOnClickListener(v -> dialogBS.dismiss());
            }
        }

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

    /**
     * Description:     <b>setViewVisible()</b> is a private method which determines whether the view is visible or not.
     * @param view      (View) The target view to be checked
     * @param isVisible (boolean) return true if it is visible, false otherwise
     */
    private void setViewVisible(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Description:     <b>setBackgroundColor()</b> is a private method which set the background tint color on the view.
     * @param view      (View) The target's background to be colored
     * @param color     (int) The color to be used
     */
    private void setBackgroundColor(View view, int color) {
        view.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    /**
     * Description:     <b>setCornerRadius()</b> is a private method which applies the corner radius to a view that looks like rounded edge.
     * @param view      (View) The target view to be applied
     * @param radius    (int) The radius to be set
     */
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

        // Basic settings - (DEFAULT)
        private String title;
        private String message;
        private boolean isCancelable = true;
        private boolean isCancelableTouchOutside = false;

        // Advanced settings - (DEFAULT)
        private int dialogStyle = DIALOG_STYLE_DEFAULT;
        private int dialogBgColor = COLOR_WHITE;
        private int dialogCornerRadius = CORNER_RADIUS_DIALOG;

        private int titleTextColor = COLOR_BLACK;
        private int messageTextColor = COLOR_GREY;
        private int titleTextAlignment = ALIGNMENT_CENTER;
        private int messageTextAlignment = ALIGNMENT_CENTER;

        private int buttonCornerRadius = CORNER_RADIUS_BUTTON;
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

        private boolean isDialogDismiss = true;
        private boolean isTitleVisible = true;
        private boolean isMessageVisible = true;
        private boolean isAnimationVisible = false;
        private boolean isImageVisible = false;
        private boolean isPositiveButtonVisible = true;
        private boolean isNegativeButtonVisible = true;
        private boolean isAnimationLoop = false;

        private String btnPositiveText = String.valueOf(android.R.string.ok);
        private OnPositiveListener onPositiveListener = modernDialog -> {};

        private String btnNegativeText = String.valueOf(android.R.string.cancel);
        private OnNegativeListener onNegativeListener = modernDialog -> {};

        /**
         * Description:     <b>Builder</b> is a constructor that is to call methods to use.
         * @param context   (Context) The context, this is used for inflating view and initializing dialogs
         */
        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Description:     <b>show()</b> is to show the dialog after the configuration is applied.
         * @return          an instance of {@link ModernDialog}
         */
        public ModernDialog show() {
            return new ModernDialog(this);
        }

        /* ==== BASIC DIALOG CONFIG ==== */

        /**
         * Description:     <b>setTitle()</b> is to set a title text.
         * @param text      (String) The text to be displayed <br>
         *                           Default value: {@link Builder#title}
         * @return          Builder
         */
        public Builder setTitle(String text) {
            this.title = text;
            return this;
        }

        /**
         * Description:     <b>setMessage()</b> is to set the message text.
         * @param text      (String) The text to be displayed <br>
         *                           Default value: {@link Builder#message}
         * @return          Builder
         */
        public Builder setMessage(String text) {
            this.message = text;
            return this;
        }

        /**
         * Description:                     <b>setCancelable()</b> is to set whether the dialog is cancelable or not.
         * @param isCancelable              (boolean) To check whether the dialog can be canceled <br>
         *                                            Default value: {@link Builder#isCancelable}
         * @param isCancelableTouchOutside  (boolean) To check whether the dialog can be canceled by touching outside, which is the edge of empty view <br>
         *                                            Default value: {@link Builder#isCancelableTouchOutside}
         * @return                          Builder
         */
        public Builder setCancelable(boolean isCancelable, boolean isCancelableTouchOutside) {
            this.isCancelable = isCancelable;
            this.isCancelableTouchOutside = isCancelableTouchOutside;
            return this;
        }

        /**
         * Description:     <b>setPositiveButton()</b> is to apply a positive button (OK button) to the dialog.
         * @param text      (String)             The text to be displayed on the button <br>
         *                                       Default value: {@link Builder#btnPositiveText}
         * @param listener  (OnPositiveListener) The listener of button's click event to be executed <br>
         *                                       Default value: {@link Builder#onPositiveListener}
         * @return          Builder
         */
        public Builder setPositiveButton(String text, OnPositiveListener listener) {
            this.btnPositiveText = text;
            this.onPositiveListener = listener;
            return this;
        }

        /**
         * Description:     <b>setNegativeButton()</b> is to apply a negative button (Cancel button) to the dialog.
         * @param text      (String)             The text to be displayed on the button <br>
         *                                       Default value: {@link Builder#btnNegativeText}
         * @param listener  (OnNegativeListener) The listener of button's click event to be executed <br>
         *                                       Default value: {@link Builder#onNegativeListener}
         * @return          Builder
         */
        public Builder setNegativeButton(String text, OnNegativeListener listener) {
            this.btnNegativeText = text;
            this.onNegativeListener = listener;
            return this;
        }

        /* ==== ADVANCED DIALOG CONFIG ==== */
        /* ==== [DIALOG] ==== */

        public Builder setDialogDismiss(boolean isDismiss) {
            this.isDialogDismiss = isDismiss;
            return this;
        }

        public Builder setDialogStyle(int style) {
            this.dialogStyle = style;
            return this;
        }

        /**
         * Description:     <b>setDialogBackgroundColor()</b> is to apply a background tint color to the dialog
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#dialogBgColor}
         * @return          Builder
         */
        public Builder setDialogBackgroundColor(int color) {
            this.dialogBgColor = color;
            return this;
        }

        /**
         * Description:     <b>setDialogCornerRadius()</b> is to adjust the corner radius of the dialog.
         * @param radius    (int) The radius to be set <br>
         *                  Default value: {@link #dialogCornerRadius}
         * @return          Builder
         */
        public Builder setDialogCornerRadius(int radius) {
            this.dialogCornerRadius = radius;
            return this;
        }

        /* ==== [TITLE] ==== */

        /**
         * Description:     <b>setTitleTextColor()</b> is to set the color of title texts.
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#titleTextColor}
         * @return          Builder
         */
        public Builder setTitleTextColor(int color) {
            this.titleTextColor = color;
            return this;
        }

        /**
         * Description:     <b>setTitleAlignment</b> is to set the gravity of title texts.
         * @param align     (int)             The gravity to be set <br>
         *                  Default value:    {@link #titleTextAlignment} <br>
         *                  Available values: {@link #ALIGNMENT_LEFT},
         *                                    {@link #ALIGNMENT_RIGHT},
         *                                    {@link #ALIGNMENT_CENTER}
         * @return          Builder
         */
        public Builder setTitleTextAlignment(int align) {
            this.titleTextAlignment = align;
            return this;
        }

        /* ==== [MESSAGE] ==== */

        /**
         * Description:     <b>setMessageTextColor()</b> is to set the color of message texts.
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#messageTextColor}
         * @return          Builder
         */
        public Builder setMessageTextColor(int color) {
            this.messageTextColor = color;
            return this;
        }

        /**
         * Description:     <b>setMessageAlignment</b> is to set the gravity of message texts.
         * @param align     (int)             The gravity to be set <br>
         *                  Default value:    {@link #messageTextAlignment} <br>
         *                  Available values: {@link #ALIGNMENT_LEFT},
         *                                    {@link #ALIGNMENT_RIGHT},
         *                                    {@link #ALIGNMENT_CENTER}
         * @return          Builder
         */
        public Builder setMessageTextAlignment(int align) {
            this.messageTextAlignment = align;
            return this;
        }

        /* ==== [ANIMATION] ==== */

        /**
         * Description:     <b>setAnimation()</b> is a first method which sets the visible of animation view.
         * @param isVisible (boolean) Returns true if visible, false otherwise <br>
         *                  Default value: {@link Builder#isAnimationVisible}
         * @return          Builder
         */
        public Builder setAnimation(boolean isVisible) {
            this.isImageVisible = false;
            this.isAnimationVisible = isVisible;
            return this;
        }

        /**
         * Description:     <b>setAnimation()</b> is a second method which sets the animation by applying its JSON URL from Lottie Animations.
         * @param url       (String) The url of the animation JSON. <br>
         *                           Default value: {@link Builder#animationJSONUrl}
         * @return          Builder
         */
        public Builder setAnimation(String url) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationJSONUrl = url;
            return this;
        }

        /**
         * Description:     <b>setAnimation()</b> is a second method which sets the animation by applying its raw resource from Lottie Animations.
         * @param rawRes    (int) The raw resource of animation file, you are required to put the raw resource JSON file into <i>res/raw</i>,
         *                        create this directory if you don't have one <br>
         *                  Default value: {@link Builder#animationRawRes}
         * @return          Builder
         */
        public Builder setAnimation(int rawRes) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationRawRes = rawRes;
            return this;
        }

        /**
         * Description:     <b>setAnimationColorOverlayForAllLayers()</b> is to apply the given color to all layers of the animation.
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#animationColorAllLayers}
         * @return          Builder
         */
        public Builder setAnimationColorOverlayForAllLayers(int color) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationColorAllLayers = color;
            return this;
        }

        /**
         * Description:     <b>setAnimationColorOverlayForSpecificLayer()</b> is to apply the given color to a specific layer of the animation.
         * @param layerName (String) The name of a part of the layer of the animation to be targeted
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#animationColorSpecificLayer}
         * @return          Builder
         */
        public Builder setAnimationColorOverlayForSpecificLayer(String layerName, int color) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationLayerName = layerName;
            this.animationColorSpecificLayer = color;
            return this;
        }

        /**
         * Description:     <b>setAnimationLoop()</b> is a first method which sets the count of the animation to be played repeatedly.
         * @param count     (int) The count to be played repeatedly <br>
         *                  Default value: {@link Builder#animationLoop} <br>
         *                  Available values: {@link #ANIMATION_INFINITE}
         * @return
         */
        public Builder setAnimationLoop(int count) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationLoop = count;
            return this;
        }

        /**
         * Description:     <b>setAnimationLoop()</b> is a second method which sets the repeating of animations.
         * @param isLoop    (boolean) True if loop, false otherwise <br>
         *                  Default value: {@link Builder#isAnimationLoop}
         * @return
         */
        public Builder setAnimationLoop(boolean isLoop) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.isAnimationLoop = isLoop;
            return this;
        }

        /**
         * Description:     <b>setAnimationMode()</b> is to set the mode of the animation to be played.
         * @param mode      (int) The mode to be set <br>
         *                  Default value: {@link #animationMode} <br>
         *                  Available value: {@link #ANIMATION_RESTART},
         *                                   {@link #ANIMATION_REVERSE}
         * @return          Builder
         */
        public Builder setAnimationMode(int mode) {
            this.isImageVisible = false;
            this.isAnimationVisible = true;
            this.animationMode = mode;
            return this;
        }

        /* ==== [IMAGE] ==== */

        /**
         * Description:     <b>setImage()</b> is a first method which sets the visibility of the image.
         * @param isVisible (boolean) Returns true if visible, false otherwise <br>
         *                  Default value: {@link #isImageVisible}
         * @return          Builder
         */
        public Builder setImage(boolean isVisible) {
            this.isAnimationVisible = false;
            this.isImageVisible = isVisible;
            return this;
        }

        /**
         * Description:     <b>setImage()</b> is a second method which sets the image resource.
         * @param imgRes    (int) The image resource to be set <br>
         *                  Default value: {@link #imageRes}
         * @return          Builder
         */
        public Builder setImage(int imgRes) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageRes = imgRes;
            return this;
        }

        /**
         * Description:     <b>setImage()</b> is a second method which sets the image uri.
         * @param imgUri    (String) The image uri to be set <br>
         *                  Default value: {@link #imageUri}
         * @return          Builder
         */
        public Builder setImage(Uri imgUri) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageUri = imgUri;
            return this;
        }

        /**
         * Description:         <b>setImage()</b> is a second method which sets the image drawable.
         * @param imageDrawable (Drawable) The image drawable to be set <br>
         *                      Default value: {@link #imageDrawable}
         * @return              Builder
         */
        public Builder setImage(Drawable imageDrawable) {
            this.isAnimationVisible = false;
            this.isImageVisible = true;
            this.imageDrawable = imageDrawable;
            return this;
        }

        /* ==== [POSITIVE BUTTON] ==== */
        /**
         * Description:     <b>setPositiveButton()</b> is a second method which sets the visibility of the positive button.
         * @param isVisible (boolean) Returns true if visible, false otherwise <br>
         *                  Default value: {@link #isPositiveButtonVisible}
         * @return          Builder
         */
        public Builder setPositiveButton(boolean isVisible) {
            this.isPositiveButtonVisible = isVisible;
            return this;
        }

        /**
         * Description:     <b>setPositiveButtonBackgroundColor()</b> is to apply a background tint color to the positive button
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#buttonPositiveBgColor}
         * @return          Builder
         */
        public Builder setPositiveButtonBackgroundColor(int color) {
            this.buttonPositiveBgColor = color;
            return this;
        }

        /**
         * Description:     <b>setPositiveButtonCornerRadius()</b> is to adjust the corner radius of the positive button.
         * @param radius    (int) The radius to be set <br>
         *                  Default value: {@link #buttonCornerRadius}
         * @return          Builder
         */
        public Builder setPositiveButtonCornerRadius(int radius) {
            this.buttonCornerRadius = radius;
            return this;
        }

        /* ==== [NEGATIVE BUTTON] ==== */
        /**
         * Description:     <b>setNegativeButton()</b> is a second method which sets the visibility of the negative button.
         * @param isVisible (boolean) Returns true if visible, false otherwise <br>
         *                  Default value: {@link #isNegativeButtonVisible}
         * @return          Builder
         */
        public Builder setNegativeButton(boolean isVisible) {
            this.isNegativeButtonVisible = isVisible;
            return this;
        }

        /**
         * Description:     <b>setNegativeButtonBackgroundColor()</b> is to apply a background tint color to the negative button
         * @param color     (int) The color to be set <br>
         *                  Default value: {@link Builder#buttonNegativeBgColor}
         * @return          Builder
         */
        public Builder setNegativeButtonBackgroundColor(int color) {
            this.buttonNegativeBgColor = color;
            return this;
        }

        /**
         * Description:     <b>setNegativeButtonCornerRadius()</b> is to adjust the corner radius of the negative button.
         * @param radius    (int) The radius to be set <br>
         *                  Default value: {@link #buttonCornerRadius}
         * @return          Builder
         */
        public Builder setNegativeButtonCornerRadius(int radius) {
            this.buttonCornerRadius = radius;
            return this;
        }

    }

    /**
     * Description:     <b>OnPositiveListener</b> is to execute the click event of positive buttons.
     */
    public interface OnPositiveListener {
        void onPositive(AlertDialog modernDialog);
    }

    /**
     * Description:     <b>OnNegativeListener</b> is to execute the click event of negative buttons.
     */
    public interface OnNegativeListener {
        void onNegative(AlertDialog modernDialog);
    }

}
