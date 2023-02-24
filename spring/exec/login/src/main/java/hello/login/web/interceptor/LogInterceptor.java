package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute("logId", uuid);
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;


        }

        log.info("req{}:{}:{}", uuid, requestURI, handler);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("modelandview:{}", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String logId = (String) request.getAttribute("logId");
        String requestURI = request.getRequestURI();

        log.info("res {}:{}:{}", logId, requestURI, handler);
        if (ex != null) {
            log.error("after error", ex);
        }

    }
}
