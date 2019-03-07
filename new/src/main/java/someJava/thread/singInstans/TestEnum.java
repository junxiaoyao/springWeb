package someJava.thread.singInstans;

public class TestEnum {
    public static void main(String[] args) {
        System.out.println(HttpEnum.HTTP_200.getCode());
        System.out.println(HttpEnum.HTTP_500.getMsg());
    }
}
