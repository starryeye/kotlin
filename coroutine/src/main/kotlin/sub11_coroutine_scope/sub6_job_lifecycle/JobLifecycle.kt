package sub11_coroutine_scope.sub6_job_lifecycle

/**
 * Job Lifecycle..
 *
 * Job 이란?
 *      Job 은 코루틴 작업 자체를 나타내는 핸들이다.
 *
 *      다음 정보를 가진다.
 *          - 현재 상태
 *          - 취소 여부
 *          - 완료 여부
 *          - 부모-자식 관계
 *
 * 큰 흐름
 *      정상 종료 경로:
 *          NEW -> ACTIVE -> COMPLETING -> COMPLETED
 *      취소 종료 경로:
 *          NEW -> ACTIVE -> CANCELLING -> CANCELLED
 *
 * Job 상태 변화
 *          - launch(start = LAZY)   -> NEW 상태
 *          - start()                -> ACTIVE 전이
 *          - cancel()               -> CANCELLING 전이
 *          - delay 중 취소 감지      -> CANCELLING / CANCELLED 흐름
 *          - 정상 종료               -> COMPLETING / COMPLETED 흐름
 *
 *
 * 상태 목록
 *
 * 1. NEW
 *      아직 시작되지 않은 상태
 *
 *      대표 예:
 *          launch(start = CoroutineStart.LAZY)
 *
 *      의미:
 *          - Job 객체는 만들어졌다.
 *          - 하지만 아직 실행은 시작하지 않았다.
 *          - 아직 코드 블록 본문이 실제로 돌지 않았다.
 *
 *      전이:
 *          NEW -> ACTIVE
 *              start(), join(), await() 등의 계기로 시작될 수 있다.
 *
 * 2. ACTIVE
 *      현재 실행 중이거나,
 *      실행 가능 상태로 살아 있는 상태
 *
 *      의미:
 *          - 코루틴이 실제로 돌고 있을 수 있다.
 *          - 혹은 suspend 되었다가 다시 재개(resume)될 수 있는 살아 있는 상태일 수도 있다.
 *
 *      중요한 점:
 *          ACTIVE 는 "CPU를 지금 쓰는 중"만 뜻하지 않는다.
 *              CPU 를 쓰고 있지 않더라도 event loop 에 실행 대기중이어도 ACTIVE 이다.
 *              delay 중처럼 suspend 상태라도 Job 관점에서는 아직 살아 있으면 ACTIVE 로 본다.
 *
 *      전이:
 *          ACTIVE -> COMPLETING
 *              본문이 정상적으로 끝나려는 중
 *
 *          ACTIVE -> CANCELLING
 *              cancel() 요청 또는 부모 취소/실패 전파가 들어온 경우
 *
 * 3. COMPLETING
 *      정상 종료로 향하는 중간 상태
 *
 *      의미:
 *          - 코루틴 본문은 거의 끝났거나 끝났다.
 *          - 하지만 자식 Job 들이 아직 남아 있을 수 있다.
 *          - 부모 Job 이 완전히 완료되기 전에 자식 완료를 정리하는 단계다.
 *
 *      전이:
 *          COMPLETING -> COMPLETED
 *              모든 자식까지 정상적으로 정리 완료
 *
 * 4. COMPLETED
 *      정상 완료 상태
 *
 *      의미:
 *          - 코루틴 작업이 성공적으로 끝났다.
 *          - 더 이상 실행되지 않는다.
 *          - join() 하는 쪽에서는 이제 즉시 통과할 수 있다.
 *
 *      특징:
 *          - 완료된 Job 은 다시 ACTIVE 로 돌아가지 않는다.
 *          - lifecycle 의 종료 상태(final state)다.
 *
 * 5. CANCELLING
 *      취소가 진행 중인 상태
 *
 *      의미:
 *          - cancel() 요청이 들어왔다.
 *          - 또는 부모 취소/실패가 전파되었다.
 *          - 아직 즉시 모든 코드가 사라진 것은 아니다.
 *
 *      왜 "진행 중" 상태가 따로 있나?
 *          취소는 버튼 한 번 누른다고 기계적으로 즉시 사라지는 게 아니다.
 *
 *          코루틴은 이 상태에서
 *              - finally 블록을 실행할 수 있고
 *              - 자식 Job 정리를 할 수도 있다
 *
 *      연결 개념:
 *          sub8 의 "cancel 은 즉시 강제 종료가 아니라 취소 요청"이라는 설명이
 *          바로 이 상태를 말한다.
 *
 *      전이:
 *          CANCELLING -> CANCELLED
 *              취소 정리 절차까지 끝난 경우
 *
 * 6. CANCELLED
 *      취소 종료 상태
 *
 *      의미:
 *          - 이 Job 은 취소로 종료되었다.
 *          - 더 이상 실행되지 않는다.
 *          - lifecycle 의 종료 상태(final state)다.
 *
 *      COMPLETED 와의 차이
 *          - COMPLETED : 정상 종료
 *          - CANCELLED : 취소로 종료
 *
 */
fun main() {}
