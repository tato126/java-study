package javaDoc.lang.string;

import static java.lang.String.valueOf;

/**
 * 이메일 파서
 * <p>
 * 입력: 이메일 문자열 1개 (test@email.com)
 * 출력: 아이디: test, 도메인: email.com
 * <p>
 * 요구사항: @가 없는 입력(예: hello)가 들어와도 잘못된 형식입니다.를 출력할 것
 * <p>
 * 1. 일단 입력을 받는다.
 * 2. @가 몇번째 위치에 있는지 확인한다.
 * 3. 아이디를 구한다. (아이디는 0번째부터 @-1까지가 아이디이다.)
 * 4. 도메인을 구한다. (도메인은 @+1부터 length - 1까지)
 * ---------- 6/16
 *
 * 5. 이제 하드코딩 대신 사용자에게 입력을 받아보도록 하자 (Scanner)
 */
public class EmailParser {

    static void main(String[] args) {

        // 입력
//        String email = "test@email.com";
        String email = "test";

        // 아이디
        String id = "";

        // 도메인
        String domain = "";

        // @ 위치 구하기
        int atSign = email.indexOf("@");
        System.out.println("@ 위치: " + atSign + "번째");

        // 요구사항 : 만약 atSign이 -1이면 에러 메시지 출력
        if (atSign == -1) {
            System.out.println("잘못된 이메일 형식입니다.");
        } else {

            // 아이디 구하기
            id = email.substring(0, atSign);

            System.out.println("아이디는 : " + id);

            // 도메인 구하기
            domain = email.substring(atSign + 1);
            System.out.println("도메인은 :" + domain);
        }

    }
}
