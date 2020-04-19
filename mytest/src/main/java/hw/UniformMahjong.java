package hw;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 题目描述：
 * 清一色是麻将番种之一，指由一种花色的序数牌组成的和牌。数字1-9，每个数字最多有4张牌。我们不考虑具体花色，我们只看数字组合。
 * 刻子：三张一样的牌；如: 111, 222, 333, ..., 999
 * 顺子：三张连续的牌；如: 123, 234, 345, ..., 789
 * 对子：两张相同的牌；如: 11, 22, 33, ..., 99
 * 需要实现一个程序，判断给定牌，是否可以和牌（胡牌）。
 * 和牌要求：
 * - 麻将牌张数只能是 2, 5, 8, 11, 14
 * - 给定牌可以组合成，除1个对子以外其他都是刻子或顺子
 * 举例：
 *
 * - "11"                    -> "11", 1对子，可以和牌
 * - "11122233"        -> "111"+"222"+"33", 2刻子，1对子，可以
 * - "11223344567"  -> "11"+"234"+"234"+"567", 1对子，3顺子，可以
 *                              -> "123"+"123"+"44"+"567", 另一种组合，也可以
 * 输入描述:
 * 合法C字符串，只包含'1'-'9'，且已经按从小到大顺序排好；字符串长度不超过15。同一个数字最多出现4次，与实际相符。
 * 输出描述:
 * C字符串，"yes"或者"no"
 * 示例1
 * 输入：2244
 *
 * 输出：no
 * ————————————————
 *
 * 思路：
 *
 * 先找刻子，再找顺子，找到顺子或刻子，则递归调用。剩下两张如果是对子，则胡牌
 * ————————————————
 * 版权声明：本文为CSDN博主「yi华」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/u014799309/article/details/104635468/
 *
 */
public class UniformMahjong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String string = scanner.nextLine();
            if(string.isEmpty()){
                System.out.println("重新输入！");
                continue;
            }
            boolean match = string.matches("[1-9]+");
            if(!match){
                System.out.println("非法输入!");
                continue;
            }
            int length = string.length();
            if(length != 2 && length != 5 && length != 8 && length != 11 && length != 14 ){
                System.out.println("no!");
                continue;
            }
            String[] split = string.split("");
            if(length == 2 && split[0] == split[1]){
                System.out.println("yes!");
                continue;
            }
            //TODO use character
            LinkedList<Integer> list = new LinkedList<>(Arrays.asList(split)
                    .stream().map(Integer::valueOf).collect(Collectors.toList()));
            boolean result = fun(list);
            System.out.println(result ? "yes" : "no");
        }
    }


    /**
     * 解析：
     *      * 1. 判断只剩对子的情况
     *      * 2. 先判断有没有刻子?
     *      *   2.1. 如果没有，则循环结束开始查询有没有顺子；
     *      *   2.2. 如果有，删除刻子元素后递归调用；
     *      *      2.2.1. 递归返回true, 则证明可以胡牌，继续返回即可；
     *      *      2.2.2. 递归返回false, 则恢复刚刚删除的刻子元素并跳出循环,让其找顺子
     *      *      依次递归...
     *      * 3. 判断有没有顺子？
     *      *   3.1. 如果没有，则循环结束自动返回false;
     *      *   3.2. 如果有，则删除顺子元素后递归调用；
     *      *      3.2.1. 递归返回true, 则可以胡牌，继续返回false即可
     *      *      3.2.2. 递归返回false, 则恢复刚刚删除的顺子元素，顺延开始元素换其他顺子方案继续查找
     *      *      (eg: "12344", 上次从链表最后一个开始找，找到234，剩下14，无法胡牌；
     *      *           则恢复删除的234，从列表的倒数第二个开始找顺子；
     *      *           依次顺延，最后找到123，剩下44)
     *
     * @param list
     * @return
     */
    private static boolean fun(LinkedList<Integer> list) {
        if(list.size() == 2){
            if(list.get(0) == list.get(1)){
                return true;
            }else{
                return false;
            }
        }
        boolean flag;
        for(int start = list.size()-1; start > 1; start--){
            //找刻子
            if(list.get(start) == list.get(start-1) && list.get(start-1) == list.get(start-2)){
                Integer a = list.get(start);
                list.remove(a);
                list.remove(a);
                list.remove(a);
                flag = fun(list);
                if(flag){
                    return true;
                }else{
                    list.add(start-2, a);
                    list.add(start-2, a);
                    list.add(start-2, a);
                    break;
                }
            }
        }
        int temp = list.size()-1;
        for(int start = temp; start > 1; start--){
            //找顺子
            if(list.get(start)-1 == list.get(start-1)){
                //找到第一个不相同的点
                int i = start-2;
                while(i>=0){
                    if(list.get(start-1)-1 == list.get(i)){
                        Integer a = list.get(start);
                        Integer b = list.get(start-1);
                        Integer c = list.get(i);
                        list.remove(a);
                        list.remove(b);
                        list.remove(c);
                        flag = fun(list);
                        if(!flag){
                            //list.add(i, c);
                            list.add(start-1, a);
                            list.add(start-1, b);
                            list.add(start-1, c);
                            start = temp--;
                            break;
                        }else{
                            return true;
                        }
                    }
                    i--;
                }
            }
        }
        return false;
    }
}
