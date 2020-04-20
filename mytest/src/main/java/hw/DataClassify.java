package hw;

/**
 * 输入描述:
 * ﻿一组输入整数序列I和一组规则整数序列R，I和R序列的第一个整数为序列的个数（个数不包含第一个整数）；整数范围为0~0xFFFFFFFF，序列个数不限
 *
 * 输出描述:
 * ﻿从R依次中取出R<i>，对I进行处理，找到满足条件的I<j>： 
 *
 * I<j>整数对应的数字需要连续包含R<i>对应的数字。比如R<i>为23，I<j>为231，那么I<j>包含了R<i>，条件满足 。 
 *
 * 按R<i>从小到大的顺序:
 *
 * (1)先输出R<i>； 
 *
 * (2)再输出满足条件的I<j>的个数； 
 *
 * (3)然后输出满足条件的I<j>在I序列中的位置索引(从0开始)； 
 *
 * (4)最后再输出I<j>。 
 *
 * 附加条件： 
 *
 * (1)R<i>需要从小到大排序。相同的R<i>只需要输出索引小的以及满足条件的I<j>，索引大的需要过滤掉 
 *
 * (2)如果没有满足条件的I<j>，对应的R<i>不用输出 
 *
 * (3)最后需要在输出序列的第一个整数位置记录后续整数序列的个数(不包含“个数”本身)
 *
 *  
 *
 * 序列I：15,123,456,786,453,46,7,5,3,665,453456,745,456,786,453,123（第一个15表明后续有15个整数） 
 *
 * 序列R：5,6,3,6,3,0（第一个5表明后续有5个整数） 
 *
 * 输出：30, 3,6,0,123,3,453,7,3,9,453456,13,453,14,123,6,7,1,456,2,786,4,46,8,665,9,453456,11,456,12,786
 *
 * 说明：
 *
 * 30----后续有30个整数
 *
 * 3----从小到大排序，第一个R<i>为0，但没有满足条件的I<j>，不输出0，而下一个R<i>是3
 *
 * 6--- 存在6个包含3的I<j> 
 *
 * 0--- 123所在的原序号为0 
 *
 * 123--- 123包含3，满足条件 
 * ————————————————
 * 版权声明：本文为CSDN博主「KittyGirllll」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/HEYIAMCOMING/article/details/80879588
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ArrayList集合类中的方法：add(int index, Integer a)；get(int index)；size()；clear()
 * 将整数转换为字符串Integer.toString()或者String.valueOf()
 */
public class DataClassify {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int m = sc.nextInt();
            int[] I = new int[m];
            for (int i = 0; i < m; i++) {
                I[i] = sc.nextInt();
            }
            int n = sc.nextInt();
            int[] R = new int[n];
            for (int i = 0; i < n; i++) {
                R[i] = sc.nextInt();
            }
            Arrays.sort(R);
            ArrayList<Integer> listAll = new ArrayList<>();

            for (int i = 0; i < R.length; i++) {
                if (i > 0 && R[i] == R[i-1])
                    continue;
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 0; j < I.length; j++) {
                    if(contain(I[j], R[i])){
                        list.add(j);
                        list.add(I[j]);
                    }
                }

                if (list.isEmpty())
                    continue;
                else {
                    int len = list.size()/2;
                    list.add(0, new Integer(len));
                    list.add(0, new Integer(R[i]));
                    listAll.addAll(list);
                    list.clear();
                }
            }
            int l = listAll.size();
            listAll.add(0, l);
            for (int i = 0; i < listAll.size(); i++) {
                System.out.print(listAll.get(i));
                if (i != listAll.size()-1)
                    System.out.print(" ");
                else
                    System.out.println();
            }
        }
    }

    private static boolean contain (int a, int b) {
        return Integer.toString(a).contains(Integer.toString(b));
    }

}
