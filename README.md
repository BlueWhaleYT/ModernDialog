# Modern Dialog [![](https://jitpack.io/v/BlueWhaleYT/ModernDialog.svg)](https://jitpack.io/#BlueWhaleYT/ModernDialog)

<img src="http://forthebadge.com/images/badges/built-for-android.svg" /><br>

Do you feel frustrated about using `AlertDialog` and `MaterialAlertDialog` with their normal designs? If so, ModernDialog is your new choice. It is a powerful library for Android Development written in Java.

## Features

Modern Dialog supports Lottie Animation by default, **you are not required to add this library on your own.** However, if you're interested about the development of Lottie Animation, you can go to this [documentation](https://github.com/airbnb/lottie-android) for more details.

## Get Started
You are required to follow the instruction if you want to use this library in your project.

### Step 1 - Add JitPack Repository
Add the JitPack repository to your root `build.gradle` or `build.gradle (Project: xxx)` inside `Gradle Scripts`.

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
However, if you're using Android Studio Bumblebee, the method describes above is not working in this version. Instead, you need to do this in your `settings.gradle`.

```
pluginManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2 - Add Dependency

Add the dependency in your app `build.gradle` or `build.gradle (Module: xxx.app)` inside `Gradle Scripts`.

The `$version` can be found in [Releases](https://github.com/BlueWhaleYT/ModernDialog/releases).

```
dependencies {
    implementation 'com.github.BlueWhaleYT:ModernDialog:$version'
}
```

## Usage

Before creating the ModernDialog, I highly recommend you to initialize the instance first. Once you do this, the methods of dialog can be called everywhere.

```java
ModernDialog dialog;
```

Then, you now can use the dialog with configuration. Like `AlertDialog`, the dialog creates as below:

```java
dialog = new ModernDialog.Builder(this)
        .setTitle("Your title here")
        .setMessage("Your text here")
        .setPositiveButton("OK", v -> clickEvent())
        .setPositiveButtonBackgroundColor(0xFF42A5F5)
        .setNegativeButton("Close", Dialog::dismiss)
        .setCancelable(true, false)
```