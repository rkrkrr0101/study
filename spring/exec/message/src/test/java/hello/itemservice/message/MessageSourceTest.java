package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {

        String hello = ms.getMessage("hello", null, null);
        Assertions.assertThat(hello).isEqualTo("ㅎㅇ");
    }

    @Test
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);

    }

    @Test
    void defaultMessageCode() {
        String message = ms.getMessage("no_code", null, "기본메시지", null);
        Assertions.assertThat(message).isEqualTo("기본메시지");

    }

    @Test
    void argsMessageCode() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, "기본메시지", null);
        Assertions.assertThat(message).isEqualTo("ㅎㅇSpring");

    }

    @Test
    void defaultLang() {

        Assertions.assertThat(ms.getMessage("hello", null, null)).isEqualTo("ㅎㅇ");
        Assertions.assertThat(ms.getMessage("hello", null, Locale.CHINA)).isEqualTo("ㅎㅇ");
        Assertions.assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("gd");
    }
}
