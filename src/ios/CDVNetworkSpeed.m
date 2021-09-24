
#import "CDVNetworkSpeed.h"
#import "CDVPing.h"

@implementation CDVNetworkSpeed

 NSTimer *timer;

- (void)getNetworkSpeed:(CDVInvokedUrlCommand *)command
{

    CDVPluginResult* pluginResult = nil;
   
    NSDictionary *netWorkSpeed = [NSObject getNetworkSpeed]; //获取当前秒流量
    NSLog(@"下载速度%@",[netWorkSpeed valueForKey: @"downLoadSpeed"]);
    NSLog(@"上传速度%@",[netWorkSpeed valueForKey: @"upLoadSpeed"]);

    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:netWorkSpeed];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)startListenNetworkSpeed:(CDVInvokedUrlCommand *)command
{
  if(timer == nil) {
      timer = [NSTimer scheduledTimerWithTimeInterval:2.0 target:self selector:@selector(getNetworkSpeed) userInfo:nil repeats:true];
      [NSObject initCheck];
      [timer fireDate];
  }
}

- (void)stopListenNetworkSpeed:(CDVInvokedUrlCommand *)command
{
  if(timer) {
      [timer invalidate];
      timer = nil;
  }
}
@end
