package models.filters;

import org.hibernate.Criteria;

/**
 * The type Base filter model.
 *
 * @param <B> the type parameter
 */
public abstract class BaseFilterModel<B extends BaseFilterModel> {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer count;

    /**
     * Instantiates a new Base filter model.
     */
    public BaseFilterModel() {
        setPageSize(0);
        setPageNumber(0);
        setCount(0);
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
        if (getPageNumber() == null) {
            setPageNumber(0);
        }
        rootCriteria.setFirstResult(getPageNumber() * getPageSize());
        rootCriteria.setMaxResults(getPageSize());
        return rootCriteria;
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
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
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
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
    public void setCount(int count) {
        this.count = count;
    }
}
