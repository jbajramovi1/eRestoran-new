package models.filters;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * The type Restaurant filter model.
 */
public class RestaurantFilterModel extends BaseFilterModel<RestaurantFilterModel> {
    private String name;
    private List<String> categories;
    private Integer rating;
    private Integer pricing;


    @Override
    public Criteria addConditions(Criteria rootCriteria) {
        if (StringUtils.isNotBlank(name)) {
            rootCriteria = rootCriteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        }
        return super.addConditions(rootCriteria);
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public RestaurantFilterModel setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     * @return the categories
     */
    public RestaurantFilterModel setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }


    /**
     * Sets rating.
     *
     * @param rating the rating
     * @return the rating
     */
    public RestaurantFilterModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Sets pricing.
     *
     * @param pricing the pricing
     * @return the pricing
     */
    public RestaurantFilterModel setPricing(Integer pricing) {
        this.pricing = pricing;
        return this;
    }
}
