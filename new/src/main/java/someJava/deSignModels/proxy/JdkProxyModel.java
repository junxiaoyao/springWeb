package someJava.deSignModels.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 17:55
 * @Description:
 */
public class JdkProxyModel<E> implements InvocationHandler {
    private E e;

    public JdkProxyModel(E e) {
        this.e = e;
    }

    public E getInstanceProxy() {
        return (E) Proxy.newProxyInstance(e.getClass().getClassLoader(), e.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------------------jdkProxy version 2--------------------------");
        Object result=method.invoke(e,args);
        System.out.println("--------------------------jdkProxy version 2--------------------------");
        return result;
    }
}
