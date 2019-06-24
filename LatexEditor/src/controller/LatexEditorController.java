package controller;


import java.util.ArrayList;

import controller.commands.*;

import model.Document;
import model.VersionsManager;
import model.VersionsStrategyFactory;
import view.LatexEditorView;

public class LatexEditorController 
{

	private LatexEditorView theView;
	private CommandsFactory theCommandsFactory = new CommandsFactory();
	private Document currentDocument;
	private VersionsStrategyFactory theVersionsStrategyFactory= new VersionsStrategyFactory();
	private VersionsManager theVersionsManager = new VersionsManager();
	private ArrayList<Document> temporaryHistory = new ArrayList<Document>();
	
	
	public LatexEditorController(LatexEditorView newView)
	{
		theView = newView;
	}
	
	public LatexEditorView getView()
	{
		return theView;
	}
	
	public void enact(String aString, String bString)
	{
		if(aString.equals("Enable") || aString.equals("Stable")) 
		{
			theVersionsManager = new VersionsManager(theVersionsStrategyFactory.createStrategy(aString), this);
		}
		
		theCommandsFactory.createCommandObject(aString, this).execute(aString, bString);
	}
	

	public void setCurrentDocument(Document newDocument)
	{
		currentDocument = newDocument;
	}
	
	public Document getCurrentDocument()
	{
		return currentDocument;
	}
	
	public VersionsManager getVersionsManager()
	{
		return theVersionsManager;
	}
	
	public ArrayList<Document> getTemporaryHistory()
	{
		return temporaryHistory;
	}
	
	public void setTemporaryHistory(ArrayList<Document> newHistory)
	{
		temporaryHistory = newHistory;
	}
	
}
