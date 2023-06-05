package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.ReadSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest(){
        ReadSubject readSubject = new ReadSubject();
        ProxyPatternClient Client = new ProxyPatternClient(readSubject);
        Client.execute();
        Client.execute();
        Client.execute();


    }
    @Test
    void cacheProxyTest(){

        ReadSubject readSubject = new ReadSubject();
        CacheProxy cacheProxy=new CacheProxy(readSubject);
        ProxyPatternClient Client = new ProxyPatternClient(cacheProxy);
        Client.execute();
        Client.execute();
        Client.execute();


    }
}
