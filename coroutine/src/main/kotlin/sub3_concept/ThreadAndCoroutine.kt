package sub3_concept

/**
 * [실행 계층 구조]
 *      CPU Core (하드웨어)
 *          ↑
 *      Thread (OS 스케줄링 단위)
 *          ↑
 *      Coroutine (유저 레벨 실행 단위)
 *          ↑
 *      Event Loop / Dispatcher (실행 제어)
 */

/**
 * CPU Core
 *      실제로 명령어를 수행하는 하드웨어
 *      모든 코드는 결국 CPU core 위에서 실행된다.
 */

/**
 * Process
 *      실행 중인 프로그램 인스턴스 (독립된 메모리 공간)
 *      다른 프로세스와 메모리를 공유하지 않는다.
 */

/**
 * Thread
 *      프로세스 내의 실행 흐름
 *      실제로 CPU core 위에 올라가 실행되는 단위
 *
 *      특징
 *          - 각 스레드는 독립적인 call stack을 가진다.
 *          - 같은 프로세스 내에서 heap 메모리를 공유한다.
 *
 *      실행 모델
 *          - 코어보다 스레드가 많으면 → 동시성(concurrency)
 *          - 코어 수만큼 동시에 실행되면 → 병렬성(parallelism)
 */

/**
 * Routine
 *      하나의 작업 흐름 (함수, 메서드, 프로시저)
 *
 *      특징
 *          - 호출되면 스택에 쌓여 순차적으로 실행됨
 *          - 실행이 끝나면 스택에서 제거됨
 *          - 중간 상태를 저장하고 나중에 이어서 실행할 수 없음
 */

/**
 * Coroutine
 *      스레드 위에서 실행되는 "중단/재개 가능한" 작업 단위
 *
 *      특징
 *          - 스레드에 종속되지 않음
 *          - suspend 시 실행 상태를 저장하고, resume 시 이어서 실행 가능
 *
 *      동작 방식
 *          suspend 시:
 *              - 현재 실행 위치
 *              - 지역 변수
 *              - 필요한 상태
 *              → Continuation 객체에 저장
 *
 *          resume 시:
 *              → 해당 상태를 기반으로 실행 재개
 *
 *      중요한 점
 *          - 스택을 이동하는 것이 아니라 상태를 저장/복원하는 구조
 *          - 같은 스레드에서 실행될 수도 있고, Dispatcher에 따라 다른 스레드에서 실행될 수도 있음
 *          - context switching 비용이 thread context switching 비용 보다 현저하게 낮아서
 *              하나의 코어 하나의 스레드 환경이지만, 위에 여러 코루틴이 있으면 동시성(Concurrent)이 확보된다...
 *                  물론 코루틴에 대기해야할 상황이 있어서 suspend 지점이 존재해야 실행 권한이 넘어가며
 *                  다른 코루틴이 그 사이에 실행될 상황이 만들어질 것이다.
 *      주의
 *          여기서의 동시성은 OS 레벨의 스레드 처럼의 동시성이 아니라..
 *          실제로 코드 상에서 suspend 지점에서만 실행 권한이 넘어간다.
 *          실행 권한이 넘어가기 전까지는 해당 스레드에서만 동작함.
 *
 *          스레드 동시성은 "진짜 동시에 도는 느낌"이 강함
 * •        코루틴 동시성은 "한 스레드에서 번갈아 진행되는 느낌"이 강함
 */

/**
 * Thread vs Coroutine
 *
 *      Thread
 *          - OS 관리 단위 (무거움)
 *          - 생성/전환 비용 큼
 *          - 독립적인 call stack 유지
 *
 *      Coroutine
 *          - 유저 레벨 단위 (가벼움)
 *          - 스레드 위에서 실행됨
 *          - suspend 시 상태를 저장하고 재개
 *          - 스레드를 효율적으로 사용하기 위한 추상화
 */

/**
 * Event Loop
 *      실행 가능한 코루틴들을 관리하고
 *      "다음에 어떤 코루틴을 실행할지" 결정하는 메커니즘
 *
 *      특징
 *          - 작업 큐 기반으로 동작
 *          - 코루틴 간 실행 순서를 조정
 *          - runBlocking에서는 현재 스레드에서 event loop가 동작함
 */

/**
 * Dispatcher
 *      코루틴이 실행될 "스레드 또는 스레드 풀"을 결정하는 정책
 *
 *      예
 *          Dispatchers.Default → CPU 바운드 작업용 공용 background thread pool
 *          Dispatchers.IO      → blocking I/O 작업용 공용 thread pool
 *          runBlocking         → 현재 스레드 기반
 */

/**
 * 핵심 정리
 *
 *      - CPU core는 실제 계산을 수행한다.
 *      - Thread는 CPU 위에서 실행되는 실제 실행 흐름이다.
 *      - Coroutine은 Thread 위에서 스케줄링되는 논리적 작업 단위다.
 *      - Event Loop는 어떤 코루틴을 실행할지 결정한다.
 *      - Dispatcher는 어느 스레드에서 실행할지 결정한다.
 *
 * 참고
 *      코루틴은 suspend/resume 시
 *      Dispatcher에 의해 서로 다른 스레드에서 실행될 수 있다.
 */
