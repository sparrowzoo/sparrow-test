/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparrow.facade.compress;

import com.sparrow.container.Container;
import com.sparrow.container.impl.SparrowContainerImpl;
import com.sparrow.utility.CompressUtility;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * @author by harry
 */
public class CompressTest {
    @Test
    public void unzip() throws IOException {
        String path = "/Users/harry/sparrow-test-zip/";
        String unzipFile = path + "unzip/sparrow-test.txt";
        Container container = new SparrowContainerImpl("/file_config.xml");
        container.init();
        CompressUtility.unzip(path + "/unzip/Q555.PROD.S01FTTC.D160926.T2000.zip");
        CompressUtility.unzip(path + "/unzip/Q555.PROD.S01FTTC.D160926.T2000.zip",unzipFile);
    }


    @Test
    public void zip() throws IOException {
        String path = "/Users/harry/sparrow-test-zip/";
        String unzipFile = path + "unzip/computer.jpg";
        Container container = new SparrowContainerImpl("/file_config.xml");
        container.init();

        CompressUtility.zip(unzipFile,new FileOutputStream(path+"unzip/computer.zip"));
    }
}
