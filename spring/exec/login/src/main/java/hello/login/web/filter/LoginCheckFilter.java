package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        String requestURI = httpReq.getRequestURI();

        try {
            log.info("인증체크필터시작", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증체크로직실행 {}", requestURI);
                HttpSession session = httpReq.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증사용자요청{}", requestURI);
                    httpRes.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("인증체크필터종료{}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String reqURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, reqURI);
    }
}
