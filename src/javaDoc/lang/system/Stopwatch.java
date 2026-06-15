package javaDoc.lang.system;

/**
 * 명령어 실행 시간을 마이크로초 단위로 재는 stopwatch 프로그램
 * 1. 먼저 프린트를 10번하는 메서드를 만든다.
 * 2. 이제 메서드의 시간을 측정한다.
 * 3. 마이크로초로 변환한다.
 * <p>
 * 1~3번까지의 문제점: 측정하려는 메서드에 매번 카운트와 출력을 작성해줘야한다. -> 코드가 중복된다.
 * <p>
 * 내가 본 중복되는 구조 ->
 * 시작 시간
 * <p>
 * 실행하려는 메서드
 * <p>
 * 측정한 시간 마이크로초 변환 후 출력
 * <p>
 * -> 이렇게 하니 각 메서드마다 안적어도 되니까 좋다.
 * 궁금증: 만약 줄바꿈을 안하고 싶을 때가 있어요. -> 그러면 인자를 넣어주자 만약 boolean enter가 true 이면 println 으로
 * <p>
 * <p>
 * 테스트 -> 만약 반복을 100번 한다면?
 * <p>
 * -> 메서드를 100번 반복했더니 0ms 초로 나온다. 어떻게 측정할 수 있을까?
 * <p>
 * -> 그러면 현재 초를 계산하는게 하드 코딩이니까 이를 변환해보자
 * 만약 % 1000이 0이면 100으로 나누던가... ---> 일단 하드코딩이라도 없애보자
 *
 *
 *
 *
 * 클로드의 피드백:
 * - 구조 설계 굿: "시작→실행→측정" 중복을 calcElapsedTime으로 뽑은 게 핵심을 정확히 짚음.
 * - 핵심 버그: '% 1000'은 "나머지"라 시간 변환이 아님. ns→µs는 '/ 1000'(나누기)여야 함.
 *   또 단위 라벨이 ms인데 목표는 마이크로초(µs). 숫자도 단위도 둘 다 틀린 값이 찍힘.
 *   예) 1_234_567ns -> /1000=1234µs(정답), /1_000_000=1ms, %1000=567(자투리·무의미)
 * - "100번 반복했더니 0" 의 원인이 바로 이것. ms는 너무 커서 버림돼 0이 됨.
 *   해결은 하드코딩 분기가 아니라 "더 작은 단위(µs)로 보기" = 단위 선택이 곧 정밀도 선택.
 * - 수정: long us = elapsedTime / 1000;  // ns→µs (소수점 원하면 / 1000.0)
 *         println("Elapsed time: " + us + " us");
 * - 자잘: 'if (enter == true)' -> 'if (enter)' 가 관용. 변수명 elapsedTime -> elapsedNanos면 단위가 드러남.
 */
public class Stopwatch {

    static void main(String[] args) {

        System.out.println("===============================================");

        long startTime = System.nanoTime();

        // 실행하려는 메서드
        print("hi", 100, true);

        calcElapsedTime(startTime);


        System.out.println("===============================================");
    }


    // 다른 동작 메서드 2
    public static void print(String printStr, int count, boolean enter) {

        if (enter == true) {

            for (int i = 0; i < count; i++) {
                System.out.println(printStr);
            }
        } else {

            for (int i = 0; i < count; i++) {
                System.out.print(printStr);
            }
        }
    }

    // 시간 초를 계산하는 메서드
    public static void calcElapsedTime(long startTime) {
        long elapsedTime = System.nanoTime() - startTime;

        System.out.println();

        System.out.println("Elapsed time: " + elapsedTime % 1000 + " ms");

    }


//    // 다른 동작 메서드 1
//    public static void print10() {
//
//        // 시작 시간을 카운트한다.
//        long start = System.nanoTime();
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Hello World!");
//        }
//
//        long elapsedNanos = System.nanoTime() - start;
//
//        System.out.println("Elapsed time: " + elapsedNanos % 1000 + " ms");
//    }

}
