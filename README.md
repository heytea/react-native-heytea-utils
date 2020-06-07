# react-native-heytea-utils

## Getting started

`$ npm install @heytea/react-native-heytea-utils --save`

### Mostly automatic installation

`$ react-native link @heytea/react-native-heytea-utils`

## Usage
```javascript
import ReactNativeHeyteaUtils from '@heytea/react-native-heytea-utils';

// 跳转设置页面
// Android
ReactNativeHeyteaUtils.navigateAndroidSetting(AndroidSettings.ACTION_SETTINGS);
// iOS todo

// 跳转其它应用
// Android
/**
 * @param packageName 其它应用的包名
 * @param activityPackageName 其它应用的activity全包名
 */
ReactNativeHeyteaUtils.navigateAndroidApp(packageName: string, activityPackageName?: string): Promise<boolean>;
// iOS todo


// 检查目标App是否已在本机安装
// Android
ReactNativeHeyteaUtils.checkAndroidAppInstalled(packageName: string): Promise<boolean>;
// iOS todo

```
