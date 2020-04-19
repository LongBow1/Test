package hw;

import java.util.*;

/**
 * 小明负责公司年会，想出一个趣味游戏：屏幕给出1~9中任意3个不重复的数字，大家以最快时间给出这几个数字可拼成的数字从小到大排列位于第N位置的数字，其中N为给出的数字中最大的（如果不到这么多个数字则给出最后一个即可），谁最快给出谁得奖。
 *
 * 注意：
 *
 * （1）屏幕如果给出的是“2”，大家可把它当作“2”，也可把它当作“5”来拼接数字；同理，如果屏幕给的是“5”，大家可把它当作“5”，也可以把它当作“2”来拼接数字，但屏幕不能同时给出“2”和“5”。
 *
 * （2）屏幕如果给出的是“6”，大家可把它当作“6”，也可把它当作“9”来拼接数字；同理，如果屏幕给的是“9”，大家可把它当作“9”，也可以把它当作“6”来拼接数字，但屏幕不能同时给出“6”和“9”。
 *
 * 现在需要编写一个小程序，根据给出的数字计算出能组合的所有2数字以及最终的正确答案。
 *
 * 如：给出：1，4，8，则可以拼成的数字为：
 *
 * 1，4，8，14，18，41，48，81，84，148，184，418，481，814，841
 *
 * 那么最第N（即8）个的数字为81.
 *
 * 输入描述：以逗号为分隔，描述3个int类型整数的字符串。
 *
 * 输出描述：这几个数字可拼成的数字从小到大排列位于第N（N为输入数字中最大的数字）位置的数字，如果输入的数字为负数或者不是合法的字符串或者有重复，返回-1。
 *
 * 输入例子：1，4，8
 *
 * 输出例子：81
 */
public class NumberArrange {
    public static int getNthNum(ArrayList array) {
        if(array == null) {
            return -1;
        }
        if(array.contains(2) && array.contains(5)) {
            return -1;
        }
        if(array.contains(6) && array.contains(9)) {
            return -1;
        }
        HashSet<Integer> hashSet = new HashSet<Integer>();
        getCombinedNumsArray(array, hashSet);
        for(int i=0; i<array.size();i++) {
            if(array.get(i).equals(2)){
                array.set(i, 5);
                getCombinedNumsArray(array, hashSet);
            }else if(array.get(i).equals(5)) {
                array.set(i, 2);
                getCombinedNumsArray(array, hashSet);
            }else if(array.get(i).equals(6)) {
                array.set(i, 9);
                getCombinedNumsArray(array, hashSet);
            }else if(array.get(i).equals(9)) {
                array.set(i, 6);
                getCombinedNumsArray(array, hashSet);
            }
        }
        ArrayList<Integer> resArray = new ArrayList<>();
        for(Integer i:hashSet) {
            resArray.add(i);
        }
        Collections.sort(resArray);
        System.out.println(resArray);
        int maxNum = 0;
        for(int i=0;i<array.size();i++) {
            if((int) array.get(i)==2) {
                maxNum = maxNum>5 ? maxNum : 5;
            }else if((int) array.get(i)==6) {
                maxNum = 9;
            }else {
                maxNum = maxNum > (int) array.get(i) ? maxNum : (int) array.get(i);
            }
        }
        return resArray.get(maxNum-1);
    }

    public static void getCombinedNumsArray(ArrayList list, HashSet hashSet) {
        int len = list.size();
        for(int i=0; i<len; i++) {
            hashSet.add((int) list.get(i));
            for(int j=0; j<len; j++) {
                if(j==i) {
                    continue;
                }
                hashSet.add((int) list.get(i)*10 + (int) list.get(j));
                for(int k=0;k<len;k++) {
                    if(k==i || k==j) {
                        continue;
                    }
                    hashSet.add((int)list.get(i)*100 + (int)list.get(j)*10 + (int)list.get(k));
                }
            }
        }
    }

    public static ArrayList getSingleNumsArray(String line) {
        String[] threeValues = line.split(" *, *");
        if(threeValues.length!=3) {
            return null;
        }else if(threeValues[0].equals(threeValues[1]) || threeValues[0].equals(threeValues[2])
                || threeValues[1].equals(threeValues[2])) {
            return null;
        }
        ArrayList res = new ArrayList<Integer>();
        for(String s:threeValues) {
            int num;
            try {
                num = Integer.parseInt(s);
            }catch(Exception e) {
                return null;
            }
            if(1 > num || num > 9) {
                return null;
            }
            res.add(num);
        }
        return res;
    }


    public static void main(String[] args) {
        String[] strings = "the sky is  blue ".split(" ");
        for(String s :strings){
            System.out.println(s);
        }

        System.out.println((int)'a'-96);
        System.out.println((int)'h'-96);
        System.out.println((int)'u'-96);
        System.out.println((int)'a'-96);
        System.out.println((int)'w'-96);
        System.out.println((int)'e'-96);
        System.out.println((int)'i'-96);
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()){
            String line = scan.nextLine().trim();
            ArrayList singleNumsArray = getSingleNumsArray(line);
            System.out.println(getNthNum(singleNumsArray));
        }

    }
}
