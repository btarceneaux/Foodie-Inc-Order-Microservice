package inc.foodie.bean;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private Date orderDateAndTime;
    private double orderTotal;
    @OneToMany(cascade = CascadeType.ALL)
    private List<LineItem> orderItems;

    public Orders()
    {
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public Date getOrderDateAndTime()
    {
        return orderDateAndTime;
    }

    public void setOrderDateAndTime(Date orderDateAndTime)
    {
        this.orderDateAndTime = orderDateAndTime;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }

    public List<LineItem> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems(List<LineItem> orderItems)
    {
        this.orderItems = orderItems;
    }
}