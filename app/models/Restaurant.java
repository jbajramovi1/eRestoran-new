package models;

import javax.persistence.*;

/**
 * The type Restaurant.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseModel<Restaurant> {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double mark;
    @Column
    private Long votes;
    @Column(name = "price_range")
    private Long priceRange;
    @Column(name = "image_file_name")
    private String imageFileName;
    @Column(name = "cover_file_name")
    private String coverFileName;
    @Column
    private String category;

    //+LOCATION


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
     * Gets mark.
     *
     * @return the mark
     */
    public Double getMark() {
        return mark;
    }

    /**
     * Sets mark.
     *
     * @param mark the mark
     */
    public void setMark(Double mark) {
        this.mark = mark;
    }

    /**
     * Gets votes.
     *
     * @return the votes
     */
    public Long getVotes() {
        return votes;
    }

    /**
     * Sets votes.
     *
     * @param votes the votes
     */
    public void setVotes(Long votes) {
        this.votes = votes;
    }

    /**
     * Gets price range.
     *
     * @return the price range
     */
    public Long getPriceRange() {
        return priceRange;
    }

    /**
     * Sets price range.
     *
     * @param priceRange the price range
     */
    public void setPriceRange(Long priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Gets image file name.
     *
     * @return the image file name
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * Sets image file name.
     *
     * @param imageFileName the image file name
     */
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    /**
     * Gets cover file name.
     *
     * @return the cover file name
     */
    public String getCoverFileName() {
        return coverFileName;
    }

    /**
     * Sets cover file name.
     *
     * @param coverFileName the cover file name
     */
    public void setCoverFileName(String coverFileName) {
        this.coverFileName = coverFileName;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public void update(Restaurant data) {
        if (data.getName() != null) {
            setName(data.getName());
        }
        if (data.getDescription() != null) {
            setDescription(data.getDescription());
        }
        if (data.getMark() != null) {
            setMark(data.getMark());
        }
        if (data.getVotes() != null) {
            setVotes(data.getVotes());
        }
        if (data.getPriceRange() != null) {
            setPriceRange(data.getPriceRange());
        }
        if (data.getImageFileName() != null) {
            setImageFileName(data.getImageFileName());
        }
        if (data.getCoverFileName() != null) {
            setCoverFileName(data.getCoverFileName());
        }
        if (data.getCategory() != null) {
            setCategory(data.getCategory());
        }

    }

}
