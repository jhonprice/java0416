package union.threadlocal.sample2;

/**
 * Created by lenovo on 2018/4/1
 */
public class Log {
    private static final ThreadLocal<TSLog> tsLogThreadLocals = new ThreadLocal<>();

    public static void println(String s){
        getTSLog().println(s);
    }
    public static void close(){
        getTSLog().close();
    }
    private static TSLog getTSLog(){
        TSLog tsLog =  tsLogThreadLocals.get();
        if(tsLog ==null){
            tsLog = new TSLog(Thread.currentThread().getName()+"-log.txt");
            tsLogThreadLocals.set(tsLog);
        }
        return tsLog;
    }
}
