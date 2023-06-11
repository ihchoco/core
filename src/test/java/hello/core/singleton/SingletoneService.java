package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletoneService {

    //클래스 로딩과 동시에 객체를 생성
    private static final SingletoneService instance = new SingletoneService();

    //클래스를 생성하지 않고 클래스명.getInstance를 통해서 사용 가능
    public static SingletoneService getInstance(){
        return instance;
    }

    //외부에서 SingletonService를 만들지 못하게 하는 작업(외부에서 만들려고 하면 컴파일 에러)
    private SingletoneService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }


}
