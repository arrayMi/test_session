package com.gupaoedu.user;

import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}
