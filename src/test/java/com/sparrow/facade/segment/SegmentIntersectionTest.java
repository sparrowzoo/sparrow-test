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

package com.sparrow.facade.segment;

import com.sparrow.core.arithmetic.gouping.Segment;

import java.util.Date;

/**
 * Created by harry on 2018/1/22.
 */
public class SegmentIntersectionTest {
    public static void main(String[] args) {
        Segment s1=new Segment(new Date("2018/01/05"),new Date("2018/01/10"));
        Segment s2=new Segment(new Date("2018/01/06"),new Date("2018/01/20"));
        System.out.println(s1.intersection(s2));

        s1=new Segment(new Date("2018/01/05"),new Date("2018/01/10"));
        s2=new Segment(new Date("2018/01/05"),new Date("2018/01/15"));
        System.out.println(s1.intersection(s2));

        s1=new Segment(new Date("2018/01/05"),new Date("2018/01/10"));
        s2=new Segment(new Date("2018/01/01"),new Date("2018/01/04"));
        System.out.println(s1.intersection(s2));

        s1=new Segment(new Date("2018/01/05"),new Date("2018/01/10"));
        s2=new Segment(new Date("2018/01/04"),new Date("2018/01/12"));
        System.out.println(s1.union(s2));

        s1=new Segment(5,10);
        s2=new Segment(4,7);
        System.out.println(s1.union(s2));

        s1=new Segment(8,12);
        s2=new Segment(8,12);
        System.out.println(s1.union(s2));

        //s1=new Segment(new Date("2018/01/01"),new Date("2018/01/10"));
        //s2=new Segment(new Date("2018/01/05"),new Date("2018/01/10"));
        System.out.println(s1.equals(s2));
    }
}
