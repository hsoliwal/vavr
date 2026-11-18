/* ____  ______________  ________________________  __________
 * \   \/   /      \   \/   /   __/   /      \   \/   /      \
 *  \______/___/\___\______/___/_____/___/\___\______/___/\___\
 *
 * Copyright 2014-2026 Vavr, https://vavr.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vavr.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

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
