package inc.foodie.controller;

import inc.foodie.dto.ResponseDto;
import inc.foodie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import inc.foodie.bean.Orders;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController
{
    @Autowired
    OrderService service;

    /**
     *
     * @return : Returns all orders
     */
    @GetMapping("/order")
    public List<Orders> getAllOrders()
    {
        return service.getAllOrders();
    }

    /**
     *
     * @param orderId  : The order id of the order the user wants to get.
     * @return : Returns ResponseDto
     */
    @GetMapping("/order/{orderId}")
    public ResponseDto getOrderByOrderId(@PathVariable int orderId)
    {
        ResponseDto response = new ResponseDto();
        Optional<Orders> optionalOrder = service.getOrderById(orderId);

        if(optionalOrder.isPresent())
        {
            Orders myOrders = optionalOrder.get();
            response.setMessage("The order was found");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(myOrders);
        }
        else
        {
            response.setMessage("The order does not exist.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    /**
     *
     * @param myOrders : The complete order to be created.
     * @return : Returns ResponseDto
     */
    @PostMapping("/order")
    public ResponseDto createOrder(@RequestBody Orders myOrders)
    {
        ResponseDto response = new ResponseDto();

        if(myOrders.getOrderItems().isEmpty())
        {
            response.setMessage("The order was not successfully saved because one of the fields were blank.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setTimestamp(new Date());
            response.setData(null);

            return response;
        }

        //Let's transfer all the data except for the id into the field.
        Orders tempOrder = new Orders();
        tempOrder.setOrderDateAndTime(new Date());
        tempOrder.setOrderTotal(myOrders.getOrderTotal());

        tempOrder.setOrderItems(myOrders.getOrderItems());

        Orders savedOrders = service.createOrder(tempOrder);

        if(savedOrders.getOrderId() > 0)
        {
            response.setMessage("The order was successfully placed.");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(savedOrders);
        }
        else
        {
            response.setMessage("The order was not successfully placed.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setTimestamp(new Date());
            response.setData(null);
        }

        return response;
    }

    /**
     *
     * @param myOrders : The complete order can contain multiple items.
     * @return : Returns ResponseDto
     */
    @PutMapping("/order")
    public ResponseDto updateOrder(@RequestBody Orders myOrders)
    {
        ResponseDto response = new ResponseDto();

        Orders updatedOrders = service.updateOrder(myOrders);

        response.setMessage("The order was updated successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        response.setData(updatedOrders);

        return response;
    }

    /**
     *
     * @param orderId : This is the id of the order that is passed.
     * @return Returns ResponseDto
     */
    @DeleteMapping("/order/{orderId}")
    public ResponseDto deleteOrder(@PathVariable int orderId)
    {
        ResponseDto response = new ResponseDto();

        int result = service.deleteOrder(orderId);
        if(result == 1)
        {
            response.setMessage("The order was successfully deleted.");
            response.setStatus(HttpStatus.OK.value());
        }
        else
        {
            response.setMessage("The order was not successfully deleted.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        }
        response.setTimestamp(new Date());
        response.setData(null);

        return response;
    }
}