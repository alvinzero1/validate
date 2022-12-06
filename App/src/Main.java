
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    //s = "D:\\transfer-DoNotTouchThisFolder\\0005ffc8449a3e95a1274aaceb7037f9\\2219C649-BAA2-4DFD-9204-365CD0701694.jpeg  ";
    //int pos1 = s.indexOf("DoNotTouchThisFolder") + "DoNotTouchThisFolder\\".length();;
    //s = "C:\\Users\\AlvinNg\\verify\\test\\test1\\CB718C312BA1B3622ECFDCBF727465F2\\Duke.png";
    public static final int HASH_INITIAL_CAP = 1000;
    public String primaryPath;
    public String targetPath;
    public HashMap<String, String> hashmap;

    public void inputPath() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(" Enter primary path name: ");
        primaryPath = keyboard.nextLine();
        //primaryPath = "D:\\temp";

        System.out.print(" Enter target path name: ");
        targetPath = keyboard.nextLine();
        //targetPath = "C:\\Users\\AlvinNg\\verify\\test\\test1";
    }

    public void putFilesToMem() {
        String str, textLine;
        int count = 0;
        Scanner fileIn = null;

        File dirfile = new File(primaryPath + "\\");
        if (dirfile.isDirectory()) {
            str = dirfile.getName();
            for (String str2 : dirfile.list()) {
                System.out.println(">> " + str2);
                String filePath = (primaryPath + "\\" + str2);

                try {
                    fileIn = new Scanner(
                            new FileInputStream(filePath));
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
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

                    subStringPutToHash(textLine);
                    count++;
                    hasNextline = fileIn.hasNextLine();
                }
                fileIn.close();
            }
        } else {
            System.out.println("Primary directory NOT correct!");
            System.exit(0);
        }
        // System.out.println(">> row count: " + count);
    }

    public boolean subStringPutToHash(String s) {
        String filename, key, sub;

        sub = s.substring(0, s.lastIndexOf("\\"));
        key = sub.substring(sub.lastIndexOf("\\") + 1); // CB718C312BA1B3622ECFDCBF727465F2
        filename = s.substring(s.lastIndexOf("\\") + 1); // Duke.png

        // check if right key lgth
        if (key.length() != 32) {
            System.err.println(">> keyLgth err: " + s);
            return false;
        }
//        System.out.print(">> Primary >> key : " + key);
//        System.out.println("     filename : " + filename);

        hashmap.put(key, filename.toLowerCase());
        
        return true;
    }

    public void targetFilesVerifyByHash() {
        File mainfile = new File(targetPath);

        if (mainfile.isDirectory()) {
            int lgth = mainfile.list().length;
            System.out.print(lgth + " ");
        } else {
            System.out.println("Target directory NOT correct!");
            System.exit(0);
        }

        for (String str : mainfile.list()) {
            String tagKey = "", tagFilename = "";

            File dirfile = new File(mainfile + "\\" + str);
            if (dirfile.isDirectory()) {
                tagKey = dirfile.getName().toLowerCase();
                for (String str2 : dirfile.list()) {
                    File subfile = new File(dirfile + "\\" + str2);
                    tagFilename = subfile.getName();
//                    System.out.print(">> target  key : " + tagKey);
//                    System.out.println("      name : " + tagFilename);

                    if (hashmap.containsKey(tagKey)) {
                        if (hashmap.get(tagKey).equalsIgnoreCase(tagFilename)) {
                            System.err.println(">> matched: " + targetPath + "\\" + str + "\\" + tagFilename);
                        } else {
                            System.err.println(">> same key err: " + targetPath + "\\" + str);
                        }
                    }
                }
            } else {
                System.out.println(">> " + dirfile.getName());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        PrintStream errStream = null;
        String logfile = "logmessages.txt";
        try {
            errStream = new PrintStream(
                            new FileOutputStream(logfile));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file with FileOutputStream.");
            System.exit(0);
        }
        System.setErr(errStream);

        Main m = new Main();
        m.inputPath();
        System.out.println("\n>> primaryPath: " + m.primaryPath);
        System.out.println(">> targetPath: " + m.targetPath);

        System.out.println("\nSubString key and name, to hashmap.");
        m.hashmap = new HashMap<>(HASH_INITIAL_CAP);
        m.putFilesToMem();
        
        System.out.println("Hashmap size: " + m.hashmap.size());
        m.targetFilesVerifyByHash();

        errStream.close();
        System.out.println("\nCompleted, check on " + logfile + " for error msg.");
    }
}
/*
run:
 Enter primary path name: D:\temp
 Enter target path name: C:\Users\AlvinNg\verify\test\test1

>> primaryPath: D:\temp
>> targetPath: C:\Users\AlvinNg\verify\test\test1

SubString key and name, to hashmap.
>> md5chksum.txt
Hashmap size: 2
2 
Completed, check on logmessages.txt for error msg.
BUILD SUCCESSFUL (total time: 11 seconds)
 */
