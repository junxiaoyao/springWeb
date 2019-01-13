package someJava.thread.futureModel;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 主线程使用Future异步执行
 */
class MyCall implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "子线程返回";
    }
}
public class MainClass {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();
        System.out.println("主线程开始执行.....");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Future<String> future=executorService.submit(new MyCall());
                try{
                    System.out.println("futrue:"+future.get());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
        System.out.println("----主线程执行后面---");
    }
}
