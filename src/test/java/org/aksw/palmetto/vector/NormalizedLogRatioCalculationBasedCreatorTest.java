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
package org.aksw.palmetto.vector;

import java.util.Arrays;
import java.util.Collection;

import org.aksw.palmetto.calculations.probbased.LogBasedCalculation;
import org.aksw.palmetto.calculations.probbased.NormalizedLogRatioCoherenceCalculation;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NormalizedLogRatioCalculationBasedCreatorTest extends AbstractProbCalcBasedVectorCreatorTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays
                .asList(new Object[][] {
                        /*
                         * word1 1 1 1
                         * 
                         * word2 0 1 1
                         * 
                         * word3 0 1 1
                         * 
                         * vector1 log(1+eps)/-log(1+eps) 0 0
                         * 
                         * vector2 0 log(3/2)/-log(2/3) log(3/2)/-log(2/3)
                         * 
                         * vector3 0 log(3/2)/-log(2/3) log(3/2)/-log(2/3)
                         */
                        {
                                3,
                                new double[] { 0, 1.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0 },
                                new double[][] {
                                        { -1, 0, 0 },
                                        { 0, Math.log(3.0 / 2.0) / (-Math.log(2.0 / 3.0)),
                                                Math.log(3.0 / 2.0) / (-Math.log(2.0 / 3.0)) },
                                        { 0, Math.log(3.0 / 2.0) / (-Math.log(2.0 / 3.0)),
                                                Math.log(3.0 / 2.0) / (-Math.log(2.0 / 3.0)) } }, "V_nlr_(0.0)" },

                        /*
                         * word1 0 1 1
                         * 
                         * word2 1 0 1
                         * 
                         * word3 1 1 0
                         * 
                         * vector1 log(3/2)/-log(2/3) log(3/4)/-log(1/3)
                         * log(3/4)/-log(1/3)
                         * 
                         * vector2 log(3/4)/-log(1/3) log(3/2)/-log(2/3)
                         * log(3/4)/-log(1/3)
                         * 
                         * vector3 log(3/4)/-log(1/3) log(3/4)/-log(1/3)
                         * log(3/2)/-log(2/3)
                         */{
                                3,
                                new double[] { 0, 2.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 2.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 0 },
                                new double[][] {
                                        { Math.log(3.0 / 2.0) / -Math.log(2.0 / 3.0),
                                                Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0),
                                                Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0) },
                                        { Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0),
                                                Math.log(3.0 / 2.0) / -Math.log(2.0 / 3.0),
                                                Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0) },
                                        { Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0),
                                                Math.log(3.0 / 4.0) / -Math.log(1.0 / 3.0),
                                                Math.log(3.0 / 2.0) / -Math.log(2.0 / 3.0) } }, "V_nlr_(0.0)" },
                        /*
                         * word1 0 0 0 1
                         * 
                         * word2 0 1 0 1
                         * 
                         * word3 0 0 1 1
                         * 
                         * vector1 log(4)/-log(1/4) log(2)/-log(1/4)
                         * log(2)/-log(1/4)
                         * 
                         * vector2 log(2)/-log(1/4) log(2)/-log(1/2) 0
                         * 
                         * vector3 log(2)/-log(1/4) 0 log(2)/-log(1/2)
                         */
                        {
                                3,
                                new double[] { 0, 0.25, 0.5, 0.25, 0.5, 0.25, 0.25, 0.25 },
                                new double[][] {
                                        { Math.log(4.0) / (-Math.log(1.0 / 4.0)),
                                                Math.log(2.0) / (-Math.log(1.0 / 4.0)),
                                                Math.log(2.0) / (-Math.log(1.0 / 4.0)) },
                                        { Math.log(2.0) / (-Math.log(1.0 / 4.0)),
                                                Math.log(2.0) / (-Math.log(1.0 / 2.0)), 0 },
                                        { Math.log(2.0) / (-Math.log(1.0 / 4.0)), 0,
                                                Math.log(2.0) / (-Math.log(1.0 / 2.0)) } }, "V_nlr_(0.0)" },
                        /*
                         * word1 1 0 0 0
                         * 
                         * word2 0 1 0 1
                         * 
                         * word3 0 0 1 1
                         * 
                         * vector1 log(4)/-log(1/4) log(8 * eps)/-log(eps) log(8
                         * * eps)/-log(eps)
                         * 
                         * vector2 log(8 * eps)/-log(eps) log(2)/-log(1/2) 0
                         * 
                         * vector3 log(8 * eps)/-log(eps) 0 log(2)/-log(1/2)
                         */
                        {
                                3,
                                new double[] { 0, 0.25, 0.5, 0, 0.5, 0, 0.25, 0 },
                                new double[][] {
                                        {
                                                Math.log(4.0) / (-Math.log(1.0 / 4.0)),
                                                Math.log(8 * LogBasedCalculation.EPSILON)
                                                        / -Math.log(LogBasedCalculation.EPSILON),
                                                Math.log(8 * LogBasedCalculation.EPSILON)
                                                        / -Math.log(LogBasedCalculation.EPSILON) },
                                        {
                                                Math.log(8 * LogBasedCalculation.EPSILON)
                                                        / -Math.log(LogBasedCalculation.EPSILON),
                                                Math.log(2.0) / (-Math.log(1.0 / 2.0)), 0 },
                                        {
                                                Math.log(8 * LogBasedCalculation.EPSILON)
                                                        / -Math.log(LogBasedCalculation.EPSILON), 0,
                                                Math.log(2.0) / (-Math.log(1.0 / 2.0)) } }, "V_nlr_(0.0)" } });
    }

    public NormalizedLogRatioCalculationBasedCreatorTest(int wordsetSize, double[] probabilities,
            double[][] expectedVectors, String expectedCreatorName) {
        super(new NormalizedLogRatioCoherenceCalculation(0), wordsetSize, probabilities, expectedVectors,
                expectedCreatorName);
    }
}
