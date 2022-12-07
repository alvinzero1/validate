
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static final PrintStream OUT = System.out;
    private static final int HASH_INITIAL_CAP = 70000;
    public HashMap<String, ArrayList<String>> hashmap = new HashMap<>(HASH_INITIAL_CAP);

    /**
     *
     * @param primaryPath
     */
    public void putFilesToMem(String primaryPath) {
        String str, textLine;
        int count = 0;
        Scanner fileIn = null;

        OUT.println("\nSubString key and name, to hashmap.");
        System.err.println("\n> primaryPath: " + primaryPath);
        var dirfile = new File(primaryPath + "\\");
        if (dirfile.isDirectory()) {
            str = dirfile.getName();
            for (var str2 : dirfile.list()) {
                OUT.println("> " + str2);
                var filePath = (primaryPath + "\\" + str2);

                try {
                    fileIn = new Scanner(
                            new FileInputStream(filePath));
                } catch (FileNotFoundException e) {
                    OUT.println("File not found.");
                    System.exit(0);
                }

                boolean hasNextline;
                hasNextline = fileIn.hasNextLine();
                toContinue:
                while (hasNextline) {
                    textLine = fileIn.nextLine().trim().toLowerCase();
                    if (textLine.length() <= 1) {
                        hasNextline = fileIn.hasNextLine();
                        continue toContinue;
                    }

                    if (!subStringPutToHash(textLine)) {
                        System.err.println(" < " + str2);
                    }
                    count++;
                    hasNextline = fileIn.hasNextLine();
                }
                fileIn.close();
            }
        } else {
            OUT.println("Primary directory NOT correct!");
            System.exit(0);
        }
        OUT.println("> row count: " + count);
        OUT.println("> Hashmap size: " + hashmap.size());
    }

    private boolean subStringPutToHash(String s) {
        String filename, mkey, sub;
        try {
            sub = s.substring(0, s.lastIndexOf("\\"));
            mkey = sub.substring(sub.lastIndexOf("\\") + 1); // CB718C312BA1B3622ECFDCBF727465F2
            filename = s.substring(s.lastIndexOf("\\") + 1); // Duke.png
        } catch (StringIndexOutOfBoundsException e) {
            System.err.print("> StringException: " + s);
            return false;
        }

        // check if right key lgth
        var keylgth = mkey.length();

        //  modified double char to single
        if (keylgth > 64) {
            var ss = "";
            for (var a : mkey.toCharArray()) {
                ss += (Integer.valueOf(a) != 0) ? (a) : ("");
            }
            mkey = ss;
            keylgth = ss.length();
        }

        if (keylgth == 32) {

            // if hashmap NOT contain key
            if (!hashmap.containsKey(mkey)) {
                hashmap.put(mkey, new ArrayList<>());
            }
            hashmap.get(mkey).add(filename.toLowerCase());

            return true;
        } else {
            System.err.print("> key lgth err: " + s);
            return false;
        }
    }

    /**
     *
     * @param targetPath
     */
    public void targetFilesVerifyByHash(String targetPath) {
        System.err.println("\n>> targetPath: " + targetPath);
        var mainfile = new File(targetPath);
        int lgth = 0, count = 0;

        if (mainfile.isDirectory()) {
            lgth = mainfile.list().length;
            OUT.println("\nWill scan thru " + lgth + " directories:");
        } else {
            OUT.println("Target directory NOT correct!");
            System.exit(0);
        }

        for (var str : mainfile.list()) {
            String tagKey = "", tagFilename = "";
            // monitoring
            count = (count <= 0) ? count = lgth / 20 : (count -= 1);
            OUT.print((count <= 0) ? "." : "");

            var dirfile = new File(mainfile + "\\" + str);
            if (dirfile.isDirectory()) {
                tagKey = dirfile.getName().toLowerCase();
                for (var str2 : dirfile.list()) {
                    var subfile = new File(dirfile + "\\" + str2);
                    tagFilename = subfile.getName();

                    // if there IS match from hashmap, will log error message.
                    if (hashmap.containsKey(tagKey)) {

                        ArrayList<String> arr = hashmap.get(tagKey);
                        for (String priFileName : arr) {
                            if (priFileName.equalsIgnoreCase(tagFilename)) {
                                System.err.println(">> matched: " + targetPath + "\\" + str
                                        + "\\" + tagFilename);
                            }
                        }
                    }
                }
            } else {
                OUT.println(">> " + dirfile.getName());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        var m = new Main();

        PrintStream errStream = null;
        var logfile = "logmessages.txt";
        try {
            errStream = new PrintStream(
                    new FileOutputStream(logfile));
        } catch (FileNotFoundException e) {
            OUT.println("Error opening file with FileOutputStream.");
            System.exit(0);
        }
        System.setErr(errStream);

        m.putFilesToMem("D:\\temp");
        m.targetFilesVerifyByHash("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201808");
        m.targetFilesVerifyByHash("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201809");

        OUT.println("\nCompleted, check on " + logfile + " for error msg.");
        errStream.close();
    }
}

/*
run:

SubString key and name, to hashmap.
> PhotoID-20181201.txt
> PhotoID-20181207.txt
> PhotoID-20181214.txt
> PhotoID-20181221.txt
> PhotoID-20181227.txt
> PhotoID_201808_01.txt
> PhotoID_201808_02.txt
> PhotoID_201808_03.txt
> PhotoID_201808_04.txt
> PhotoID_201808_05.txt
> PhotoID_201808_06.txt
> PhotoID_201808_07.txt
> PhotoID_201808_08.txt
> PhotoID_201808_09.txt
> PhotoID_201808_10.txt
> PhotoID_201808_11.txt
> PhotoID_201808_12.txt
> PhotoID_201808_13.txt
> PhotoID_201808_14.txt
> PhotoID_20180901.txt
> PhotoID_20180916.txt
> PhotoID_20180924.txt
> PhotoID_20180927.txt
> PhotoID_20181001.txt
> PhotoID_20181007.txt
> PhotoID_20181014.txt
> PhotoID_20181022.txt
> PhotoID_20181027.txt
> PhotoID_20181101.txt
> PhotoID_20181110.txt
> PhotoID_20181118.txt
> PhotoID_20181125.txt
> row count: 69461
> Hashmap size: 68896

Will scan thru 34683 directories:
...................
Will scan thru 2439 directories:
...................
Completed, check on logmessages.txt for error msg.
BUILD SUCCESSFUL (total time: 6 seconds)
 */

 /* logmessages.txt

> primaryPath: D:\temp
> StringException: �� < PhotoID_20180924.txt
> StringException: f u l l n a m e < PhotoID_20180924.txt
> StringException: - - - - - - - - < PhotoID_20180924.txt
> key lgth err: d : \ t o o l s \ t e m p \ p h o t o i d _ 2 0 2 0 0 9 2 4 r a r . z i p < PhotoID_20180924.txt
> StringException: photoid_20181118.zip md5 c8139bf1e2aff9f95c5a238a2a0656c6 < PhotoID_20181118.txt
> StringException: photoid_20181125.zip  md5 - 25abcf53412401d7981b8940a7354aa0 < PhotoID_20181125.txt

>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201808

>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201809

*/
