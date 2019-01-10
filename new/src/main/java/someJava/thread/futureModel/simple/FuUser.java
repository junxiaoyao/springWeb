package someJava.thread.futureModel.simple;

public class FuUser implements SuperInter {
    private boolean isOk = false;
    private FuCreater fuCreater;

    public synchronized void setFuCreater(FuCreater fuCreater) {
        if (isOk) {
            return;
        }
        this.fuCreater = fuCreater;
        isOk = true;
        //唤醒等待
        notify();
    }

    @Override
    public synchronized String getResult() {
        //未运行结束因此需要等待
        while (!isOk) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fuCreater.getResult();
    }

}
