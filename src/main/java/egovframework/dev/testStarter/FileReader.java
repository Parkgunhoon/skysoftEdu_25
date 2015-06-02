package egovframework.dev.testStarter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class FileReader{
    public static void main(String args[]) throws IOException {
    	FileOutputStream output = new FileOutputStream("C:/Eclipse/Workspace/Test/src/text.txt");
        String data = "이것은 텍스트파일 입니다.\r\n내용은이것과 같습니다.";
        output.write(data.getBytes());
    	output.close();

    	byte[] b = new byte[1024];
        FileInputStream input = new FileInputStream("C:/Eclipse/Workspace/Test/src/text.txt");
        input.read(b);
        System.out.println(new String(b));
        input.close();
    }
}