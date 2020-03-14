package test.threadlocal;


import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class DirtyData {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static void unsafeAdd(List list, Object o){
        list.add(o);
    }

    public static <K,V> HashMap<K,V> HasshMap(){
        return new HashMap<K,V>();
    }

    static void noIntendTest(){
        System.out.println("da");
    }


    public static void main(String[] args) {
        new Thread(DirtyData::noIntendTest).start();

        /*Object[] objects = new Long[1];
        objects[0] = "dsaf";
        System.out.println(objects.length);
        System.out.println(objects[0]);*/

        AtomicLong atomicLong = new AtomicLong();

        List<Long> a = new ArrayList();
        //a.add("d");
        unsafeAdd(a,new Integer(1));
        System.out.println(a.get(0));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(int i=0;i<2;i++){
            MyThread myThread = new MyThread();
            executorService.execute(myThread);
        }
    }

    public static class MyThread extends Thread{
        private static boolean flag = true;

        public void run(){
            if(flag){
                threadLocal.set(this.getName()+". session info");
                flag = false;
            }
            System.out.println(this.getName()+" thread is:"+threadLocal.get());
        }
    }
}
