package com.github.pdimitrov97.foliotracker.model;

import java.util.Observer;

public interface IStock
{
	public enum Change {
		UP, DOWN, NONE
	}

	public String getTicker();
	
	public String getStockName();
	
	public int getNumberOfShares();
	
	public double getPricePerShare();
	
	public double getValueOfHolding();
	
	public void setTicker(String ticker);
	
	public void setStockName(String name);
	
	public void setNumberOfShares(int value);
	
	public void setPricePerShare(double value);
	
	//public void setNameAndShares(String name, int numberOfShares);

	public void addObserver(Observer newObserver);

	public Change getPriceChange();

	public void setPriceChange(Change priceChange);
}