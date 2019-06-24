package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


public class DocumentManager 
{
	HashMap <String, Document> templatesMap = new HashMap <String, Document>();
	
	public DocumentManager()
	{
		dynamicallyLoadTemplates("Report", System.getProperty("user.dir")+"\\reportTemplate.txt");
		dynamicallyLoadTemplates("Book", System.getProperty("user.dir")+"\\bookTemplate.txt");
		dynamicallyLoadTemplates("Article", System.getProperty("user.dir")+"\\articleTemplate.txt");
		dynamicallyLoadTemplates("Letter", System.getProperty("user.dir")+"\\letterTemplate.txt");
		dynamicallyLoadTemplates("Empty", System.getProperty("user.dir")+"\\emptyTemplate.txt");
	}
	
	public void dynamicallyLoadTemplates(String templateName, String pathFile)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader (pathFile));
			String currentLine;
			String contents = "";
			while ((currentLine = br.readLine()) != null)
			{
				
				if(currentLine.equals("\\end{document}")) 
				{
					contents = contents + currentLine;
				}
				else 
				{
					contents = contents + currentLine + "\n";
				}
			}

			templatesMap.put(templateName, new Document("unknown", "unknown", "unknown", "Version_0", contents));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Document createDocument(String templateID)
	{
		Document newDocument = templatesMap.get(templateID);
		return newDocument.clone();
	}	
}
