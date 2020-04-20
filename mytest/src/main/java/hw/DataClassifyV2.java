package hw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 作者：看啥看
 * 链接：https://www.nowcoder.com/discuss/62516?type=0&order=0&pos=12&page=1
 * 来源：牛客网
 *
 * 对一个数据a进行分类，分类方法为：此数据a（四个字节大小）的四个字节相加%一个给定的值b，如果得到的结果小于一个给定的值c，则此结果即为数据a的类型；如果得到的结果大于或者等于c，则此结果无效即为数据a的类型无效。比如一个数据a=0x01010101,b=3,按照分类方法计算（0x01+0x01+0x01+0x01）%3=1,所以如果c=2，则此a的类型是1，如果c=1，则此a的类型是无效。输入12个数据，第一个数据为c，第二个数据为b，剩余10个数据为需要分类的数据a。计算数据最多的类型（有效类型）有多少个数据。
 */
public class DataClassifyV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strs = str.split("\\|");
        int c = Integer.parseInt(strs[0]);
        int b = Integer.parseInt(strs[1]);
        int f1,f2,f3,f4;
        int count = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 2;i < strs.length; ++i) {
            int temp = Integer.parseInt(strs[i]);
            f1 = temp >> 24;
            f2 = (temp & 0x00FFFFFF)>>16;
            f3 = (temp  & 0x0000FFFF) >> 8;
            f4 = (temp & 0x000000FF);
            int sum = f1 + f2 + f3 + f4;
            int tt = sum % b;
            if(tt < c) {
                if(map.containsKey(tt)) {
                    int x = map.get(tt) + 1;
                    map.put(tt, x);
                    if(x > count) {
                        count = x;
                    }
                } else {
                    map.put(tt, 1);
                }

            }
        }
        System.out.println(count);

    }
}
