# Mini-DB

매일 쓰는 데이터베이스의 한 층 아래를 Java로 직접 만들어보는 학습 프로젝트.

## 목표

- HashMap 메모리 저장 → 파일 영속화 → append-only log → 인덱스 → compaction → 동시성
- BitCask 풍 KV 데이터베이스를 단계별로 구현

## 챕터

| 챕터 | 내용 |
|---|---|
| R1 | In-memory HashMap + 파일 dump (Properties) |
| R2 | Append-only log + replay |
| R3 | 메모리 인덱스 (offset map) |
| R4 | Compaction (tombstone 정리) |
| R5 | 동시성 (ReentrantReadWriteLock) |

## R1 실행 방법

```bash
# 컴파일
javac -d out src/minidb/r1/MiniDB.java

# 명령어
java -cp out minidb.r1.MiniDB put name chan
java -cp out minidb.r1.MiniDB get name
java -cp out minidb.r1.MiniDB list
java -cp out minidb.r1.MiniDB delete name
java -cp out minidb.r1.MiniDB clear
```

IntelliJ에서 실행할 경우 `Run > Edit Configurations > Program arguments`에 명령어를 입력한다.

## 파일 구조

```
src/
  minidb/
    r1/
      MiniDB.java    # 레퍼런스 구현
      Practice.java  # 요구사항만 보고 혼자 작성한 버전
```

## 환경

- Java 21
- IntelliJ Community Edition
- 빌드 도구 없음 (plain Java project)
