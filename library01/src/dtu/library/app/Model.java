package dtu.library.app;

import java.text.*;
import java.time.*;
import java.util.*;

public class Model {
	private View view;

	private ArrayList<Worker> workers;

	private Random random;

	private Worker worker;

	public Model(View view) {
		this.view = view;

		workers = new ArrayList<Worker>();

		random = new Random();

		// TODO �ndre variabel navnene til noget fornuftigt, s� det ikke er i
		// konflikt med om et project har en aktivitet osv.

	}

}
