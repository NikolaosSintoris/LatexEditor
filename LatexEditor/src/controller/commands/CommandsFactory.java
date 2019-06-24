package controller.commands;

import java.util.ArrayList;

import controller.LatexEditorController;


public class CommandsFactory 
{

	
	private static ArrayList <String> createCommandsList = new ArrayList<String>(){{
		add("Report");
		add("Book");
		add("Letter");
		add("Article");
		add("Empty");
	}};
	
	private static ArrayList <String> addLatexCommandsList = new ArrayList<String>(){{
		add("Add Chapter");
		add("Add Section");
		add("Add SubSection");
		add("Add SubSubSection");
		add("Add Itemize List");
		add("Add Enumeration List");
		add("Add Table");
		add("Add Figure");
	}};
	
	public CommandsFactory()
	{
		
	}
	
	public static Command createCommandObject(String commandString, LatexEditorController newController)
	{
		if (createCommandsList.contains(commandString) == true)
		{
			return  new CreateCommand(newController);
		}
		else if (addLatexCommandsList.contains(commandString) == true)
		{
			return new AddLatexCommand(newController);
		}
		else if (commandString.equals("Submit Changes"))
		{
			return new EditCommand(newController);
		}
		else if (commandString.equals("Load Document"))
		{
			return new LoadCommand(newController);
		}
		else if (commandString.equals("Save Document"))
		{
			return new SaveCommand(newController);
		}
		else if(commandString.equals("Enable"))
		{
			return new EnableVersionsManagementCommand(newController);
		}
		else if(commandString.equals("Stable"))
		{
			return new StableCommand(newController);
		}
		else if(commandString.equals("Disable")) 
		{
			return new DisableVersionsManagementCommand(newController);
		}
		else if(commandString.equals("Rollback To Previous Version"))
		{
			return new RollbackToPreviousVersionCommand(newController);
		}
		else if(commandString.equals("ChangeVersionsStrategy")) 
		{
			return new ChangeVersionsStrategyCommand(newController);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

}
