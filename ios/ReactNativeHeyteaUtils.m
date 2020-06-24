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

RCT_EXPORT_METHOD(getLocationAuthorizationStatus:(RCTResponseSenderBlock)callback) {
 CLAuthorizationStatus status =  [CLLocationManager authorizationStatus];
 callback(@[@(status)]);
  
}

RCT_EXPORT_METHOD(getSimCountryIso:(RCTPromiseResolveBlock)resolve
                  :(RCTPromiseRejectBlock)reject){
    CTTelephonyNetworkInfo *netInfo = [[CTTelephonyNetworkInfo alloc]init];
    CTCarrier *carrier = [netInfo subscriberCellularProvider];
    NSString *code = [carrier isoCountryCode];
    resolve(code);
}


@end
