package dtu.library.app.controllerInterface;

import dtu.library.app.Model;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.Project;
import dtu.library.app.View;

import java.util.Scanner;

public class ControllerWorker {
    private Model model;
    private View view;

    Scanner scanner;

    public ControllerWorker(View view, Model model) {
        this.view = view;
        this.model = model;
        scanner = new Scanner(System.in);
    }
    public void runCommand() throws OperationNotAllowedException {

    }

}
