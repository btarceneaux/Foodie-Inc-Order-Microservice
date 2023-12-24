package inc.foodie.service;

import inc.foodie.bean.Orders;
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

    public List<Orders> getAllOrders()
    {
        return repository.findAll();
    }

    public Optional<Orders> getOrderById(int orderId)
    {
        return repository.findById(orderId);
    }

    public Orders createOrder(Orders myOrders)
    {
        return repository.save(myOrders);
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

    public Orders updateOrder(Orders updatedOrders)
    {
        boolean exists = repository.existsById(updatedOrders.getOrderId());

        if(exists)
        {
            return repository.save(updatedOrders);
        }
        else
        {
            return null;
        }
    }
}