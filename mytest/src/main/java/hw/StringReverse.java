package hw;

public class StringReverse {
    public static void main(String[] args) {
        String s = "the  sky is blue";
        System.out.println(reverString(s));
        Integer a = 5;
    }

    private static String reverString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] arr = s.split(" ");
        for (int i = arr.length -1; i >= 0 ; i--) {
            String tmp =arr[i].trim();
            if(!tmp.isEmpty()){
                stringBuilder.append(tmp).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }
}
