package com.networkspeed;

import android.net.TrafficStats;
import android.util.Log;
import android.content.Context;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Locale;

/**
 * This plugin utilizes the Android TrafficStats class to get the network speed.
 */
public class NetworkSpeedPlugin extends CordovaPlugin {
    private static final String GET_NETWORK_SPEED = "getNetworkSpeed";

    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;

    private long lastDownLoadBytes = 0;
    private long lastUpLoadBytes = 0;
    private long lastDownLoadBytesUid = 0;
    private long lastUpLoadBytesUid = 0;

    private long lastDownLoadTimeStamp = 0;
    private long lastUpLoadTimeStamp = 0;
    private long lastUpLoadTimeStampUid = 0;
    private long lastDownLoadTimeStampUid = 0;

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        Log.e("Network Plugin Data", mLastRxBytes + "" + mLastTxBytes + "" + mLastTime);
    }

    /**
     * Executes the request.
     * <p>
     * This method is called from the WebView thread. To do a non-trivial
     * amount of work, use:
     * cordova.getThreadPool().execute(runnable);
     * <p>
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments in JSON form.
     * @param callbackContext The callback context used when calling back into
     *                        JavaScript.
     * @return Whether the action was valid.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals(GET_NETWORK_SPEED)) {
            this.getNetworkSpeed(callbackContext);
            return true;
        }
        return false;
    }

     // 获取手机所有接收流量
     public long getTotalRxBytes() {
        long nowTimeStamp = System.currentTimeMillis();
        long nowTotalRxBytes = TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
        long speed = ((nowTotalRxBytes - lastDownLoadBytes) * 1000 / (nowTimeStamp - lastDownLoadTimeStamp));//毫秒转换
        lastDownLoadTimeStamp = nowTimeStamp;
        lastDownLoadBytes = nowTotalRxBytes;
        return speed;
    }

    // 获取手机指定进程的接收流量
    public long getTotalRxBytesByUid(int uid) {
        long nowTimeStamp = System.currentTimeMillis();
        long nowTotalRxBytes = TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getUidRxBytes(uid) / 1024);//转为KB
        long speed = ((nowTotalRxBytes - lastDownLoadBytesUid) * 1000 / (nowTimeStamp - lastDownLoadTimeStampUid));//毫秒转换
        lastDownLoadTimeStampUid = nowTimeStamp;
        lastDownLoadBytesUid = nowTotalRxBytes;
        return speed;

    }

    // 获取指定进程的发送流量
    public long getTotalTxBytesByUid(int uid) {
        long nowTimeStamp = System.currentTimeMillis();
        long nowTotalTxBytes = TrafficStats.getUidTxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getUidTxBytes(uid) / 1024);//转为KB
        long speed = ((nowTotalTxBytes - lastUpLoadBytesUid) * 1000 / (nowTimeStamp - lastUpLoadTimeStampUid));//毫秒转换
        lastUpLoadTimeStampUid = nowTimeStamp;
        lastUpLoadBytesUid = nowTotalTxBytes;
        return speed;
    }

    // 获取手机所有的发送流量
    public long getTotalTxBytes() {
        long nowTimeStamp = System.currentTimeMillis();
        long nowTotalTxBytes = TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalTxBytes() / 1024);//转为KB
        long speed = ((nowTotalTxBytes - lastUpLoadBytes) * 1000 / (nowTimeStamp - lastUpLoadTimeStamp));//毫秒转换
        lastUpLoadTimeStamp = nowTimeStamp;
        lastUpLoadBytes = nowTotalTxBytes;
        return speed;
    }

    /**
     * Gets the network speed.
     *
     * @param callbackContext The callback context used when calling back into
     *                        JavaScript.
     * @return The network speed.
     */
    private void getNetworkSpeed(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        int uid = context.getApplicationInfo().uid;
        try {
            long downLoadSpeed = getTotalRxBytes();
            long upLoadSpeed = getTotalTxBytes();
            long downLoadSpeedUid = getTotalRxBytesByUid(uid);
            long upLoadSpeedUid = getTotalTxBytesByUid(uid);
            JSONObject result = new JSONObject();
            result.put("downLoadSpeed", String.valueOf(downLoadSpeed));
            result.put("downLoadSpeedUid", String.valueOf(downLoadSpeedUid));
            result.put("upLoadSpeed", String.valueOf(upLoadSpeed));
            result.put("upLoadSpeedUid", String.valueOf(upLoadSpeedUid));
            callbackContext.success(result);
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
    }
}
