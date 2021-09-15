package test;

import java.io.*;

public class T1 {
    //输入一个字符串  判断该字符串是否包含非空字符
    public static boolean TestString(String test) {
        if ("".equals(test))
            return true;
        return false;
    }

    //打开文本文件 计算某个字符串出现得次数
    public static int countOccurrenceNumber(File filename, String target) throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder strb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            strb.append(line);
        }
        String result = strb.toString();
        int count = 0;
        int index = 0;
        while(true) {
            index = result.indexOf(target, index);
            if (index >= 0) {
                index = index+target.length();
                count++;
            } else {
                break;
            }
        }
        br.close();
        return count;
    }
    public static void main(String[] args) {
        System.out.println(TestString("123"));
        System.out.println(TestString(""));
        File file = new File("test.txt");
        try {
            System.out.println(countOccurrenceNumber(file,"abc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
