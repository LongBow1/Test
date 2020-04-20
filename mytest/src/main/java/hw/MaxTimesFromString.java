package hw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MaxTimesFromString {
    public static void main(String[] args)
    {

        String str ="abcdABCdE";
        char[] StrArr = str.toCharArray();
        Map<Character,Integer> map = MapFunction(StrArr);
        char ch = FindMapMaxValue(map);
    }

    public static Map<Character,Integer> MapFunction(char[] StrArr)
    {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        if(!(StrArr == null || StrArr.length == 0))
        {
            for(int i = 0;i<StrArr.length;i++)
            {
                if(null != map.get(StrArr[i]))
                {
                    map.put(StrArr[i],map.get(StrArr[i])+1);
                }
                else
                {
                    map.put(StrArr[i],1);
                }
            }
        }
        return map;
    }
    public static Character FindMapMaxValue(Map<Character,Integer> map)
    {
        Set<Character> keys = map.keySet();
        Iterator keys_Itera = keys.iterator();
        Character maxkey = (Character)keys_Itera.next();
        int maxvalue = map.get(maxkey);

        while(keys_Itera.hasNext())
        {
            Character temp = (Character)keys_Itera.next();
            if(maxvalue<map.get(temp))
            {   maxkey = temp;
                maxvalue = map.get(temp);
            }
        }
        System.out.println("maxkey is --->: "+maxkey+"  "+
                "maxvalue is ---> :"+maxvalue);
        return maxkey;
    }
}
