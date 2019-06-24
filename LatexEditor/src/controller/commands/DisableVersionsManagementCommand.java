package controller.commands;

import controller.LatexEditorController;

public class DisableVersionsManagementCommand implements Command{

	private LatexEditorController theController;
	
	public DisableVersionsManagementCommand(LatexEditorController newController)
	{
		
		theController=newController;
	
	}
	public void execute(String aString, String bString) 
	{
		
		theController.getVersionsManager().disable();
		
	}
}
