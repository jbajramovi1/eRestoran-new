package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Reservation.
 */
@Entity
@Table(name = "reservation")
public class Reservation extends BaseModel<Reservation> {
    @Column
    @Constraints.Required(message = "Tables field is required")
    private Integer tables;
    @Column(name = "reservation_date")
    private Date reservationDate;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    /**‸
     * Gets tables.
     *
     * @return the tables
     */
    public Integer getTables() {
        return tables;
    }

    /**
     * Sets tables.
     *
     * @param tables the tables
     */
    public void setTables(Integer tables) {
        this.tables = tables;
    }

    /**
     * Gets reservation date.
     *
     * @return the reservation date
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * Sets reservation date.
     *
     * @param date the date
     */
    public void setReservationDate(Date date) {
        this.reservationDate = date;
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
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
    public void update(Reservation data) {
        if (data.getTables() != null) {
            setTables(data.getTables());
        }
        if (data.getReservationDate() != null) {
            setReservationDate(data.getReservationDate());
        }
        if (data.getAccount() != null) {
            setAccount(data.getAccount());
        }
        if (data.getAccount() != null) {
            setAccount(data.getAccount());
        }

    }

}
