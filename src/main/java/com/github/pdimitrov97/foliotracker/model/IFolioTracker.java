package com.github.pdimitrov97.foliotracker.model;

import java.util.List;
import java.util.Observer;

public interface IFolioTracker
{
	public String getFolioTrackerName();

	public void setFolioTrackerName(String folioTrackerName);

	public boolean addFolio(IFolio portfolio);

	public boolean renameFolio(String oldName, String newName);

	public boolean deleteFolio(String folioName);

	public IFolio getFolio(String portfolioName);

	public List<IFolio> getFolios();

	public void addObserver(Observer newObserver);
}