package union.threadlocal.sample2;

/**
 * Created by lenovo on 2018/4/1.
 */
public class Main {
    public static void main(String[] args) {
        new ClientThread("A").start();
        new ClientThread("B").start();
        new ClientThread("C").start();
    }
}
