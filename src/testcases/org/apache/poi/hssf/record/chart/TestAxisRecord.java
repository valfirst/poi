/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hssf.record.chart;


import static org.apache.poi.hssf.record.TestcaseRecordInputStream.confirmRecordEncoding;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.poi.hssf.record.TestcaseRecordInputStream;
import org.junit.jupiter.api.Test;

/**
 * Tests the serialization and deserialization of the AxisRecord
 * class works correctly.  Test data taken directly from a real
 * Excel file.
 */
final class TestAxisRecord {
    byte[] data = new byte[] {
        (byte)0x00,(byte)0x00,                               // type
        (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
        (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
        (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
        (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00
    };

    @Test
    void testLoad() {
        AxisRecord record = new AxisRecord(TestcaseRecordInputStream.create(0x101d, data));
        assertEquals( AxisRecord.AXIS_TYPE_CATEGORY_OR_X_AXIS, record.getAxisType());
        assertEquals( 0, record.getReserved1());
        assertEquals( 0, record.getReserved2());
        assertEquals( 0, record.getReserved3());
        assertEquals( 0, record.getReserved4());

        assertEquals( 4 + 18, record.getRecordSize() );
    }

    @SuppressWarnings("squid:S2699")
    @Test
    void testStore() {
        AxisRecord record = new AxisRecord();
        record.setAxisType( AxisRecord.AXIS_TYPE_CATEGORY_OR_X_AXIS );
        record.setReserved1( 0 );
        record.setReserved2( 0 );
        record.setReserved3( 0 );
        record.setReserved4( 0 );

        byte [] recordBytes = record.serialize();
        confirmRecordEncoding(AxisRecord.sid, data, recordBytes);
    }
}
