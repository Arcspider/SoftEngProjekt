package projectManagerObjects;

public class ModelApplication {
	private String stage;
	private View view;
	
	public ModelApplication(View view) {
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
