package models;

import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * The type Menu item.
 */
@Entity
@Table(name = "menu_item")
public class MenuItem extends BaseModel<MenuItem> {
    @Column
    @Constraints.Required(message = "Menu item name field is required")
    private String name;
    @Column
    private Float price;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

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
     * Gets price.
     *
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Sets menu.
     *
     * @param menu the menu
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void update(MenuItem data) {
        if (data.getName() != null) {
            setName(data.getName());
        }
        if (data.getPrice() != null) {
            setPrice(data.getPrice());
        }
        if (data.getDescription() != null) {
            setDescription(data.getDescription());
        }
        if (data.getMenu() != null) {
            setMenu(data.getMenu());
        }


    }
}
