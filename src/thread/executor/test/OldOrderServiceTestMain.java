package thread.executor.test;

public class OldOrderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "Order#1234";
        OldOrderService oldorderService = new OldOrderService();
        oldorderService.order(orderNo);

    }
}
