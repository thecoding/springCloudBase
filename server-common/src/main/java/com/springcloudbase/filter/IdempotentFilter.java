package com.springcloudbase.filter;

import com.springcloudbase.annotation.Token;
import com.springcloudbase.exception.BusinessException;
import com.springcloudbase.redis.RedisUtil;
import com.springcloudbase.util.RequestUtil;
import com.springcloudbase.util.SessionUtil;
import com.springcloudbase.vo.BaseUser;
import com.springcloudbase.vo.result.ResponseEnums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Mirko on 2020/4/11.
 */

@Component
public class IdempotentFilter extends AbstractBaseFilter {

    private static String PREFIX = "TOKEN_USER_METHOD_";

    @Autowired
    RedisUtil redisUtil;


    @Override
    public boolean preExecute() {
        Method method = RequestUtil.getAttribute(Method.class);
        Token token = method.getAnnotation(Token.class);
        BaseUser baseUser = SessionUtil.getUser();
        if (token != null) {
            //TODO 类名需要修改
            String str = (String) redisUtil.get(PREFIX + method.getClass().getSimpleName()+ baseUser.getUserId());
            if (StringUtils.isNotBlank(str)) {
                throw new BusinessException(ResponseEnums.INVOKE_TIMES);
            }else{
                redisUtil.set(PREFIX + method.getClass().getSimpleName()+ baseUser.getUserId(),"test", token.timeSecond());
            }
        }
        return true;
    }

    @Override
    public int order() {
        return 3;
    }

    @Override
    public boolean isMustExecute() {
        return false;
    }
}
