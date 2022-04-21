package northwind.workshop2304.Northwind.Order.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import northwind.workshop2304.Northwind.Order.service.NorthWindService;

@Controller
@RequestMapping("/order")
public class NorthWindController {

    @Autowired
    private NorthWindService northWindSvc;

    @GetMapping("/total/{order_id}")
    public ResponseEntity<String> orderTotal(@PathVariable Integer order_id) {
        Optional<JsonObject> message = northWindSvc.getOrderDetails(order_id);

        if (message.isEmpty()) {
            JsonObject errorMsg = Json.createObjectBuilder()
                .add("error", "this order_id does not exist").build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(errorMsg.toString());
        } else {
            JsonObject successMsg = message.get();
            return ResponseEntity.ok().body(successMsg.toString());
        }

    }
    
}
