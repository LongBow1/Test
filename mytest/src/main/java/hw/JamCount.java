package hw;

import java.util.Scanner;

/**
 * Jam是个喜欢标新立异的科学怪人。他不使用阿拉伯数字计数，而是使用小 写英文字母计数，他觉得这样做，会使世界更加丰富多彩。在他的计数法中，每个数字的位数都是相同的（使用相同个数的字母），英文字母按原先的顺序，排在前 面的字母小于排在它后面的字母。我们把这样的“数字”称为Jam数字。在Jam数字中，每个字母互不相同，而且从左到右是严格递增的。每次，Jam还指定 使用字母的范围，例如，从2到10，表示只能使用{b,c,d,e,f,g,h,i,j}这些字母。如果再规定位数为5，那么，紧接在Jam数字 “bdfij”之后的数字应该是“bdghi”。（如果我们用U、V依次表示Jam数字“bdfij”与“bdghi”，则U〈V，且不存在Jam数字 P，使U〈P〈V）。你的任务是：对于从文件读入的一个Jam数字，按顺序输出紧接在后面的5个Jam数字，如果后面没有那么多Jam数字，那么有几个就 输出几个。
 *
 * 输入
 * 有2行，第1行为3个正整数，用一个空格隔开：
 * s t w
 * （其中s为所使用的最小的字母的序号，t为所使用的最大的字母的序号。w为数字的位数，这3个数满足：1≤s〈t≤26, 2≤w≤t-s ）
 * 第2行为具有w个小写字母的字符串，为一个符合要求的Jam数字。
 * 所给的数据都是正确的，不必验证
 *
 * 输出
 * 最多为5行，为紧接在输入的Jam数字后面的5个Jam数字，如果后面没有那么多Jam数字，那么有几个就输出几个。每行只输出一个Jam数字，是由w个小写字母组成的字符串，不要有多余的空格。
 *
 * 样例输入
 * 2 10 5
 * bdfij
 * 1
 * 2
 * 样例输出
 * bdghi
 * bdghj
 * bdgij
 * bdhij
 * befgh
 * 1
 * 2
 * 3
 * 4
 * 5
 * 分析
 * 这题一看就是递推的题目，但是想想又不好写，还是太菜了。
 * 这题的方法的从最后面向前面递推，假设现在有六个数123456，现在让你写字母长度为3，给定数为145后面的数，那么下一个就是146，再然后就是156，这时答案就已经结束了，156是最后一个数。
 * 从146到156的变化我们可以发现，这其中主要的变化就是4->5，这也就是这题递推的关键点，还有一点就是在这个关键点变化后，156的第二位已经确定为5，那么第三位一定是5+1
 * ————————————————
 * 版权声明：本文为CSDN博主「hypocrisies」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_37438740/article/details/86652291
 */
public class JamCount {
    static int begin;
    static int end;
    static int len;
    static int time;
    static int a[]= new int[27];

    static boolean test() {
        int ends = end;
        int lens = len;
        time++;

        if(time==6) return false;
        while(a[lens]==ends) {
            ends--;
            lens--;
        }
        if(lens==0) return false;
        a[lens]++;
        for(int i=lens+1;i<=len;i++) {
            a[i]=a[i-1]+1;
        }
        for(int i=1;i<=len;i++) {
            System.out.print((char)(a[i]+'a'-1));

        }
        System.out.println();

        return true;
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        begin = s.nextInt();
        end = s.nextInt();
        len = s.nextInt();
        String data = s.next();

        for(int i=1;i<=data.length();i++) {
            a[i] = data.charAt(i-1)-'a'+1;
        }

        while(test());

        //new JamCount();
    }


    private Scanner sc;
    private int s, t, w;
    private String str;

    public JamCount() {
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            input();
            for (int i = 0; i < 5; i++) {// 5个Jam数
                if (false == nextJam()) {// 无，直接退出
                    break;
                }
                System.out.println(str);// 输出
            }
        }
        sc.close();
    }

    private void input() {// 输入
        s = sc.nextInt();
        t = sc.nextInt();
        w = sc.nextInt();
        str = sc.next();
    }

    private boolean contains(char ch) {// 检查字符串str是否包含字符ch
        for (int i = w - 1; i >= 0; i--) {
            if (str.charAt(i) == ch) {
                return true;
            }
        }
        return false;
    }

    // 有，将字符串str变成下一个Jam数并返回true；无，返回false
    private boolean nextJam() {
        char ch;
        for (int j = w - 1; j >= 0; j--) {// 向前遍历
            ch = (char) (str.charAt(j) + 1);// 当前字符的后继字符
            if (ch - 'a' + 1 > t) {// 出界
                continue;
            }
            if (true == contains(ch)) {// 包含
                continue;
            }
            str = str.replace(str.charAt(j), ch);// 当前字符替换成后继字符
            for (j = j + 1; j < w; j++) {// 当前字符后面的字符依次替换成后继字符
                ch = (char) (ch + 1);
                str = str.replace(str.charAt(j), ch);
            }
            return true;
        }
        return false;
    }

}
