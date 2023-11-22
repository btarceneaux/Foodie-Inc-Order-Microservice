package inc.foodie.service;

import inc.foodie.bean.Order;
import inc.foodie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService
{
    @Autowired
    OrderRepository repository;

    public List<Order> getAllOrders()
    {
        return repository.findAll();
    }

    public Optional<Order> getOrderById(int orderId)
    {
        return repository.findById(orderId);
    }

    public Order createOrder(Order myOrder)
    {
        return repository.save(myOrder);
    }

    public int deleteOrder(int orderId)
    {
        //First determine whether record is in the database.
        int result = 0;
        boolean exists = repository.existsById(orderId);

        if(exists)
        {
            repository.deleteById(orderId);
            result = 1;
        }

        return result;
    }

    public Order updateOrder(Order updatedOrder)
    {
        boolean exists = repository.existsById(updatedOrder.getOrderIdNumber());

        if(exists)
        {
            return repository.save(updatedOrder);
        }
        else
        {
            return null;
        }
    }
}