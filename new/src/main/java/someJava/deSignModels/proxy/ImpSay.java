package someJava.deSignModels.proxy;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 16:52
 * @Description:
 */
public class ImpSay implements ISay{
    @Override
    public void say() {
        System.out.println("i say hello world");
    }

    @Override
    public void cry() {
        System.out.println("i am crying");
    }
}
