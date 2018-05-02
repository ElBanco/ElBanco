package service.Operacion;

import service.UpdateResult;

public class OperationUpdateResult extends UpdateResult{
	
	private OperationError error;
	
	public enum OperationError{
		UNKOWN_ACCOUNT,
		LOWER_LIMIT,
		BALANCE,
		DAYLY_LIMIT;
		
	}

	public OperationError getError() {
		return error;
	}

	public void setError(OperationError error) {
		this.error = error;
	}


}
