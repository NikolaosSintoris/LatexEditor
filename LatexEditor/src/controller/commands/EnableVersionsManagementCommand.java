package controller.commands;

import controller.LatexEditorController;


public class EnableVersionsManagementCommand implements Command 
{

	private LatexEditorController theController;

	public EnableVersionsManagementCommand(LatexEditorController newController)
	{
		theController=newController;
	}
	public void execute(String aString, String bString) 
	{
		theController.getVersionsManager().enable();
		theController.getVersionsManager().getVersionsStrategy().setEntireHistory(theController.getTemporaryHistory());	
	}
}
