package controller.commands;

import controller.LatexEditorController;

public class ChangeVersionsStrategyCommand implements Command 
{

private LatexEditorController theController;
	
	public ChangeVersionsStrategyCommand(LatexEditorController newController) 
	{
		theController = newController;
	}
	
	public void execute(String aString, String bString) 
	{
		theController.setTemporaryHistory(theController.getVersionsManager().getVersionsStrategy().getEntireHistory());
	}

}
