package someJava.deSignModels.proxy;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 12:17
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        CglibProxy<Testda> testdaCglibProxy=new CglibProxy();
        Testda testda= testdaCglibProxy.getInstance(new Testda());
        testda.say();
        JdkProxy<ISay> jdkProxy=new JdkProxy<>(new ImpSay());
        jdkProxy.getProxy().say();
        jdkProxy.getProxy().cry();
    }
}
