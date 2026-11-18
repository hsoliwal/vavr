/*
 * Copyright 2014-2026 Vavr, https://vavr.io
 * Licensed under the Apache License, Version 2.0.
 */
package io.vavr.collection;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/** Physical ownership regressions for immutable BitSet word compaction. */
public class BitSetShrinkReuseTest {

    private static BitSetModule.AbstractBitSet<Integer> owner() {
        return (BitSetModule.AbstractBitSet<Integer>) BitSet.of(1, 70, 130);
    }

    @Test
    public void alreadyCompactOwnedCopyIsReused() {
        long[] copy = {1L, 2L, Long.MIN_VALUE};
        assertSame(copy, owner().shrink(copy));
        assertArrayEquals(new long[] {1L, 2L, Long.MIN_VALUE}, copy);
    }

    @Test
    public void trailingZeroWordsStillProduceDistinctCompactArray() {
        long[] copy = {7L, 0L, 0L};
        long[] compact = owner().shrink(copy);
        assertNotSame(copy, compact);
        assertArrayEquals(new long[] {7L}, compact);
        assertArrayEquals(new long[] {7L, 0L, 0L}, copy);
    }

    @Test
    public void allZeroWordsCompactToEmpty() {
        long[] copy = {0L, 0L, 0L};
        assertArrayEquals(new long[0], owner().shrink(copy));
        assertArrayEquals(new long[] {0L, 0L, 0L}, copy);
    }

    @Test
    public void removeAndRemoveAllDoNotMutateImmutableParent() {
        BitSet<Integer> original = BitSet.of(1, 70, 130);
        BitSet<Integer> removed = original.remove(70);
        assertEquals(BitSet.of(1, 130), removed);
        assertEquals(BitSet.of(1, 70, 130), original);

        BitSet<Integer> bulk = original.removeAll(List.of(1, 70));
        assertEquals(BitSet.of(130), bulk);
        assertEquals(BitSet.of(1, 70, 130), original);

        BitSet<Integer> compacted = original.remove(130);
        assertEquals(BitSet.of(1, 70), compacted);
        assertEquals(BitSet.of(1, 70, 130), original);
    }
}
