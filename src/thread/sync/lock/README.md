## Syncronized 
* synchronized java 1.0부터지원되지만 한계가 있음
* Synchronized 단점 
  * 무한대기 : BLOCKED 상태의 스레드는 락이 풀릴때까지 무한대기한다. 
    * 특정 시간까지만 대기하는 타임아웃이 아니다. 
    * 중간 인터럽트 안됨
  * 공정성: 락이 돌아왔을때 BLOCKED 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 알 수 없다. 
- - -

## java.util.concurrent 등장.(since java 1.5)
## LockSupport 
* LockSupport는 스레드를 WAITING 상태로 변경한다. 
* WAITING 상태는 누가 깨워주기 전까지 계속 대기하며, CPU 실행 스케쥴에 들어가지 않는다. 

## LockSupport 주요기능
* park() 스레드를 WAITING상태로 변경
  * 스레드를 대기상태로 둔다
* parkNanos(nanos): 스레드를 나노초동안 TIME_WAITING상태로 변경
  * 지정한 나노초가 지나면 TIME_WAITING 상태에서 빠져 나오고 RUNNABLE 상태로 변경됨
* unpark(thread): WAITING 상태의 대장 스레드를 RUNNABLE상태로 변경한다. 
