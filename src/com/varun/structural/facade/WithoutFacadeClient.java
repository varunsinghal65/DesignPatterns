package com.varun.structural.facade;
import java.util.Arrays;
import java.util.List;

/*
- A system might have many subsystems so that there is separation of concerns.
- However, to perform a task in a system, the client will have to interact with all of these subsystems
    --> makes difficult for the client to consume the subsystem
- There is also a tight coupling between the subsystems and client, if the underlying subsystems change
    --> direct impact on client.

    Source URL : https://springframework.guru/gang-of-four-design-patterns/facade-pattern/
 */
public class WithoutFacadeClient {
    public static void main(String[] args) {
        /**
         * Client needs to know that inventory, payment and shipping have to be called --> complex
         * If some of these services are dropped modified --> client impacted
         */
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

class ShippingService {
    void ship(int productId) {
        System.out.print("Product ID" + productId + " is shipped");
    }
}

class PaymentService {
    void performPayment(){
        System.out.println("Payment done");
    }
}

class InventoryService {
    boolean isAvailable(int productId) {
        //connect to inventory db
        List<Integer> productIds = Arrays.asList(1,2,3,4,5,6,7,8,9);
        return productIds.contains(productId);
    }
}