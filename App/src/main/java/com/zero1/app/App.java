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

//        String primaryPath = "D:\\temp2";
//        String targetPath = "C:\\Users\\AlvinNg\\verify\\test\\test1";
//
        String primaryPath = "D:\\temp";
        String targetPath = "C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201808";
        var m = new Main(primaryPath, targetPath);

        m.addTargetPath("C:\\Users\\AlvinNg\\Zero1 Pte Ltd\\Portal - ToBeDeleted\\201809");

        System.out.println("\nCompleted, check " + logfile + " for error msg.");
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
> PhotoID_20190101.txt
> PhotoID_20190108.txt
> PhotoID_20190114.txt
> PhotoID_20190121.txt
> PhotoID_20190128.txt
> PhotoID_20190203.txt
> PhotoID_20190210.txt
> PhotoID_20190223.txt
> PhotoID_20190301.txt
> PhotoID_20190306.txt
> PhotoID_20190312.txt
> PhotoID_20190318.txt
> PhotoID_20190325.txt
> PhotoID_20190331.txt
> PhotoID_20190405.txt
> PhotoID_20190410.txt
> PhotoID_20190416.txt
> PhotoID_20190422.txt
> PhotoID_20190428.txt
> PhotoID_20190503.txt
> PhotoID_20190509.txt
> PhotoID_20190514.txt
> PhotoID_20190519.txt
> PhotoID_20190524.txt
> PhotoID_20190530.txt
> PhotoID_20190605.txt
> PhotoID_20190611.txt
> PhotoID_20190616.txt
> PhotoID_20190620.txt
> PhotoID_20190625.txt
> PhotoID_20190629.txt
> PhotoID_20190704.txt
> PhotoID_20190709.txt
> PhotoID_20190712.txt
> PhotoID_20190716.txt
> PhotoID_20190719.txt
> PhotoID_20190722.txt
> row count: 141018
> Hashmap size: 139949
Scanning thru 34683 directories:
.................................................
Scanning thru 2439 directories:
.................................................
Completed, check logmessages.txt for error msg.
BUILD SUCCESSFUL (total time: 6 seconds)
 */

 /*  logmessages.txt
> primaryPath: D:\temp
> StringException: �� < PhotoID_20180924.txt
> StringException: f u l l n a m e < PhotoID_20180924.txt
> StringException: - - - - - - - - < PhotoID_20180924.txt
> key lgth err: d : \ t o o l s \ t e m p \ p h o t o i d _ 2 0 2 0 0 9 2 4 r a r . z i p < PhotoID_20180924.txt
> StringException: photoid_20181118.zip md5 c8139bf1e2aff9f95c5a238a2a0656c6 < PhotoID_20181118.txt
> StringException: photoid_20181125.zip  md5 - 25abcf53412401d7981b8940a7354aa0 < PhotoID_20181125.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 1 0 1 . z i p   m d 5 s u m   d d 8 f 7 2 e a 6 c 2 1 1 5 6 4 0 4 b 8 1 3 3 f 3 a c d f a 4 7 < PhotoID_20190101.txt
> key lgth err: c : \ p h t o i d - b a c k u p \ p h o t o i d _ 2 0 1 9 0 1 0 1 . z i p < PhotoID_20190101.txt
> StringException: photoid_20190108.zip md5sum 0a2272bcf4b227e346bf8966eeaecb68 < PhotoID_20190108.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 1 1 4 . z i p   m d 5 s u m   5 9 a b 6 f 0 6 e 4 c d d 8 7 8 4 6 a 6 6 1 f 4 8 b 5 8 6 8 4 d < PhotoID_20190114.txt
> key lgth err: d : \ t r a n s f e r - d o n o t t o u c h t h i s f o l d e r \ p h o t o i d _ 2 0 1 9 0 1 1 4 . z i p < PhotoID_20190114.txt
> StringException: photoid_20190121.zip md5sum a02542e5939605b9a088e7fd5ed4f0c3 < PhotoID_20190121.txt
> key lgth err: d:\transfer-donottouchthisfolder\photoid_20190127.zip < PhotoID_20190121.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 1 2 8 . z i p   m d 5 s u m   8 3 c e 8 b 9 4 8 c e e 5 1 1 5 f a e d b 5 0 1 9 1 7 8 7 7 2 1 < PhotoID_20190128.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 2 0 3 . z i p   m d 5 s u m   4 f f e 9 b 2 3 7 9 4 d e 2 6 e d c b e 6 0 c 6 4 7 6 f 1 5 d c < PhotoID_20190203.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 2 1 0 . z i p   m d 5 s u m   e 2 0 0 8 6 8 a e e 3 4 d b 6 8 6 e a 5 0 b 9 b 7 b f 1 e 7 c 5 < PhotoID_20190210.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 2 2 3 . z i p   m d 5 s u m   9 b e 0 b e 1 3 d 4 9 b 3 3 c e f f a 8 6 d a 1 d e 9 a f 7 b 6 < PhotoID_20190223.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 1 0 3 . z i p   m d 5 s u m   e 7 4 3 6 d 6 f b 2 9 0 a 1 b b 3 1 4 9 5 5 f a 6 e 4 2 f 4 1 6 < PhotoID_20190301.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 3 0 6 . z i p   m d 5 s u m   a c b b 1 6 3 f 3 d 4 e 9 5 8 1 a 1 0 3 5 2 f 2 3 2 4 b 1 8 c 0 < PhotoID_20190306.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 3 1 2 . z i p   m d 5 s u m   9 b a 1 8 3 6 e 4 3 f 9 6 d 9 f 6 c 0 1 3 3 2 2 e 3 0 8 4 5 9 b < PhotoID_20190312.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 3 1 8 . z i p   m d 5 s u m   8 5 3 9 b c 0 0 b 8 0 8 1 d e d c f 9 4 f 3 c b 2 d 3 7 e 8 2 e < PhotoID_20190318.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 3 2 5 . z i p   m d 5 s u m   2 c 9 c b 4 1 0 9 6 7 8 3 0 d 5 5 b a 9 d d a 7 4 0 d 3 f 6 5 7 < PhotoID_20190325.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 3 3 1 . z i p   m d 5 s u m   d 1 1 2 9 7 9 7 c f d 9 a 0 7 c d 4 f 4 4 2 c 4 c a b d 1 f 9 c < PhotoID_20190331.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 4 0 5 . z i p   m d 5 s u m   f 1 a a 8 4 8 b 2 a 6 6 d 1 0 6 e 6 b 1 f b 1 a d c 2 b 7 4 3 3 < PhotoID_20190405.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 4 1 0 . z i p   m d 5 s u m   c 1 2 d 9 1 8 1 b 7 7 f a f 1 9 5 f a 0 7 0 6 d 7 a 4 f 0 8 5 9 < PhotoID_20190410.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 4 1 6 . z i p   m d 5 s u m   5 7 a e 9 b b d a a 0 1 1 b 8 a 9 0 0 4 6 5 f 1 8 d b 1 2 1 f 2 < PhotoID_20190416.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 4 2 2 . z i p   m d 5 s u m   d 4 6 0 c 9 5 a 3 7 f 7 e 2 0 5 d f 4 7 e 9 2 f 4 8 b 2 f 6 8 1 < PhotoID_20190422.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 4 2 8 . z i p   m d 5 s u m   c b 5 d 6 a 3 8 2 b 0 e 4 1 9 3 3 e a c 7 3 7 a 0 e 5 5 a a 8 6 < PhotoID_20190428.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 0 3 . z i p   m d 5 s u m   4 c a 1 e e 5 7 f 5 d c 2 e a 7 b 5 a 6 8 c 9 f 3 d d 3 2 8 0 b < PhotoID_20190503.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 0 9 . z i p   m d 5 s u m   4 d 0 5 4 8 e 4 b 3 a a 0 1 8 7 7 d 5 3 e 4 4 6 3 e d 4 8 d 4 9 < PhotoID_20190509.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 1 4 . z i p   m d 5 s u m   0 3 c 9 b 9 f 1 8 d 9 3 5 b e b a 3 d 6 8 a 2 c f 3 d 4 a 5 b b < PhotoID_20190514.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 1 9 . z i p   m d 5 s u m   d 5 f a 3 3 e 5 1 0 6 d 8 5 e b 6 d a b 0 3 1 2 4 8 1 9 9 1 c f < PhotoID_20190519.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 2 4 . z i p   m d 5 s u m   d 8 b b 5 c 0 7 6 7 5 e a 0 a 8 2 b b 9 7 d 8 2 c 8 f 3 f d a d < PhotoID_20190524.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 5 3 0 . z i p   m d 5 s u m   7 5 4 3 c 7 a 6 7 0 6 7 2 8 8 3 3 a 5 d 7 9 1 f 2 a 2 a a 6 f 9 < PhotoID_20190530.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 0 5 . z i p   m d 5 s u m   6 8 b 8 6 a 6 5 9 0 0 4 3 d c 0 2 c 5 7 7 6 e d 4 4 a 4 9 d f 0 < PhotoID_20190605.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 1 1 . z i p   m d 5 s u m   0 e 4 6 6 a 1 4 c 9 6 5 1 0 a 8 b 0 d 2 9 2 4 4 c d 7 2 4 0 a 2 < PhotoID_20190611.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 1 6 . z i p   m d 5 s u m   6 0 d 5 d 4 6 b 0 e b f 5 4 1 5 3 f a 8 0 e 3 c 4 9 1 0 0 4 7 1 < PhotoID_20190616.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 2 0 . z i p   m d 5 s u m   9 8 a 1 6 8 3 d 9 2 9 e a d c 7 a 4 7 6 8 f 6 7 3 1 c f 7 2 b 0 < PhotoID_20190620.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 2 5 . z i p   m d 5 s u m   e 0 4 e 0 8 b b 6 c 3 5 6 a c 2 2 e d 4 3 6 2 6 d 3 1 9 c 7 e c < PhotoID_20190625.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 6 2 9 . z i p   m d 5 s u m   c 3 b 1 9 8 d 5 a 4 d 5 3 d d 4 5 c f 1 e 1 e e c b 7 b 5 d 0 a < PhotoID_20190629.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 7 0 4 . z i p   m d 5 s u m   b 1 6 8 7 7 d 6 6 3 6 f 2 b 5 4 1 d e 0 f 8 3 f 9 6 5 3 e 0 0 d < PhotoID_20190704.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 7 0 9 . z i p   m d 5 s u m   4 e 0 c 9 2 5 d 5 5 2 c 5 8 1 c b 6 1 c 9 d 5 2 5 a 2 a 0 1 6 1 < PhotoID_20190709.txt
> StringException: ��p h o t o i d _ 2 0 1 9 0 7 1 2 . z i p 	 m d 5 s u m 	 6 4 4 e 2 2 f 8 8 e 1 9 1 9 9 e 3 8 0 e 2 b 0 a 3 b 9 e 1 8 3 7 < PhotoID_20190712.txt
> StringException: ��m d 5 s u m   b b 0 1 b 5 a 4 8 a 2 6 1 7 4 a e 5 9 5 6 d 9 9 0 7 0 0 1 c c 2 < PhotoID_20190716.txt
> StringException: ��m d 5 s u m   2 5 7 d 8 0 3 e 2 e d 3 7 1 f 3 6 d b f e a b d 6 8 7 4 f 5 c f < PhotoID_20190719.txt
> StringException: ��m d 5 s u m   c 4 e 7 b a f b 6 6 9 4 2 4 1 5 0 c c 1 6 a d f 3 5 2 0 6 a e d < PhotoID_20190722.txt
>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201808
>> targetPath: C:\Users\AlvinNg\Zero1 Pte Ltd\Portal - ToBeDeleted\201809
 */
