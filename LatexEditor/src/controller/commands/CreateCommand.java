package controller.commands;

import controller.LatexEditorController;
import model.Document;
import model.DocumentManager;

public class CreateCommand implements Command
{
	private DocumentManager documentManager = new DocumentManager();
	private LatexEditorController theController;
	
	public CreateCommand(LatexEditorController newController)
	{
		theController = newController;
	}
	
	public void execute(String aString, String bString) 
	{
		Document newDocument = documentManager.createDocument(aString);
		theController.setCurrentDocument(newDocument);; 
		String contextInit = newDocument.getContents();
		theController.getView().setTextArea(contextInit);
	}
	

}
