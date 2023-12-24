package inc.foodie.bean;

import jakarta.persistence.*;

@Entity
@Table(name = "dish_item")
public class Dish
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int dishId;
    private String dishName;
    private String dishCategory;
    private double cost;
    private String imageURL;

    @OneToOne(mappedBy = "myDish")
    private LineItem myLineItem;

    public Dish()
    {
    }

    public int getDishId()
    {
        return dishId;
    }

    public void setDishId(int dishId)
    {
        this.dishId = dishId;
    }

    public String getDishName()
    {
        return dishName;
    }

    public void setDishName(String dishName)
    {
        this.dishName = dishName;
    }

    public String getDishCategory()
    {
        return dishCategory;
    }

    public void setDishCategory(String dishCategory)
    {
        this.dishCategory = dishCategory;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
}