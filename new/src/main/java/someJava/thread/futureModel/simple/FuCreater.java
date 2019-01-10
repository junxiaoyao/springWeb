package someJava.thread.futureModel.simple;

public class FuCreater implements SuperInter {
    private String string;
    private String result;
    public FuCreater(String string) {
        this.string = string;
        try {
            System.out.println("睡眠5秒,传入参数："+string);
            Thread.sleep(5000);
            System.out.println("睡眠5秒结束");
        }catch (Exception e){
            e.printStackTrace();
        }
        this.result="Future生产者返回的结果";
    }

    @Override
    public String getResult() {
        return this.result;
    }
}
