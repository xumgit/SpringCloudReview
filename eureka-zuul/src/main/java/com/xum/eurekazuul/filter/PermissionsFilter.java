package com.xum.eurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class PermissionsFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PermissionsFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
        /*RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("isSuccess");*/
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        LOG.info(String.format("%s AccessNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));
        String name = request.getParameter("name");// 获取请求的参数
        if(null != name && "xum".equalsIgnoreCase(name)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"name is not correct!\"}");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
