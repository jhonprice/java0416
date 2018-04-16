package union.forkJoin;

import java.util.concurrent.RecursiveAction;

/**
 * Created by lenovo on 2018/4/8.
 */
class SqrtTransform extends RecursiveAction{
    final int seqThreshold = 1000;
    double[] data;
    int start ,end;

    public SqrtTransform(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if((end-start)<seqThreshold){
            for(int i=start;i<end;i++){
                data[i] = Math.sqrt(data[i]);
            }
        }else{
            int middle = (start+end)/2;
            invokeAll(new SqrtTransform(data,start,middle),new SqrtTransform(data,middle,end));
        }
    }
}

public class ForkJoinDemo{
    public static void main(String[] args) {
        double[] nums = new double[100000];
        for(int i = 0;i<nums.length;i++){
            nums[i] = (double) i;
        }

        System.out.println("original queue");

        for(int i=0;i<10;i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println("");


        SqrtTransform task  =new SqrtTransform(nums,0,nums.length);

        task.invoke();

        System.out.println("transformed sequeue");
        for(int i=0;i<10;i++){
            System.out.format("%.4f",nums[i]);
        }
        System.out.println("");
    }
}
