package model;

import controller.LatexEditorController;
import model.strategies.VersionsStrategy;

public class VersionsManager
{
	private boolean enabled;
	private  VersionsStrategy strategy;
	private  LatexEditorController theController;
	
	public VersionsManager(VersionsStrategy newVersionsStrategy, LatexEditorController newController) 
	{
		strategy = newVersionsStrategy;
		theController = newController;
	}
	
	public VersionsManager() 
	{
		enabled = false;
	}
	
	public boolean isEnabled() 
	{
		
		return enabled;
	}
	
	public void enable()
	{
		enabled=true;
	}
	
	public void disable()
	{
		enabled=false;
	}
	
	public void setVersionsStrategy(VersionsStrategy newVersionsStrategy) 
	{
		strategy=newVersionsStrategy;
	}
	
	public VersionsStrategy getVersionsStrategy()
	{
		return strategy;
	}
	
	public void setCurrentVersion(Document newDocumentVersion)
	{
		theController.setCurrentDocument(newDocumentVersion);
		strategy.putVersion(newDocumentVersion);
	}
	

	
	public void rollbackToPreviousVersion() 
	{
		strategy.removeVersion();
		theController.setCurrentDocument(strategy.getVersion());
	}
	

}
