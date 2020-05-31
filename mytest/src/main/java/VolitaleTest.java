public class VolitaleTest extends Thread{
    private static volatile boolean flag = false;

    public void run(){
        while (!flag);
        System.out.println("run exit");
    }

    public static void main(String[] args) {
        new VolitaleTest().start();
        try {
            Thread.sleep(5000);
            flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
