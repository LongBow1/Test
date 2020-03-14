package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] result = twoSum(nums,6);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = {0,0};
        HashMap<Integer,Integer> valMap = new HashMap();
        valMap.put(nums[0],0);
        for(int i=1;i < nums.length;i++){
            if(valMap.containsKey(target-nums[i])){
                result[0] = valMap.get(target-nums[i]);
                result[1] = i;
                return result;
            }
            valMap.put(nums[i],i);
        }
        return result;
    }
}
