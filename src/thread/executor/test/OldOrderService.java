package thread.executor.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class OldOrderService {

    public void order(String orderNo){
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        // 작업 요청
        boolean inventoryResult = inventoryWork.call();
        boolean shippingResult = shippingWork.call();
        boolean accountingResult = accountingWork.call();

        // 결과 확인
        if (inventoryResult && shippingResult && accountingResult){
            log("모든 주문 처리가 성공적으로 완료 되었습니다. ");

        }
    }

    static class InventoryWork {
        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call(){
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork {
        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call(){
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork {
        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call(){
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
