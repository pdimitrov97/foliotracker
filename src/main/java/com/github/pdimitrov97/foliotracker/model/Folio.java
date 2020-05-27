package com.github.pdimitrov97.foliotracker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Folio extends Observable implements IFolio, Observer, Serializable
{
	private String name;
	private List<IStock> stocks;

	public Folio(String name)
	{
		this.name = name;
		stocks = new ArrayList<IStock>();
	}

	public String getFolioName()
	{
		return this.name;
	}

	public List<IStock> getFolioStocks()
	{
		return stocks;
	}

	public double getFolioTotalValue()
	{
		int sum = 0;

		for (IStock s : stocks)
			sum += s.getValueOfHolding();

		return sum;
	}

	public void setFolioName(String name)
	{
		this.name = name;
	}

	public boolean addStock(IStock stock)
	{
		IStock temp = getStock(stock.getTicker());

		if (temp != null)
		{
			temp.setNumberOfShares(temp.getNumberOfShares() + stock.getNumberOfShares());
			assert (temp.getNumberOfShares() >= stock.getNumberOfShares()) : temp.getNumberOfShares() + "<" + stock.getNumberOfShares();
			return true;
		}
		else
		{
			stocks.add(stock);
			stock.addObserver(this);
			setChanged();
			notifyObservers(stock.getTicker());

			assert (stocks.contains(stock)) : "addStock return true, but " + stock.getStockName() + " was not added";
			return true;
		}
	}

	public boolean deleteStock(String stockTicker)
	{
		IStock stock = getStock(stockTicker);

		if (stock == null)
			return false;

		stocks.remove(stock);
		setChanged();
		notifyObservers(stock.getTicker());
		assert (!stocks.contains(stock)) : "deleteStock return true, but " + stock.getStockName() + " is still in the list";
		return true;
	}

	public IStock getStock(String stockTicker)
	{
		for (IStock stock : stocks)
		{
			if (stock.getTicker().equals(stockTicker))
				return stock;
		}

		return null;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		setChanged();
		notifyObservers();
	}
}
