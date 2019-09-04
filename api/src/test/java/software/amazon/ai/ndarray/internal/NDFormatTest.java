/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package software.amazon.ai.ndarray.internal;

import org.testng.Assert;
import org.testng.annotations.Test;
import software.amazon.ai.ndarray.NDArray;
import software.amazon.ai.ndarray.NDManager;
import software.amazon.ai.ndarray.types.DataType;
import software.amazon.ai.ndarray.types.Shape;

public class NDFormatTest {

    private static final String LF = System.getProperty("line.separator");

    @Test
    public void testUint8Format() {
        try (NDManager manager = NDManager.newBaseManager()) {
            byte[] data = {Byte.MAX_VALUE, Byte.MIN_VALUE, 1};
            NDArray array = manager.create(new Shape(3), DataType.UINT8);
            array.set(data);

            String str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (3) cpu(0) uint8" + LF + "[0x7F, 0x80, 0x01]" + LF);
        }
    }

    @Test
    public void testInt8Format() {
        try (NDManager manager = NDManager.newBaseManager()) {
            byte[] data = {Byte.MAX_VALUE, Byte.MIN_VALUE, 1};
            NDArray array = manager.create(data);

            String str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (3) cpu(0) int8" + LF + "[ 127, -128,    1]" + LF);
        }
    }

    @Test
    public void testIntFormat() {
        try (NDManager manager = NDManager.newBaseManager()) {
            int[] data = {Integer.MAX_VALUE, Integer.MIN_VALUE, 1};
            NDArray array = manager.create(data);

            String str = NDFormat.format(array);
            Assert.assertEquals(
                    str,
                    "ND: (3) cpu(0) int32"
                            + LF
                            + "[ 2.14748365e+09, -2.14748365e+09,  1.00000000e+00]"
                            + LF);

            data = new int[] {1, -256, 1000};
            array = manager.create(data);
            str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (3) cpu(0) int32" + LF + "[   1, -256, 1000]" + LF);
        }
    }

    @Test
    public void testLongFormat() {
        try (NDManager manager = NDManager.newBaseManager()) {
            long[] data = {Long.MAX_VALUE, Long.MIN_VALUE, 1};
            NDArray array = manager.create(data);

            String str = NDFormat.format(array);
            Assert.assertEquals(
                    str,
                    "ND: (3) cpu(0) int64"
                            + LF
                            + "[ 9.22337204e+18, -9.22337204e+18,  1.00000000e+00]"
                            + LF);
        }
    }

    @Test
    public void testFloat64Format() {
        try (NDManager manager = NDManager.newBaseManager()) {
            double[] data = {Double.NEGATIVE_INFINITY, Double.MAX_VALUE, Double.NaN, -1};
            NDArray array = manager.create(data);

            String str = NDFormat.format(array);
            Assert.assertEquals(
                    str,
                    "ND: (4) cpu(0) float64"
                            + LF
                            + "[       -inf,  1.79769313e+308,         nan, -1.00000000e+00]"
                            + LF);

            data = new double[] {Double.NEGATIVE_INFINITY, Double.NaN, -1};
            array = manager.create(data);
            str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (3) cpu(0) float64" + LF + "[-inf,  nan,  -1.]" + LF);

            data = new double[] {123., 0.123, Double.NEGATIVE_INFINITY};
            array = manager.create(data, new Shape(3, 1));
            str = NDFormat.format(array);
            Assert.assertEquals(
                    str,
                    "ND: (3, 1) cpu(0) float64"
                            + LF
                            + "[[123.   ],"
                            + LF
                            + " [  0.123],"
                            + LF
                            + " [   -inf],"
                            + LF
                            + "]"
                            + LF);

            data = new double[] {0.123, Double.NEGATIVE_INFINITY};
            array = manager.create(data);
            str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (2) cpu(0) float64" + LF + "[0.123,  -inf]" + LF);

            data = new double[] {1., 2., 100};
            array = manager.create(data);
            str = NDFormat.format(array);
            Assert.assertEquals(str, "ND: (3) cpu(0) float64" + LF + "[  1.,   2., 100.]" + LF);
        }
    }
}