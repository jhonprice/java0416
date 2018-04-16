package union.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by lenovo on 2018/4/15.
 */
class Sum extends RecursiveTask<Double> {
    final int seqThreadsHold = 500;
    double[] data;
    int start,end;

    Sum(double[] vals,int s,int e){
        data  = vals;
        start = s;
        end = e;
    }

    @Override
    protected Double compute() {
        double sum =0 ;
        if((end-start)<seqThreadsHold){
            for(int i=start;i<end;i++){
                sum+=data[i];
            }
        }else{
            int middle = (start+end)/2;
            Sum sum1 = new Sum(data,start,middle);
            Sum sum2 = new Sum(data,middle,end);

            System.out.println("sum1");
            sum1.fork();
            System.out.println("sum1end");
            sum2.fork();

            sum = sum1.join()+sum2.join();
            return sum;
        }
        return sum;
    }
}

class Demo{
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("plevel::;"+forkJoinPool.getParallelism());

        double[] nums = new double[5000];

        for(int i=0;i<nums.length;i++){
            nums[i] = (double)(i%2==0?i:-i);
        }
        Sum task = new Sum(nums,0,nums.length);

        double summation = forkJoinPool.invoke(task);

        System.out.println("Sum:::"+summation);
    }
}
