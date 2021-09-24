#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVInvokedUrlCommand.h>
#import "NSObject+CheckNetWorkBytes.h"

@interface CDVNetworkSpeed : CDVPlugin 

- (void)getNetworkSpeed:(CDVInvokedUrlCommand*)command;
- (void)startListenNetworkSpeed:(CDVInvokedUrlCommand*)command;
- (void)stopListenNetworkSpeed:(CDVInvokedUrlCommand*)command;

@end
