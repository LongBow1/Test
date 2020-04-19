package hw;

import java.util.Scanner;

public class DiceChange {
    static StringBuilder sb = new StringBuilder("123456");

    public static void main(String[] args) {
        System.out.println(new StringBuilder("123456").reverse());
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            char[] split = in.nextLine().toCharArray();
            //in.close();
            for (Character s : split) {
                changeNum(s);
            }
            System.out.print(sb);
        }
    }

    private static  void changeNum(char a) {
        switch (a) {
            case 'L':
                String str1 = new StringBuilder(sb.substring(0, 2)).reverse().toString();
                sb.replace(0, 2, sb.substring(4, 6)).replace(4, 6, str1);
                break;
            case 'R':
                String str2 = new StringBuilder(sb.substring(4, 6)).reverse().toString();
                sb.replace(4, 6, sb.substring(0, 2)).replace(0, 2, str2);
                break;
            case 'F':
                String str3 = new StringBuilder(sb.substring(2, 4)).reverse().toString();
                sb.replace(2, 4, sb.substring(4, 6)).replace(4, 6, str3);
                break;
            case 'B':
                String str4 = new StringBuilder(sb.substring(4, 6)).reverse().toString();
                sb.replace(4, 6, sb.substring(2, 4)).replace(2, 4, str4);
            case 'A':
                String str5 = new StringBuilder(sb.substring(2, 4)).reverse().toString();
                sb.replace(2, 4, sb.substring(0, 2)).replace(0, 2, str5);
                break;
            case 'C':
                String str6 = new StringBuilder(sb.substring(0, 2)).reverse().toString();
                sb.replace(0, 2, sb.substring(2, 4)).replace(2, 4, str6);
                break;
        }
    }
}
