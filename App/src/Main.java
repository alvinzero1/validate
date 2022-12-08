
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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

    public Main() {
        hashmap = new HashMap<>();
    }

    /**
     * To get text lines from file, extract subString folder name and file name.
     * Sample format {@code CB718C312BA1B3622ECFDCBF727465F2\filename.png}.
     *
     * Put key of 32 chars string, and filename as value to {@code hashmap}.
     *
     * @param primaryPath File of list of paths
     */
    public void putFilenameToHashmap(String primaryPath) {
        String textLine;
        int priCount = 0;
        Scanner fileIn;
        System.out.println("");
        System.out.println("\nSubString key and name, to hashmap.");
        System.err.println("\n> primaryPath: " + primaryPath);
        var dirfile = new File(primaryPath + DELIMITER);
        if (dirfile.isDirectory()) {
            for (var str2 : dirfile.list()) {
                System.out.println("> " + str2);
                var filePath = (primaryPath + DELIMITER + str2);

                try {
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
                            System.err.println(" < " + str2);
                        }
                        priCount++;
                        hasNextline = fileIn.hasNextLine();
                    }
                    fileIn.close();

                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Primary directory NOT correct!");
            System.exit(0);
        }
        System.out.println("> row count: " + priCount);
        System.out.println("> Hashmap size: " + hashmap.size());
    }

    private boolean subStringPutToHash(String s) {
        String filename, mkey, sub;
        try {
            sub = s.substring(0, s.lastIndexOf(DELIMITER));
            mkey = sub.substring(sub.lastIndexOf(DELIMITER) + 1); //eg. CB718C312BA1B3622ECFDCBF727465F2
            filename = s.substring(s.lastIndexOf(DELIMITER) + 1);
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
     * To get the full path name of directory individual files. And compare with
     * {@code hashmap} for key as folder, value as filename.
     *
     * @param targetPath Path from local drive, or local OneDrive.
     * @return True for process done, or false for wrong directory
     */
    public boolean targetFilesVerifyByHash(String targetPath) {
        System.err.println("\n>> targetPath: " + targetPath);
        var mainfile = new File(targetPath);

        if (mainfile.isDirectory()) {
            int monitor = 0;
            var dirLgth = mainfile.list().length;
            System.out.println("\nScanning thru " + dirLgth + " directories:");

            for (var str : mainfile.list()) {
                var dirfile = new File(mainfile + DELIMITER + str);
                if (dirfile.isDirectory()) {
                    var tagKey = dirfile.getName().toLowerCase();
                    for (var str2 : dirfile.list()) {
                        var subfile = new File(dirfile + DELIMITER + str2);
                        var tagFilename = subfile.getName();

                        // if there IS match from hashmap, will log error message.
                        if (hashmap.containsKey(tagKey)) {

                            ArrayList<String> arr = hashmap.get(tagKey);
                            for (String priFileName : arr) {
                                if (priFileName.equalsIgnoreCase(tagFilename)) {
                                    System.err.println(">> matched: " + targetPath + DELIMITER + str
                                            + DELIMITER + tagFilename);
                                }
                            }
                        }
                    }
                } else {
                    System.out.println(">> " + dirfile.getName());
                }

                // monitoring, done at 50 'dots'
                monitor = (monitor <= 0) ? dirLgth / 50 : monitor - 1;
                System.out.print((monitor <= 0) ? "." : "");
            }
            return true;
        } else {
            System.err.println("Target directory NOT correct!");
            return false;
        }
    }

    @Override
    public String toString() {

        return hashmap.toString();
    }

}
