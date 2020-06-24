
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

export enum IOSAuthorizationStatus {
    AuthorizationStatusNotDetermined = 0,  // 用户还未选择是否允许授权 可以代表第一次询问
    AuthorizationStatusRestricted = 1, // 拒绝授权 包括 Restricted & Denied
    AuthorizationStatusDenied = 2, // 拒绝授权 包括 Restricted & Denied
    AuthorizationStatusAuthorizedAlways = 3, //已授权
    AuthorizationStatusAuthorizedWhenInUse = 4 //已授权
}

export function navigateAndroidSetting(settings: AndroidSettings): Promise<boolean>;
export function navigateiOSSetting(): void;

/**
 * 跳转其它应用
 * @param packageName 其它应用的包名
 * @param activityPackageName 其它应用的activity全包名
 */
export function navigateAndroidApp(packageName: string, activityPackageName?: string): Promise<boolean>;
/**
 * 检查目标App是否已在本机安装
 * @param packageName 目标App的包名
 */
export function checkAndroidAppInstalled(packageName: string): Promise<boolean>;
/**
 * 跳转应用商店
 * @param appPkg app包名
 * @param marketPkg 应用商店包名，如果为空，则由系统弹出应用商店列表供用户选择
 */
export function navigateAndroidAppStore(appPkg: string, marketPkg?: string): void;
/**
 * 显示系统对话框
 * @param title 标题
 * @param content 内容
 * @param cancelText 取消文本
 * @param confirmText 确认文本
 * @param cancelCallback 取消回调
 * @param confirmCallback 确认回调
 */
export function showAlert(title: string, content: string, cancelText: string, confirmText: string, cancelCallback?: () => void, confirmCallback?: () => void): void;

/**
 * 沉浸式状态栏
 */
export function requestTransparentStatusBar(): void;

export function getLocationAuthorizationStatus():Promise<IOSAuthorizationStatus>


/**
 * 获取Sim卡的国家代码
 */
export function getSimCountryIso():Promise<string>
