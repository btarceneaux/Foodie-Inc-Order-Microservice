package inc.foodie.bean;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

@Entity
public class LineItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int lineItemId;
    private int restaurantId;
    private int quantity;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "my_dish_id", referencedColumnName = "id")
    private Dish myDish;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    private int dish_item_dish_id;

    public Orders getOrder()
    {
        return order;
    }

    public void setOrder(Orders order)
    {
        this.order = order;
    }

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

    public Dish getMyDish()
    {
        return myDish;
    }

    public void setMyDish(Dish myDish)
    {
        this.myDish = myDish;
    }

    public int getDish_item_dish_id()
    {
        return dish_item_dish_id;
    }

    public void setDish_item_dish_id(int dish_item_dish_id)
    {
        this.dish_item_dish_id = dish_item_dish_id;
    }
}
