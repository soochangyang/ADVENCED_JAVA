package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;

public class SimpleListMainV0 {
    static void main(String[] args) {
        List<String> list = new ArrayList<>();

        //스레드1, 스레드2가 동시에 실행 가정
        list.add("A"); //스레드 1 실행 가정
        list.add("B"); //스레드 2 실행 가정
        System.out.println(list);
        log(list);
    }
}
