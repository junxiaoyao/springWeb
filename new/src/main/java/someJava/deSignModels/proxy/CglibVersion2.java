package someJava.deSignModels.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: jxy
 * @Date: 2019/3/7 18:07
 * @Description:
 */
public class CglibVersion2<E> implements MethodInterceptor {
    private E e;

    public CglibVersion2(E e) {
        this.e = e;

    }

    public E getIntance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(e.getClass());
        enhancer.setCallback(this);
        return (E)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------------------------CglibProxy version 2--------------------------");
        Object result = methodProxy.invoke(e, objects);
        System.out.println("--------------------------CglibProxy version 2--------------------------");
        return result;
    }
}
