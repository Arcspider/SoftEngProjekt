package applicationManagerInterface;
//Søren(s194630) har haft ansvaret for denne klasse.
import projectManagerObjects.View;

public class ApplicationManager {
	private String stage;
	public ApplicationManager(View view) {
		stage = "Application";
	}

	public void changeStage(String stage) {
		this.stage = stage;
	}

	public String getStage() {
		return stage;
	}

}
