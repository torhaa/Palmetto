/**
 * Copyright (C) 2014 Michael Röder (michael.roeder@unister.de)
 *
 * Licensed under the Creative Commons Attribution-NonCommercial 4.0
 * International Public License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Unless required by applicable law or agreed to in writing, a file
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aksw.palmetto.calculations.vectorbased;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VectorCreationTest {

    private static final double DOUBLE_PRECISION_DELTA = 0.00000001;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 1, new double[] { 1, 0, -1 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 2, new double[] { 1, 2, 3 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 4, new double[] { 1, 1, 1 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 3, new double[] { 2, 2, 2 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 5, new double[] { 2, 1, 0 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 6, new double[] { 2, 3, 4 } },
                { new double[][] { { 1, 0, -1 }, { 1, 2, 3 }, { 1, 1, 1 } }, 7, new double[] { 3, 3, 3 } } });
    }

    private double vectors[][];
    private int vectorId;
    private double expectedVector[];

    public VectorCreationTest(double[][] vectors, int vectorId, double[] expectedVector) {
        this.vectors = vectors;
        this.vectorId = vectorId;
        this.expectedVector = expectedVector;
    }

    @Test
    public void test() {
        AbstractVectorBasedCalculation calculation = new CosinusBasedCalculation();
        Assert.assertArrayEquals(expectedVector, calculation.createVector(vectorId, vectors), DOUBLE_PRECISION_DELTA);
    }
}
