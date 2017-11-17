package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The type Base model.
 *
 * @param <M> the type parameter
 */
@MappedSuperclass
public abstract class BaseModel<M> {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Update.
     *
     * @param model the model
     */
    public abstract void update(M model);

}
