package com.actions.prototype.model.resource.action;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * ActionListResponse class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Data
@Builder
public class ActionListResponse implements Serializable {
	
	/** Constant <code>serialVersionUID=-5371816749538118894L</code> */
	private static final long serialVersionUID = -5371816749538118894L;

	private List<Action> actions;
	private Integer total;
}
