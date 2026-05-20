package minidb.r1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * r1 손으로 보고 타이핑 한것을 요구사항을 보고 내가 작성해본다. 5/20
 * <p>
 * 간단한 hashMap 메모리 저장 디비를 만든다.
 */
public class Practice {

//    $ java MiniDB put name chan
//    $ java MiniDB put age 28
//    $ java MiniDB get name
//            chan
//    $ java MiniDB list
//    name=chan
//            age=28
//    $ java MiniDB delete age
//    $ java MiniDB get age
//            (null)
//    $ cat db.dat
//    name=chan

    // 주소 경로 선언
    private static final Path FILE_PATH = Path.of("db.dat");

    // kv 맵 선언 (임시 저장)
    private final Map<String, String> map = new HashMap<>();

    // put 명령어
    public void put(String key, String value) {
        map.put(key, value);
    }

    // get 명령어
    public String get(String key) {
        return map.get(key);
    }

    // list 명령어
    public void list() {
        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    // delete 명령어
    public void delete(String key) {
        map.remove(key);
    }

    // clear 명령어
    public void clear() {
        map.clear();
    }

    // save
    public void save() throws IOException {

        Properties properties = new Properties();
        properties.putAll(map);
        try (var writer = Files.newBufferedWriter(FILE_PATH)) {
            properties.store(writer, null); // properties 에 파일을 쓸 때는 store
        }
    }

    // load

    // 불러오기
    // 기존의 내용을 불러온다.
    // 파일을 불러와서 새로운 파일에 내용을 처음부터 끝까지 복사한다.
    // 영속성을 위해서 다시 메모리에 넣어둔다.
    public void load() throws IOException {

        Properties properties = new Properties();
        try (var loader = Files.newBufferedReader(FILE_PATH)) {
            properties.load(loader);
        }
        properties.forEach((k, v) -> map.put((String) k, (String) v)); // 근데 put 넣을 때 k,v 를 String 으로 넣었는데 왜 형변환을 해주지?
    }

    public static void main(String[] args) throws IOException {

        // 객체를 생성한다.
        Practice practice = new Practice();

        // 영속석을 통해 정보를 불러온다.
        practice.load();

        // 만약 입력 길이가 0이면
        if (args.length == 0) {
            System.err.println("Usage: put|get|list|clear|delete ...");
            return;
        }

        String cmd = args[0];

        // 명령별로 커맨드 라인을 입력받는다.
        switch (cmd) {
            case "put" -> {
                practice.put(args[1], args[2]);
                System.out.println("Successfully put " + args[1] + " = " + args[2]);
                practice.save();
            }

            case "get" -> {
                practice.get(args[1]);
                System.out.println(practice.get(args[1]));
            }

            case "list" -> {
                practice.list();
            }

            case "clear" -> {
                practice.clear();
                practice.save();
            }

        // default 로는 unkown 커맨드 라고 알려준다.
            default -> {
                System.err.println("Unknown command: " + cmd);
            }
        }
    }
}
