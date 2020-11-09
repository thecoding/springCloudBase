package com.springcloud.base.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * zuulFilter + guava限流
 * @author Mirko
 * @Description
 * @createTime 2020年11月02日 00:05:00
 */
@Component
public class LimitFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 当前过滤器是否执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();

        if(RATE_LIMITER.tryAcquire()){
            System.out.println("正常进入...");
        }else{
            System.out.println("被限流了...");
            currentContext.setSendZuulResponse(false);  //false  不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
            currentContext.setResponseBody("error...rate limit");
            currentContext.set("sendForwardFilter.ran", true);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
