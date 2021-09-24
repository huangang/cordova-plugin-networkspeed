var exec = require('cordova/exec');

var PLUGIN_NAME = 'networkspeed';

var networkspeed = {
    getNetworkSpeed: function(success, error) {
        exec(success, error, PLUGIN_NAME, "getNetworkSpeed", []);
    },
    startListenNetworkSpeed: function(success, error) {
        exec(success, error, PLUGIN_NAME, "startListenNetworkSpeed", []);
    },
    stopListenNetworkSpeed: function(success, error) {
        exec(success, error, PLUGIN_NAME, "stopListenNetworkSpeed", []);
    }
};

module.exports = networkspeed;
