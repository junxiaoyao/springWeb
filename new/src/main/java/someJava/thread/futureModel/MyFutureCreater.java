package someJava.thread.futureModel;

public class MyFutureCreater<V, S> implements MyFutureInterface<V, S> {
    private V result;
    private S s;

    public MyFutureCreater(S s) {
        this.s = s;
        this.run(s);
    }

    @Override
    public V getData(S s) {
        return null;
    }

    public void run(S s) {
        System.out.println("run 方法被执行！睡眠3秒");
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("run 方法执行完毕！");
    }

}
