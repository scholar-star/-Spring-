package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // 1개만 만들어서 저장

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    // 싱글톤 패턴의 기본 골조(필수)

    public void logic() {
        System.out.println("logic");
    }
}
