package com.owner.me.mytest;

import com.sun.xml.internal.ws.api.client.WSPortInfo;

import java.util.Arrays;
import java.util.List;

public class MixTest {
    public static void main(String[] args){

        int[] arra = {1,2,3};
        System.out.println(arra.length);
        int ii = 0,jj=0;
        int a = ii++, b =++jj;
        System.out.println(ii);
        System.out.println(jj);
        System.out.println(a);
        System.out.println(b);

        String str = "testlength";

        for(int i =0; i< 10; i++){
            for(int j=0; j< 10; j++){
                if(j == 2){
                    System.out.println(i);
                    System.out.println(j);
                    return;
                }
            }
        }

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
