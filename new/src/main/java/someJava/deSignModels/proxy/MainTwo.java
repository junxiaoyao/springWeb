package someJava.deSignModels.proxy;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 18:06
 * @Description:
 */
public class MainTwo {
    public static void main(String[] args) {
        JdkProxyModel<ISay> jdkProxyModel=new JdkProxyModel<>(new ImpSay());
        jdkProxyModel.getInstanceProxy().cry();
        CglibVersion2<ISay> cglibVersion2=new CglibVersion2<>(new ImpSay());
        cglibVersion2.getIntance().say();
    }
}
