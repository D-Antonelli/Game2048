package game2048.logic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class UtilitiesTest {

    @Test
    void getRandomNumber() {
        int number = Utilities.getRandomNumber(9);
        Assert.assertFalse(number > 9);
        Assert.assertFalse(number < 0);
    }

}