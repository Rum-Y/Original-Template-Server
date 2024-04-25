package com.hq.treasurechest.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserAccessService {
    void enterLoginMsg(HttpServletRequest request, String userId);
}
