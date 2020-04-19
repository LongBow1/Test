package hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 描述
 * LISP语言唯一的语法就是括号要配对。
 * 形如 (OP P1 P2 …)，括号内元素由单个空格分割。
 * 其中第一个元素OP为操作符，后续元素均为其参数，参数个数取决于操作符类型
 * 注意：参数 P1, P2 也有可能是另外一个嵌套的 (OP P1 P2 …)
 * 当前OP类型为add/sub/mul/div(全小写)，分别代表整数的加减乘除法。简单起见，所以OP参数个数为2
 * 举例：
 *
 * 输入：(mul 3 -7)输出：-21
 * 输入：(add 1 2) 输出：3
 * 输入：(sub (mul 2 4) (div 9 3)) 输出 ：5
 * 输入：(div 1 0) 输出：error
 * 解决方案
 * 众所周知，应用程序中运行的时候会有各种各样的函数的嵌套调用，甚至是递归，整个函数调用的路径（调用链）是以栈（称之为调用栈）的形式保存，异常的时候打印（或者coredump的时候来记录的吐核）都会记录逐层的调用栈信息。为什么函数逐层深入调用完毕之后能回到原来的调用点呢，就是一个配对的问题。配对也就是有明确的固定的格式来区别起始位置和结束位置，比如函数的调用入口就是起始位置，函数返回就是结束位置。针对配对的问题，可以用栈（stack）这种数据结构解决，具体的解决思路：
 *
 * 开始调用symbol-1，起始位置symbol-1-starting压栈
 * symbol-1中调用symbol-2，起始位置symbol-2-starting压栈
 * symbol-2调用完毕，结束位置symbol-2-ending，栈顶是symbol-2-starting，弹栈进行处理
 * symbol-1调用完毕，结束位置是symbol-1-ending，栈顶是symbol-1-starting，弹栈进行处理
 * 调用整个过程也完毕，调用栈也是空栈
 * 举个栗子
 * 举个很常见的例子，斐波那契数列:
 *
 * // 数列前两项是1，后面的每一项是前面两项的和
 * Fib(n) = {1, 1, 2, 3, 5, 8, 13, 21, ...}
 * 1
 * 2
 * 用代码程序的话，描述（描述语言：C/C++）如下：
 *
 * // 计算第count个斐波那契数列的元素
 * int Fib(const int& count)
 * {
 *     if(count < 1)
 *         return -1;  // 输入有误
 *     else if(count < 3)
 *         return 1;   // 最开始的两项
 *     else
 *         return Fib(count-1) + Fib(count-2);  // 后面的元素进行递归运算
 * }
 * 1
 * 2
 * 3
 * 4
 * 5
 * 6
 * 7
 * 8
 * 9
 * 10
 * 比如要获取斐波那契数列的第5个元素，也就是调用Fib(5)，调用栈的轨迹如下：
 *
 * 进入Fib(5)，压栈，栈顶记为Fib-5-starting
 * 进入Fib(4)，压栈，栈顶记为Fib-4-starting
 * 进入Fib(3)，压栈，栈顶记为Fib-3-starting
 * 进入Fib(2)，直接返回1
 * 进入Fib(1)，直接返回1
 * 结束Fib(3)，弹栈Fib-3-starting，结果是Fib(2)+Fib(1) = 2，返回2
 * 结束Fib(4)，弹栈Fib-4-starting，结果是Fib(3)+Fib(2) = 3，返回3
 * 结束Fib(5)，结果是Fib(4)+Fib(3)，上述Fib(4)已经返回3，需要继续算Fib(3)，压栈，栈顶记为Fib-3-starting
 * 进入Fib(2)，直接返回1
 * 进入Fib(1)，直接返回1
 * 结束Fib(3)，弹栈Fib-3-starting，结果是Fib(2)+Fib(1) = 2，返回2
 * 继续结束Fib(5)，弹栈栈顶记为Fib-5-starting，结果是Fib(4)+Fib(3) = 5，返回5
 * 调用结束，栈也是空栈，返回结果：5
 * ————————————————
 * 版权声明：本文为CSDN博主「pqcoder」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/pengqianghhu/article/details/81428762
 */
