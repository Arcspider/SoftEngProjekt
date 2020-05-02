package dtu.library.app;

public class ModelAplication {
	private String stage;
	private View view;
	
	public ModelAplication(View view) {
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
