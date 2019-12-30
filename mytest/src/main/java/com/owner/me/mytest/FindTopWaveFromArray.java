package com.owner.me.mytest;

public class FindTopWaveFromArray {

    public static void main(String[] args){
        Integer[] intArray = {1,2,3,4,5,8,7,6,5,4};
        Integer[] intArray2 = new Integer[]{1,2,3,4,5,8,7,6,5,4};

        int topWaveIndex = getTopWaveFromArrayByBisection(intArray);
        System.out.println(topWaveIndex);
        if(topWaveIndex > 0){
            System.out.println(intArray[topWaveIndex]);
        }


    }

    private static int getTopWaveFromArrayByBisection(Integer[] intArray) {
        if(intArray.length < 3){
            return -1;
        }
        int start = 1;
        int end = intArray.length - 1;
        while (start < end){
            int mid = start + (end - start)/2;
            if(intArray[mid] < intArray[mid - 1]){
                end = mid - 1;
            }else if(intArray[mid] < intArray[mid + 1]){
                start = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }


}
