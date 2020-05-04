package applicationManagerInterface;

import projectManagerObjects.View;

public class applicationManager {
	private String stage;
	private View view;
	
	public applicationManager(View view) {
		this.view = view;
		stage = "Application";
	}

	public void changeStage(String stage) {
		this.stage = stage;
	}

	public String getStage() {
		return stage;
	}

}
