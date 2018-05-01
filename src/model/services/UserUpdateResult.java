package model.services;

public class UserUpdateResult extends UpdateResult {
	
	private Error err;
	
	public enum Error{
		DUPLICATED_USER,
		DUPLICATED_EMAIL;
	}

	public UserUpdateResult(boolean successfulUpdate) {
		super(successfulUpdate);
	}

	public UserUpdateResult(boolean successfulUpdate, Error err) {
		super(successfulUpdate);
		this.err = err;
	}

	public Error getErr() {
		return err;
	}

	public void setErr(Error err) {
		this.err = err;
	}
	

}
