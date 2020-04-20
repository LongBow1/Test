package hw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 一台打印机有若干个任务，但是打印机每次只能打印一个任务，每个任务是有优先级的，从1到9。打印的时候从序列的第一个开始，如果第一个的优先级不是最大的，则将其出队，并加入队尾。若其优先级是最大的，则直接打印该任务。设计算法实现如下功能：
 * 输入：
 * （1）第一行：输入测试案例的个数，第二行：第一个数字是打印任务个数，第二个数据是目标任务在当前任务序列的位置
 * 输出：
 *
 * （2）目前任务打印完成需要的时间（假设打印一个任务需要1个单位时间，转移、判断任务不需要时间）
 * （3）所有任务的打印顺序
 *
 * 例如：
 *
 * 1（只有一个测试用例）
 *
 * 2 1（一共两个任务，目标任务的位置是1--任务是从0开始的）
 * 2 3（序列）
 *
 * 输出：
 * 2 1 0
 * ————————————————
 * 版权声明：本文为CSDN博主「Aoulun」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/Aoulun/article/details/79960147
 */
public class PrintOrder {
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);

        while(in.hasNext())
        {
            String str=in.nextLine();
            String[] strs=str.split(",");
            // 数组有可能为零
            if(strs.length>0)
            {
                Integer [] ints=new Integer[strs.length];
                int vi[]=new int[ints.length];

                for(int i=0;i<strs.length;i++)
                {
                    ints[i]=Integer.parseInt(strs[i]);// 无序的数组

                }

                //降序排序，9 3 3 5
                Arrays.sort(ints,new Comparator<Integer>(){

                    @Override
                    public int compare(Integer arg0, Integer arg1) {
                        // TODO Auto-generated method stub
                        return arg1-arg0;
                    }

                });

                StringBuilder sb=new StringBuilder();
                // 输出所在的坐标
                for(int j=0;j<strs.length;j++)
                {
                    int c=Integer.parseInt(strs[j]);
                    int k=0;
                    while(k<ints.length)
                    {
                        if(c==ints[k] && vi[k]==0)
                        {
                            vi[k]=1;
                            break;
                        }
                        k++;
                    }

                    sb.append(k+",");
                }
                System.out.println(sb.substring(0,sb.length()-1));
            }
        }
    }
}
