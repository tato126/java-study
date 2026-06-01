# Java 학습 저장소

Java를 직접 만들며·읽으며 익히는 학습 모음.

## 트랙

| 트랙 | 내용 |
|---|---|
| **minidb** | BitCask 풍 KV 데이터베이스 단계별 구현 (R1~R5) |
| **javaDoc** | Javadoc만 보고 표준 라이브러리 익히기 (java.lang ~) |

## 환경

- Java 21
- IntelliJ Community Edition
- 빌드 도구 없음 (plain Java project)

---

## minidb — BitCask 풍 KV DB

매일 쓰는 데이터베이스의 한 층 아래를 Java로 직접 만들어보는 학습 프로젝트.

### 목표

- HashMap 메모리 저장 → 파일 영속화 → append-only log → 인덱스 → compaction → 동시성
- BitCask 풍 KV 데이터베이스를 단계별로 구현

### 챕터

| 챕터 | 내용 |
|---|---|
| R1 | In-memory HashMap + 파일 dump (Properties) |
| R2 | Append-only log + replay |
| R3 | 메모리 인덱스 (offset map) |
| R4 | Compaction (tombstone 정리) |
| R5 | 동시성 (ReentrantReadWriteLock) |

### R1 실행 방법

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

### 파일 구조

```
src/minidb/
  r1/
    MiniDB.java    # 레퍼런스 구현
    Practice.java  # 요구사항만 보고 혼자 작성한 버전
  r2/
    AppendOnly.java  # R2: Append-only log
```

---

## javaDoc — Javadoc만 보고 표준 라이브러리 익히기

블로그·AI 없이 **공식 Javadoc만** 보고 자바 표준 라이브러리를 익히는 워크북.
명세(Javadoc) → 실험 → 소스(ground truth) 순으로 검증한다.

### 단계

| 단계 | 패키지 | 내용 |
|---|---|---|
| 1 | java.lang | String / Object / Integer / Math / System / Exception |
| 2 | java.util | 컬렉션 |
| 3 | java.io · java.nio.file | I/O·파일 |
| 4 | java.util.concurrent | 동시성 |

### 파일 구조

```
src/javaDoc/lang/system/
  SystemDoc.java   # System 학습 노트
  Test.java        # 실험 1: out/err 버퍼링 (halt 로 버퍼 손실 검증)
  Test2.java       # 실험 2: currentTimeMillis vs nanoTime
```
