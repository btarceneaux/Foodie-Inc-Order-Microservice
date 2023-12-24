package inc.foodie.bean;

import jakarta.persistence.*;

@Entity
public class LineItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineItemId;
    private int restaurantId;
    private int quantity;
    private int dishNumber;

    public LineItem()
    {
    }

    public int getLineItemId()
    {
        return lineItemId;
    }

    public void setLineItemId(int lineItemId)
    {
        this.lineItemId = lineItemId;
    }

    public int getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId)
    {
        this.restaurantId = restaurantId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getDishNumber()
    {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber)
    {
        this.dishNumber = dishNumber;
    }
}
