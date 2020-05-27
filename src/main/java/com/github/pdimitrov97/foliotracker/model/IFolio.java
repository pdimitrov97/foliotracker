package com.github.pdimitrov97.foliotracker.model;

import java.util.List;
import java.util.Observer;

public interface IFolio
{
	public String getFolioName();

	public List<IStock> getFolioStocks();

	public double getFolioTotalValue();

	public void setFolioName(String name);

	public boolean addStock(IStock stock);

	public boolean deleteStock(String stockTicker);

	public IStock getStock(String stockTicker);

	public void addObserver(Observer newObserver);
}