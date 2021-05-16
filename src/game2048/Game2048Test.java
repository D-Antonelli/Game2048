package game2048;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Game2048Test {
    @Test
    boolean mergeRow(int[] ints) {
        boolean result = mergeRow(new int[]{4, 4, 0, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{2, 2, 2, 2});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{4, 2, 2, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{0, 2, 2, 0});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{0, 2, 2, 2});
        Assert.assertTrue(result);

        result = mergeRow(new int[]{4, 0, 4, 0});
        Assert.assertFalse(result);

        result = mergeRow(new int[]{0, 0, 0, 0});
        Assert.assertFalse(result);
        return false;
    }
}
