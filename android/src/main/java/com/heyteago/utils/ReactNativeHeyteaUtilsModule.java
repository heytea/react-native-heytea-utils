package com.heyteago.utils;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ReactNativeHeyteaUtilsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReactNativeHeyteaUtilsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeHeyteaUtils";
    }

    /**
     * 跳转到设置页面，参数可配
     *
     * @param settings 参考 {@link android.provider.Settings}
     */
    @ReactMethod
    public void navigateAndroidSetting(String settings, Promise promise) {
        try {
            Intent intent = new Intent(settings);
            getCurrentActivity().startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(new Throwable(e.getMessage()));
        }
    }

    /**
     * 跳转到其它App
     *
     * @param packageName         包名
     * @param activityPackageName activity的全包名，可为空。需要其它App的manifest文件中声明export
     * @param promise
     */
    @ReactMethod
    public void navigateAndroidApp(String packageName, @Nullable String activityPackageName, Promise promise) {
        try {
            if (activityPackageName == null) {
                Intent intent = reactContext.getPackageManager().getLaunchIntentForPackage(packageName);
                if (intent != null) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getCurrentActivity().startActivity(intent);
                }
                promise.resolve(true);
                return;
            }
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName componentName = new ComponentName(packageName, activityPackageName);
            intent.setComponent(componentName);
            getCurrentActivity().startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(new Throwable(e.getMessage()));
        }
    }

    /**
     * 检查目标App是否已在本机安装
     *
     * @param packageName 目标App包名
     * @param promise
     */
    @ReactMethod
    public void checkAndroidAppInstalled(String packageName, Promise promise) {
        try {
            PackageInfo packageInfo = reactContext.getPackageManager().getPackageInfo(packageName, 0);
            promise.resolve(packageInfo != null);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            promise.reject(new Throwable(e.getMessage()));
        }
    }
}
