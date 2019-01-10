package someJava.thread.futureModel.simple;

public class FuClentMain {
    public static void main(String[] args) {
        FutureClient futureClient=new FutureClient();
        System.out.println("主线程开始执行");
        SuperInter result=futureClient.submit("传入乱写的");
        System.out.println("主线程继续开始执行");
        System.out.println("主线程获取Result："+result.getResult());

    }
}
