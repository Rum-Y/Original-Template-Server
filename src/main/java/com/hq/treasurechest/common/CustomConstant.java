package com.hq.treasurechest.common;

import com.hq.treasurechest.utils.GenerateUtil;

public final class CustomConstant {
    public static final String SQL_HOSTNAME_KEY =  "SqlHostNameKey";

    public static String DB_URL = "jdbc:mysql://";

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final int SUCCESS_CODE = 2000;

    public static final int FAILED_CODE = 4000;

    public static final String CHECK_CODE_KEY = "check_code";

    public static final int SQL_CONNECT_ERROR = 401; // 数据库连接错误

    // 正式环境中应将SECRET_KET保存在更安全的地方
    public static final String SECRET_KEY = GenerateUtil.getSecretKey();

}
