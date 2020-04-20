import java.util.Scanner;

public class UnzipDemo {
    public static void main(String[] args){
        Integer a = 128;
        Integer b =128;
        System.out.println();

        //System.out.println((int)'0');
        //System.out.println((int)'9');
        //System.out.println(unzip("2abb4cd"));
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String inStr = in.nextLine();
            System.out.println(unzip(inStr));
        }

    }

    /**
     * unzip method
     * @param inputStr input string
     * @return error tip or unzip string
     */
    private static String unzip(String inputStr){
        String errorRes = "!error";
        //input cannot be empty or null
        if(inputStr == null ||inputStr.isEmpty()){
            return errorRes;
        }
        //append char
        StringBuilder unzipBuilder = new StringBuilder();
        //input convert to char array
        char[] inChars = inputStr.toCharArray();
        //repeat count for every char
        int numCount = 0;
        //check 1: first char cannot be zero 0 and last char cannot be digit
        if(inChars[0] == '0' || inChars[inChars.length-1] <= '9'){
            return errorRes;
        }
        //iterate chars
        for (int i = 0; i < inChars.length; i++) {
            char tmpChar = inChars[i];
            //invalid char exclude
            if(tmpChar < '0' || tmpChar > 'z' || (tmpChar > '9' && tmpChar < 'a')){
                return errorRes;
            }
            //0 cannot follow letters
            if(i > 0 && inChars[i-1] >= 'a' && tmpChar == '0'){
                return errorRes;
            }
            //more than three same letter should compact
            if(i >= 2 && tmpChar == inChars[i-1] && tmpChar == inChars[i-2]){
                return errorRes;
            }
            //count digit length
            if(tmpChar <= '9'){
                numCount++;
            }
            //unzip letters : divide letter and digit
            if(tmpChar >= 'a' && tmpChar <= 'z'){
                if(numCount > 0){
                    int charRepeat = Integer.valueOf(inputStr.substring(i - numCount,i));
                    //less than 3 is invalid: compact only for letters appears more than three times
                    if(charRepeat < 3){
                        return errorRes;
                    }
                    for (int j = 0; j < charRepeat; j++) {
                        unzipBuilder.append(tmpChar);
                    }
                    //reset for next time use
                    numCount = 0;
                }else{
                    //one letter case, append
                    unzipBuilder.append(tmpChar);
                }
            }
        }
        return unzipBuilder.toString();
    }
}
