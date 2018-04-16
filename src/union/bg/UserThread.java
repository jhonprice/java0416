package union.bg;

/**
 * Created by lenovo on 2018/3/20.
 */
public class UserThread extends Thread{
    final Gate gate;
    final String name;
    final String myadress;

    public UserThread(Gate gate, String name, String myadress) {
        this.gate = gate;
        this.name = name;
        this.myadress = myadress;
    }
    public void run(){
        System.out.println(name+" BEGIN");
        for(int i = 0;i<100000;i++){
           gate.pass(name,myadress);
        }
    }
}
