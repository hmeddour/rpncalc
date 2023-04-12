package com.exaltit.rpncalc;

import com.exaltit.rpncalc.domain.HackerRank;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class HackerRankTest {

    @Test
    public void test_get_title_movies() {
        String[] spidermanTitles = new String[]{"Fighting, Flying and Driving: The Stunts of Spiderman 3",
                "Italian Spiderman", "Spiderman, Spiderman 5",
                "Spiderman in Cannes", "Superman, Spiderman or Batman"};

        Assertions.assertEquals(Arrays.toString(spidermanTitles),
                Arrays.toString(HackerRank.getMovieTitles("spiderman")));
    }

    @Test
    public void test_max_xor() {
        int lo = 2;
        int hi = 4;
        int k = 8;
        Assertions.assertEquals(7, HackerRank.maxXor(lo, hi, k));
    }
}
