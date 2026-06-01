package javaDoc.lang.system;

import java.io.*;

import static java.io.FileDescriptor.out;

/**
 * 실험
 */
public class Test {

    /**
     * out vs err 의 버퍼링 차이 (크래시 직전 출력)
     */
    static void main(String[] args) {

//        outPrintln();
//        errPrintln();
        outPrintlnNoFlush();
        errPrintlnNoFlush();
        Runtime.getRuntime().halt(1);

    }

    /**
     * System.out.println()을 카운트한다.
     */
    public static void outPrintln() {

        int count = 0;

        for (int i = 0; i < 10; i++) {
            System.out.print("count:" + count);
            System.out.print("- Out -");
            count++;
        }
    }

    public static void errPrintln() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            System.err.print("count:" + count);
            System.err.print("- Err - ");
            count++;
        }
    }

    public static void outPrintlnNoFlush() {

        PrintStream noFlush = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), false);

        int count = 0;

        for (int i = 0; i < 10; i++) {
            noFlush.println("count:" + count);
            noFlush.println("- Out -");
            count++;
        }

    }

    public static void errPrintlnNoFlush() {

        PrintStream noFlush = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), false);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            noFlush.println("count:" + count);
            noFlush.println("- Err - ");
            count++;
        }

    }

}
