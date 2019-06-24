package model.strategies;

import java.util.ArrayList;

import model.Document;

public interface VersionsStrategy {

	public void putVersion(Document aDocument);

	public Document getVersion();
	
	public void setEntireHistory(ArrayList<Document> theVersionsHistory);
	
	public ArrayList<Document> getEntireHistory();
	
	public void removeVersion();
}
