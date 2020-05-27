package com.github.pdimitrov97.foliotracker.controller;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

import com.github.pdimitrov97.foliotracker.model.Folio;
import com.github.pdimitrov97.foliotracker.model.IFolio;
import com.github.pdimitrov97.foliotracker.model.IStock;
import com.github.pdimitrov97.foliotracker.view.IFolioTrackerGUI;

public class FolioTrackerGUIController implements IFolioTrackerGUIController
{
	private IFolioTrackerGUI folioTrackerGUI;
	private Timer refreshTimer;
	
	public FolioTrackerGUIController(IFolioTrackerGUI folioTrackerGUI)
	{
		this.folioTrackerGUI = folioTrackerGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		switch (command)
		{
			case "new foliotracker":
			{
				createNewFolio();
				break;
			}
			case "open foliotracker":
			{
				loadFolio();
				break;
			}
			case "close foliotracker":
			{
				closeFolio();
				break;
			}
			case "save foliotracker":
			{
				saveFolio();
				break;
			}
			case "save and close foliotracker":
			{
				saveFolio();
				closeFolio();
				break;
			}
			case "exit":
			{
				System.exit(0);
			}
			case "add folio":
			{
				addFolio();
				break;
			}
			case "delete folio":
			{
				deleteFolio();
				break;
			}
			case "rename folio":
			{
				renameFolio();
				break;
			}
			case "folio select":
			{
				openSelectedFolio();
				break;
			}
			case "refresh":
			{
				refreshFolioTracker();
				break;
			}
			case "autorefresh":
				autoRefresh(((JCheckBoxMenuItem) e.getSource()).getState());
				break;
		}
	}
	
	private void createNewFolio()
	{
		String folioTrackerName;
		
		do
		{
			folioTrackerName = JOptionPane.showInputDialog("Please enter the new folio tracker name", null);
			
			if (folioTrackerName == null)
				break;
			
			if (folioTrackerName.isEmpty())
			{
				createWarningMessage("Please enter a folio tracker name.", "Invalid input");
				continue;
			}
			
			folioTrackerGUI.createNewFolioTracker(folioTrackerName);
			break;
		}
		while (true);
	}

