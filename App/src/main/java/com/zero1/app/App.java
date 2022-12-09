/* www.Zero1.Sg 2022Dec */
package com.zero1.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class App {

    public static void main(String args[]) {
        PrintStream errStream = null;
        var logfile = "logmessages.txt";
        try {
            errStream = new PrintStream(
                    new FileOutputStream(logfile));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file with FileOutputStream.");
            System.exit(0);
        }
        System.setErr(errStream);

        System.out.println("SubString key and name, to hashmap.");

        String primaryPath = ("D:\\temp");
        String targetPath = ("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201808");
        var m = new Main(primaryPath, targetPath);
        System.err.println("> primaryPath: " + primaryPath);
        m.addTargetPath("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201809");
        System.err.println("> primaryPath: " + primaryPath);
//
//        String primaryPath = "D:\\temp2";
//        String targetPath = "C:\\testdata\\test1";
//        var m = new Main(primaryPath, targetPath);
//        System.err.println("> primaryPath: " + primaryPath);

        System.out.println("> row count: " + m.primaryLineCount);
        System.out.println("> Hashmap size: " + m.getHashmap().size());

        System.err.println(">> targetPath: " + targetPath);
        System.out.println(">> files count: " + m.targetFileChkCount);

        System.err.println(">> matched found: " + m.getMatchedArr().size());
        for (String s : m.getMatchedArr()) {
            System.err.println(s);
        }

        System.out.println("Completed. Check " + logfile + " for "
                + m.getMatchedArr().size() + " matched data.");
        errStream.close();
    }
}

/*
---------------------------< com.zero1:app >----------------------------
Building app 0
--------------------------------[ jar ]---------------------------------

--- exec-maven-plugin:3.0.0:exec (default-cli) @ app ---
SubString key and name, to hashmap.
> row count: 141018
> Hashmap size: 139949
>> files count: 37494
Completed. Check logmessages.txt for 0 matched data.
------------------------------------------------------------------------
BUILD SUCCESS
 */
