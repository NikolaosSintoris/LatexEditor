package model.strategies;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Document;

public class VolatileVersionsStrategy implements VersionsStrategy
{

	private ArrayList<Document> versionsHistory=new ArrayList<Document>();
	
	public VolatileVersionsStrategy() 
	{
		
	}
	
	public void putVersion(Document newDocument)
	{
		versionsHistory.add(newDocument);
	}


	public Document getVersion()
	{

		return versionsHistory.get(versionsHistory.size()-1);
	
	}


	public void setEntireHistory(ArrayList<Document> newVersionsHistory ) 
	{
		versionsHistory=newVersionsHistory;
	}


	public ArrayList<Document> getEntireHistory() 
	{
		return versionsHistory;
	}


	public void removeVersion() 
	{
		if(versionsHistory.size()>=2) 
		{
			versionsHistory.remove(versionsHistory.size()-1);
		}else 
		{
			JOptionPane.showMessageDialog(null, "This is the last version you have");
		}
	}
}
