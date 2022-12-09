package com.zero1.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    String primaryPath = "..\\..\\verify\\app\\testSample";
    String targetPath = "..\\..\\verify\\app\\testSample";

    public MainTest() {
    }

    @Test
    public void testMainHashSize() {
        var m = new Main(primaryPath, targetPath);
        assertEquals(5, m.getHashmap().size());
    }

    @Test
    public void testMainMatchedQty() {
        var m = new Main(primaryPath, targetPath);
        assertEquals(1, m.getMatchedArr().size());
    }
}
