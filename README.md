# Java 학습 일지

Java를 직접 만들며·읽으며 익힌 기록. **javaDoc** 표준 라이브러리 익히기.
---

### 2026-06-15
- **javaDoc · java.lang.System** — Step 5(졸업 과제): `System.nanoTime()`으로 실행 시간을 재는 Stopwatch 작성 (`src/javaDoc/lang/system/Stopwatch.java`)
  - 측정 도구(`calcElapsedTime`)와 측정 대상(`print`) 분리
  - ns → µs 단위 변환 학습 (`%`나머지 vs `/`나누기 구분)

### 2026-06-01
- **javaDoc · java.lang.System** — System 학습 시작 + 실험 2종
  - `out` vs `err` 버퍼링: `halt`로 버퍼 손실 검증 (`Test.java`)
  - `currentTimeMillis` vs `nanoTime` 정밀도 비교 (`Test2.java`)
  - 학습 노트 정리 (`SystemDoc.java`)
- **minidb · R2** — Append-only log + replay 구현 (`src/minidb/r2/AppendOnly.java`)

### 2026-05-21
- **minidb · R1** — In-memory HashMap + 파일 dump(Properties) 구현 (`src/minidb/r1/MiniDB.java`)
  - 요구사항만 보고 직접 작성한 버전 `Practice.java` 추가 (PR #1)

### 2026-05-18
- **프로젝트 시작** — minidb: BitCask 풍 키-값 영속 저장소 학습 프로젝트 개시
