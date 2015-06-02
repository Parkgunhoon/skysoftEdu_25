package egovframework.dev.testStarter;

import java.io.FileInputStream;
import java.io.IOException;

class FileReader{
    public static void main(String args[]) throws IOException {
    	byte[] b = new byte[1024];
        FileInputStream input = new FileInputStream("C:/Eclipse/Workspace/Test/src/text.txt");
        input.read(b);
        System.out.println(new String(b));
        input.close();
    }
}