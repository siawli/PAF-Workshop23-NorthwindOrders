package northwind.workshop2304.Northwind.Order.service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import northwind.workshop2304.Northwind.Order.model.Order;
import northwind.workshop2304.Northwind.Order.model.OrderDetails;
import northwind.workshop2304.Northwind.Order.repository.NorthWindRepository;

@Service
public class NorthWindService {

    @Autowired
    private NorthWindRepository northwindRepo;

    public Optional<JsonObject> getOrderDetails(Integer orderId) {

        SqlRowSet result = northwindRepo.getOrderDetails(orderId);
        JsonObjectBuilder messageToReturn = Json.createObjectBuilder();
        
        if (!result.next()) {
            return Optional.empty();
        }

        Order order = new Order();
        List<OrderDetails> listOrderDetails = new LinkedList<>();

        order.setCustomerId(result.getInt("customerId"));
        order.setOrderDate(result.getString("orderDate"));
        order.setOrderId(result.getInt("orderId"));

        result.beforeFirst();
        while (result.next()) {
            listOrderDetails.add(setOrderDetails(result));
        }

        Double totalPrice = 0.0;
        Double totalDiscount = 0.0;
        Double totalCostPrice = 0.0;
        
        for (OrderDetails orderDetail : listOrderDetails) {
            totalPrice += orderDetail.getQuantity() * orderDetail.getUnitPrice();
            totalDiscount += orderDetail.getQuantity() * orderDetail.getDiscount();
            totalCostPrice += orderDetail.getQuantity() * orderDetail.getStandardCost();
        }

        JsonObject message = messageToReturn.add("Order id", orderId)
                                .add("order date", "%s".formatted(order.getOrderDate().substring(0, 10)))
                                .add("customer id", order.getCustomerId())
                                .add("total price of order", "%.2f".formatted(totalPrice))
                                .add("total discount given", "%.2f".formatted(totalDiscount))
                                .add("total cost price", "%.2f".formatted(totalCostPrice))
                                .build();
        return Optional.of(message);
    }

    public OrderDetails setOrderDetails(SqlRowSet result) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setDiscount(result.getDouble("discount"));
        orderDetails.setQuantity(result.getDouble("quantity"));
        orderDetails.setUnitPrice(result.getDouble("unitPrice"));
        orderDetails.setStandardCost(result.getDouble("standardCost"));

        return orderDetails;
    }


    
}
