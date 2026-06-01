package javaDoc.lang.system;

/**
 * javaDoc 의 System 에 해당하는 문서를 읽고 작성해본다.
 */
public class SystemDoc {

    public static void main(String[] args) {

        /**
         * err 과 out 의 차이점
         */
        System.out.println("print - out");
        System.err.println("print - err");

        /**
         * currentMills 와 nanoMills 의 차이점
         */
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());




        /**
         * 대조 실험
         */

        try {
            System.out.println("Return 실행");
            return;
        } catch (Exception e) {
            System.out.println("Return 중 에러 발생.");
        } finally {
            System.out.println("finally 실행됨");
        }

        /**
         * 호출 직전의 finally는 호출될까?
         */
        try {
            System.out.println("System.exit(0) 실행");
            System.exit(0);
            System.out.println("실행 종료");
        } catch (Exception e) {
            System.out.println("exit 전에 실행되었습니다.");
        } finally {
            System.out.println("finally 실행됨");
        }


    }
}
