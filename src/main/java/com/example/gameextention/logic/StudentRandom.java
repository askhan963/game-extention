package com.example.gameextention.logic;

import java.util.Random;

public final class StudentRandom {

    private StudentRandom() {
    }

    private static Random rand = new Random();

    /**
     * Returns a random integer in the range minval (inclusive) to maxval
     * (inclusive)
     */
    public static int randomInt(int minval, int maxval) {
        return minval + rand.nextInt(maxval - minval + 1);
    }

}
