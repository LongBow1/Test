package meituan;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        //http://conf.ctripcorp.com/pages/viewpage.action?pageId=140136591
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        //Thread.currentThread().sleep(200);
        System.out.println("Hello World!");
        String[] input = {"eat","tea","tan","nat"};

        Map<Integer, List<String>> strMap = new HashMap();
        int tmpVal = 0;
        for(int i=0;i<input.length;i++){
            tmpVal = compute(input[i]);
            if(strMap.containsKey(tmpVal)){
                strMap.get(tmpVal).add(input[i]);
            }else{
                strMap.put(tmpVal,new ArrayList(Arrays.asList(input[i])));
            }
        }
        for(Map.Entry<Integer,List<String>> entry : strMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }

    private static int compute(String str){
        int res = 0;
        for(int i=0;i<str.length();i++){
            res += str.charAt(i);
        }
        return res;
    }
}
