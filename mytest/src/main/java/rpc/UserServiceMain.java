package rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServiceMain {
    public static void main(String[] args) {
        /*UserServiceInvocationHandler userServiceInvocationHandler = new UserServiceInvocationHandler();
        UserService proxy = (UserService) userServiceInvocationHandler.bind(new UserServiceImpl());
        proxy.sayUser(" this is main ");*/

        try {
            Class<?> clz = Class.forName("rpc.Foo");
            Object o = clz.newInstance();
            Method m = clz.getMethod("foo",String.class);
            for (int i = 0; i < 17; i++) {
                m.invoke(o,Integer.toString(i));
            }
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
