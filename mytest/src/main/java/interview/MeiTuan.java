package interview;

import java.util.*;

public class MeiTuan {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        //Thread.currentThread().sleep(200);
        System.out.println("Hello World!");
        String[] input = {"eat","tea","tan","nat"};
        //Objects.hashCode()

        Map<Integer, List<String>> strMap = new HashMap();
        int tmpVal = 0;
        strMap.values();
        for(int i=0;i<input.length;i++){
            tmpVal = compute(input[i]);
            if(strMap.containsKey(tmpVal)){
                strMap.get(tmpVal).add(input[i]);
            }else{
                strMap.put(tmpVal,new ArrayList(Arrays.asList(input[i])));
            }
        }
        for(Map.Entry<Integer,List<String>> entry : strMap.entrySet()){
            System.out.println(entry.getValue());
        }
    }

    private  static int compute(String str){
        int res = 0;
        for(int i=0;i<str.length();i++){
            res += str.charAt(i);
        }
        return res;
    }
}
