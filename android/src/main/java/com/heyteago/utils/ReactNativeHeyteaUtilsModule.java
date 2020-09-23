package com.heyteago.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import java.util.TimeZone;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.net.URISyntaxException;
import java.util.TimeZone;

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

    /**
     * 跳转应用商店
     *
     * @param appPkg    app包名
     * @param marketPkg 应用商店包名，如果为空，则由系统弹出应用商店列表供用户选择
     */
    @ReactMethod
    public void navigateAndroidAppStore(String appPkg, @Nullable String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getCurrentActivity().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void showAlert(String title, String content, String cancelText, String confirmText, boolean showCancel, final Callback cancelCallback, final Callback confirmCallback) {
        HeyteaAlert heyteaAlert = new HeyteaAlert(getCurrentActivity());
        heyteaAlert.setTitle(title);
        heyteaAlert.setContent(content);
        heyteaAlert.setCancelText(cancelText);
        heyteaAlert.setConfirmText(confirmText);
        if (showCancel) {
            heyteaAlert.setCancelBtnState(showCancel);
            heyteaAlert.setOnCancelClick(new HeyteaAlert.OnCancelClick() {
                @Override
                public void onClick(View view) {
                    if (cancelCallback != null) {
                        cancelCallback.invoke();
                    }
                }
            });
        }else {
            heyteaAlert.setCancelBtnState(showCancel);
        }
        heyteaAlert.setOnConfirmClick(new HeyteaAlert.OnConfirmClick() {
            @Override
            public void onClick(View view) {
                if (confirmCallback != null) {
                    confirmCallback.invoke();
                }
            }
        });
        heyteaAlert.show();
    }

    /**
     * 沉浸式状态栏
     */
    @ReactMethod
    public void requestTransparentStatusBar() {
        getCurrentActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getCurrentActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
    }


    /**
     * 获取Sim卡的国家代码
     */
    @ReactMethod
    public void getSimCountryIso(Promise promise) {
        TelephonyManager tm = (TelephonyManager) reactContext.getSystemService(Context.TELEPHONY_SERVICE);
        promise.resolve(tm != null ? tm.getSimCountryIso() : "");
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        String s = timeZone.getDisplayName(false, TimeZone.SHORT);
        return s;
    }

    @ReactMethod
    public void navigateAndroidBaiduMap(String latitude, String longitude) {
        try {
            Intent intent = Intent.getIntent("intent://map/direction?destination=latlng:"+ latitude + "," + longitude +
                    "|name:&origin=" + "我的位置" + "&mode=driving?ion=" + "我的位置"+ "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            getCurrentActivity().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void navigateAndroidGaodeMap(String latitude, String longitude) {
        try {
            Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=appName&poiname=我的目的地&lat=" + latitude + "&lon=" + longitude + "&dev=0");
            getCurrentActivity().startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void navigateAndroidGoogleMap(String latitude, String longitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude+"&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        getCurrentActivity().startActivity(mapIntent);
    }

}
