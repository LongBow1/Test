package com.owner.me.mytest;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetObjectSizeTest {
    static List<String> list = new ArrayList(){{
        add("1");
        add("2");
    }};


    private static Instrumentation instrumentation;

    static long getObjectSize(Object o){
        return instrumentation.getObjectSize(o);
    }

    static class ClassTest{
        private int x;
        private int y;

    }

    static class ObjectSizeFetcher {
        private static Instrumentation instrumentation;

        public static void premain(String args, Instrumentation inst) {
            instrumentation = inst;
        }

        public static long getObjectSize(Object o) {
            return instrumentation.getObjectSize(o);
        }
    }

    public static byte bit2byte(String bString){
        byte result=0;
        for(int i=bString.length()-1,j=0;i>=0;i--,j++){
            result+=(Byte.parseByte(bString.charAt(i)+"")*Math.pow(2, j));
        }
        return result;
    }

    static void switchTest(String string) {
        switch (string) {
            case "1":
                System.out.println(string);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        //RamUsageEstimator.
        int objectAlignment = 2;
        try {
            final Class<?> beanClazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
            final Object hotSpotBean = ManagementFactory.newPlatformMXBeanProxy(
                    ManagementFactory.getPlatformMBeanServer(),
                    "com.sun.management:type=HotSpotDiagnostic",
                    beanClazz
            );
            final Method getVMOptionMethod = beanClazz.getMethod("getVMOption", String.class);
            final Object vmOption = getVMOptionMethod.invoke(hotSpotBean, "ObjectAlignmentInBytes");
            System.out.println(objectAlignment);
            objectAlignment = Integer.parseInt(
                    vmOption.getClass().getMethod("getValue").invoke(vmOption).toString()
            );
            System.out.println(objectAlignment);
            //supportedFeatures.add(JvmFeature.OBJECT_ALIGNMENT);
        } catch (Exception e) {
            // Ignore.
        }


        Random random = new Random();

        System.out.println(MessageFormat.format("{0}{2}",1,3,4));

        System.out.println(false && true || false);

        switchTest("1");
        //switchTest(null);
        System.out.println(Character.digit('a',16));
        byte[] bytes = new byte[]{'a'};
        System.out.println("string".charAt(0));

        char values[] = new char[10];
        System.out.println(Integer.toBinaryString(15));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02X",bytes[0]));
        System.out.println(String.format("%02x",bytes[0]));
        System.out.println(String.format("%02X",15));
        //System.out.println(list);
        //System.out.println(list.get(2));
        //System.out.println(ObjectSizeFetcher.getObjectSize(new ClassTest()));
    }
}
