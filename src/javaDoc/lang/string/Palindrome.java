package javaDoc.lang.string;

import java.util.Locale;

/**
 * ㅎ회문(팰린드롬)검사기
 * <p>
 * 입력: 문자열 1개
 * 출력: 회문입니다. 또는 회문이 아닙니다.
 * 규칙: 공백과 대소문자는 무시하고 판정
 * 예시: `level` → 회문 / `hello` → 아님 / `never odd or even` → 회문 / `기러기` → 회문
 * <p>
 * 1. 입력값을 받는다.
 * 2. 공백을 제거하고 소문자로 만든뒤 문자열을 끝에서부터 받아온다.
 * 3. 초기값과 끝에서부터 읽어온 값을 비교한다.
 * 4. 결과를 반환한다.
 *
 *
 * 클로드의 피드백:
 * - 구조 굿: 공백 제거 → StringBuilder.reverse() → equals 비교는 회문 검사의 정석. 한글 `기러기`도 BMP 문자라 reverse가 정상 동작(직접 실행해 확인).
 * - 핵심 버그: 대소문자 무시가 빠졌다. 규칙·2번 계획엔 "소문자로 만든 뒤"라 적었고 `import java.util.Locale`까지 추가했는데 정작 toLowerCase 호출이 없다.
 *   그래서 `Level`·`Anna`·`A man a plan a canal Panama`가 전부 "아님"으로 오판정됨(직접 실행해 재현).
 *   수정: str1 = str1.replaceAll("\\s", "").toLowerCase(Locale.ROOT);  // 추가해 둔 Locale import가 여기서 쓰인다(로케일 의존 케이스 방지)
 * - 군더더기: str1을 이미 공백 제거로 재할당한 뒤 str2 = str1.replaceAll(...)로 또 제거 → str2는 str1과 동일(2중 처리·무의미).
 *   "정제한 문자열 1개 + 그 reverse" 비교라 변수 하나면 충분. 비교 대상을 한 변수로 맞추면 의도가 분명해진다.
 * - 다음 단계: 지금은 입력이 하드코딩("hello"). EmailParser처럼 Scanner로 사용자 입력 받기로 확장하면 좋다.
 * - 자잘: 첫 줄 `ㅎ회문` 앞의 'ㅎ'는 오타.
 */
public class Palindrome {

    static void main(String[] args) {

        String str1 = "hello";
        System.out.println("str: " + str1);

        str1 = str1.replaceAll("\\s", "");
        String str2 = str1.replaceAll("\\s", "");
        System.out.println("공백제거: " + str2);

        StringBuilder stringBuilder = new StringBuilder(str2);

        stringBuilder.reverse();
        System.out.println("str2: " + stringBuilder);

        if (str1.equals(stringBuilder.toString())) {
            System.out.println("회문입니다.");
        } else {
            System.out.println("회문이 아닙니다.");
        }

    }
}
