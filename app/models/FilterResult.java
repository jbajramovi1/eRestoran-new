package models;

import java.util.List;

public class FilterResult <B extends BaseModel<B>> {
    private Integer count;
    private Integer pageNumber;
    private Integer pageSize;
    private List<B> data;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<B> getData() {
        return data;
    }

    public void setData(List<B> data) {
        this.data = data;
    }
}
