package com.github.pdimitrov97.foliotracker.controller;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import com.github.pdimitrov97.foliotracker.view.IEditStock;

public class EditStockController implements IEditStockController
{
	private IEditStock editStock;
	
	public EditStockController(IEditStock editStock)
	{
		this.editStock = editStock;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		switch (command)
		{
			case "ok pressed":
			{
				String stockName = editStock.getStockName();
				//String numberOfSharesString = editStock.getNumberOfShares();
				/*int numberOfShares;
				
				try
				{
					numberOfShares = Integer.parseInt(numberOfSharesString);
				}
				catch (Exception e1)
				{
					createWarningMessage("Please enter a valid Number Of Shares.", "Invalid input");
					return;
				}*/
				
				editStock.getStock().setStockName(stockName);
				//editStock.getStock().setNumberOfShares(numberOfShares);
				//editStock.getStock().setNameAndShares(stockName, numberOfShares);
				editStock.getFrame().dispose();
				break;
			}
			case "cancel pressed":
			{
				editStock.getFrame().dispose();
				break;
			}
		}
	}
	
	private void createWarningMessage(String message, String title)
	{
		JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.WARNING_MESSAGE);
	}
}
