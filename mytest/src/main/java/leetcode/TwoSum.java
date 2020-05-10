package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int[] result = twoSum(nums,0);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
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

    public int[] twoSumV2(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer,Integer> numValueMap = new HashMap<>(nums.length);
        for(int i =0; i< nums.length;i++){
            if(numValueMap.containsKey(target -nums[i])){
                return new int[]{numValueMap.get(target-nums[i]),i };
            }
            numValueMap.put(nums[i], i);
        }

        return result;
    }
}
