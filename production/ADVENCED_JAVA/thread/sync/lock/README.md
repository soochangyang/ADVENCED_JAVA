## Syncronized 
* synchronized java 1.0부터지원되지만 한계가 있음
* Synchronized 단점 
  * 무한대기 : BLOCKED 상태의 스레드는 락이 풀릴때까지 무한대기한다. 
    * 특정 시간까지만 대기하는 타임아웃이 아니다. 
    * 중간 인터럽트 안됨
  * 공정성: 락이 돌아왔을때 BLOCKED 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 알 수 없다. 
- - -

## java.util.concurrent 등장.(since java 1.5)
## LockSupport 😁😎🥷
* LockSupport는 스레드를 WAITING 상태로 변경한다. 
* WAITING 상태는 누가 깨워주기 전까지 계속 대기하며, CPU 실행 스케쥴에 들어가지 않는다. 

## LockSupport 주요기능
* **park()**: 스레드를 ***WAITING***상태로 변경
  * 스레드를 대기상태로 둔다
* **parkNanos(nanos)**: 스레드를 나노초동안 TIME_WAITING상태로 변경
  * 지정한 나노초가 지나면 TIME_WAITING 상태에서 빠져 나오고 RUNNABLE 상태로 변경됨
* **unpark(thread)**: WAITING 상태의 대장 스레드를 RUNNABLE상태로 변경한다.


## ReeentrantLock 
* synchronized 와 BLOCKED 상태를 통한 임계 영역관리의 한계를 극복하기 위해 1.5 부터 Lock 인터페이스와 ReenterantLock 구현체를 제공.
* synchronized는 공정성 대한 이슈가 있는데 이를 해결하기 위해 ReenterantLock이 제공된다.
* ReentrantLock은 공정성/ 비공정성 모드로 설정가능
  * **Non-fair mode**
    * ReentrantLock의 기본 모드이다. 이 모드에서는 락을 먼저 요청한 스레드가 락을 먼저 획득한다는 보장이 없다. 락을 풀었을때, 대기중인 스레드중 아무나 lock을 획득할 수 있다. 이는 lock를 빨리 획득할 수 있지만, 특정 스레드가 장기간 락을 획득하지 못활 가능성이 있다. 
      * 성능우선: 락을 획득하는 속도 빠름
      * 선점가능: 새로운 스레드가 기존 대기 스레드보다 먼저 락을 획득할 수 있다 
      * 기아 현상 가능성: 특정 스레드가 계속해서 락을 획득하지 못할 수 있다. 
  * **Fair mode**
    * 생성자에서 true를 전달하면 된다. ex) new ReentrantLock(true);
    * lock을 요청한 순서대로 스레드가 lock을 획득할 수 있게 한다. 이는 대기한 스레드가 먼저 lock을 획득하게 되어 스레드간 공정성을 보장하지만, 성능저하가 발생할 수 있다. 
      * 대기큐에서 먼저 대기한 스레드가 먼저 lock을 획득함
      * 모든 스레드가 언제나 락을 획득할 수 있게 보장함
      * 락을 획득하난 속도가 느려짐
```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {
    private final Lock nonFairLock = new ReenterantLock();
    private final Lock fairLock = new ReentrantLock(true);
    
    public void nonFairLockTest(){
        nonFairLock.lock();
        try {
            //임계영역
        } finally {
            nonFairLock.unlock();
        }
    }
    
    public void fairLockTest(){
        fairLock.lock();
        try {
            //임계영역
        } finally {
            fairLock.unlock();
        }
    }
}
```


### Lock interface
* Lock 인터페이스는 동시성 프로그래밍에서 쓰이는 안전한 임계 영역을 위한 Lock을 구현하는데 사용한다.
* Lock Interface 에서 제공하는 Method ReentrantLock
  * void lock()
    * lock 획득한다. 이미 다른스레드가 lock를 획득했다면, lock 풀릴때까지 현재 스레드는 WAITING 한다.
    * interrupt에 응답하지 않는다.
  * void lockInterruptibly()
    * lock 획득을 시도하고, 다른 스레드가 인터럽트 할 수 있도록 한다. 다른 스레드가 이미 락을 획득하였다면, 현재 스레드는 락을 획득할 때까지 대기한다 또한 대기중에 Interrupt가 발생하면  InterruptedException을 발생하며 lock획득을 포기한다. 
  * boolean tryLock()
    * lock획득을 시도하고, 즉시 성공 여부를 반환한다. 만약 다른스레드가 이미 락을 획득하였다면 false를 반환하고, 그렇지 않다면 lock을 획득하고 true를 반환한다. 
  * boolean tryLock(long time, TimeUnit unit)
    * 주어진 시간 동안 lock 획득을 시도한다. 주어진 시간 안에 락을 획득하면, true를 반환한다. 주어진 시간이 지나도 락을 획득하지 못한 경우 false를 반환한다. 대기중에 Interupt가 발생하면 InterruptedException이 발생하고 lock획득 포기. 
  * void unlock()
    * lock을 해제한다. lock을 해제하면 lock획득 대기중인 스레드중 하나가 lock을 획득할 수 있게 된다.
    * lock을 획득한 스레드가 호출해야 하며, 그렇지 않으면 IllegalMonitorStateException이 발생할 수 있다. 
  * Condititon newCondition()
    * Condition 객체를 생성하여 반환한다. Condition 객체는 lock과 결합되어 사용되며, 스레드가 특정 조건을 기다리거나 신호를 받을 수 있도록 함. 이는 Object 클래스의 wait, notify, notifyAll 메써드롸 유사한 역할을 한다. 
```java
package java.util.concurrent.locks;

public interface Lock {
  void lock();
  void lockInterruptibly() throws InterruptedException;
  boolean tryLock();
  boolean tryLock(long time, TimeUnit unit) throws InterrupedException;
  void unlock();
  Condition newCondition();
}
```

