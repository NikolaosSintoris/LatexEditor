package controller.commands;

import controller.LatexEditorController;

public class StableCommand implements Command {


	private LatexEditorController theController;
	
	
	public StableCommand(LatexEditorController newController) 
	{
		theController = newController;
		
	}
	
	
	public void execute(String aString, String bString) {

		theController.getVersionsManager().enable();
		theController.getVersionsManager().getVersionsStrategy().setEntireHistory(theController.getTemporaryHistory());	
		
	}

}
