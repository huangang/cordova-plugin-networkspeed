
#import "CDVNetworkSpeed.h"
#import "CDVPing.h"

@implementation CDVNetworkSpeed

- (void)getNetworkSpeed:(CDVInvokedUrlCommand *)command
{

    CDVPluginResult* pluginResult = nil;
   
    NSDictionary *netWorkSpeed = [NSObject getNetworkSpeed]; //获取当前秒流量
    NSLog(@"下载速度%@",[netWorkSpeed valueForKey: @"downLoadSpeed"]);
    NSLog(@"上传速度%@",[netWorkSpeed valueForKey: @"upLoadSpeed"]);

    pluginResult =  [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:netWorkSpeed];


    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
@end
