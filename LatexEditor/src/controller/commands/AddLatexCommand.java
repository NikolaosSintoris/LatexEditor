package controller.commands;

import controller.LatexEditorController;
import model.Document;

public class AddLatexCommand implements Command{
	
	private LatexEditorController theController;
	
	public AddLatexCommand(LatexEditorController newController) 
	{
		theController = newController;
	}
	
	public void execute(String aString, String bString)
	{
		if(theController.getVersionsManager().isEnabled())
		{
			theController.getView().getTextArea().insert(bString, theController.getView().getTextArea().getCaretPosition());
			
			Document newDocument= theController.getCurrentDocument().clone();
			
			String oldVersionID = newDocument.getVersionID();
			String versionArray[] = oldVersionID.split("_");
			int number = Integer.parseInt(versionArray[1]);
			number++;
			String newVersionID = "Version_" + Integer.toString(number);
			
			newDocument.setVersion(newVersionID);

			newDocument.setContents(theController.getView().getTextArea().getText());
			
			
			theController.getVersionsManager().setCurrentVersion(newDocument);
			
			//theController.getVersionsManager().getVersionsStrategy().putVersion(doc);
		}
		else
		{
			theController.getView().getTextArea().insert(bString, theController.getView().getTextArea().getCaretPosition());
			
			Document newDocument= theController.getCurrentDocument().clone();


			newDocument.setContents(theController.getView().getTextArea().getText());
			
			
			theController.setCurrentDocument(newDocument);
		}

	}
}
