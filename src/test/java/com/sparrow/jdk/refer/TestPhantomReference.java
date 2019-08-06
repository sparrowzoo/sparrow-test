package com.sparrow.jdk.refer;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class TestPhantomReference {
    static class StreamWrap {
        private InputStream inputStream;
        private byte[] bytes;

        public StreamWrap(InputStream inputStream, byte[] bytes) {
            this.inputStream = inputStream;
            this.bytes = bytes;
        }
    }

    public static void main(String[] args) throws IOException {
        ReferenceQueue<Byte[]> referenceQueue = new ReferenceQueue<>();

        List<PhantomReference<Byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            InputStream file = new FileInputStream(new File("D:\\data\\logs\\mjyx\\error.log"));
            StreamWrap streamWrap = new StreamWrap(file, new byte[2048]);
            PhantomReference phantomReference = new PhantomReference(streamWrap, referenceQueue);
            list.add(phantomReference);
            Reference reference = referenceQueue.poll();
            if (reference != null) {
                System.out.println(reference);
                //todo @Override  PhantomReference method
                /**
                 * static class ConnectionPhantomReference extends PhantomReference<ConnectionImpl> {
                 *         private NetworkResources io;
                 *
                 *         ConnectionPhantomReference(ConnectionImpl connectionImpl, ReferenceQueue<ConnectionImpl> q) {
                 *             super(connectionImpl, q);
                 *
                 *             try {
                 *                 this.io = connectionImpl.getIO().getNetworkResources();
                 *             } catch (SQLException e) {
                 *                 // if we somehow got here and there's really no i/o, we deal with it later
                 *             }
                 *         }
                 *
                 *         void cleanup() {
                 *             if (this.io != null) {
                 *                 try {
                 *                     this.io.forceClose();
                 *                 } finally {
                 *                     this.io = null;
                 *                 }
                 *             }
                 *         }
                 *     }
                 */
                //((InputStream)reference.get()).close();
                System.out.println(i);
                System.exit(0);
            }
        }
    }
}
