package javaDoc.lang.system;


/**
 * currentTimeMills vs nanoTime 으로 같은 작업 시간 재기
 */
public class Test2 {

    static void main(String[] args) {


        // 짧은 작업
        currentMillsSmallWork();
        nanoTimeSmallWork();

        // 긴 작업
        currentMillsBigWork();
        nanoTimeBigWork();

        // 두 작업 비교
        both();


    }


    // 짧은 작업
    public static void currentMillsSmallWork() {

        // 시작 시간
        var currentMills = System.currentTimeMillis();

        // 짧은 작업
        int i = 0;

        // 결과 출력
        System.out.println("MillsTime:" + (System.currentTimeMillis() - currentMills));
    }

    public static void nanoTimeSmallWork() {

        // 시작 시간
        long nanoTime = System.nanoTime();

        // 짧은 작업
        int i = 0;

        // 결과 출력
        System.out.println("NanoTime:" + (System.nanoTime() - nanoTime));
    }

    // 긴 작업
    public static void currentMillsBigWork() {

        // 시작시간
        long currentTimeMillis = System.currentTimeMillis();

        // 긴 작업
        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }

        // 결과 출력
        System.out.println("MillsTime:" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static void nanoTimeBigWork() {
        long nanoTime = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }

        System.out.println("NanoTime:" + (System.nanoTime() - nanoTime));
    }

    // 두 작업 비교
    public static void both() {

        long nanoTime = System.nanoTime();
        long currentTimeMillis = System.currentTimeMillis();


        for (int i = 0; i < 100000; i++) {

            int sum = +i;
        }

        System.out.println("MillsTime:" + (System.currentTimeMillis() - currentTimeMillis));
        System.out.println("NanoTime:" + ((System.nanoTime() - nanoTime) / 100000));
    }

}
