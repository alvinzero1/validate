/* www.Zero1.Sg 2022Dec */
package com.zero1.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * To check if there is match from GoogleDrive and ToBeDelete_Portal. If match
 * found LOG pathname as error message.
 *
 * Folder for files should be 32 chars String.
 */
public class Main {

    private static final String DELIMITER = "\\";
    private final HashMap<String, ArrayList<String>> hashmap;
    private final ArrayList<String> matchedArr;
    private final ArrayList<String> errPrint;
    private int primaryLineCount = 0;
    private int targetFileChkCount = 0;
    private int mode = 0;

    /**
     * Initiate application.
     *
     * @param primaryPath
     * @param targetPath
     */
    public Main(String primaryPath, String targetPath) {
        hashmap = new HashMap<>();
        matchedArr = new ArrayList<>();
        errPrint = new ArrayList<>();
        addPrimaryPath(primaryPath);
        addTargetPath(targetPath);
    }

    /**
     * Initiate application with mode = 1, i.e. filename as hash key.
     *
     * @param primaryPath
     * @param targetPath
     * @param hashkeyType mode = 1: hash key will be filename, hash value for
     * folder name
     */
    public Main(String primaryPath, String targetPath, int hashkeyType) {
        mode = hashkeyType;
        hashmap = new HashMap<>();
        matchedArr = new ArrayList<>();
        errPrint = new ArrayList<>();
        addPrimaryPath(primaryPath);
        addTargetPath(targetPath);
    }

    public int getMode() {
        return mode;
    }

    public HashMap<String, ArrayList<String>> getHashmap() {
        return hashmap;
    }

    public ArrayList<String> getMatchedArr() {
        return matchedArr;
    }

    public ArrayList<String> getErrPrint() {
        return errPrint;
    }

    public void setErrPrint(String s) {
        System.out.println(s);
        errPrint.add(s);
    }

    public int getPrimaryLineCount() {
        return primaryLineCount;
    }

    public int getTargetFileChkCount() {
        return targetFileChkCount;
    }

    /**
     * To get text lines from file, extract subString folder name and file
     * name.Sample format {@code CB718C312BA1B3622ECFDCBF727465F2\filename.png}.
     * Put key of 32 chars string, and filename as value to {@code hashmap}.
     *
     * @param primaryPath File of list of paths
     * @return
     */
    public boolean addPrimaryPath(String primaryPath) {
        setErrPrint("> primaryPath: " + primaryPath);
        return putFilenameToHashmap(primaryPath);
    }

    private boolean putFilenameToHashmap(String primaryPath) {
        String textLine;
        Scanner fileIn;

        var dirfile = new File(primaryPath + DELIMITER);
        if (dirfile.isDirectory()) {
            for (var str2 : dirfile.list()) {
                var filePath = (primaryPath + DELIMITER + str2);

                try {
                    if ((new File(primaryPath + DELIMITER + str2)).isDirectory()) {
                        continue;
                    }

                    fileIn = new Scanner(
                            new FileInputStream(filePath));

                    boolean hasNextline = fileIn.hasNextLine();
                    while (hasNextline) {
                        textLine = fileIn.nextLine().trim().toLowerCase();
                        if (textLine.length() <= 1) {
                            hasNextline = fileIn.hasNextLine();
                            continue;
                        }

                        if (!subStringPutToHash(textLine)) {
                            errPrint.add(" < " + str2);
                        }
                        primaryLineCount++;
                        hasNextline = fileIn.hasNextLine();
                    }
                    fileIn.close();

                } catch (FileNotFoundException e) {
                    setErrPrint("> Error at FileNotFoundException " + e);
                    return false;
                }
            }
            return true;
        } else {
            errPrint.add("Primary directory NOT correct!");
            return false;
        }
    }

    private boolean subStringPutToHash(String s) {
        String filename, mkey, sub;
        try {
            sub = s.substring(0, s.lastIndexOf(DELIMITER));
            mkey = sub.substring(sub.lastIndexOf(DELIMITER) + 1); //eg. CB718C312BA1B3622ECFDCBF727465F2
            filename = s.substring(s.lastIndexOf(DELIMITER) + 1);
        } catch (StringIndexOutOfBoundsException e) {
            errPrint.add("> StringException: " + s);
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
            if (mode != 1) {
                // folder(mKey) as hash key
                if (!hashmap.containsKey(mkey)) {
                    hashmap.put(mkey, new ArrayList<>());
                }
                hashmap.get(mkey).add(filename.toLowerCase());
            } else {
                //  System.out.println("> filename as hashkey");
                if (!hashmap.containsKey(filename)) {
                    hashmap.put(filename, new ArrayList<>());
                }
                hashmap.get(filename).add(mkey.toLowerCase());
            }

            return true;
        } else {
            errPrint.add("> key lgth err: " + s);
            return false;
        }
    }

    /**
     * Add additional path. To get the full path name of directory individual
     * files. And compare with {@code hashmap} for key as folder, value as
     * filename.
     *
     * @param targetPath Path from local drive, or local sharepoint.
     * @return True for process done, or false for wrong directory
     */
    public boolean addTargetPath(String targetPath) {
        setErrPrint(">> targetPath: " + targetPath);
        return targetFilesVerifyByHash(targetPath);
    }

    private boolean targetFilesVerifyByHash(String targetPath) {

        var mainfile = new File(targetPath);

        if (mainfile.isDirectory()) {
            for (var str : mainfile.list()) {
                var dirfile = new File(mainfile + DELIMITER + str);
                if (dirfile.isDirectory()) {
                    var tagKey = dirfile.getName().toLowerCase();
                    for (var str2 : dirfile.list()) {
                        var subfile = new File(dirfile + DELIMITER + str2);
                        var tagFilename = subfile.getName().toLowerCase();

                        // if there IS match from hashmap, will log error message.
                        if (mode != 1) {
                            // folder(mKey) as hash key,
                            if (hashmap.containsKey(tagKey)) {
                                ArrayList<String> arr = hashmap.get(tagKey);
                                arr.forEach(priFileName -> {
                                    if (priFileName.equalsIgnoreCase(tagFilename)) {
                                        matchedArr.add((targetPath + DELIMITER + str + DELIMITER + tagFilename));
                                    }
                                });
                            }
                        } else {
                            // System.out.println(">>  filename as hashkey");
                            if (hashmap.containsKey(tagFilename)) {
                                ArrayList<String> arr = hashmap.get(tagFilename);
                                arr.forEach(priFileName -> {
                                    if (priFileName.equalsIgnoreCase(tagKey)) {
                                        matchedArr.add((targetPath + DELIMITER + str + DELIMITER + tagFilename));
                                    }
                                });
                            }
                        }

                        targetFileChkCount++;
                    }
                } else {
                    errPrint.add(">> " + dirfile.getName());
                }

                /* // monitoring, done at 50 'dots' KIV
                monitor = (monitor <= 0) ? dirLgth / 50 : monitor - 1;
                System.out.print((monitor <= 0) ? "." : ""); */
            }
            return true;
        } else {
            setErrPrint("Target directory NOT correct!");
            return false;
        }
    }

    public void printErrlogNMatchedToFile(String filename) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(
                    new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
            setErrPrint("Error opening the file " + filename);
            System.exit(0);
        }

        for (String e : getErrPrint()) {
            outputStream.println(e);
        }

        for (String s : getMatchedArr()) {
            outputStream.println(s);
        }
        outputStream.close();
    }

    @Override
    public String toString() {
        return hashmap.toString();
    }

}
