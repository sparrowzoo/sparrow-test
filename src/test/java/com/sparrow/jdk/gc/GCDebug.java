package com.sparrow.jdk.gc;

/**
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGClogFiles=1
 * -XX:GCLogFileSize=1M
 * -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:+HeapDumpBeforeFullGC
 * -XX:+HeapDumpBeforeFullGC
 * -XX:HeapDumpPath=d://logs/dump.hprof
 * -XX:ErrorFile=d://logs/java_error_%p.log
 * -Xms10m
 * -Xmx10m
 * -Xmn10m
 * -Xss128k
 * -Xloggc:d://logs/gc_%p.log
 * -----------------------------------------------
 * -XX:+UseGCLogFileRotation  启用GC日志文件的自动转储 (Since Java)
 * -XX:NumberOfGClogFiles=1  GC日志文件的循环数目 (Since Java)
 * -XX:GCLogFileSize=1M  控制GC日志文件的大小 (Since Java)
 * -XX:+PrintGCDetails (-verbose:gc & -XX:+PrintGC)
 * -XX:+HeapDumpBeforeFullGC  Full GC前dump
 * -XX:+HeapDumpAfterFullGC    在Full GC后dump
 * -XX:HeapDumpPath=d://logs/dump.hprof  设置Dump保存的路径
 * -Xmn2g 设置年轻代大小为2G。整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
 */
public class GCDebug {
    public static void main(String[] args) {
        int memoryLength = 1000;
    }
}
