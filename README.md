# Modern Dialog [![](https://jitpack.io/v/BlueWhaleYT/ModernDialog.svg)](https://jitpack.io/#BlueWhaleYT/ModernDialog)

<img src="http://forthebadge.com/images/badges/built-for-android.svg" /><br>

Do you feel frustrated about using `AlertDialog` and `MaterialAlertDialog` with their normal designs? If so, ModernDialog is your new choice. It is a powerful library for Android Development written in Java.

## Features

Modern Dialog supports [Lottie Animations](https://lottiefiles.com/) by default, **you are not required to add this library on your own.** However, if you're interested about the development of Lottie Animation, you can go to this [documentation](https://github.com/airbnb/lottie-android) for more details.

## Get Started
You are required to follow the instruction if you want to use this library in your project.

### Step 1 - Add JitPack Repository
Add the JitPack repository to your root `build.gradle` or `build.gradle (Project: xxx)` inside `Gradle Scripts`.

```kt
allprojects {
    repositories {
        ...
        // add this line
        maven { url 'https://jitpack.io' }
    }
}
```
However, if you're using **Android Studio Bumblebee**, the method describes above **is not working in this version**. Instead, you need to add JitPack repository in your `settings.gradle`.

```kt
pluginManagement {
    repositories {
        ...
        // add this line
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        // add this line
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2 - Add Dependency

Add the dependency in your app `build.gradle` or `build.gradle (Module: xxx.app)` inside `Gradle Scripts`.

The `$version` can be found in [Releases]().

```kt
dependencies {
    implementation 'com.github.BlueWhaleYT:ModernDialog:$version'
}
```

## Usage

These are the basic methods came from original `Dialog`, so we're not going to investigate these methods.

```java
ModernDialog dialog = new ModernDialog.Builder(this)

        // set title
        .setTitle("Your title here")

        // set message
        .setMessage("Your text here")

        // set dialog cancelable
        // 1st param: setCancelable
        // 2nd param: setCancelableTouchOutside
        .setCancelable(true, false)

        // set positive button, set "null" if dismiss only
        .setPositiveButton("OK", null)

        // set negative button, set "null" if dismiss only
        .setNegativeButton("Close", null)

        // show the dialog
        .show();

```

```java
// Extra Instructions of Button click listener:
// Note: PositiveButton & NegativeButton use same method of implementing click listeners
.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
    @public void onClick(DialogInterface d, int which) {
        // code here ..
    }
});

// If your project is using Java 11, then you can simply simplified the above code with lambda
.setPositiveButton(android.R.string.ok, (d, i) -> {
    // code here
});
```
### Dialog

#### Dismiss

Sometimes you don't want the dialog to be dismissed after clicking the positive button or anything else. By default, the dismiss action will be executed after the click listener is done. If you want to ignore this, you can simply add this code.

```java
.setDialogDismiss(false)
```

Note that if you set `null` in the click listener, this method will not work and will still dismiss the dialog.

#### Style

ModernDialog has provided two styles, one is default, another one is Bottom Sheet style. Let's see how we can alter the dialog to Bottom Sheet style.

```java
.setDialogStyle(ModernDialog.DIALOG_STYLE_BOTTOM_SHEET)
```

If you want to specific its style to a default style, this code below will set the default style.

```java
.setDialogStyle(ModernDialog.DIALOG_STYLE_DEFAULT)
```

### Animation

ModernDialog supports Lottie Animations by default. Before initializing your animation file, you need to enable it first. However, this action will be automatically done after setting the animation file, this method is a stright method.

```java
.setAnimation(true)
```

Now we're going to load the animation to the dialog. Currently, there are two ways to load it.

```java
.setAnimation(String url)
.setAnimation(int rawRes)
```

#### Load animation via URL

Now, go to [Lottie Animations](https://lottiefiles.com/) and search for any animations you like, find the `Lottie Animation URL` section and copy the URL for later use.

Once we copied it, we can now load the selected animation to the dialog. For example, I chose this animation and this is it's URL.

```java
.setAnimation("https://assets9.lottiefiles.com/packages/lf20_iIq3IA.json")
```

Very simple, isn't it?

#### Load animation via raw resource

Instead of grabing the URL, we need to find the `Download` button and download it by clicking `Lottie JSON`.

Once we downloaded it, we need to put the JSON file into `res/raw/` directory, create one if you do not.

For example, I put this animation into `res/raw/7974-gooey-cube-emits.json` and I rename it to `animation1.json` for a cleaner name.

Then, this JSON file can be loaded via:

```java
.setAnimation(R.raw.animation1)
```

Done, enjoy the dialog within the beautiful animation!

#### Set the animation to play repeatedly

By default, animation is played for once, if you want to loop the animation. You can add following.

```java
// choose either one: 
.setAnimationLoop(true)
.setAnimationLoop(ModernDialog.ANIMATION_INFINITE)
```

Or, you want to specific its repeat time, then you can set like below:

```java
.setAnimationLoop(int count)
```

#### Set color to the animation

When the animation is sucessfully loaded, sometimes you may not feel satisfied the color of the animation. Therefore, there are two methods to apply colors to the animation.

##### Set color globally

If you just want to change whole set of color overlay, then you can change it globally. That's mean, the animation's color overlay will be applied once you set the color.

```java
.setAnimationColorOverlayForAllLayers(int color)
```

##### Set color specifically

You may want to change a part of the layer's color instead of changing whole set of animation's color. Then, you need to specific the layer name and set its color.

```java
.setAnimationColorOverlayForSpecificLayer(String layerName, int color)
```
To get the layer name of the part of animation, you can go to the JSON file or Lottie Animation Editor to inspect it.

For example, the part of animation's layer name is `Shape Layer 3`. Then, you can do following.

```java
.setAnimationColorOverlayForSpecificLayer("Shape Layer 3", color)
```

Tutorial ends, Enjoy!