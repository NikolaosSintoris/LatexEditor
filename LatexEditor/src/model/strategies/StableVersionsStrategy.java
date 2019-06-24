package model.strategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Document;

public class StableVersionsStrategy implements VersionsStrategy
{

	private ArrayList<Document> versionsHistory=new ArrayList<Document>();
	private String filename = System.getProperty("user.dir")+"\\serialization.bin";
	
	public StableVersionsStrategy()
	{
		
	}
	
	public void putVersion(Document newDocument)
	{
		versionsHistory.add(newDocument);
		
		ObjectOutputStream outputSream;
		try 
		{
			outputSream = new ObjectOutputStream (new FileOutputStream(filename));
			outputSream.writeObject(versionsHistory);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	
	public Document getVersion() 
	{
        FileInputStream fis;
        ArrayList<Document> myList=new ArrayList<>();
		try 
		{
			fis = new FileInputStream(filename);
			ObjectInputStream ois=new ObjectInputStream(fis);
			myList=(ArrayList<Document>)ois.readObject();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		 return myList.get(myList.size()-1);
	}
	
	public void setEntireHistory(ArrayList<Document> newVersionsHistory) 
	{
	
		versionsHistory=newVersionsHistory;
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream (new FileOutputStream(filename));
			os.writeObject(versionsHistory);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

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
			
			ObjectOutputStream os;
			try 
			{
				os = new ObjectOutputStream (new FileOutputStream(filename));
				os.writeObject(versionsHistory);
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}else 
		{
			JOptionPane.showMessageDialog(null, "This is the last version you have");
		}
	}
}
