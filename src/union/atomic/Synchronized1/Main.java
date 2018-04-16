package union.atomic.Synchronized1;

class Something {
    private int x = 0;
    private int y = 0;

    public void write() {
        x = 100;
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        y = 50;
    }

    public void read() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (x < y) {
            System.out.println("x < y");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        final Something obj = new Something();

        // 写数据的线程A



        new Thread() {
            public void run() {
                obj.write();
            }
        }.start();

        // 读数据的线程B

        new Thread() {
            public void run() {
                obj.read();
            }
        }.start();

    }
}
