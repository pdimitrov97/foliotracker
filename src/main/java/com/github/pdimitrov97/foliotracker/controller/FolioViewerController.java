package com.github.pdimitrov97.foliotracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.github.pdimitrov97.foliotracker.model.IStock;
import com.github.pdimitrov97.foliotracker.model.Stock;
import com.github.pdimitrov97.foliotracker.view.EditStock;
import com.github.pdimitrov97.foliotracker.view.IFolioViewer;

public class FolioViewerController implements IFolioViewerController
{
	private IFolioViewer folioViewer;
	
	public FolioViewerController(IFolioViewer folioViewer)
	{
		this.folioViewer = folioViewer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		switch (command)
		{
			case "add stock":
			{
				String ticker = folioViewer.getTickerSymbol();
				String numberOfSharesString = folioViewer.getNumberOfShares();
				int numberOfShares;
				
				try
				{
					numberOfShares = Integer.parseInt(numberOfSharesString);
				}
				catch (Exception e1)
				{
					createWarningMessage("Please enter a valid Number Of Shares.", "Invalid input");
					break;
				}
				
				if (numberOfShares < 0)
				{
					createWarningMessage("Please enter a positive Number Of Shares.", "Invalid input");
					break;
				}
				
				StrathQuoteServer quote = new StrathQuoteServer();
				
				String pricePerShareString;
				Double pricePerShare;
				
				try
				{
					pricePerShareString = quote.getLastValue(ticker);
				} 
				catch (Exception e1)
				{
					createWarningMessage("Please enter a valid Ticker Symbol.", "Invalid input");
					break;
				}
				
				try
				{
					pricePerShare = Double.parseDouble(pricePerShareString);
				}
				catch (Exception e1)
				{
					createWarningMessage("Invalid price given from server.", "Invalid input");
					break;
				}
				
				IStock newStock = new Stock(ticker, "", numberOfShares, pricePerShare);
				folioViewer.getFolio().addStock(newStock);
				folioViewer.resetAddTextValues();
				break;
			}
			case "sell stock":
			{
				String ticker = folioViewer.getTickerSymbol();
				IStock temp = folioViewer.getFolio().getStock(ticker);
				
				if (temp == null)
				{
					createWarningMessage("Please enter an existing ticker.", "Invalid input");
					break;
				}
				
				String numberOfSharesString = folioViewer.getNumberOfShares();
				int numberOfShares;
				
				try
				{
					numberOfShares = Integer.parseInt(numberOfSharesString);
				}
				catch (Exception e1)
				{
					createWarningMessage("Please enter a valid Number Of Shares.", "Invalid input");
					break;
				}
				
				if (numberOfShares < 0)
				{
					createWarningMessage("Please enter a positive Number Of Shares.", "Invalid input");
					break;
				}

				if (numberOfShares > temp.getNumberOfShares())
				{
					createWarningMessage("Number of Shares for sell is more than actual amount.", "Invalid input");
					return;
				}
				else if (numberOfShares == temp.getNumberOfShares())
				{
					folioViewer.getFolio().deleteStock(ticker);
					folioViewer.resetAddTextValues();
				}
				else
				{
					temp.setNumberOfShares(temp.getNumberOfShares() - numberOfShares);
					folioViewer.resetAddTextValues();
				}
				
				break;
			}
		}
	}
	
	private void createWarningMessage(String message, String title)
	{
		JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 2)
		{
		      JTable target = (JTable) e.getSource();
		      int row = target.getSelectedRow();
		      
		      String ticker = target.getValueAt(row, 0).toString();
		      
		      new EditStock(folioViewer.getFolio().getStock(ticker));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
