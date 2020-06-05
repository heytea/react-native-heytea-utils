export enum AndroidSettings {
    ACTION_SETTINGS = "android.settings.SETTINGS",
    ACTION_LOCATION_SOURCE_SETTINGS = "android.settings.LOCATION_SOURCE_SETTINGS",
    ACTION_LOCATION_SCANNING_SETTINGS = "android.settings.LOCATION_SCANNING_SETTINGS",
    ACTION_USER_SETTINGS = "android.settings.USER_SETTINGS",
    ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS",
    ACTION_AIRPLANE_MODE_SETTINGS = "android.settings.AIRPLANE_MODE_SETTINGS",
    ACTION_ACCESSIBILITY_SETTINGS = "android.settings.ACCESSIBILITY_SETTINGS",
    ACTION_USAGE_ACCESS_SETTINGS = "android.settings.USAGE_ACCESS_SETTINGS",
    ACTION_SECURITY_SETTINGS = "android.settings.SECURITY_SETTINGS",
    ACTION_PRIVACY_SETTINGS = "android.settings.PRIVACY_SETTINGS",
    ACTION_VPN_SETTINGS = "android.settings.VPN_SETTINGS",
    ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS",
    ACTION_WIFI_IP_SETTINGS = "android.settings.WIFI_IP_SETTINGS",
    ACTION_DATA_USAGE_SETTINGS = "android.settings.DATA_USAGE_SETTINGS",
    ACTION_BLUETOOTH_SETTINGS = "android.settings.BLUETOOTH_SETTINGS",
    ACTION_ASSIST_GESTURE_SETTINGS = "android.settings.ASSIST_GESTURE_SETTINGS",
    ACTION_CAST_SETTINGS = "android.settings.CAST_SETTINGS",
    ACTION_DATE_SETTINGS = "android.settings.DATE_SETTINGS",
    ACTION_SOUND_SETTINGS = "android.settings.SOUND_SETTINGS",
    ACTION_DISPLAY_SETTINGS = "android.settings.DISPLAY_SETTINGS",
    ACTION_NIGHT_DISPLAY_SETTINGS = "android.settings.NIGHT_DISPLAY_SETTINGS",
    ACTION_LOCALE_SETTINGS = "android.settings.LOCALE_SETTINGS",
}

export function navigateAndroidSetting(settings: AndroidSettings): Promise<boolean>;
export function navigateiOSSetting(): void;