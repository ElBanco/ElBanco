package service.Usuario;

import service.UpdateResult;

public class UserUpdateResult extends UpdateResult {
	
	private UserError error;

	public enum UserError{
		DUPLICATED_USER,
		DUPLICATED_EMAIL;
		
	}

	public UserError getError() {
		return error;
	}

	public void setError(UserError error) {
		this.error = error;
	}

}
