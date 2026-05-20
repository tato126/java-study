package minidb.r1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MiniDB {

    // 저장 -> hashmap (휘발성)
    private final Map<String, String> store = new HashMap<>();
    // 영속성 -> db.dat
    private static final Path DB_FILE_PATH = Path.of("db.dat");

    // db 명령어

    // put
    public void put(String key, String value) {
        store.put(key, value);
    }

    // get
    public String get(String key) {
        return store.get(key);
    }

    // delete
    public void delete(String key) {
        store.remove(key);
    }

    // list
    public void list() {
        store.forEach((k, v) -> System.out.println(k + "= " + v));
    }

    // clear
    public void clear() {
        store.clear();
    }

    // 영속성

    // save
    public void save() throws IOException {
        Properties properties = new Properties();
        properties.putAll(store);
        try (var writer = Files.newBufferedWriter(DB_FILE_PATH)) {
            properties.store(writer, null);
        }
    }

    // load
    public void load() throws IOException {

        // 1. 파일이 존재하지 않을 경우
        if (!Files.exists(DB_FILE_PATH)) {
            return;
        }

        // 2. 파일을 새로 만듬
        Properties properties = new Properties();

        // 3. 파일을 새로 읽어온다.
        try (var load = Files.newBufferedReader(DB_FILE_PATH)) {
            properties.load(load);
        }

        // 4. 반복해서 가져옴
        properties.forEach((k, v) -> put((String) k, (String) v));

    }

    // 5/18
    // main
    public static void main(String[] args) throws IOException {

        // 객체 생성
        MiniDB miniDB = new MiniDB();

        // 초기 로드
        miniDB.load();

        // 입력값이 없을 때 도움말 보여주기
        if (args.length == 0) {
            System.err.println("usage: put|get|delete|list|clear");
            return;
        }

        // 사용자 입력에 따른 응답
        String cmd = args[0];

        switch (cmd) {

            // 1. 사용자가 데이터를 넣었을 때에
            case "put" -> {
                miniDB.put(args[1], args[2]);
                miniDB.save();
            }

            // 2. 사용자가 키를 조회할 때
            case "get" -> {
                System.out.println(miniDB.get(args[1]));
            }

            // 3. 사용자가 데이터를 삭제했을 때에
            case "delete" -> {
                miniDB.delete(args[1]);
                miniDB.save();
            }

            // 4. 사용자가 디비 리스트를 전부 보여달라 할 때에
            case "list" -> {
                miniDB.list();
            }

            // 5. 사용자가 데이터를 영구 삭제 했을 때에
            case "clear" -> {
                miniDB.clear();
                miniDB.save();
            }

            // 6. 사용자가 잘못된 값을 입력할 때에
            default -> {
                System.err.println("unknown command: " + cmd);
                return;
            }
        }

    }

}
