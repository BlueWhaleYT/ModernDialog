# Modern Dialog

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

Add the dependency in your app `build.gradle` or `build.gradle (Module.app)` inside `Gradle Scripts`.

The `$version` can be found in [Releases]().

```
dependencies {
    implementation 'com.bluewhaleyt.moderndialog:ModernDialog:$version'
}
```

## Basic Usages

Here's an example to create ModernDialog.

```java
ModernDialog.init(this)
            .create();
```