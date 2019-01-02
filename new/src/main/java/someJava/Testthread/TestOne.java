package someJava.Testthread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

class MythreadCreat implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private volatile boolean create=true;
    public MythreadCreat(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("----生产者开始生产----");
            int index=0;
            while (create){
                Thread.sleep(100);
                boolean off= blockingQueue.offer(++index+"",1, TimeUnit.SECONDS);
                if (off){
                    System.out.println("生产了："+index);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("----生产者结束生产了----");
        }

    }
    public void stopCreate(){
        this.create=false;
    }
}
class MythreadUse implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private volatile boolean create=true;
    MythreadUse(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("----消费者开始消费----");
            int index=0;
            Thread.sleep(1000);
            while (create){
                String str= blockingQueue.poll(5, TimeUnit.SECONDS);
                if(str==null) {
                    System.out.println("没有东西");
                    return;
                }
                System.out.println("消费了："+str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("----消费结束了----");
        }

    }
}
public class TestOne {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new LinkedBlockingDeque<>(5);
        MythreadCreat mythreadCreat=new MythreadCreat(blockingQueue);
        MythreadUse mythreadUse=new MythreadUse(blockingQueue);
        new Thread(mythreadCreat).start();
        new Thread(mythreadUse).start();
        try {
            Thread.sleep(20000);
            mythreadCreat.stopCreate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("-----测试完了-------");
        }
    }
}
