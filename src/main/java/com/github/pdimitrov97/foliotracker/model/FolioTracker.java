package com.github.pdimitrov97.foliotracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FolioTracker extends Observable implements IFolioTracker
{
	private String folioTrackerName;
	private List<IFolio> folios;
	
	public FolioTracker()
	{
		this.folioTrackerName = "";
		folios = new ArrayList<IFolio>();
	}
	
	public FolioTracker(String folioTrackerName)
	{
		this.folioTrackerName = folioTrackerName;
		folios = new ArrayList<IFolio>();
	}
	
	public String getFolioTrackerName()
	{
		return this.folioTrackerName;
	}
	
	public void setFolioTrackerName(String folioTrackerName)
	{
		this.folioTrackerName = folioTrackerName;
	}
	
	public boolean addFolio(IFolio folio)
	{
		if (getFolio(folio.getFolioName()) != null)
			return false;

		folios.add(folio);
		setChanged();
		notifyObservers(folio.getFolioName());
		assert(folios.contains(folio)) : "addFolio returns true, but " + folio.getFolioName() + " was not added to the list";
		return true;
	}
	
	public boolean renameFolio(String oldName, String newName)
	{
		IFolio renameFolio = getFolio(oldName);
		
		if (renameFolio == null)
			return false;
	
		renameFolio.setFolioName(newName);
		setChanged();
		notifyObservers(newName);
		
		assert(getFolio(newName) != null) : "Can't find folio " + newName;
		assert(getFolio(oldName) == null) : oldName + " is still available";
		return true;
	}
	
	public boolean deleteFolio(String folioName)
	{
		IFolio folio = getFolio(folioName);
		
		if (folio == null)
			return false;
		
		folios.remove(folio);
		setChanged();
		notifyObservers(folio.getFolioName());
		assert(!folios.contains(folio)) : "deleteFolio returns true, but " + folio.getFolioName() + "is still in the list";
		return true;
	}
	
	public IFolio getFolio(String folioName)
	{
		for(IFolio folio: folios)
		{
			if(folio.getFolioName().equals(folioName))
				return folio;
		}
	
		return null;
	}
	
	public List<IFolio> getFolios()
	{
		return this.folios;
	}
}
