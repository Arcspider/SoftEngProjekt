package dtu.library.app;

import java.text.*;
import java.util.*;
public class Model {
	private View view;
	private ArrayList<Project> projects;
	private Calendar calendar;
	private DateFormat dateFormat;
	private Random random;
	public static void main(String[] args) {

	}
	public Model(View view) {
		this.view = view;
		projects = new ArrayList<Project>();
		this.calendar = new GregorianCalendar();
		dateFormat = new SimpleDateFormat("MM-yy");
		random = new Random();
	}
	public void createProject() {
		String date = dateFormat.format(Calendar.getInstance().getTime());
		String id = date+"-"+ random.nextInt(100);
		Project newProject = new Project(id);
		projects.add(newProject);
		view.showMessage("Project has been created with ID: " + id);


	}
	public Project getProject(String id) {
		for(int i = 0; i< projects.size();i++) {
			Project currentProject = projects.get(i);
			String currentId = currentProject.getId();
			
			if(currentId.equals(id)) {
				return currentProject;
			}

		}
		return null;
	}

	public boolean canBeCreated(String id){
		return !projects.contains(id);
	}
}
