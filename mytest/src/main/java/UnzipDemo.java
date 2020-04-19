import java.util.Scanner;

public class UnzipDemo {
    public static void main(String[] args){
        Integer a = 128;
        Integer b =128;
        System.out.println();

        //System.out.println((int)'0');
        //System.out.println((int)'9');
        //System.out.println(unzip("2abb4cd"));
        /*Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String inStr = in.nextLine();
            System.out.println(unzip(inStr));
        }*/

    }
    private static String unzip(String instr){
        String errorRes = "!error";
        StringBuilder unzipBuilder = new StringBuilder();
        char[] inChars = instr.toCharArray();
        int numCount = 0;
        //最后一位不能为数字
        if(inChars[inChars.length-1] <= '9'){
            return errorRes;
        }
        for (int i = 0; i < inChars.length; i++) {
            char tmpChar = inChars[i];
            if(tmpChar < '0' || tmpChar > 'z' || (tmpChar > '9' && tmpChar < 'a')){
                return errorRes;
            }
            //字母后不能为0
            if(i > 0 && inChars[i-1] > '9' && tmpChar == '0'){
                return errorRes;
            }
            if(i >= 2 && tmpChar == inChars[i-1] && tmpChar == inChars[i-2]){
                return errorRes;
            }
            //if(i >= 2 && tmpChar >= 'a' && inChars[i-2])
            if(tmpChar <= '9'){
                numCount++;
            }
            //valid
            if(tmpChar >= 'a' && tmpChar <= 'z'){
                if(numCount > 0){
                    int charRepeat = Integer.valueOf(instr.substring(i - numCount,i));
                    if(charRepeat < 3){
                        return errorRes;
                    }
                    for (int j = 0; j < charRepeat; j++) {
                        unzipBuilder.append(tmpChar);
                    }
                    numCount = 0;
                }else{
                    unzipBuilder.append(tmpChar);
                }
            }

        }
        return unzipBuilder.toString();
    }
}
