package models;

import play.data.validation.Constraints;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable extends BaseModel<RestaurantTable> {
    @Column
    @Constraints.Required(message = "Sitting places field is required")
    private Integer sittingPlaces;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    /**
     * Gets sitting places.
     *
     * @return the sitting places
     */
    public Integer getSittingPlaces() {
        return sittingPlaces;
    }
    /**
     * Sets sitting places.
     *
     * @param sittingPlaces the sitting places
     */
    public void setSittingPlaces(Integer sittingPlaces) {
        this.sittingPlaces = sittingPlaces;
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
    public void update(RestaurantTable data) {
        if (data.getSittingPlaces() != null) {
            setSittingPlaces(data.getSittingPlaces());
        }
        if (data.getRestaurant()!=null) {
            setRestaurant(data.getRestaurant());
        }


    }
}
