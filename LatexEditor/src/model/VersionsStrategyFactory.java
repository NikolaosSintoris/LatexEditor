package model;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

public class VersionsStrategyFactory 
{
	public VersionsStrategyFactory()
	{
		
	}
	
	public VersionsStrategy createStrategy(String aString)
	{
		if(aString.equals("Volatile"))
		{
			return new VolatileVersionsStrategy();
		}
		else if(aString.equals("Stable"))
		{
			return new StableVersionsStrategy();
		}
		else
		{
			return new VolatileVersionsStrategy();
		}
	}

}
