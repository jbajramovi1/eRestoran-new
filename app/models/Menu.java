package models;

import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * The type Menu.
 */
@Entity
@Table(name = "menu")
public class Menu extends BaseModel<Menu> {
    @Column
    @Constraints.Required(message = "Menu name field is required")
    private String name;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets restaurant.
     *
     * @param restaurant the restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void update(Menu data) {
        if (data.getName() != null) {
            setName(data.getName());
        }
        if (data.getRestaurant() != null) {
            setRestaurant(data.getRestaurant());
        }


    }
}
