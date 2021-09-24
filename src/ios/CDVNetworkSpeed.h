#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVInvokedUrlCommand.h>
#import "NSObject+CheckNetWorkBytes.h"

@interface CDVNetwordSpeed : CDVPlugin 

- (void)getNetworkSpeed:(CDVInvokedUrlCommand*)command;

@end