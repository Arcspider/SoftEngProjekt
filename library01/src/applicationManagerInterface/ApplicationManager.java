package applicationManagerInterface;

import projectManagerObjects.View;

public class ApplicationManager {
	private String stage;
	private View view;
	
	public ApplicationManager(View view) {
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
