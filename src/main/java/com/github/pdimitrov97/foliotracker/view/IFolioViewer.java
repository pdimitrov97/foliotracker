package com.github.pdimitrov97.foliotracker.view;

import com.github.pdimitrov97.foliotracker.model.IFolio;

public interface IFolioViewer
{
	public String getTickerSymbol();
	
	public String getNumberOfShares();
	
	public void resetAddTextValues();
	
	public IFolio getFolio();
}