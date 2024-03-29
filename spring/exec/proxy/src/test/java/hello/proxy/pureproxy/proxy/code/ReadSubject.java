package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadSubject implements Subject{
    @Override
    public String operation() {
        log.info("실제객체호출");
        sleep(1000);
        return "data";
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
