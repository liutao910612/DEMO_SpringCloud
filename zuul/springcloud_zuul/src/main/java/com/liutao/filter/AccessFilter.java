package com.liutao.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 * 在请求被路由之前，检查请求中是否带有accessToken，如果有则被路由，如果没有则返回401
 *
 * @author LIUTAO
 * @version 2017/4/19
 * @see
 * @since
 */
public class AccessFilter extends ZuulFilter {

    /**
     * 返回一个字符代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型。
     * pre:可以在请求被路由之前调用。
     * routing:在路由请求时候被调用。
     * post:在routing和error过滤器之后被调用。
     * error:处理请求时发生错误被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int定义过滤器执行的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否执行，从而该方法相当于是一个过滤器的开关。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {

            //设置不过滤该请求。并且返回错误码
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
