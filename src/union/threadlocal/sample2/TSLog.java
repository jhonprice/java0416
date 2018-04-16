package union.threadlocal.sample2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 为了使不同的线程写不同的文件
 */
public class TSLog {
    private BufferedWriter writer;
    public TSLog(String filename){
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void println(String s){
        try {
            System.out.print("");
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            writer.write("============== END OF STRING==============");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
