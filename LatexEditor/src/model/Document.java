package model;

import java.io.Serializable;

public class Document implements Serializable
{
	
	private String author;
	private String date;
	private String copyright;
	private String versionID;
	private String contents;
	
	
	public Document(String author,String date,String copyright,String versionID,String contents) 
	{
		this.author = author;
		this.date = date;
		this.copyright = copyright;
		this.versionID = versionID;
		this.contents = contents;
	}
	
	public void setContents(String newContents) 
	{
		this.contents = newContents;
	}
	
	public String getContents() 
	{
		return contents;
	}
	
	public void setAuthor(String newAuthor) 
	{
		this.author=newAuthor;
	}
	
	public String getAuthor() 
	{
		return author;
	}
	
	public void setVersion(String newVersionID) 
	{
		this.versionID=newVersionID;
	}
	
	public String getVersionID() 
	{
		return versionID;
	}
	
	public void setDate(String newDate) 
	{
		this.date=newDate;
	}
	
	public String getDate() 
	{
		return date;
	}

	
	public Document clone() 
	{
		Document newDocument = new Document(new String(this.author), new String(this.date), new String(this.copyright), new String(this.versionID), new String(this.contents));
		return newDocument;
	}
	
	

}
