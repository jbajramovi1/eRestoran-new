package models;

import javax.persistence.*;

import play.data.validation.Constraints.Required;

/**
 * The type Request log.
 */
@Entity
@Table(name = "request_log")
public class LogRequest extends BaseModel<Long, LogRequest> {

	@Id
	@GeneratedValue
	private Long id;

	@Required
	private String name;

	@Required
	@Enumerated(value = EnumType.STRING)
	private LogRequestType type;


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
	 * Gets type.
	 *
	 * @return the type
	 */
	public LogRequestType getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(LogRequestType type) {
		this.type = type;
	}

	@Override
	void update() {
		// not implemented
	}
}
