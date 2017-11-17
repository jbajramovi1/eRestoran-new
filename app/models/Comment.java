package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Comment.
 */
@Entity
@Table(name = "comment")
public class Comment extends BaseModel<Comment> {
    @Column
    private String content;
    @Column
    @Constraints.Required(message = "Mark field is required")
    private Float mark;
    @Column(name = "insert_date")
    private Date insertTime;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public Float getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(Float mark) {
        this.mark = mark;
    }

    /**
     * Gets insert time.
     *
     * @return the insert time
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * Sets insert time.
     *
     * @param insertTime the insert time
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
    public void update(Comment data) {
        if (data.getContent() != null) {
            setContent(data.getContent());
        }
        if (data.getMark() != null) {
            setMark(data.getMark());
        }
        if (data.getInsertTime() != null) {
            setInsertTime(data.getInsertTime());
        }
        if (data.getAccount() != null) {
            setAccount(data.getAccount());
        }
        if (data.getRestaurant() != null) {
            setRestaurant(data.getRestaurant());
        }

    }
}
