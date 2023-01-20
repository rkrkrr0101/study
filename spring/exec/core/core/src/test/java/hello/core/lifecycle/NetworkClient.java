package hello.core.lifecycle;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자호출,url = " + url);


    }

    public void setUrl(String url) {
        this.url = url;
    }
    //서비스시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }
    public void call(String message){
        System.out.println("call = " + url+" message="+message);
    }

    //서비스종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init(){
        connect();
        call("초기화 메시지");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }
}
