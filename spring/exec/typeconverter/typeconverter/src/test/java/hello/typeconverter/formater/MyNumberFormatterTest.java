package hello.typeconverter.formater;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        Number res = formatter.parse("1,000", Locale.KOREA);
        Assertions.assertThat(res).isEqualTo(1000L);

    }

    @Test
    void print() {
        String res = formatter.print(1000, Locale.KOREA);
        Assertions.assertThat(res).isEqualTo("1,000");
    }
}