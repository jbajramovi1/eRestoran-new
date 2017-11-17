package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;

/**
 * The type Account.
 */
@Entity
@Table(name = "account")
public class Account extends BaseModel<Account> {
    @Column
    @Constraints.Required(message = "Email field is required")
    @Constraints.Email(message = "Email format is incorrect")
    private String email;
    @Column
    @JsonIgnore
    @Constraints.Required(message = "Password field is required")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role = Role.USER;
    @Column
    private String phone;
    @Column
    private String country;
    @Column
    private String city;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Updates Account instance
     *
     * @param data
     */
    @Override
    public void update(Account data) {
        if (data.getFirstName() != null) {
            setFirstName(data.getFirstName());
        }
        if (data.getLastName() != null) {
            setLastName(data.getLastName());
        }
        if (data.getPassword() != null) {
            setPassword(data.getPassword());
        }
        if (data.getCity() != null) {
            setCity(data.getCity());
        }
        if (data.getEmail() != null) {
            setEmail(data.getEmail());
        }
        if (data.getRole() != null) {
            setRole(data.getRole());
        }

    }

}
