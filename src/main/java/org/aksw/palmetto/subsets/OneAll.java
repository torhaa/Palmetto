package org.aksw.palmetto.subsets;

import com.carrotsearch.hppc.BitSet;

public class OneAll implements SubsetCreator {

    public SubsetDefinition getSubsetDefinition(int wordsetSize) {
	/*
	 * Code the combinations of elements not with ids but with bits. 01 is
	 * only the first element, 10 is the second and 11 is the combination of
	 * both.
	 */
	int conditions[][] = new int[wordsetSize][1];
	int segments[] = new int[wordsetSize];
	int bit = 1, pos = 0;
	int mask = (1 << wordsetSize) - 1;
	BitSet neededCounts = new BitSet(1 << wordsetSize);
	while (bit < mask) {
	    segments[pos] = bit;
	    neededCounts.set(bit);
	    conditions[pos] = new int[] { mask - bit };
	    bit = bit << 1;
	    ++pos;
	}
	neededCounts.set(mask);
	return new SubsetDefinition(segments, conditions, neededCounts);
    }
}
