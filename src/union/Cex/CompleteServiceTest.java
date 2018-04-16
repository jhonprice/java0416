package union.Cex;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by lenovo on 2018/4/7.
 */
public class CompleteServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);


        Random random = new Random(31232);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            completionService.submit(new Callable<String>() {
                public String call(){
                    int rnt = random.nextInt(1000);

                    try {
                        Thread.sleep(rnt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"  "+ finalI +"job "+"run rnt = "+rnt);
                    return String.valueOf(rnt);
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            Future<String> future = completionService.take();
            System.out.println("get Result :::"+future.get());
        }
        executorService.shutdown();
    }
}
