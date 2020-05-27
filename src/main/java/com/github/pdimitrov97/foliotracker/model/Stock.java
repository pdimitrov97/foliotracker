package com.github.pdimitrov97.foliotracker.model;

import java.io.Serializable;
import java.util.Observable;

public class Stock extends Observable implements IStock, Serializable
{
	private String ticker;
	private String name;
	private int numberOfShares;
	private double pricePerShare;
	private double valueOfHolding;
	private Change priceChange;

	public Stock(String ticker, String name, int numberOfShares, double pricePerShare)
	{
		this.ticker = ticker;
		this.name = name;
		this.numberOfShares = numberOfShares;
		this.pricePerShare = pricePerShare;
		this.priceChange = Change.NONE;
		calculateValueOfHolding();
	}

	public String getTicker()
	{
		return this.ticker;
	}

	public String getStockName()
	{
		return this.name;
	}

	public int getNumberOfShares()
	{
		return this.numberOfShares;
	}

	public double getPricePerShare()
	{
		return this.pricePerShare;
	}

	public double getValueOfHolding()
	{
		return this.valueOfHolding;
	}

	public Change getPriceChange()
	{
		return priceChange;
	}

	public void setPriceChange(Change priceChange)
	{
		this.priceChange = priceChange;
	}

	public void setTicker(String ticker)
	{
		this.ticker = ticker;
		setChanged();
		notifyObservers();
	}

	public void setStockName(String name)
	{
		this.name = name;
		setChanged();
		notifyObservers();
	}

	public void setNumberOfShares(int value)
	{
		this.numberOfShares = value;
		calculateValueOfHolding();
		setChanged();
		notifyObservers();
	}

	public void setPricePerShare(double value)
	{
		this.pricePerShare = value;
		calculateValueOfHolding();
		setChanged();
		notifyObservers();
	}

	/*
	 * public void setNameAndShares(String name, int numberOfShares) { this.name =
	 * name; this.numberOfShares = numberOfShares; calculateValueOfHolding();
	 * setChanged(); notifyObservers(); }
	 */

	private void calculateValueOfHolding()
	{
		this.valueOfHolding = this.numberOfShares * this.pricePerShare;
		assert (this.valueOfHolding >= 0) : "Holding has negative value " + this.valueOfHolding;
	}
}