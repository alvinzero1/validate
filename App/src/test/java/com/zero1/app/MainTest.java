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
        Main m = new Main(primaryPath, targetPath);
        assertEquals(5, m.getHashmapSize());
        assertEquals(1, m.getMatchedArr().size());
        assertEquals(9, m.getPrimaryLineCount());
        assertEquals(3, m.getTargetFileChkCount());
    }

    @Test
    public void testMainHashSizeMode1() {
        Main m = new Main(primaryPath, targetPath, 1);
        assertEquals(1, m.getMode());
        assertEquals(7, m.getHashmapSize());
    }

    @Test
    public void testMode2() {
        Main m = new Main(primaryPath, targetPath, 2);
        assertEquals(2, m.getMode());
        assertEquals(7, m.getHashmapSize());
        assertEquals(0, m.getMatchedFilesOnly().size());
        assertEquals(1, m.matchedNameCount);
    }

    @Test
    public void testErrPrint() {
        Main m = new Main(primaryPath, targetPath);
        m.setErrPrint("test");
        m.getErrPrint();
//        m.getErrPrint().forEach(s -> {
//            System.out.println(">>MainTest>> "+s);
//        });
        assertEquals(6, m.getErrPrint().size());
    }
}
