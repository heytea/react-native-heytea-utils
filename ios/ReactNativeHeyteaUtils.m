#import "ReactNativeHeyteaUtils.h"

@implementation ReactNativeHeyteaUtils

RCT_EXPORT_MODULE(ReactNativeHeyteaUtils);

RCT_EXPORT_METHOD(navigateiOSSetting){
  NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
   if([[UIApplication sharedApplication] canOpenURL:url]) {
       [[UIApplication sharedApplication] openURL:url];
   }
}

@end
