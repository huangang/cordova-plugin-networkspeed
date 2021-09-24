
#import <Cordova/CDVViewController.h>
#import "CDVNetworkSpeed.h"

@implementation CDVNetworkSpeed

- (void)getNetworkSpeed:(CDVInvokedUrlCommand *)command
{
    CDVPluginResult* pluginResult = nil;
   
    NSDictionary *netWorkSpeed = [NSObject getNetworkSpeed]; //获取当前秒流量
    [self sendEventWithName:@"onSpeedUpdate" body:netWorkSpeed];
    // NSLog(@"下载速度%@",[netWorkSpeed valueForKey: @"downLoadSpeed"]);
    // NSLog(@"上传速度%@",[netWorkSpeed valueForKey: @"upLoadSpeed"]);

    NSString *RESULT = [NSString stringWithFormat:@"%@ (%@)", "NetWorkSpeed", netWorkSpeed];
    
    [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString: RESULT] callbackId:callbackId];
}