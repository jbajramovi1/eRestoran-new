package models;

import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Location.
 */
@Entity
@Table(name = "location")
public class Location extends BaseModel<Location> {
    @Column
    @Constraints.Required(message = "Latitude field is required")
    private Float latitude;
    @Column
    @Constraints.Required(message = "Longitude field is required")
    private Float longitude;
    @Column
    @Constraints.Required(message = "Location name field is required")
    private String name;

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

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

    @Override
    public void update(Location data) {
        if (data.getLatitude() != null) {
            setLatitude(data.getLatitude());
        }
        if (data.getLongitude() != null) {
            setLongitude(data.getLongitude());
        }
        if (data.getName() != null) {
            setName(data.getName());
        }


    }
}
