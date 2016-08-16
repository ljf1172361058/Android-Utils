package com.softbrain.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
/**
 * 跟网络相关的工具类
 * 
 * 获取网络信息需要在AndroidManifest.xml文件中加入相应的权限。 
 * <uses-mission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * @author lzh
 *
 */
public class NetUtils
{
	private NetUtils()
	{
		/* 不能被实例化  */
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	
	/**
	 * 判断网络连接状态并提示
	 */
	public static void networkState(Context context) {
		if (NetUtils.isMobileConnected(context)) {
			ToastUtils.showLong(context, "当前为2G/3G/4G网络,请注意您的流量哦");
		} else if (NetUtils.isWifiConnected(context)) {
			ToastUtils.showLong(context, "当前为WiFi连接,不用心疼您的流量哦");
		} else {
			ToastUtils.showLong(context, "当前无网络连接");
		}
	}
	/**
	 * 判断网络是否连接(包含MOBILE网络和WIFI)
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
	        if (mNetworkInfo != null) {
	            return mNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	/**
	 * 判断MOBILE网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				 if(mMobileNetworkInfo.getState() == State.CONNECTED || mMobileNetworkInfo.getState() == State.CONNECTING){  
				   return mMobileNetworkInfo.isAvailable();
				 	}
				}
			}
		return false;
	}
	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifiConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
	                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
	        if (mWiFiNetworkInfo != null) { 
	        	if(mWiFiNetworkInfo.getState() == State.CONNECTED || mWiFiNetworkInfo.getState() == State.CONNECTING){  
	        		return mWiFiNetworkInfo.isAvailable();  
	        	}
	        }  
	    }  
	    return false;  
	}
	/**
	 *获取当前网络连接的类型信息
	 */
	public static int getConnectedType(Context context) {  
    		if (context != null) {  
        		ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
                	.getSystemService(Context.CONNECTIVITY_SERVICE);  
       	 		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
        	if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {  
           		 return mNetworkInfo.getType();  
      		  	}  
   		 }  
   			 return -1;  
	}
	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity){
		 Intent intent = null;  
         /** 
          * 判断手机系统的版本！如果API大于10 就是3.0+ 
          * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同 
          */  
         if (android.os.Build.VERSION.SDK_INT > 10) {  
        	//跳转到系统WIFI设置界面(安卓4.4.2下测试)
             intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
         } else {  
             intent = new Intent();  
             ComponentName component = new ComponentName(  
                     "com.android.settings",  
                     "com.android.settings.WirelessSettings");  
             intent.setComponent(component);  
             intent.setAction("android.intent.action.VIEW");  
         }  
         activity.startActivity(intent);  
	}
}
