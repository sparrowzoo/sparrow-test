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

import com.sparrow.core.arithmetic.gouping.Point;
import com.sparrow.core.arithmetic.gouping.Segment;
import com.sparrow.core.arithmetic.gouping.Coordinate;

import java.util.*;

/**
 * @author harry
 */
public class Main {
    static class IntegerCoordinate extends Coordinate<BusinessSegment, Integer> {

        public IntegerCoordinate(List<BusinessSegment> dataList) {
            super(dataList);
        }

        @Override
        public void section() {
            for (int i = 0; i < this.coordinate.size() - 1; i++) {
                Point current = this.coordinate.get(i);
                Point next = this.coordinate.get(i + 1);
                this.segments.add(new Segment(current, next));
            }
        }
    }

    enum CAT_TYPE {
        R0_CAT001,
        R0_CAT002,

        R1_CAT001,
        R1_CAT002,

        R2_FR_CAT001,
        R2_FR_CAT002,

        R2_GR_CAT001,
        R2_GR_CAT002,

        R2_AGR_CAT001,
        R2_AGR_CAT002
    }

    public static void main(String[] args) {
        List<BusinessSegment> list = new ArrayList<BusinessSegment>();

        //Long id, String type, Integer start, Integer end
        list.add(new BusinessSegment(1L, CAT_TYPE.R0_CAT001.name(), 1, 10));
        list.add(new BusinessSegment(2L, CAT_TYPE.R0_CAT001.name(), 1, 5));
        list.add(new BusinessSegment(3L, CAT_TYPE.R0_CAT002.name(), 1, 3));
        list.add(new BusinessSegment(3L, CAT_TYPE.R1_CAT001.name(), 1, 2999));
        list.add(new BusinessSegment(3L, CAT_TYPE.R1_CAT002.name(), 1, 10));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_FR_CAT001.name(), 99, 100));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_FR_CAT002.name(), 1, 5));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_GR_CAT001.name(), 2, 8));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_GR_CAT002.name(), 3, 5));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_AGR_CAT002.name(), 3, 10));
        list.add(new BusinessSegment(3L, CAT_TYPE.R2_AGR_CAT001.name(), 1, 5));

        Coordinate<BusinessSegment, Integer> coordinate = new IntegerCoordinate(list);
        coordinate.draw();
        coordinate.section();

        Map<Segment, List<BusinessSegment>> map = coordinate.aggregation();
        for (Segment segment : coordinate.getSegments()) {
            System.out.println(segment);
            System.out.println(map.get(segment));
        }
    }
}
