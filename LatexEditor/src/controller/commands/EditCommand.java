package controller.commands;

import controller.LatexEditorController;
import model.Document;

public class EditCommand implements Command
{
	private LatexEditorController theController;
	
	public EditCommand(LatexEditorController newController) 
	{
		theController = newController;
	}
	
	public void execute(String aString, String bString) 
	{
		if(theController.getVersionsManager().isEnabled())
		{
			Document newDocument = theController.getCurrentDocument().clone();

			String lines[]=theController.getView().getTextArea().getText().split("\\n");

			//for the version
			String oldVersionID = newDocument.getVersionID();
			String versionArray[] = oldVersionID.split("_");
			int number = Integer.parseInt(versionArray[1]);
			number++;
			String newVersionID = "Version_" + Integer.toString(number);
				
			newDocument.setVersion(newVersionID);
				
				
			//for the  author
			int pos=0;
					
			for(int i=0;i<lines.length;i++) 
			{
				if(lines[i].contains("author"))
				{
					pos=i;
					break;
				}
			}
			String authorLine[] =lines[pos].split("\\{");
			if(pos>0) 
			{
				String newAuthor=authorLine[1].replace("}", "");
				newDocument.setAuthor(newAuthor);
			}
				
			//for the  date
			int thesi=0;
			for(int i=0;i<lines.length;i++) 
			{
				if(lines[i].contains("date"))
				{
					thesi=i;
					break;
				}
			}
			String dateLine[] =lines[thesi].split("\\{");
			if(thesi>0)
			{
				String newDate=dateLine[1].replace("}", "");
				newDocument.setDate(newDate);
			}

			
			//for the  contents
			newDocument.setContents(theController.getView().getTextArea().getText());
		
			theController.getVersionsManager().setCurrentVersion(newDocument);
			
			//theController.getVersionsManager().getVersionsStrategy().putVersion(newDocument);
		}
		else
		{
			Document newDocument = theController.getCurrentDocument().clone();

			String lines[]=theController.getView().getTextArea().getText().split("\\n");

	
			//for the author
			int pos=0;
					
			for(int i=0;i<lines.length;i++) 
			{
				if(lines[i].contains("author"))
				{
					pos=i;
					break;
				}
			}
			String authorLine[] =lines[pos].split("\\{");
			if(pos>0) 
			{
				String newAuthor=authorLine[1].replace("}", "");
				newDocument.setAuthor(newAuthor);
			}
				
			//for the  date
			int thesi=0;
			for(int i=0;i<lines.length;i++) 
			{
				if(lines[i].contains("date"))
				{
					thesi=i;
					break;
				}
			}
			String dateLine[] =lines[thesi].split("\\{");
			if(thesi>0)
			{
				String newDate=dateLine[1].replace("}", "");
				newDocument.setDate(newDate);
			}

			
			//for the  contents
			newDocument.setContents(theController.getView().getTextArea().getText());
		

			theController.setCurrentDocument(newDocument);
		}

	}
}
