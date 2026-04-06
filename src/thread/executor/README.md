# Executor 
* java의 Executor framework는 multithread 및 병열 처리를 쉽게 사용할 수 있도록 돕는 기능의 모음이다. 이프레임워크는 작업 실행의 관리 및 스레드 풀 관리를 효율적으로 처리해서 개발자 직접 스레드를 생성하고 관리하는 복잡함을 줄여준다.
## Executor framework의 강점
<pre>
요청 스레드가 결과를 반아야 하는 상황이라면, Callable을 사용한 방식은 Runnable을 사용하는 방식보다 훨씬 편리하다.
코드만 보면 복잡한 멀티스레드를 사용한다는 느낌보다는, 단순한 싱글스레드 방식으로 개발한다는 느낌이 들 것이다. 
이 과정에서 내가 스레드를 생성하거나, join()으로 스레드를 제어하거나 한 코드는 전혀 없다. 심지어 Thread라는 코드도 없다. 
단순하게 ExecutorService에 필요한 작업을 요청하고 결과를 받아 쓰면 된다. 
복잡한 멀티스레드를 매우 편리하게 사용할 수 있는 것이 바로 Executor 프레임워크의 강점이다. 
</pre>

## Excutor Interface
```java
package java.util.concurrent;

public interface Executor {
    void execute(Runnable command);
}
```

## ExecutorService Interface
```java
public interface ExecutorService extends Executor, AutoCloseable {
    <T> Future<T> submit(Callable<T> task);
    
    @Override
    default void close(){
        //...
    }
    //....
}
```
* Executor interface를 확장하여 작업 제출과 제어 기능을 추가로 제공
* 주요 메서드로
  * submit() 
    * ExecutorService가 제공하는 submit()을 통해 Callable을 직접 전달할 수 있다.
      * Future<Integer> future = es.submit(new MyCallable());
        * MyCallable 인스턴스가 블로킹 큐에 전달되고, 스레드 풀의 스레드 중 하나가 이 작업을 실행할 것이다. 이때 작업의 처리 결과는 직접 반환되는 것이 아니라 Future라는 특별한 인터페이스를 통해 반환된다.
  * close()
  * etc.....
* Executor framework를 사용할대 대부분 ExecutorService Interface를 사용함.

## ThreadPoolExecutor 생성자
* ThreadPoolExecutor의 생성자는 다음 속성을 사용한다.
  * corePoolSize : 스레드 풀에서 관리되는 기본 스레드의 수
  * maximumPoolSize : 스레드 풀에서 관리되는 최대 스레드 수 
  * keepAliveTime, TimeUnit unit: 기본 스레드 수를 초과해서 만들어진 스레드가 생존할 수 있는 대기 시간이다. 이 시간동안 처리할 작업이 없다면 초과 스레드는 제거된다. 
  * BlockingQueue workQueue: 작업을 보관할 블로킹 큐 
* java.util.concurrent.Executors가 제공하는 __newFixedThreadPool(size)__ 을 사용하면 편리하게 ExecutorService를 생성할 수 있다.
  * Before: ExecutorService es = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISCONDS, new LinkedBlockingQueue<>());
  * Now: ExecutorService es = Executors.newFixedThreadPool(1);

# Runnable & Callable 
## Runnabl 
```java
package java.lang;

public interface Runnable {
    void run();
}
```
* Runnable 의 run()은 반환 타입이 void이다. 따라서 값을 반환할 수 없다. 
* 예외가 선언되어 있지 않다. 따라서 해당 인터페이스를 구현하는 모든 메서드는 체크 예외를 던질 수 없다.
* 런타임(비체크) 예외는 제외된다. 

## Callable 
```java
package java.util.concurrent;

public interface Callable<V> {
    V call() throws Exception; // <--Exception 예외 
}
```
* java.util.concurrent에서 제공되는 기능
* Callable 의 call() 반환 타입이 제너릭 V 이다. 따라서 값을 반환할 수 있다.
* throws Exception 예외가 선언되어 있다. 따라서 해당 인터페이스를 구현하는 모든 메서드는 체크 예외인 Exception과 하위 예외를 모두 던질 수 있다. 