package com.exaltit.rpncalc;

import com.exaltit.rpncalc.domain.CodingGame;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CodingGameTest {

    @Test
    public void test_translate() {
        Assertions.assertEquals("havEllavo wavorld avOutlavook avaUx",
                CodingGame.translate("hEllo world Outlook aUx"));
    }
}