public class ImitateLISP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            //solution(scanner.nextLine());
            System.out.println(lisp(scanner.nextLine()));
        }
    }

    public static void solution(String str) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> operStack = new Stack<>();
        int mark = 0;
        int paramOne = 0;
        int paramTwo = 0;
        for(int i = 0;i<str.length();i++){
            char chas = str.charAt(i);
            if(chas == '('){
                //截取符号位
                operStack.push(str.substring(i+1,i+4));
                //这里为空格的索引位置
                i = i + 4;
                //符号位后第一个数字的索引坐标
                mark = i+1;
            }else if(chas == ')'){
                if(mark < i){
                    //所有数字的截取
                    numStack.push(Integer.valueOf(str.substring(mark,i)));
                    i++;
                    mark = i+1;
                }
                //得到一次（）的对应，就进行一次计算
                paramOne = numStack.pop();
                paramTwo = numStack.pop();
                calc(numStack,operStack,paramOne,paramTwo);
            }else{
                //空格位将数字进行区分
                if(chas == ' '){
                    if(mark < i ){
                        numStack.push(Integer.valueOf(str.substring(mark,i)));
                        //下一个数字的索引为空格后面一位，故mark = i+1;
                        mark = i + 1;
                    }
                }
            }
        }
        //如果还有没计算完的，就进行再次计算
        while (!operStack.isEmpty()){
            paramTwo = numStack.pop();
            paramOne = numStack.pop();
            calc(numStack,operStack,paramOne,paramTwo);
        }
    }

    private static void calc(Stack<Integer> numStack, Stack<String> operStack, int paramOne, int paramTwo) {
        switch(operStack.pop()){
            case "add":
                numStack.push(paramOne + paramTwo);
                break;
            case "sub":
                numStack.push(paramOne - paramTwo);
                break;
            case "mul":
                numStack.push(paramOne * paramTwo);
                break;
            case "div":
                if(paramTwo == 0)
                    System.out.println("error");
                else
                    numStack.push(paramOne / paramTwo);
                break;
        }
    }


    // 只通过80%
    // (+ (* 2 3) (^ 4))
    // (+ (* 2 3) (^ 4))(2 3)
    // ((+ 2 3)
    // ((+ 2 3))
    // (^ (+ (* 2 3) (^ ((^ 4)))))
    public static void mainv2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String express = scanner.nextLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < express.length(); i++) {
            char c = express.charAt(i);
            if (c == ')') {
                StringBuilder builder = new StringBuilder();
                char top;
                while (!stack.empty() &&(top = stack.pop()) != '(') {
                    builder.append(top);
                }

                String currExp = builder.reverse().toString();
//                System.out.println(currExp);

                String[] ops = currExp.split(" ");
                if (ops.length == 2) {
                    int value = Integer.valueOf(ops[1]);
                    value++;
                    String count = String.valueOf(value);
                    for (int j = 0; j < count.length(); j++) {
                        stack.push(count.charAt(j));
                    }
                } else if (ops.length == 3) {
                    int a = Integer.valueOf(ops[1]);
                    int b = Integer.valueOf(ops[2]);
                    int value = 0;
                    switch (ops[0]) {
                        case "+":
                            value = a + b;
                            break;
                        case "*":
                            value = a * b;
                            break;
                    }
                    String count = String.valueOf(value);
                    for (int j = 0; j < count.length(); j++) {
                        stack.push(count.charAt(j));
                    }
                } else if (ops.length == 1) {
                    for (int j = 0; j < ops[0].length(); j++) {
                        stack.push(ops[0].charAt(j));
                    }
                }

            } else {
                stack.push(c);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.empty())
            builder.append(stack.pop());

        try {
            System.out.println(Integer.valueOf(builder.reverse().toString()));
        } catch (Exception e) {
            System.out.println(-1);
        }
    }


    public static int lisp(String s) {
        s = s.trim();  // 去掉前导和后导的空格
        s = s.substring(1, s.length() - 1); // 去掉首尾的（）
        s = s.trim();  // 去掉前导和后导的空格
        String op = s.substring(0, 3);  // 取得操作符
        List<Integer> list = new ArrayList<>();  // 保存数字
        int num = 0;
        for (int i = 3, len = s.length(); i < len ; i++) {  // i从3开始，0-2是操作符
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + c - '0';
                    i++;
                }
                i--;
                list.add(num);
                num = 0;
            } else if (c == '(') {   // 递归处理
                int j = i;
                int cnt = 0;
                for ( ; i < len; i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }
                num = lisp(s.substring(j , i + 1));
                list.add(num);
                num = 0;
            }
        }

        switch (op) {
            case "add":
                return list.stream().mapToInt(Integer::intValue).sum();
            case "mul":
                return list.stream().mapToInt(Integer::intValue).reduce(1, (l, r) -> l * r);
            case "sub":
                return list.get(0) - list.get(1);
            case "div":
                return list.get(0) / list.get(1);
            default:
                throw new RuntimeException("Input illegal");
        }
    }
}
