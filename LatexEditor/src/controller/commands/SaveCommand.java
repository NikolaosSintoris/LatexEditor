package controller.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.LatexEditorController;
import model.Document;


public class SaveCommand implements Command
{

	private LatexEditorController theController;
	
	
	public SaveCommand(LatexEditorController newController) 
	{
		theController = newController;
		
	}
	
	public void execute(String aString, String bString) 
	{
		Writer writer = null;
		String filename = "myDoc";
		
		Document newDocument = theController.getCurrentDocument().clone();
		
		//for the author
		int pos=0;
		String lines[]=theController.getView().getTextArea().getText().split("\\n");		
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
		}
		
		//for the date
		int thesi=0;
		String liness[]=theController.getView().getTextArea().getText().split("\\n");		
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

	
		//for the contents
		newDocument.setContents(theController.getView().getTextArea().getText());
	
	
		theController.setCurrentDocument(newDocument);
		
		try 
		{
			JFileChooser chooser = new JFileChooser();
			String savePath="";
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION)
			{
				File saveFile = chooser.getSelectedFile();
				savePath = saveFile.getAbsolutePath();
			}
			filename = filename + theController.getCurrentDocument().getVersionID();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath + "\\" +filename + ".tex"), "utf-8"));
			writer.write(theController.getCurrentDocument().getContents());
		} catch (IOException ex) {
		 
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		
	}
}