	private void loadFolio()
	{
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(folioTrackerGUI.getFrame()) == JFileChooser.APPROVE_OPTION)
		{
			ObjectInputStream objectinputstream = null;

			try
			{
				FileInputStream file = new FileInputStream(fileChooser.getSelectedFile());
				objectinputstream = new ObjectInputStream(file);
				List<IFolio> newFolioTracker = (List<IFolio>) objectinputstream.readObject();
				
				String name = fileChooser.getSelectedFile().getName();
				
				int pos = name.lastIndexOf(".");
				
				if (pos > 0)
				    name = name.substring(0, pos);
				
				folioTrackerGUI.createNewFolioTracker(name);

				for (int i = 0 ; i < newFolioTracker.size() ; i++)
				{
					IFolio folio = newFolioTracker.get(i);
					IFolio temp = new Folio(folio.getFolioName());
					folioTrackerGUI.getFolioTracker().addFolio(temp);
					List<IStock> stocks = folio.getFolioStocks();
					
					for (int j = 0 ; j < stocks.size() ; j++)
					{
						temp.addStock(stocks.get(j));
					}
				}
			}
			catch (Exception e)
			{
				createWarningMessage("Cannot load foliotracker!", "Invalid file");
				return;
			}
			finally
			{
				if(objectinputstream != null)
				{
					try
					{
						objectinputstream.close();
					}
					catch (IOException e)
					{
						createWarningMessage("Cannot load foliotracker!", "Invalid file");
						return;
					}
				}
			}
		}
	}
	
	private void closeFolio()
	{
		folioTrackerGUI.createNewFolioTracker("New Folio Tracker");
	}
	
	private void saveFolio()
	{
		JFileChooser fileChooser = new JFileChooser() {
			// If file exists, ask the user whether they want to replace it
			@Override
		    public void approveSelection()
		    {
		    	File file = getSelectedFile();

		        if(file.exists() && getDialogType() == SAVE_DIALOG)
		        {
		        	int result = JOptionPane.showConfirmDialog(this, "This file already exists! Do you want to overwrite it?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);

		        	if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION)
		        		return;
		        	else if (result == JOptionPane.CANCEL_OPTION)
		        	{
		        		cancelSelection();
					    return;
		        	}
		        	else if (result == JOptionPane.YES_OPTION)
		        	{
		        		super.approveSelection();
					    return;
		        	}
		        	else
		        	{
		        		createWarningMessage("Cannot save foliotracker!", "Invalid file");
						return;
		        	}
		        }

		        super.approveSelection();
		    }        
		};

		fileChooser.setSelectedFile(new File(folioTrackerGUI.getFolioTracker().getFolioTrackerName() + ".foliotracker"));

		if (fileChooser.showSaveDialog(folioTrackerGUI.getFrame()) == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();

			// Create or overwrite file
			try
			{
				if (file.exists())
					file.delete();

				file.createNewFile();								
			}
			catch (IOException e1)
			{
				createWarningMessage("Cannot save foliotracker!", "Invalid file");
				return;
			}

			ObjectOutputStream OOS = null;
			FileOutputStream fOS = null;
			
			try
			{
				fOS = new FileOutputStream(file);
				OOS = new ObjectOutputStream(fOS);
				OOS.writeObject(folioTrackerGUI.getFolioTracker().getFolios());
			}
			catch (Exception ex)
			{
				createWarningMessage("Cannot save foliotracker!", "Invalid file");
				return;
			}
			finally
			{
				if(OOS != null)
				{
					try
					{
						OOS.close();
					}
					catch (IOException e)
					{
						createWarningMessage("Cannot save foliotracker!", "Invalid file");
						return;
					}
				}
			}
		}
	}
	
	private void addFolio()
	{
		String portfolioName;
		
		do
		{
			portfolioName = JOptionPane.showInputDialog("Please enter the new portfolio name", null);
			
			if (portfolioName == null)
				break;

			if (portfolioName.isEmpty())
			{
				createWarningMessage("Please enter a portfolio name.", "Invalid input");
				continue;
			}
			
			IFolio newFolio = new Folio(portfolioName);
			folioTrackerGUI.getFolioTracker().addFolio(newFolio);
			
			break;
		}
		while (true);
	}
	
	private void deleteFolio()
	{
		if (!(folioTrackerGUI.getTabbedPane().getSelectedIndex() >= 0 && folioTrackerGUI.getTabbedPane().getSelectedIndex() < folioTrackerGUI.getTabbedPane().getTabCount()))
			return;
		
		String name = folioTrackerGUI.getTabbedPane().getTitleAt(folioTrackerGUI.getTabbedPane().getSelectedIndex());
		JPanel deleteFolio = new JPanel();
		deleteFolio.add(new JLabel("Do you want to delete \"" + name + "\"?"));
		
		int result = JOptionPane.showConfirmDialog(null, deleteFolio, "Delte folio", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION)
			folioTrackerGUI.getFolioTracker().deleteFolio(folioTrackerGUI.getTabbedPane().getTitleAt(folioTrackerGUI.getTabbedPane().getSelectedIndex()));
		else
			return;
	}
	
	private void renameFolio()
	{
		if (!(folioTrackerGUI.getTabbedPane().getSelectedIndex() >= 0 && folioTrackerGUI.getTabbedPane().getSelectedIndex() < folioTrackerGUI.getTabbedPane().getTabCount()))
			return;
		
		String newPortfolioName;
		
		do
		{
			newPortfolioName = JOptionPane.showInputDialog("Please enter the new name for portfolio", null);
			
			if (newPortfolioName == null)
				break;

			if (newPortfolioName.isEmpty())
			{
				createWarningMessage("Please enter a new portfolio name.", "Invalid input");
				continue;
			}
			
			folioTrackerGUI.getFolioTracker().renameFolio(folioTrackerGUI.getTabbedPane().getTitleAt(folioTrackerGUI.getTabbedPane().getSelectedIndex()), newPortfolioName);
			break;
		}
		while (true);
	}
	
	private void openSelectedFolio()
	{
		folioTrackerGUI.getTabbedPane().setSelectedIndex(folioTrackerGUI.getComboBox().getSelectedIndex());
	}
	
	private void refreshFolioTracker()
	{
		StrathQuoteServer sqs = new StrathQuoteServer();
		String ticker;
		Double newPrice;

		List<IFolio> folios = folioTrackerGUI.getFolioTracker().getFolios();
		
		for (int i = 0 ; i < folios.size() ; i++)
		{
			List<IStock> stocks = folios.get(i).getFolioStocks();
			IStock temp = null;
	
			for (int j = 0 ; j < stocks.size() ; j++)
			{
				temp = stocks.get(j);
				ticker = temp.getTicker();
				
				try
				{
					newPrice = Double.parseDouble(sqs.getLastValue(ticker));
				} 
				catch (Exception e1)
				{
					createWarningMessage("Invalid Ticker Symbol: " + ticker, "Invalid ticker");
					break;
				}

				if (newPrice == temp.getPricePerShare()) {
					temp.setPriceChange(IStock.Change.NONE);
				} else if (newPrice > temp.getPricePerShare()){
					temp.setPriceChange(IStock.Change.UP);
				} else {
					temp.setPriceChange(IStock.Change.DOWN);
				}

				temp.setPricePerShare(newPrice);
			}
		}
		
		folioTrackerGUI.setLastRefreshedLabel();
	}

	private void autoRefresh(boolean state) {
		TimerTask refresh = new TimerTask() {
			@Override
			public void run() {
				refreshFolioTracker();
			}
		};
		if (state) {
			refreshTimer = new Timer("autoRefreshTimer");
			refreshTimer.schedule(refresh, 0, 180000);
		} else {
			refreshTimer.cancel();
		}
	}

	private void createWarningMessage(String message, String title)
	{
		JOptionPane.showMessageDialog(null, "Error: " + message, title, JOptionPane.WARNING_MESSAGE);
	}
}
