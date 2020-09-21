#import "ReactNativeHeyteaUtils.h"
#import <CoreLocation/CoreLocation.h>
#import <CoreTelephony/CTCarrier.h>
#import <CoreTelephony/CTTelephonyNetworkInfo.h>

@implementation ReactNativeHeyteaUtils

RCT_EXPORT_MODULE(ReactNativeHeyteaUtils);

RCT_EXPORT_METHOD(navigateiOSSetting){

  NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];

   if([[UIApplication sharedApplication] canOpenURL:url]) {
       [[UIApplication sharedApplication] openURL:url];
   }
   
}

RCT_EXPORT_METHOD(getLocationAuthorizationStatus
                :(RCTPromiseResolveBlock)resolve    
                :(RCTPromiseRejectBlock)reject) {
 CLAuthorizationStatus status =  [CLLocationManager authorizationStatus];
 resolve(@(status));
  
}

RCT_EXPORT_METHOD(getSimCountryIso:(RCTPromiseResolveBlock)resolve
                  :(RCTPromiseRejectBlock)reject){
    CTTelephonyNetworkInfo *netInfo = [[CTTelephonyNetworkInfo alloc]init];
    CTCarrier *carrier = [netInfo subscriberCellularProvider];
    NSString *code = [carrier isoCountryCode];
    resolve(code);
}


RCT_EXPORT_METHOD(getTimeZone:(RCTPromiseResolveBlock)resolve
                  :(RCTPromiseRejectBlock)reject){
 //获取当地的时区
  NSString *tzStr;
  [NSTimeZone resetSystemTimeZone]; // 重置手机系统的时区
  NSInteger offset = [NSTimeZone localTimeZone].secondsFromGMT;//获取距离0时区偏差的时间
  offset = offset/3600;
  if (offset >0) {
    tzStr = [NSString stringWithFormat:@"GMT+%ld:00", (long)offset];
  } else {
    tzStr = [NSString stringWithFormat:@"GMT-%ld:00", (long)offset];
  }
    resolve(tzStr);
}

// 判断是否安装app
RCT_EXPORT_METHOD(appIsInstalled:(NSString *)appID
                  :(RCTPromiseResolveBlock)resolve
                  :(RCTPromiseRejectBlock)reject){
    NSURL *appUrl = [NSURL URLWithString:appID];
    dispatch_async(dispatch_get_main_queue(), ^{
        BOOL isCanOpen = [[UIApplication sharedApplication] canOpenURL:appUrl];
        if (isCanOpen) {
            resolve(@(YES));
        }else{
            resolve(@(NO));
        }
    });
   
}

// 打开一个app
RCT_EXPORT_METHOD(openApp:(NSString *)appID
                  :(RCTPromiseResolveBlock)resolve
                  :(RCTPromiseRejectBlock)reject){
    
    NSString *urlStr = [[NSString stringWithFormat:@"%@", appID] stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSURL *jumpURL = [NSURL URLWithString:urlStr];
    
    dispatch_async(dispatch_get_main_queue(), ^{
        BOOL isCanOpen = [[UIApplication sharedApplication] canOpenURL:jumpURL];
    
        if (isCanOpen) {
            [[UIApplication sharedApplication] openURL:jumpURL];
        }else{
            resolve(@(NO));
        }
    });
   
}

 


@end
