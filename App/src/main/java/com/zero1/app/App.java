/* www.Zero1.Sg 2022Dec */
package com.zero1.app;

public class App {

    public static void main(String args[]) {

        var logfile = "logmessages.txt";

        System.out.println("SubString key and name, to hashmap.");

//        String primaryPath = ("D:\\temp");
//        String targetPath = ("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201808");
//        var m = new Main(primaryPath, targetPath, 2);
//        m.addTargetPath("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201809");
//
        String primaryPath = "D:\\temp2";
        String targetPath = "C:\\testdata\\test1";
        var m = new Main(primaryPath, targetPath);

        System.out.println(m.info);
        System.out.println("> row count: " + m.getPrimaryLineCount());
        System.out.println("> Hashmap size: " + m.getHashmapSize());
        System.out.println(">> files count: " + m.getTargetFileChkCount());

        if (m.getMode() != 2) {
            m.setErrPrint(">> matched found: " + m.getMatchedArr().size());
        }

        m.getErrPrint().forEach(s -> {
            System.out.println(s);
        });
        System.out.println("\nMatched 'folder file':");
        m.getMatchedArr().forEach(s -> {
            System.out.println(s);
        });

//        m.printErrlogNMatchedToFile(logfile); // OR
        if (m.getMode() == 2) {
            System.out.println("""
                               Portal filename   <> against the txt files:
                               only compare filename.length > 20, ie. ignore  image.jpg, ...
                               """);
            m.getMatchedFilesOnly().forEach(s -> {
                System.out.println(s);
            });
            System.out.println(">>> matchedNameCount: " + m.matchedNameCount);
        }

        System.out.println("Completed. Check " + logfile);
    }
}

/*
--- exec-maven-plugin:3.0.0:exec (default-cli) @ app ---
SubString key and name, to hashmap.

> primaryPath: D:\temp2
>> targetPath: C:\testdata\test1
> row count: 9
> Hashmap size: 5
>> files count: 4
>> matched found: 2
> key lgth err: c:\\users\alvinng\verify\test\test1\z01r002\zero1.png
 < md5chksum.txt
> StringException: photoid_20181118.zip md5 c8139bf1e2aff9f95c5a238a2a0656c6
 < md5chksum.txt
>> matched found: 2

Matched 'folder file':
C:\testdata\test1\CB718C312BA1B3622ECFDCBF727465F2\duke.png
C:\testdata\test1\CB718C312BA1B3622ECFDCBF727465F2\z01r002.png
Completed. Check logmessages.txt
 */
