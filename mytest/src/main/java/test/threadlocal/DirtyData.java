package test.threadlocal;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirtyData {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
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
