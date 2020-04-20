import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeleGraph {
    /**
     * ALL LETTER MAPPING
     */
    static Map<String,String> letterMap = new HashMap<>(51);
    static {
        letterMap.put("0","F");
        letterMap.put("1","G");
        letterMap.put("10","R");
        letterMap.put("11","S");
        letterMap.put("100","T");
        letterMap.put("101","L");
        letterMap.put("110","M");
        letterMap.put("111","N");
        letterMap.put("1000","O");
        letterMap.put("1001","P");
        letterMap.put("1010","Q");

        letterMap.put("1011","W");
        letterMap.put("1100","X");
        letterMap.put("1101","Y");
        letterMap.put("1110","Z");
        letterMap.put("1111","U");
        letterMap.put("10000","A");
        letterMap.put("10001","G");
        letterMap.put("10010","H");
        letterMap.put("10011","I");
        letterMap.put("10100","J");
        letterMap.put("10101","K");

        letterMap.put("10110","B");
        letterMap.put("10111","C");
        letterMap.put("11000","D");
        letterMap.put("11001","E");
        letterMap.put("11010","l");
        letterMap.put("11011","m");
        letterMap.put("11100","n");
        letterMap.put("11101","o");

        letterMap.put("11110","p");
        letterMap.put("11111","i");
        letterMap.put("100000","j");
        letterMap.put("100001","k");
        letterMap.put("100010","f");
        letterMap.put("100011","g");
        letterMap.put("100100","h");
        letterMap.put("100101","a");
        letterMap.put("100110","b");
        letterMap.put("100111","c");
        letterMap.put("101000","d");

        letterMap.put("101001","e");
        letterMap.put("101010","q");
        letterMap.put("101011","r");
        letterMap.put("101100","w");
        letterMap.put("101101","x");
        letterMap.put("101110","y");
        letterMap.put("101111","z");

        letterMap.put("110000","s");
        letterMap.put("110001","t");
        letterMap.put("110010","u");
        letterMap.put("110011","v");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(telegraph(scanner.nextLine()));
        }
    }

    /**
     * telegraph function
     * @param inputStr
     * @return
     */
    private static String telegraph(String inputStr){
        String errorTip = "ERROR";
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStr == null || inputStr.isEmpty()){
            return errorTip;
        }
        // 1 index
        int valueIndex;
        String[] letterArray = inputStr.trim().replace('-','0').replace('.','1').split("#");
        for (int i = 0; i < letterArray.length; i++) {
            valueIndex = letterArray[i].indexOf("1");
            if(valueIndex >= 0){
                String letterValue = letterMap.get(letterArray[i].substring(valueIndex));
                if(letterValue  == null){
                    return errorTip;
                }
                stringBuilder.append(letterValue);
            }else {
                //all 000
                stringBuilder.append("F");
            }
        }

        return stringBuilder.toString();
    }
}
