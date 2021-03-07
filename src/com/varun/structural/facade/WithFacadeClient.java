package com.varun.structural.facade;

public class WithFacadeClient {
    public static void main(String[] args) {
        /**
         * The client has to now call only 1 method for performing the task --> easy to interact
         * If subsystems change, PlaceOrderFacadeImpl will be impacted, client not impacted --> loose coupling
         */
        PlaceOrderFacade orderFacade = new PlaceOrderFacadeImpl();
        orderFacade.placeOrder(1);
    }
}
interface PlaceOrderFacade {
    void placeOrder(int productId);
}
class PlaceOrderFacadeImpl implements PlaceOrderFacade {
    @Override
    public void placeOrder(int productId) {
        //purchase product
        int orderId = 1;
        InventoryService invSvc = new InventoryService();
        boolean isAvail = invSvc.isAvailable(orderId);
        if (isAvail) {
            PaymentService ps = new PaymentService();
            ps.performPayment();
            ShippingService ss = new ShippingService();
            ss.ship(orderId);
        }
    }
}