package northwind.workshop2304.Northwind.Order.repository;

public interface Queries {

    public static final String SQL_SEARCH_ORDER_AND_ORDER_DETAILS = 
        "select orders.id as orderId, orders.order_date as orderDate, orders.customer_id as customerId, order_details.quantity as quantity, order_details.unit_price as unitPrice, order_details.discount, products.standard_cost as standardCost from orders join order_details on orders.id = order_details.order_id join products on products.id = order_details.product_id where orders.id = ?";

        /*
        select orders.id as orderId, orders.order_date as orderDate, orders.customer_id as customerId, 
        order_details.quantity as quantity,
        order_details.unit_price as unitPrice, 
        order_details.discount, products.standard_cost
        from orders
        join order_details
        on orders.id = order_details.order_id
        join products
        on products.id = order_details.product_id
        where orders.id = 44;
        */
}
