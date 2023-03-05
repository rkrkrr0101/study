package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {
    @Test
    void stringToInteger(){
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer res=converter.convert("10");
        Assertions.assertThat(res).isEqualTo(10);
    }
    @Test
    void IntegerToString(){
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String res=converter.convert(10);
        Assertions.assertThat(res).isEqualTo("10");
    }
    @Test
    void stringToIpPort(){
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8000);
        String res = converter.convert(ipPort);
        Assertions.assertThat(res).isEqualTo("127.0.0.1:8000");
    }
    @Test
    void IpPortToString(){
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8000);
        IpPort res = converter.convert("127.0.0.1:8000");
        Assertions.assertThat(res).isEqualTo(new IpPort("127.0.0.1", 8000));
    }
}
