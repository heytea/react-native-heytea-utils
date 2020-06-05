package com.reactlibrary;

import android.content.Intent;

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
     * @param settings 参考 {@link android.provider.Settings}
     */
    @ReactMethod
    public void navigateAndroidSetting(String settings) {
        Intent intent =  new Intent(settings);
        reactContext.startActivity(intent);
    }
}
