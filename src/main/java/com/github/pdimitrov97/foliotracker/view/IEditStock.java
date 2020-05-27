package com.github.pdimitrov97.foliotracker.view;

import javax.swing.JFrame;
import com.github.pdimitrov97.foliotracker.model.IStock;

public interface IEditStock
{
	public IStock getStock();
	
	public String getStockName();
	
	//public String getNumberOfShares();
	
	public JFrame getFrame();
}