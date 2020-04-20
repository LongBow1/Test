package hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlphabetCount {
    //等价于26进制转换
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String str=sc.nextLine();
            String outstr=null;
            convertTo26(str);
        }
    }

    //转化为26进制
    private static void convertTo26(String str) {
        //判断输入的是数字、 还是字母字符串
        if (str.length()<1||str.length()>6) {
            return;
        }
        //判断是字母还是数字
        int sum=0;
        int len=str.length();
        char [] c=str.toCharArray();//转换为数组
        if ( str.charAt(0)>=97 && str.charAt(0)<=122) {//字母
            for (int i = 0; i <c.length; i++) {
                sum+=(c[i]-96)*Math.pow(26, c.length-i-1);
            }
            System.out.println(sum);
        }else if (str.charAt(0)>=48 && str.charAt(0)<=57) {//数字
            int num=Integer.parseInt(str);
            int mod=0;
            List<Integer> list=new ArrayList<>();
            while(num!=0){
                mod=num%26;
                num=num/26;
                list.add(mod);//将结果进行保存,需要进行逆序处理
            }
            Collections.reverse(list);//逆序处理

            String result="";
            for (int i = 0; i < list.size(); i++) {
                result+=String.valueOf((char)(list.get(i)+96));
            }
            System.out.println(result);
        }


    }
}
