package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "Order#1234";
        NewOrderService newOrderService = new NewOrderService();
        //NewOrderAllService newOrderService = new NewOrderAllService();
        newOrderService.order(orderNo);


    }
}
