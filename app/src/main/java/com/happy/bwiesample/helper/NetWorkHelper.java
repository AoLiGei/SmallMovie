package com.happy.bwiesample.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.NetworkInterface;
import java.net.SocketException;
import android.annotation.TargetApi;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.net.InetAddress;
import java.util.Enumeration;

/**
 * Created by 红玫瑰 on 2017/12/13.
 */

public class NetWorkHelper {
    /**
     * 网络类型 - 无连接
     */
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;

    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_3G = "eg";
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_WAP = "wap";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

    private Context mContext;
    public NetWorkHelper(Context context){
        this.mContext=context;
    }

    /**
     * Get network type
     *
     * @return 网络状态
     */
    public int getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = (connectivityManager == null) ? null : connectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }


    /**
     * Get network type name
     *
     * @return NetworkTypeName
     */
    public String getNetworkTypeName() {
        ConnectivityManager manager
                = (ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null ||
                (networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }
        ;

        if (networkInfo.isConnected()) {
            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                type = NETWORK_TYPE_WIFI;
            }
            else if ("MOBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = TextUtils.isEmpty(proxyHost)
                        ? (isFastMobileNetwork()
                        ? NETWORK_TYPE_3G
                        : NETWORK_TYPE_2G)
                        : NETWORK_TYPE_WAP;
            }
            else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }


    /**
     * Whether is fast mobile network
     *
     * @return FastMobileNetwork
     */
    private boolean isFastMobileNetwork() {
        TelephonyManager telephonyManager
                = (TelephonyManager) mContext.getSystemService(
                Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return false;
        }

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }


    /**
     * 获取当前网络的状态
     *
     * @return 当前网络的状态。具体类型可参照NetworkInfo.State.CONNECTED、NetworkInfo.State.CONNECTED.DISCONNECTED等字段。当前没有网络连接时返回null
     */
    public NetworkInfo.State getCurrentNetworkState() {
        NetworkInfo networkInfo
                = ((ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null ? networkInfo.getState() : null;
    }


    /**
     * 获取当前网络的类型
     *
     * @return 当前网络的类型。具体类型可参照ConnectivityManager中的TYPE_BLUETOOTH、TYPE_MOBILE、TYPE_WIFI等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    public int getCurrentNetworkType() {
        NetworkInfo networkInfo
                = ((ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null
                ? networkInfo.getType()
                : NETWORK_TYPE_NO_CONNECTION;
    }


    /**
     * 获取当前网络的具体类型
     *
     * @return 当前网络的具体类型。具体类型可参照TelephonyManager中的NETWORK_TYPE_1xRTT、NETWORK_TYPE_CDMA等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    public int getCurrentNetworkSubtype() {
        NetworkInfo networkInfo
                = ((ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null
                ? networkInfo.getSubtype()
                : NETWORK_TYPE_NO_CONNECTION;
    }


    /**
     * 判断当前网络是否已经连接
     *
     * @return 当前网络是否已经连接。false：尚未连接
     */
    public boolean isConnectedByState() {
        return getCurrentNetworkState() == NetworkInfo.State.CONNECTED;
    }


    /**
     * 判断当前网络是否正在连接
     *
     * @return 当前网络是否正在连接
     */
    public boolean isConnectingByState() {
        return getCurrentNetworkState() == NetworkInfo.State.CONNECTING;
    }


    /**
     * 判断当前网络是否已经断开
     *
     * @return 当前网络是否已经断开
     */
    public boolean isDisconnectedByState() {
        return getCurrentNetworkState() ==
                NetworkInfo.State.DISCONNECTED;
    }


    /**
     * 判断当前网络是否正在断开
     *
     * @return 当前网络是否正在断开
     */
    public boolean isDisconnectingByState() {
        return getCurrentNetworkState() ==
                NetworkInfo.State.DISCONNECTING;
    }


    /**
     * 判断当前网络是否已经暂停
     *
     * @return 当前网络是否已经暂停
     */
    public boolean isSuspendedByState() {
        return getCurrentNetworkState() == NetworkInfo.State.SUSPENDED;
    }


    /**
     * 判断当前网络是否处于未知状态中
     *
     * @return 当前网络是否处于未知状态中
     */
    public boolean isUnknownByState() {
        return getCurrentNetworkState() == NetworkInfo.State.UNKNOWN;
    }


    /**
     * 判断当前网络的类型是否是蓝牙
     *
     * @return 当前网络的类型是否是蓝牙。false：当前没有网络连接或者网络类型不是蓝牙
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public boolean isBluetoothByType() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType() ==
                    ConnectivityManager.TYPE_BLUETOOTH;
        }
    }


    /**
     * 判断当前网络的类型是否是虚拟网络
     *
     * @return 当前网络的类型是否是虚拟网络。false：当前没有网络连接或者网络类型不是虚拟网络
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public boolean isDummyByType(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType() ==
                    ConnectivityManager.TYPE_DUMMY;
        }
    }


    /**
     * 判断当前网络的类型是否是ETHERNET
     *
     * @return 当前网络的类型是否是ETHERNET。false：当前没有网络连接或者网络类型不是ETHERNET
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public boolean isEthernetByType(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType() ==
                    ConnectivityManager.TYPE_ETHERNET;
        }
    }


    /**
     * 判断当前网络的类型是否是移动网络
     *
     * @return 当前网络的类型是否是移动网络。false：当前没有网络连接或者网络类型不是移动网络
     */
    public boolean isMobileByType() {
        return getCurrentNetworkType() ==
                ConnectivityManager.TYPE_MOBILE;
    }


    /**
     * 判断当前网络的类型是否是MobileDun
     *
     * @return 当前网络的类型是否是MobileDun。false：当前没有网络连接或者网络类型不是MobileDun
     */
    public boolean isMobileDunByType() {
        return getCurrentNetworkType() ==
                ConnectivityManager.TYPE_MOBILE_DUN;
    }


    /**
     * 判断当前网络的类型是否是MobileHipri
     *
     * @return 当前网络的类型是否是MobileHipri。false：当前没有网络连接或者网络类型不是MobileHipri
     */
    public boolean isMobileHipriByType() {
        return getCurrentNetworkType() ==
                ConnectivityManager.TYPE_MOBILE_HIPRI;
    }


    /**
     * 判断当前网络的类型是否是MobileMms
     *
     * @return 当前网络的类型是否是MobileMms。false：当前没有网络连接或者网络类型不是MobileMms
     */
    public boolean isMobileMmsByType() {
        return getCurrentNetworkType() ==
                ConnectivityManager.TYPE_MOBILE_MMS;
    }


    /**
     * 判断当前网络的类型是否是MobileSupl
     *
     * @return 当前网络的类型是否是MobileSupl。false：当前没有网络连接或者网络类型不是MobileSupl
     */
    public boolean isMobileSuplByType() {
        return getCurrentNetworkType() ==
                ConnectivityManager.TYPE_MOBILE_SUPL;
    }


    /**
     * 判断当前网络的类型是否是Wifi
     *
     * @return 当前网络的类型是否是Wifi。false：当前没有网络连接或者网络类型不是wifi
     */
    public boolean isWifiByType() {
        return getCurrentNetworkType() == ConnectivityManager.TYPE_WIFI;
    }


    /**
     * 判断当前网络的类型是否是Wimax
     *
     * @return 当前网络的类型是否是Wimax。false：当前没有网络连接或者网络类型不是Wimax
     */
    public boolean isWimaxByType() {
        return getCurrentNetworkType() == ConnectivityManager.TYPE_WIMAX;
    }


    /**
     * 判断当前网络的具体类型是否是1XRTT
     *
     * @return false：当前网络的具体类型是否是1XRTT。false：当前没有网络连接或者具体类型不是1XRTT
     */
    public boolean is1XRTTBySubtype(Context context) {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_1xRTT;
    }


    /**
     * 判断当前网络的具体类型是否是CDMA（Either IS95A or IS95B）
     *
     * @return false：当前网络的具体类型是否是CDMA。false：当前没有网络连接或者具体类型不是CDMA
     */
    public boolean isCDMABySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_CDMA;
    }


    /**
     * 判断当前网络的具体类型是否是EDGE
     *
     * @return false：当前网络的具体类型是否是EDGE。false：当前没有网络连接或者具体类型不是EDGE
     */
    public boolean isEDGEBySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_EDGE;
    }


    /**
     * 判断当前网络的具体类型是否是EHRPD
     *
     * @return false：当前网络的具体类型是否是EHRPD。false：当前没有网络连接或者具体类型不是EHRPD
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean isEHRPDBySubtype() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype() ==
                    TelephonyManager.NETWORK_TYPE_EHRPD;
        }
    }


    /**
     * 判断当前网络的具体类型是否是EVDO_0
     *
     * @return false：当前网络的具体类型是否是EVDO_0。false：当前没有网络连接或者具体类型不是EVDO_0
     */
    public boolean isEVDO_0BySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_EVDO_0;
    }


    /**
     * 判断当前网络的具体类型是否是EVDO_A
     *
     * @return false：当前网络的具体类型是否是EVDO_A。false：当前没有网络连接或者具体类型不是EVDO_A
     */
    public boolean isEVDO_ABySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_EVDO_A;
    }


    /**
     * 判断当前网络的具体类型是否是EDGE
     *
     * @return false：当前网络的具体类型是否是EVDO_B。false：当前没有网络连接或者具体类型不是EVDO_B
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean isEVDO_BBySubtype() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype() ==
                    TelephonyManager.NETWORK_TYPE_EVDO_B;
        }
    }


    /**
     * 判断当前网络的具体类型是否是GPRS
     * @return false：当前网络的具体类型是否是GPRS。false：当前没有网络连接或者具体类型不是GPRS
     */
    public boolean isGPRSBySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_GPRS;
    }


    /**
     * 判断当前网络的具体类型是否是HSDPA
     * @return false：当前网络的具体类型是否是HSDPA。false：当前没有网络连接或者具体类型不是HSDPA
     */
    public boolean isHSDPABySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_HSDPA;
    }


    /**
     * 判断当前网络的具体类型是否是HSPA
     * @return false：当前网络的具体类型是否是HSPA。false：当前没有网络连接或者具体类型不是HSPA
     */
    public boolean isHSPABySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_HSPA;
    }


    /**
     * 判断当前网络的具体类型是否是HSPAP
     * @return false：当前网络的具体类型是否是HSPAP。false：当前没有网络连接或者具体类型不是HSPAP
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public boolean isHSPAPBySubtype() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype() ==
                    TelephonyManager.NETWORK_TYPE_HSPAP;
        }
    }


    /**
     * 判断当前网络的具体类型是否是HSUPA
     * @return false：当前网络的具体类型是否是HSUPA。false：当前没有网络连接或者具体类型不是HSUPA
     */
    public boolean isHSUPABySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_HSUPA;
    }


    /**
     * 判断当前网络的具体类型是否是IDEN
     * @return false：当前网络的具体类型是否是IDEN。false：当前没有网络连接或者具体类型不是IDEN
     */
    public boolean isIDENBySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_IDEN;
    }


    /**
     * 判断当前网络的具体类型是否是LTE
     * @return false：当前网络的具体类型是否是LTE。false：当前没有网络连接或者具体类型不是LTE
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean isLTEBySubtype() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype() ==
                    TelephonyManager.NETWORK_TYPE_LTE;
        }
    }


    /**
     * 判断当前网络的具体类型是否是UMTS
     * @return false：当前网络的具体类型是否是UMTS。false：当前没有网络连接或者具体类型不是UMTS
     */
    public boolean isUMTSBySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_UMTS;
    }


    /**
     * 判断当前网络的具体类型是否是UNKNOWN
     * @return false：当前网络的具体类型是否是UNKNOWN。false：当前没有网络连接或者具体类型不是UNKNOWN
     */
    public boolean isUNKNOWNBySubtype() {
        return getCurrentNetworkSubtype() ==
                TelephonyManager.NETWORK_TYPE_UNKNOWN;
    }


    /**
     * 判断当前网络是否是中国移动2G网络

     * @return false：不是中国移动2G网络或者当前没有网络连接
     */
    public boolean isChinaMobile2G() {
        return isEDGEBySubtype();
    }


    /**
     * 判断当前网络是否是中国联通2G网络
     * @return false：不是中国联通2G网络或者当前没有网络连接
     */
    public boolean isChinaUnicom2G() {
        return isGPRSBySubtype();
    }


    /**
     * 判断当前网络是否是中国联通3G网络
     * @return false：不是中国联通3G网络或者当前没有网络连接
     */
    public boolean isChinaUnicom3G() {
        return isHSDPABySubtype() || isUMTSBySubtype();
    }


    /**
     * 判断当前网络是否是中国电信2G网络
     * @return false：不是中国电信2G网络或者当前没有网络连接
     */
    public boolean isChinaTelecom2G() {
        return isCDMABySubtype();
    }


    /**
     * 判断当前网络是否是中国电信3G网络
     * @return false：不是中国电信3G网络或者当前没有网络连接
     */
    public boolean isChinaTelecom3G() {
        return isEVDO_0BySubtype() || isEVDO_ABySubtype() ||
                isEVDO_BBySubtype();
    }


    /**
     * 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
     * @return 取值为WifiManager中的WIFI_STATE_ENABLED、WIFI_STATE_ENABLING、WIFI_STATE_DISABLED、WIFI_STATE_DISABLING、WIFI_STATE_UNKNOWN之一
     * @throws Exception 没有找到wifi设备
     */
    public int getWifiState() throws Exception {
        WifiManager wifiManager = ((WifiManager) mContext.getSystemService(
                Context.WIFI_SERVICE));
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        else {
            throw new Exception("wifi device not found!");
        }
    }


    /**
     * 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
     * @return true：打开；false：关闭
     */
    public boolean isWifiOpen() throws Exception {
        int wifiState = getWifiState();
        return wifiState == WifiManager.WIFI_STATE_ENABLED ||
                wifiState == WifiManager.WIFI_STATE_ENABLING
                ? true
                : false;
    }


    /**
     * 设置Wifi，需要CHANGE_WIFI_STATE权限
     * @param enable wifi状态
     * @return 设置是否成功
     */
    public boolean setWifi(boolean enable)
            throws Exception {
        //如果当前wifi的状态和要设置的状态不一样
        if (isWifiOpen() != enable) {
            ((WifiManager) mContext.getSystemService(
                    Context.WIFI_SERVICE)).setWifiEnabled(enable);
        }
        return true;
    }


    /**
     * 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
     * @return true：打开；false：关闭
     */
    public boolean isMobileNetworkOpen() {
        return (((ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE)).isConnected();
    }


    /**
     * 获取本机IP地址
     *
     * @return null：没有网络连接
     */
    public String getIpAddress() {
        try {
            NetworkInterface nerworkInterface;
            InetAddress inetAddress;
            for (Enumeration<NetworkInterface> en
                 = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                nerworkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr
                     = nerworkInterface.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
