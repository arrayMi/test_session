package com.gupaoedu.user.dal.persistence;

import com.gupaoedu.user.dal.entity.User;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public interface UserMapper {

    /**
     *
     * @return
     */
    User getUserByUserName(String userName);

}
