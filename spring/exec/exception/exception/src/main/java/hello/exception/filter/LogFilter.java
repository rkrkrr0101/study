package hello.exception.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("dofilter 동작");
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String requestURI = httpReq.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("Req{}:{}:{}", uuid, request.getDispatcherType(), requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("res{}:{}:{}", uuid, request.getDispatcherType(), requestURI);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log필터 init");
    }

    @Override
    public void destroy() {
        log.info("log필터 destroy");
    }
}
