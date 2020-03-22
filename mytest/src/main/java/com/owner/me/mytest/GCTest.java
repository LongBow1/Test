package com.owner.me.mytest;

import sun.management.ManagementFactoryHelper;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCTest {
    public static void main(String[] args) {
        byte[] placeHolder = new byte[64*1024*1024];
        System.out.println(placeHolder.length/1024);
        //System.gc();

        placeHolder = null;
        System.gc();
    }
    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        return ManagementFactoryHelper.getGarbageCollectorMXBeans();
    }

    /*private static void reportGC(ReportAPI reporter) {
        long fullCount = 0, fullTime = 0, youngCount = 0, youngTime = 0;
        List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : gcs) {
            switch (GarbageCollectorName.of(gc.getName())) {
                case MarkSweepCompact:
                case PSMarkSweep:
                case ConcurrentMarkSweep:
                    fullCount += gc.getCollectionCount();
                    fullTime += gc.getCollectionTime();
                    break;
                case Copy:
                case ParNew:
                case PSScavenge:
                    youngCount += gc.getCollectionCount();
                    youngTime += gc.getCollectionTime();
                    break;
            }
            //todo your deal code， perfcounter report or write log here
        }
    }*/
}
