package controller.commands;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import controller.LatexEditorController;
import model.Document;

public class LoadCommand implements Command 
{


	private LatexEditorController theController;
	
	public LoadCommand(LatexEditorController newController) 
	{
		theController = newController;
	}
	
	public void execute(String aString, String bString) 
	{
		String pathName="";
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 0)
		{
		    pathName=fd.getFiles()[0].getAbsolutePath();
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathName)))
		{
		    String line,text="";
		    try {
				while ((line = br.readLine()) != null) 
				{
					if(line.equals("\\end{document}")) 
					{
						text= text + line;
					}
					else 
					{
						text = text + line + "\n";
					}
				}
				Document newDocument = theController.getCurrentDocument().clone();
				
				
				//for the  version
				newDocument.setVersion("Version_0");
				
				
				//for the  contents
				newDocument.setContents(text);
				
				
				//for the  author
				int pos=0;
				String lines[]=text.split("\\n");		
				for(int i=0;i<lines.length;i++) {
					if(lines[i].contains("author")){
						pos=i;
						break;
					}
				}
				String authorLine[] =lines[pos].split("\\{");
				if(pos>0) {
					String newAuthor=authorLine[1].replace("}", "");
					newDocument.setAuthor(newAuthor);
				};
				
				
				//for the  date
				int thesi=0;
				String liness[]=text.split("\\n");		
				for(int i=0;i<liness.length;i++) {
					if(liness[i].contains("date")){
						thesi=i;
						break;
					}
				}
				String dateLine[] =liness[thesi].split("\\{");
				if(thesi>0) {
					String newDate=dateLine[1].replace("}", "");
					newDocument.setDate(newDate);
				}
				
				theController.setCurrentDocument(newDocument);
				theController.getView().setTextArea(newDocument.getContents());


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

