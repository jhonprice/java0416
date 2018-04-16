package union.bg;

/**
 * Created by lenovo on 2018/3/20.
 */

class Gate{
    int counter = 0;
    String name = "Nobody";
    String address  = "Nowhere";
    void pass(String name,String address){
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }

    void check(){
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("BROKEN-------------"+toString());
        }
    }
    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

public class TestNotSafeThread {
    public static void main(String[] args) {
        Gate gate = new Gate();
        new UserThread(gate,"ANAME","APLACE").start();
        new UserThread(gate,"BNAME","BPLACE").start();
        new UserThread(gate,"CNAME","CPLACE").start();
    }
}
