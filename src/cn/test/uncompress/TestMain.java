package cn.test.uncompress;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class TestMain {

    public static BlockingQueue<Future> taskQueue = new LinkedBlockingQueue<>();
    public static void main(String args[]){
        int rlen = 0;
        ExecutorService service = Executors.newScheduledThreadPool(5);
//        Thread thread = new Thread(new PaintThread());
//        thread.start();
        try{
            for (int i = 0; i < 10; i++ ){
                rlen += 100;
                UnCompressTask task = new UnCompressTask(null, rlen, false);
                Future future = service.submit(task);
                taskQueue.add(future);
                Thread.sleep(100);
            }
        }catch (Exception e){
            System.out.println("主线程异常");
            e.printStackTrace();
        }
    }
}
