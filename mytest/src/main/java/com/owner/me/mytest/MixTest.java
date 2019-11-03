package com.owner.me.mytest;

import java.util.Arrays;
import java.util.List;

public class MixTest {
    public static void main(String[] args){
        String str = "testlength";

        int a32 = 3*1024*1024;
        System.out.println(a32);
        System.out.println(a32 >> 1);
        System.out.println(a32<<1);

        changeStr(str);
        System.out.println(str);
        System.out.println(isSkuItemMatch("黑 灰","黑|灰"));

    }

    private static void changeStr(String str) {
        str = "changester";
    }

    private static boolean isSkuItemMatch(String skuItem, String keyword) {
        if (skuItem.contains(keyword)) {
            return true;
        }
        if (keyword != null && keyword.contains("+")) {
            System.out.println(keyword);
            List<String> keywordList = Arrays.asList(keyword.split("\\+"));
            System.out.println(keyword);
            if(keyword.replaceAll("\\+"," ").equalsIgnoreCase(skuItem)){
                System.out.println(keyword);
                return true;
            }
            if (keywordList.stream().allMatch(key -> skuItem.contains(key.trim()))) {
                return true;
            }
        }
        if (keyword != null && keyword.contains("|")) {
            List<String> keywordList = Arrays.asList(keyword.split("\\|"));
            /*if(keyword.replaceAll("\\|"," ").equalsIgnoreCase(skuItem)){
                System.out.println(keyword);
                return true;
            }*/
            if (keywordList.stream().allMatch(key -> skuItem.contains(key.trim()))) {
                return true;
            }
        }
        return false;
    }
}
