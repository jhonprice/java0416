package union.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by lenovo on 2018/4/15.
 */
public class Transform extends RecursiveAction{

    int seqThreshold;
    double[] data;

    int start,end;

    Transform(double[] value,int s,int e,int t){
        data=value;
        start = s;
        end = e;
        seqThreshold = t;
    }

    @Override
    protected void compute() {
        if((end -start)<seqThreshold){
            for(int i=start;i<end;i++){
                if(data[i]%2==0){
                    data[i] = Math.sqrt(data[i]);
                }else{
                    data[i] = Math.sqrt(data[i]);
                }
            }
        }else{
            int middle = (start+end)/2;
            invokeAll(new Transform(data,start,middle,seqThreshold),new Transform(data,middle,end,seqThreshold));
        }
    }
}

class FJExperiment{
    public static void main(String[] args) {
        int pLevel;
        int threshold;

//        if(args.length!=2){
//            System.out.println("Usage: parallelism threshold::");
//            return;
//        }
        pLevel = Integer.parseInt("1");
        threshold = Integer.parseInt("1000");

        long beginT,endT;

        ForkJoinPool fjp  = new ForkJoinPool(pLevel);
        double[] nums = new double[1000000];

        for(int i=0;i<nums.length;i++){
            nums[i] = (double) i ;

            Transform task  = new Transform(nums,0,nums.length,threshold);

            beginT = System.nanoTime();

            fjp.invoke(task);
            endT = System.nanoTime();


            System.out.println("pvlevel:::"+fjp.getParallelism());
            System.out.println("commmon:::"+ForkJoinPool.getCommonPoolParallelism());
            System.out.println("coreNum:::"+Runtime.getRuntime().availableProcessors());
            System.out.println("Level:::"+pLevel);
            System.out.println("Sequential threshold:::"+threshold);

            System.out.println("Elapsed::::"+(endT-beginT)+"ns");
        }

    }
}