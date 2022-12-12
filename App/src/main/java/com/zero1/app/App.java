/* www.Zero1.Sg 2022Dec */
package com.zero1.app;

public class App {

    public static void main(String args[]) {

        var logfile = "logmessages.txt";

        System.out.println("SubString key and name, to hashmap.");

        String primaryPath = ("D:\\temp");
        String targetPath = ("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201808");
        var m = new Main(primaryPath, targetPath, 1);
        m.addTargetPath("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201809");
//
//        String primaryPath = "D:\\temp2";
//        String targetPath = "C:\\testdata\\test1";
//        var m = new Main(primaryPath, targetPath);

        System.out.println("> row count: " + m.getPrimaryLineCount());
        System.out.println("> Hashmap size: " + m.getHashmap().size());
        System.out.println(">> files count: " + m.getTargetFileChkCount());

        m.setErrPrint(">> matched found: " + m.getMatchedArr().size());

//        m.printErrlogNMatchedToFile(logfile); // OR
        m.getErrPrint().forEach(s -> {
            System.out.println(s);
        });
        m.getMatchedArr().forEach(s -> {
            System.out.println(s);
        });

        System.out.println("Completed. Check " + logfile + " for "
                + m.getMatchedArr().size() + " matched data.");
    }
}

/*
--- exec-maven-plugin:3.0.0:exec (default-cli) @ app ---
SubString key and name, to hashmap.
> primaryPath: D:\temp
>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201808
>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201809
> row count: 141018
> Hashmap size: 95768
>> files count: 37494
>> matched found: 0
Completed. Check logmessages.txt for 0 matched data.
------------------------------------------------------------------------
BUILD SUCCESS
 */
 /*
var m = new Main(primaryPath, targetPath, 1);
> row count: 141018
> Hashmap size: 95768
>> files count: 37494
 */
