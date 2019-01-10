package someJava.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
    public static void main(String[] args) {
        //可以缓存的线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            final int temp=i;
            newCachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    System.out.println(Thread.currentThread().getId()+",i"+temp);
                }
            });
        }
    }
}
