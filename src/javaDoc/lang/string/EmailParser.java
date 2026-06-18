package javaDoc.lang.string;

import java.util.Scanner;

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
 * <p>
 * 5. 이제 하드코딩 대신 사용자에게 입력을 받아보도록 하자 (Scanner) // 6/18
 *
 *
 * 클로드의 피드백:
 * - TODO 5 정확히 완료: 입력 출처만 Scanner로 갈아끼우고 기존 @ 분리 로직은 그대로 재사용 — 깔끔한 변경.
 *   `nextLine()` 선택도 적절(한 줄 통째로 받음. next()는 공백에서 끊긴다).
 * - 자잘1: 안 쓰는 import 정리 — `import static java.lang.String.valueOf;`는 어디서도 안 쓰인다.
 * - 자잘2: 입력 앞뒤 공백 방지로 `input.nextLine().trim()`을 고려. 사용자가 실수로 공백을 넣으면 indexOf/substring 결과가 흔들린다.
 * - 참고: Scanner를 close하지 않았다. 짧은 학습용 main에선 무방하나(System.in은 닫으면 재오픈 불가), 관용은 try-with-resources.
 * - 자잘3: 출력 콜론 띄어쓰기가 들쭉날쭉("입력한 값은:" / "도메인은 :") — 통일하면 보기 좋다.
 */
public class EmailParser {

    static void main(String[] args) {

        // 입력
        // 사용자에게 이메일을 입력받도록 한다.
//        String email = "test@email.com";
//        String email = "test";

        System.out.println("Enter an email address:");

        Scanner input = new Scanner(System.in);

        String email = input.nextLine();
        System.out.println("사용자가 입력한 값은:" + email + "입니다. ");


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
