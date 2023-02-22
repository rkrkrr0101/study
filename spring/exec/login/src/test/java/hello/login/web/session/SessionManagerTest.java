package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        Member member = new Member();
        MockHttpServletResponse resp = new MockHttpServletResponse();
        sessionManager.createSession(member, resp);

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setCookies(resp.getCookies());

        Object result = sessionManager.getSession(req);

        Assertions.assertThat(result).isEqualTo(member);

        sessionManager.expire(req);
        Object expiredResult = sessionManager.getSession(req);
        Assertions.assertThat(expiredResult).isNull();

    }


}