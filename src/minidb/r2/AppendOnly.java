package minidb.r2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * 5/22
 * <p>
 * 매번 새로운 데이터를 덮어쓰는 r1 대신 끝에만 추가하는 appendOnly 형식을 추가한다.
 */
public class AppendOnly {

    // file 경로 (형식자는 log)
    private static final Path FILE_PATH = Path.of("db.log");

    // kv 선언
    private static Map<String, String> map = new HashMap<>();

    // 데이터 넣기
    public void put(String k, String v) {
        map.put(k, v);
    }

    // 데이터 가져오기
    public String get(String k) {
        return map.get(k);
    }

    // 삭제
    public void remove(String k) {
        map.remove(k);
    }

    // 전체 보여주기
    public void list() {
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    // 전체 삭제
    public void clear() {
        map.clear();
    }

    // appendLog
    public void appendLog(String line) throws IOException {

        // byte 형식의 데이터를 받는다. ("\n" 으로 구분한다.)
        byte[] bytes = (line + "\n").getBytes(StandardCharsets.UTF_8);
        // 파일 경로에 byte파일을 생성하고 파일끝에 생성한다.
        Files.write(FILE_PATH, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }


    // r1은 properties KV 로 받아서 바로 key, value 로 매핑되었는데 왜 r2는 line으로 받아오지?
    // 불러오기
    public void load() throws IOException {

        // 파일이 존재하는지 확인
        if (!Files.exists(FILE_PATH)) {
            System.err.println("File does not exist.");
            return;
        }

        // 파일에 있는 모든 라인을 불러온다.
        for (String line : Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8)) {

            // 빈칸인지 확인한다.
            if (line.isBlank()) {
                return;
            }

            String[] parts = line.split(" ", 3); // 왜 이렇게 자르지?

            // 명령어를 검사한다.
            switch (parts[0]) {

                // 만약 put 이면 메모리에 저장한다.
                case "PUT" -> {
                    map.put(parts[1], parts[2]);
                }

                // 만약 del 이면 메모리에서 삭제한다.
                case "REMOVE" -> {
                    map.remove(parts[1]);
                }
            }
        }


    }

    // main
    public static void main(String[] args) throws IOException {

        // 객체 생성
        AppendOnly db = new AppendOnly();

        // load
        db.load();

        // 아무것도 입력안했을 때에 보여주는 메시지
        if (args.length == 0) {
            System.err.println("Usage: PUT|GET|REMOVE");
        }

        // 각 명령어 처리

        // default 에서는 사용자에게 에러 반환

    }

}
