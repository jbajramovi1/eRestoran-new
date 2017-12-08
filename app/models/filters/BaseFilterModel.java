package models.filters;

import org.hibernate.Criteria;
import play.libs.Json;
import services.BaseService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import static play.mvc.Results.internalServerError;

/**
 * The type Base filter model.
 *
 * @param <B> the type parameter
 */
public abstract class BaseFilterModel<B extends BaseFilterModel, S extends BaseService> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer count;
    private String sortKey;
    private Boolean sortAsc;
    protected S service;
    /**
     * Instantiates a new Base filter model.
     */
    public BaseFilterModel() {
        setPageSize(0);
        setPageNumber(0);
        setCount(0);
        setSortKey("");
        setSortAsc(false);
    }


    @Inject
    public void setService(S service) {
        this.service = service;
    }

    /**
     * Add conditions criteria.
     *
     * @param rootCriteria the root criteria
     * @return the criteria
     */
    protected Criteria addConditions(Criteria rootCriteria) {
        if (getPageSize() != null && getPageSize() != 0) {
            rootCriteria = this.addLimitAndOffset(rootCriteria);
        }
        return rootCriteria;
    }

    /**
     * Add limit and offset criteria.
     *
     * @param rootCriteria the root criteria
     * @return the criteria
     */
    protected Criteria addLimitAndOffset(Criteria rootCriteria) {
        try {
            if (getPageNumber() == null) {
                setPageNumber(0);
            }
            setCount ((int)(service.count()/getPageSize()));
            rootCriteria.setFirstResult(getPageNumber() * getPageSize());
            rootCriteria.setMaxResults(getPageSize());
            return rootCriteria;
        }
        catch (ServiceException e){

        }
        return null;
    }

    /**
     * Gets page number.
     *
     * @return the page number
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public B setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return (B)this;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public B setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return (B)this;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public B setCount(int count) {
        this.count = count;
        return (B)this;
    }

    public String getSortKey() {
        return sortKey;
    }

    public B setSortKey(String sortKey) {
        this.sortKey = sortKey;
        return (B)this;
    }

    public Boolean getSortAsc() {
        return sortAsc;
    }

    public B setSortAsc(Boolean sortAsc) {
        this.sortAsc = sortAsc;
        return (B)this;
    }
}
