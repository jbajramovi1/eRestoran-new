package models.filters;

import org.hibernate.Criteria;

import javax.inject.Singleton;

/**
 * The type Base filter model.
 *
 * @param <B> the type parameter
 */
@Singleton
public abstract class BaseFilterModel<B extends BaseFilterModel> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer count;
    private String sortKey;
    private Boolean sortAsc;

    /**
     * Instantiates a new Base filter model.
     */


    /**
     * Add conditions criteria.
     *
     * @param rootCriteria the root criteria
     * @return the criteria
     */
    protected Criteria addConditions(Criteria rootCriteria) {
        if (pageSize != null && pageSize != 0) {
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
            if (pageNumber == null) {
                setPageNumber(0);
            }
            rootCriteria.setFirstResult(pageNumber * pageSize);
            rootCriteria.setMaxResults(pageSize);
            return rootCriteria;
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

    public Integer getPageNumber() {
        return pageNumber;
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

    public Integer getPageSize() {
        return pageSize;
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


    public B setSortKey(String sortKey) {
        this.sortKey = sortKey;
        return (B)this;
    }

    public B setSortAsc(boolean sortAsc) {
        this.sortAsc = sortAsc;
        return (B)this;
    }


}
