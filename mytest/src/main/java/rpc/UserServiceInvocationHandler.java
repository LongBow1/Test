package rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceInvocationHandler implements InvocationHandler {

    private Object object;
    public Object bind(Object o){
        this.object = o;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this::invoke);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("  this jdk dynamic proxy  ");
        System.out.println(" ready to say hello ");
        Object result = method.invoke(object, args);
        System.out.println(" hello said ");
        return result;
    }
}
