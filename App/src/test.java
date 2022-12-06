
/**
 *
 * @author AlvinNg
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        test t = new test();

        String s, s2, s3, s4, out, out2;
        int p, p2, pl;
        s = "D:\\transfer-DoNotTouchThisFolder\\0005ffc8449a3e95a1274aaceb7037f9\\2219C649-BAA2-4DFD-9204-365CD0701694.jpeg  ";
        s = "C:\\Users\\AlvinNg\\verify\\test\\test1\\CB718C312BA1B3622ECFDCBF727465F2\\Duke.png";

        String sub = s.substring(0, s.lastIndexOf("\\")); //
        String key = sub.substring(sub.lastIndexOf("\\") + 1); // CB718C312BA1B3622ECFDCBF727465F2
        String filename = s.substring(s.lastIndexOf("\\") + 1); // Duke.png
        System.out.println(">>> sub: " + sub);
        System.out.println(">>> key: " + key);
        System.out.println(">>> filename: " + filename);

//        p2 = s.substring(p + 1).indexOf("\\");
//        out = s.substring(p + 1, p + p2 + 1);
//        System.out.println(">>> :: " + out);
//        s2 = s.substring(p + p2 + 2);
//        System.out.println(">>> :: " + s2);
    }
}
