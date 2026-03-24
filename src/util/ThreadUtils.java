package util;

import static util.MyLogger.log;

//abstract로 만들면 생성 불가
public abstract class ThreadUtils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("인터럽트 발생, "+ e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
