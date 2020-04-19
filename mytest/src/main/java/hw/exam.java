package hw;

import java.util.*;
import java.util.stream.Collectors;

public class exam {
    public static void main(String[] args) {
        String[] stringsplit = "111222".split("");
        for(String s : stringsplit){
            System.out.println(s);
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String string = scanner.nextLine();
            if(string.isEmpty()){
                System.out.println("重新输入！");
                continue;
            }
            boolean match = string.matches("[1-9]+");
            if(!match){
                System.out.println("非法输入!");
                continue;
            }
            int length = string.length();
            if(length != 2 && length != 5 && length != 8 && length != 11 && length != 14 ){
                System.out.println("no!");
                continue;
            }
            String[] split = string.split("");
            if(length == 2 && split[0] == split[1]){
                System.out.println("yes!");
                continue;
            }
            //TODO use character
            LinkedList<Integer> list = new LinkedList<>(Arrays.asList(split)
                    .stream().map(Integer::valueOf).collect(Collectors.toList()));
            boolean result = fun(list);
            System.out.println(result ? "yes" : "no");
        }

        /*String[] argsss = "(sub (mul (add 1 1) (mul 2 2)) (div 9 3))".split("\\(|\\)");
        for (int i = 0; i < argsss.length; i++) {
            System.out.println(argsss[i]);"(mul 3 -7)","(add 1 2)","(div 1 0)","(sub (mul 2 4) (div 9 3))",
            //System.out.println(argsss[i].length());
        }*/
        /*System.out.println(firstUniqChar("leetcode"));
        String[] argss = new String[]{"(sub (mul (add 1 1) (mul 2 2)) (div 9 3))"};
        for (int i = 0; i < argss.length; i++) {
            imitateLispLanguage(argss[i]);
        }*/

    }

    private static void imitateLispLanguage(String el) {
        Stack<String> stringStack = new Stack<>();
        String[] elArr = el.split("\\(|\\)");

        String p1, p2, res = "";
        Character tmp;
        for (int i = 0; i < elArr.length; i++) {
            if (elArr[i].trim().length() == 0) {
                continue;
            }
            if (elArr[i].trim().length() == 3) {
                stringStack.push(elArr[i].trim());
            } else {
                //compute
                String[] tmpArr = elArr[i].split(" ");
                p1 = tmpArr[1];
                p2 = tmpArr[2];
                switch (tmpArr[0]) {
                    case "add":
                        res = String.valueOf(Integer.valueOf(p2) + (Integer.valueOf(p1)));
                        break;
                    case "sub":
                        res = String.valueOf(Integer.valueOf(p1) - (Integer.valueOf(p2)));
                        break;
                    case "mul":
                        res = String.valueOf(Integer.valueOf(p2) * (Integer.valueOf(p1)));
                        break;
                    case "div":
                        if (Integer.valueOf(p2) == 0) {
                            System.out.println("error");
                            return;
                        }
                        res = String.valueOf(Integer.valueOf(p1) / (Integer.valueOf(p2)));
                        break;
                }
                if (stringStack.empty()) {
                    System.out.println(res);
                    return;
                } else {
                    stringStack.push(res);
                }
            }
        }
        while (stringStack.size() >= 3) {
            p2 = stringStack.pop();
            p1 = stringStack.pop();
            switch (stringStack.pop()) {
                case "add":
                    res = String.valueOf(Integer.valueOf(p2) + (Integer.valueOf(p1)));
                    break;
                case "sub":
                    res = String.valueOf(Integer.valueOf(p1) - (Integer.valueOf(p2)));
                    break;
                case "mul":
                    res = String.valueOf(Integer.valueOf(p2) * (Integer.valueOf(p1)));
                    break;
                case "div":
                    if (Integer.valueOf(p2) == 0) {
                        System.out.println("error");
                        return;
                    }
                    res = String.valueOf(Integer.valueOf(p1) / (Integer.valueOf(p2)));
                    break;
            }
            if (stringStack.empty()) {
                System.out.println(res);
                return;
            } else {
                stringStack.push(res);
            }
        }
    }

    private static void firstCharForOnce(String inputStr, boolean caseSensitive){
        Map<Character,Boolean> linkMap = new LinkedHashMap<>();
        char[] chars = inputStr.toCharArray();
        for(char c : chars){
            linkMap.put(c, !linkMap.containsKey(c));
        }
        for(Map.Entry<Character,Boolean> entry : linkMap.entrySet()){
            if(entry.getValue()){
                System.out.println(entry.getKey());
                return;
            }
        }
    }

    public static char firstUniqChar(String s) {
        Map<Character, Boolean> cMap = new HashMap();
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(cMap.containsKey(chars[i])){
                cMap.put(chars[i],false);
            }else{
                cMap.put(chars[i],true);
            }
        }
        for (int i=0;i<chars.length;i++){
            if(cMap.containsKey(chars[i]) && cMap.get(chars[i])){
                return chars[i];
            }
        }
        return ' ';
    }


    private static boolean fun(LinkedList<Integer> list) {
        if(list.size() == 2){
            if(list.get(0) == list.get(1)){
                return true;
            }else{
                return false;
            }
        }
        boolean flag;
        for(int start = list.size()-1; start > 1; start--){
            //找刻子
            if(list.get(start) == list.get(start-1) && list.get(start-1) == list.get(start-2)){
                Integer a = list.get(start);
                list.remove(a);
                list.remove(a);
                list.remove(a);
                flag = fun(list);
                if(flag){
                    return true;
                }else{
                    list.add(start-2, a);
                    list.add(start-2, a);
                    list.add(start-2, a);
                    break;
                }
            }
        }
        int temp = list.size()-1;
        for(int start = temp; start > 1; start--){
            //找顺子
            if(list.get(start)-1 == list.get(start-1)){
                //找到第一个不相同的点
                int i = start-2;
                while(i>=0){
                    if(list.get(start-1)-1 == list.get(i)){
                        Integer a = list.get(start);
                        Integer b = list.get(start-1);
                        Integer c = list.get(i);
                        list.remove(a);
                        list.remove(b);
                        list.remove(c);
                        flag = fun(list);
                        if(!flag){
                            //list.add(i, c);
                            list.add(start-1, a);
                            list.add(start-1, b);
                            list.add(start-1, c);
                            start = temp--;
                            break;
                        }else{
                            return true;
                        }
                    }
                    i--;
                }
            }
        }
        return false;
    }
}
