package projectManagerObjects;
//Søren(s194630) har haft ansvaret for denne klasse.
public class Application {
	public static void main(String[] args) throws OperationNotAllowedException {
		View view = new View();
		view.showApplicationIntroduction();
		view.startup();
	}

}
