package models;


import javax.persistence.MappedSuperclass;

/**
 * The type Base model.
 *
 * @param <T> the type parameter
 * @param <M> the type parameter
 */

@MappedSuperclass
public abstract class BaseModel<T, M> {

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	abstract T getId();

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	abstract void setId(T id);

	/**
	 * Update.
	 */
	abstract void update();
}
