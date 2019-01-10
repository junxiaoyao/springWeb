package someJava.thread.futureModel.simple;

public class FutureClient {
    public SuperInter submit(String str){
        FuUser fuUser=new FuUser();
        new Thread(new Runnable() {
            @Override
            public void run() {
              FuCreater fuCreater=new FuCreater(str);
                fuUser.setFuCreater(fuCreater);
            }
        }).start();
        return fuUser;
    }
}
