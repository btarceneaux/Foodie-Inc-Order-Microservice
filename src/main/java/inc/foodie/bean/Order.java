package inc.foodie.bean;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderIdNumber;
    private Date dateAndTimeOfOrder;
    @ElementCollection
    private List<Integer> orderList = new ArrayList<>();
    private double orderTotal;

    public Order()
    {
    }

    public int getOrderIdNumber()
    {
        return orderIdNumber;
    }

    public void setOrderIdNumber(int orderNumber)
    {
        this.orderIdNumber = orderNumber;
    }
    public Date getDateAndTimeOfOrder()
    {
        return dateAndTimeOfOrder;
    }

    public void setDateAndTimeOfOrder(Date dateAndTimeOfOrder)
    {
        this.dateAndTimeOfOrder = dateAndTimeOfOrder;
    }

    public List<Integer> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(ArrayList<Integer> orderList)
    {
        this.orderList = orderList;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal)
    {
        this.orderTotal = orderTotal;
    }
}