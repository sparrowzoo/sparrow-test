package com.sparrow.jdk.threadlocal;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by harry
 */
public class ShareObject {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ShareObject shareObject = new ShareObject();
        shareObject.obj();
    }

    private void obj() {
        ReentrantLock lock = this.lock;
        lock.lock();
    }
}
