package someJava.designModel;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 12:07
 * @Description:
 */
public class CglibProxy<E> implements MethodInterceptor {
    private E targetObject;

    public E getInstance(E target) {
        this.targetObject = target;  //给业务对象赋值
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return (E)enhancer.create();
    }

    // 实现回调方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("-------------cglib say-------------");
        Object result = methodProxy.invoke(targetObject, args); //调用业务类（父类中）的方法
        System.out.println("-------------cglib say-------------");
        return result;
    }
}
