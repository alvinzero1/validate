package com.zero1.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    String primaryPath = "..\\..\\verify\\app\\testSample";
    String targetPath = "..\\..\\verify\\app\\testSample";
    Main m = new Main(primaryPath, targetPath);

    public MainTest() {
    }

    @Test
    public void testMainHashSize() {
        m = new Main(primaryPath, targetPath);
        assertEquals(5, m.getHashmap().size());
    }

    @Test
    public void testMainHashSizeMode1() {
        m = new Main(primaryPath, targetPath, 1);
        assertEquals(1, m.getMode());
        assertEquals(7, m.getHashmap().size());
    }

    @Test
    public void testMainMatchedQty() {
        assertEquals(1, m.getMatchedArr().size());
    }

    @Test
    public void testCount() {
        assertEquals(9, m.getPrimaryLineCount());
        assertEquals(3, m.getTargetFileChkCount());
    }

    @Test
    public void testErrPrint() {
        m.setErrPrint("test");
        assertEquals(8, m.getErrPrint().size());
    }
}
