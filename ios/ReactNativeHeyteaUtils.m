#import "ReactNativeHeyteaUtils.h"
#import <CoreLocation/CoreLocation.h>

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


@end
