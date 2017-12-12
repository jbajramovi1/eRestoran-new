package models.filters;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import services.RestaurantService;

public class RestaurantFilterModel extends BaseFilterModel<RestaurantFilterModel>{
    private String name;
    private List<String> categories;
    private Integer rating;
    private Integer pricing;


    @Override
    public Criteria addConditions(Criteria rootCriteria) {
        if(StringUtils.isNotBlank(name)) {
            rootCriteria = rootCriteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
        }
        return super.addConditions(rootCriteria);
    }

    public RestaurantFilterModel setName(String name) {
        this.name = name;
        return this;
    }

    public RestaurantFilterModel setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }


    public RestaurantFilterModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public RestaurantFilterModel setPricing(Integer pricing) {
        this.pricing = pricing;
        return this;
    }
}
