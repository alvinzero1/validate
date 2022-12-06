
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileExplore {

    public static String targetPath = "";
    public static String primaryPath = "";
    public static Scanner fileIn = new Scanner(System.in);
    public static int MAXARRAYINDEX = 5000;

    public File mainfile;
    public String md5dirName;
    public String[] md5ChkArr;
    public String[] md5FileArr;
    public String imgfileName;
    public int md5Lgth;
    //public static ArrayList<String> dirListing = new ArrayList<String>();

    public void md5SumFileChk(String filename) {
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(
                    new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        readAllFmFile(fileIn);
        fileIn.close(); // close file
    }

    public void readAllFmFile(Scanner fileIn) {
        String textLine;
        boolean hasNextline;
        md5ChkArr = new String[MAXARRAYINDEX];
        md5FileArr = new String[MAXARRAYINDEX];

        md5Lgth = 0;
        hasNextline = fileIn.hasNextLine();
        while (hasNextline) {
            textLine = fileIn.nextLine();
            System.out.println(textLine);
            //C:\Users\AlvinNg\verify\test\test1\CB718C312BA1B3622ECFDCBF727465F2\Duke.png
            // D:\transfer-DoNotTouchThisFolder\0005ffc8449a3e95a1274aaceb7037f9\2219C649-BAA2-4DFD-9204-365CD0701694.jpeg  
            getsubString(textLine);

            md5Lgth++;
            // if m5Lgth == MAXARRAYINDEX >>> error
            hasNextline = fileIn.hasNextLine();
        }

        System.out.println(">>>>>> row no :: " + md5Lgth);

        fileIn.close();
    }

    public void getsubString(String s) {
        String chkAndImg, ending2;
        //s = "D:\\transfer-DoNotTouchThisFolder\\0005ffc8449a3e95a1274aaceb7037f9\\2219C649-BAA2-4DFD-9204-365CD0701694.jpeg  ";
        //int pos1 = s.indexOf("DoNotTouchThisFolder") + "DoNotTouchThisFolder\\".length();;

        //s = "C:\\Users\\AlvinNg\\verify\\test\\test1\\CB718C312BA1B3622ECFDCBF727465F2\\Duke.png";
        int pos1 = s.indexOf("test1") + "test1\\".length();;

        chkAndImg = s.substring(pos1);

        int pos2 = chkAndImg.indexOf("\\") + "\\".length();
        ending2 = chkAndImg.substring(pos2);
        //System.out.println(">> md5ChkArr  >> :: " + ending2);
        md5FileArr[this.md5Lgth] = ending2;
        //System.out.println(">> md5FileArr >> : " + chkAndImg.substring(0, pos2 - 1));
        md5ChkArr[this.md5Lgth] = chkAndImg.substring(0, pos2 - 1);

    }

    public boolean chkDir() {
//        File newF = new File(f.getAbsolutePath() + "/" + dirName);
        File newF = new File(targetPath);

        if (newF.isDirectory()) {
            System.out.println("Directory is right path.");
            int lgth = newF.list().length;
            System.out.println("" + lgth);
//            if (lgth > 1) {
//                System.out.println("This app only for 1 subdir and 1 file.");
//                System.exit(0);
//            }
            return true;
        } else {
            System.out.println("Directory NOT correct!");
            return false;
        }
    }

    public void getFileName(String str) {
//        File mainfile = new File(dirName);
//
//        for (String str : mainfile.list()) {

        File dirfile = new File(mainfile + "\\" + str); // windows version
        //File file = new File(folder + "/" + str);
        if (dirfile.isDirectory()) {
            md5dirName = dirfile.getName();

            for (String str2 : dirfile.list()) {
                File subfile = new File(dirfile + "\\" + str2);
                imgfileName = subfile.getName();

            }

        } else {
            System.out.println(">>> " + dirfile.getName());
        }

//        }
    }

    public static void main(String[] args) {
        FileExplore f = new FileExplore();

        System.out.print(" Enter md5 checksum file: ");
        //primaryPath = fileIn.nextLine();
        primaryPath = "C:\\Users\\AlvinNg\\verify\\md5chksum.txt";

        //File f = new File("..\\..\\verify\\test");
        //File f = new File("C:\\Users\\AlvinNg\\verify\\test");
        System.out.print(" Enter dir name to verify :: ");
        //targetPath = fileIn.nextLine();
        targetPath = "C:\\Users\\AlvinNg\\verify\\test\\test1";

        f.md5SumFileChk(primaryPath);

        for (int i = 0; i < 2; i++) {
            System.out.println(">>> " + f.md5ChkArr[i] + " " + f.md5FileArr[i]);
        }

        f.mainfile = new File(targetPath);
        System.out.println(" Directory Name Entered is : " + targetPath);
        f.chkDir();

        for (String str : f.mainfile.list()) {
            f.getFileName(str);
            System.out.print(">>> " + f.md5dirName);
            System.out.println("    >> >> " + f.imgfileName);
        }
    }
}
/*
run:
 Enter md5 checksum file:  Enter dir name to verify :: C:\Users\AlvinNg\verify\test\test1\C5094E4C507910CFBE9974D1C97CE73D\zero1.png
C:\Users\AlvinNg\verify\test\test1\CB718C312BA1B3622ECFDCBF727465F2\Duke.png
>>>>>> row no :: 2
>>> C5094E4C507910CFBE9974D1C97CE73D zero1.png
>>> CB718C312BA1B3622ECFDCBF727465F2 Duke.png
 Directory Name Entered is : C:\Users\AlvinNg\verify\test\test1
Directory is right path.
2
>>> C5094E4C507910CFBE9974D1C97CE73D    >> >> zero1.png
>>> CB718C312BA1B3622ECFDCBF727465F2    >> >> Duke.png
BUILD SUCCESSFUL (total time: 0 seconds)
 */
 /*
run:
 Enter dir name to verify :: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201808
 Directory Name Entered is : C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201808
Directory is right directory
BUILD SUCCESSFUL (total time: 1 minute 39 seconds)
 */
 /*
run:
 Enter md5 checksum file: D:\PhotoID-20181227.txt
 Enter dir name to verify :: >>>>>> row no :: 3347
BUILD SUCCESSFUL (total time: 28 seconds)
 */
