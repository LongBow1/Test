package effective;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MixTest {
    public static void main(String[] args) {
        Set<EqualsObject> hashSet = new HashSet<>();

        Integer[] aar = {12,3};
        EqualsObject a = new EqualsObject(1,"one");
        EqualsObject b = new EqualsObject(1,"one");
        EqualsObject c = new EqualsObject(1,"one");
        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);
        System.out.println(hashSet.size());

        ArrayList masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");
        List branchList = masterList.subList(0,3);
        System.out.println(branchList);
        System.out.println(masterList);
        masterList.remove(0);
        System.out.println(masterList);
        masterList.add("ten");
        System.out.println(masterList);
        masterList.clear();
        System.out.println(masterList);

        System.out.println(branchList);
        branchList.clear();
        System.out.println(branchList);
        branchList.add("six");
        branchList.add("seven");
        System.out.println(branchList);
        branchList.remove(0);
        System.out.println(branchList);

        for(Object t:branchList){
            System.out.println(t);
        }
        System.out.println(masterList);
    }
}
