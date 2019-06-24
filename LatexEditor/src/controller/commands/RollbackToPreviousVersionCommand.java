package controller.commands;


import controller.LatexEditorController;

public class RollbackToPreviousVersionCommand implements Command{

	

	private LatexEditorController theController;
	
	public RollbackToPreviousVersionCommand(LatexEditorController newController) 
	{
		theController = newController;
	}


	public void execute(String aString, String bString) 
	{

		theController.getVersionsManager().rollbackToPreviousVersion();
		theController.getView().setTextArea(theController.getCurrentDocument().getContents());

	}
	
}
