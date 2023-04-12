package com.exaltit.rpncalc;

import com.exaltit.rpncalc.domain.MasterDevFrance;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MasterDevFranceTest {

    @Test
    public void test_pierre_feuille_ciseaux() {
        Assertions.assertEquals("P", MasterDevFrance.pfc("PFFCPC"));
    }
}
