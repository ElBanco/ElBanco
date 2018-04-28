package model.services;

public class UpdateResult {
	
	public boolean successfulUpdate;

	public UpdateResult(boolean successfulUpdate) {
		super();
		this.successfulUpdate = successfulUpdate;
	}

	public boolean isSuccessfulUpdate() {
		return successfulUpdate;
	}

	public void setSuccessfulUpdate(boolean successfulUpdate) {
		this.successfulUpdate = successfulUpdate;
	}
	
	
}
