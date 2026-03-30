## LOCK

## WAIT & NOTIFY 

## CONDITION
condation은 ReentrantLock을 사용하는 스레드가 대기하는 스레드내 대기 공간임
lock.newCondation() 메서드를 호출하면 스레드 대기 공간이 만들어 진다. 
* condation.await()
  * Object.wait() 와 유사한기능이다. 지정한 condition에 현재 스레드를 대기(WAITING) 상태로 보관한다.이때 ReentrantLock에서 획득한 락을 반납하고 대기 상태로 condition에 보관된다.    
* condation.signal()
  * Object.nofify() 와 유사한 기능이다ㅏ. 지정한 condition에서 대기중인 스레드를 하나 깨운다. 깨어난 스레드는 condition에서 빠져나온다. 
