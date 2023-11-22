package inc.foodie.controller;

import inc.foodie.dto.ResponseDto;
import inc.foodie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import inc.foodie.bean.Order;
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
    public List<Order> getAllOrders()
    {
        return service.getAllOrders();
    }

    /**
     *
     * @param orderId  : The order id of the order the user wants to get.
     * @return : Returns ResponseDto
     */
    @GetMapping("/order/{orderId}")
    public ResponseDto getBookingByBookingId(@PathVariable int orderId)
    {
        ResponseDto response = new ResponseDto();
        Optional<Order> optionalOrder = service.getOrderById(orderId);

        if(optionalOrder.isPresent())
        {
            Order myOrder = optionalOrder.get();
            response.setMessage("The order was found");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(myOrder);
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
     * @param myOrder : The complete order to be created.
     * @return : Returns ResponseDto
     */
    @PostMapping("/order")
    public ResponseDto createCustomer(@RequestBody Order myOrder)
    {
        ResponseDto response = new ResponseDto();

        if((myOrder.getOrderList().isEmpty()) ||
                (myOrder.getDateAndTimeOfOrder() == null))
        {
            response.setMessage("The order was not successfully saved because one of the fields were blank.");
            response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            response.setTimestamp(new Date());
            response.setData(null);

            return response;
        }

        Order savedOrder = service.createOrder(myOrder);

        if(savedOrder.getOrderIdNumber() > 0)
        {
            response.setMessage("The order was successfully placed.");
            response.setStatus(HttpStatus.OK.value());
            response.setTimestamp(new Date());
            response.setData(savedOrder);
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
     * @param myOrder : The complete order can contain multiple items.
     * @return : Returns ResponseDto
     */
    @PutMapping("/order")
    public ResponseDto updateCustomer(@RequestBody Order myOrder)
    {
        ResponseDto response = new ResponseDto();

        Order updatedOrder = service.updateOrder(myOrder);

        response.setMessage("The order was updated successfully.");
        response.setStatus(HttpStatus.OK.value());
        response.setTimestamp(new Date());
        response.setData(updatedOrder);

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