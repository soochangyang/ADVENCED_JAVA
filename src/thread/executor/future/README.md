# Future
<pre>
미리의 결과를 받을 수 있는 객체라는 의미이다. 
</pre>
```java
Future<Integer> future = es.submit(new MyCallable());
```
<pre>
submit()의 호출로 MyCallable의 instance를 전달한다.
이때 submit()은 MyCallable.call() 이 반환하는 무작위 숫자 대신에 Future를 반환한다. 
생각해보면 MyCallable이 즉시 실행되어서 즉시 결과를 반환하는 것은 불가능하다. 
왜냐하면 MyCallable은 즉시 실행되는 것이 아니다. 
스레드 풀의 스레드가 미래의 어떤 시점에 이 코드를 대신 실행해야 한다. 
MyCallable.call() 메서드는 호출 스레드가 실행하는 것도 아니고, 스레드 풀의 다른 스레드가 실행하기 때문에 언제 실행이 완료되어서 결과를 반환할지 알수 없다.
따라서 결과를 즉시 받는 것은 불가능하다. 
이런 이유로 es.submit()은 MyCallable의 결과를 반환하는 대신에 MyCallable의 결과를 나중에 받을 수 있는 Future라는 객체를 대신 제공한다. 

Future는 전달한 작업의 미래이다. 
이 객체를 통해 전달한 작업의 미래 결과를 받을 수 있다. 
</pre>

### 주요메서드
* __boolean cancel(boolean mayInterruptIfRunning)__
  * 아직 완료되지 않은 작업을 취소한다. 
  * 매개변수 : mayInterruptIfFunning
    * cancle (true) : Future를 취소 상태로 변경한다. 이때 작업이 실행중이라면 Thread.interrupt()를 호출해서 작업을 중단한다. 
    * clancel(false): Future를 취소 상태로 변경한다. 단 이미 실행 중인 작업을 중단하지 않는다. 
  * 반환값 : 작업이 성공적으로 취소된 경우 true, 이미 완료되었거나 취소할 수 없는 경우 false
  * DESC: 작업이 실행 중이 아니거나 아직 시작되지 않았으면 취소하고, 실행 중인 작업이 경우 mayInteruptIfRunning이 true이면 중단을 시도한다.
  * 참고: 취소상태의 Future에 Future.get()을 호출하면 CancellationException 런타임 예외가 발생한다.
  <pre>
   cancel(true) : Future를 취소 상태로 변경한다. 이때 작업이 실행중이면 Thread.interrupt()를 호출해서 작업을 중단한다. 
   cancel(false): Future를 취소 상태로 변경한다. 단 이미 실행 중인 작업을 중단하지 않는다.
  </pre>
* __boolean isCancelled()__
  * 작업이 취소 되었는지 여부를 확인한다. 
  * 반환값: 작업이 취소된경우 ture, 그렇지 않은 경우 false
  * 설명: 이 메서드는 작업이 cancel() 메서드에 의해 취소된 경우에 true를 반환한다. 
* __boolean isDone()__
  * 기능: 작업이 완료되었는지 여부를 확인한다. 
  * 반환값: 작업이 완료된 경우 true, 그렇지 않은 경우 false
  * 설명: 작업이 정상적으로 완료되거나, 취소되었거나, 예외가 발생하여 종료된 경우 true를 반환한다.
* __State state()__
  * 기능: Future의 상태를 반환한다. 자바 19부터 지원한다. 
    * RUNNING: 작업 실행 중 
    * SUCCESS: 성공 완료 
    * FAILED: 실패 완료
    * CANCELLED: 취소 완료
* __V get()__
  * 기능: 작업이 완료되 때까지 대기하고, 완료되면 결과를 반환한다. 
  * 반환값: 작업의 결과
  * 예외:
    *  InterruptedException : 대기 중에 현재 스레드가 인터럽트된 경우 발생
    *  ExecutionException: 작업 계산 중에 예외가 발생한 경우 발생
  * 설명: 작업이 완료될 때까지 get()을 호출한 현재 스레드를 대기(블로킹)한다. 작업이 완료되면 결과를 반환한다. 
* __V get(long timeout, timeUnit unit)
  * 기능 : get() 과 같은데, 시간 초과되면 예외를 발생시킨다. 
  * 매개변수 :
    * timeout : 대기할 최대 시간
    * unit : timeout 매개변수의 시간 단위 지정
  * 반환값 : 작업의 결과
  * 예외 : 
    * InterruptedException: 대기 중에 현재 스레드가 인터럽터된 경우 발생 
    * ExecutionException: 계산 중에 예외가 발생한 경우 발생
    * TimeoutException: 주어진 시간 내에 작업이 완료되지 않은 경우 발생
  * 설명: 지정된 시간 동안 결과를 기다린다. 시간이 초과되면 TimeoutException을 발생시킨다.


- - - 
### ExecutorService -- 작업 컬랙션 처리 
* invokeAll()
```java  
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException
// -- 모든 Callable 작업을 제출하고, 모든 작업이 완료될 때까지 기다린다.
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException 
// -- 지정된 시간 내에 모든 Callable 작업을 제출하고 완료될 때까지 기다린다.
```
 * invokeAny()
```java
<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws
InterruptedException, ExecutionException
// --하나의 `Callable` 작업이 완료될 때까지 기다리고, 가장 먼저 완료된 작업의 결과를 반환한다.
// 완료되지 않은 나머지 작업은 취소한다.
<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout,
TimeUnit unit) throws InterruptedException, ExecutionException,
TimeoutException
//지정된 시간 내에 하나의 `Callable` 작업이 완료될 때까지 기다리고, 가장 먼저 완료된 작업의 결과를 반환한다.
//완료되지 않은 나머지 작업은 취소
```