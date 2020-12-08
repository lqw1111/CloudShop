package com.bjsxt.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class RateLimitFilter extends ZuulFilter {

    //创建令牌桶
    //RateLimiter.create(1); 1是每秒生成令牌的数量，数值越大，代表处理请求量越多
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 限流器的优先级应为最高
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    /**
     * 是否开启过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤的逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //是否能从令牌桶中获取到令牌
        if (!RATE_LIMITER.tryAcquire()) {
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(429); //表示请求超时
            System.out.println("限流");
        }
        //能拿到令牌 放行
        return null;
    }
}
