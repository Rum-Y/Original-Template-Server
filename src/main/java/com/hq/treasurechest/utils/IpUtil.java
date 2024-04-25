package com.hq.treasurechest.utils;


import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    private final static String localIp = "127.0.0.1";

    private static Searcher searcher = null;

    /*
    在服务启动时加载 ip2region.db 到内存中
    解决打包jar后找不到 ip2region.db 的问题
     */
    static {
        try {
            // 从资源目录下加载ip2region.xdb文件
            InputStream ris = IpUtil.class.getResourceAsStream("/ip2region/ip2region.xdb");
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            searcher = Searcher.newWithBuffer(dbBinStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址
     *  如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *  取X-Forwarded-For中第一个非unknown的有效IP字符串就是真实IP
     */
    public static String getIp(HttpServletRequest request) {
        String ipAddress;
        try {
            // 以下两个获取在k8s中，将真实的客户端IP，放到了x-Original-Forwarded-For。而将WAF的回源地址放到了 x-Forwarded-For了。
            ipAddress = request.getHeader("X-Original-Forwarded-For");
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("X-Forwarded-For");
            }

            //获取nginx等代理的ip
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("x-forwarded-for");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddress == null || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
            }

            // 2.如果没有转发的ip，则取当前通信的请求端的ip(兼容k8s集群获取ip)
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                // 如果是127.0.0.1，则取本地真实ip
                if (localIp.equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        //e.printStackTrace();
                    }
                }
            }

            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            System.out.println("解析请求ip失败");
            ipAddress = "";
        }
        return "0:0:0:0:0:0:0:1".equals(ipAddress) ? localIp : ipAddress;
    }

    /**
     * 获取访问设备
     */
    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

    /**
     * 根据ip获取 城市信息
     */
    public static String getCityInfo(String ipAddress) {
        String cityInfo = null;
        try {
            return searcher.search(ipAddress);
        } catch (Exception e) {
            //logger.error("搜索:{} 失败: {}",ipAddress, e);
        }
        return null;
    }

    /**
     * 根据ip2region解析ip地址
     * @param ip ip地址
     * @return 解析后的ip地址信息
     */
    public static String getIp2region(String ip)  {

        if(searcher == null){
            //logger.error("Error: DbSearcher is null");
            return null;
        }

        try {
            String ipInfo = searcher.search(ip);
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            return ipInfo;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取IP地址
     *
     * @return 本地IP地址
     */
    public static String getHostIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            // e.printStackTrace();
        }
        return localIp;
    }


    /**
     * 获取主机名
     *
     * @return 本地主机名
     */
    public static String getHostName(){
        try{
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            // e.printStackTrace();
        }
        return "未知";
    }

    // 测试ip地址解析
    public static void main(String[] args) {
        //String cityInfo = IpUtil.getCityInfo("202.192.72.216");
        String ip2region = IpUtil.getIp2region("202.192.72.216");
    }


}
