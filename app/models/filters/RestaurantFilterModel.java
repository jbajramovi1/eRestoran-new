package models.filters;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import services.RestaurantService;

public class RestaurantFilterModel extends BaseFilterModel<RestaurantFilterModel, RestaurantService>{
    private String name;
    private List<String> categories;
    private Integer rating;
    private Integer pricing;

    public RestaurantFilterModel() {
        setName("");
        setCategories(new ArrayList<>());
        setRating(null);
        setPricing(null);
    }

    @Override
    public Criteria addConditions(Criteria rootCriteria) {
        if(!getName().isEmpty()) {
            rootCriteria = rootCriteria.add(Restrictions.ilike("name", getName(), MatchMode.ANYWHERE));
        }

        return super.addConditions(rootCriteria);
    }

    public String getName() {
        return name;
    }

    public RestaurantFilterModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public RestaurantFilterModel setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public RestaurantFilterModel setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public Integer getPricing() {
        return pricing;
    }

    public RestaurantFilterModel setPricing(Integer pricing) {
        this.pricing = pricing;
        return this;
    }
}
