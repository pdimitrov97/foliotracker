package com.github.pdimitrov97.foliotracker.view;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.github.pdimitrov97.foliotracker.controller.FolioViewerController;
import com.github.pdimitrov97.foliotracker.controller.IFolioViewerController;
import com.github.pdimitrov97.foliotracker.model.IFolio;
import com.github.pdimitrov97.foliotracker.model.IStock;

public class FolioViewer extends JPanel implements IFolioViewer, Observer
{
	private IFolio folio;
	private IFolioViewerController controller;
	
	private JLabel tickerSymbolLabel;
	private JLabel numberOfSharesLabel;
	private JLabel valueLabel;
	private JTextField tickerSymbolText;
	private JTextField numberOfSharesText;
	private JButton addStockButton;
	private JButton sellStockButton;
	private JTable table;
	
	public FolioViewer(IFolio folio)
	{
		this.folio = folio;
		this.controller = new FolioViewerController(this);
		
		folio.addObserver(this);
		initPortfolioViewer();
	}
	
	private void initPortfolioViewer()
	{		
		String[] columnNames = { "Ticker Symbol",
                				 "Stock name",
                				 "Number Of Shares",
				                 "Price Per Share",
				                 "Value Of Holding",
				 				 "▼/▲"
                				};
		
		Object[][] empty = { };
		
		DefaultTableModel tableModel = new DefaultTableModel(empty, columnNames)
		{
			@Override
            public Class getColumnClass(int column)
			{
                switch (column)
                {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Double.class;
					case 5:
						return IStock.Change.class;
                    default:
                    	return String.class;
                }
            }
			
			@Override
			public boolean isCellEditable(int row, int col)
			{
				return false;
			}
		};
		
		table = new JTable(tableModel);
		table.setEnabled(true);
		table.addMouseListener(controller);
		table.setFillsViewportHeight(true);	
		table.setAutoCreateRowSorter(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(5).setMaxWidth(35);

		JScrollPane scrollPane = new JScrollPane(table); 
		scrollPane.setBounds(5, 10, 575, 360);
		this.add(scrollPane);

		tickerSymbolLabel = new JLabel("Ticker Symbol:");
		tickerSymbolLabel.setBounds(20, 380, 90, 20);
		this.add(tickerSymbolLabel);
		
		tickerSymbolText = new JTextField();
		tickerSymbolText.setBounds(110, 380, 150, 20);
		this.add(tickerSymbolText);
		
		numberOfSharesLabel = new JLabel("Number of Shares:");
		numberOfSharesLabel.setBounds(20, 410, 110, 20);
		this.add(numberOfSharesLabel);

		valueLabel = new JLabel("Total value of folio: " + getTotalValue());
		valueLabel.setBounds(375, 365, 200, 20);
		valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		valueLabel.setForeground(Color.GRAY);
		this.add(valueLabel);
		
		numberOfSharesText = new JTextField();
		numberOfSharesText.setBounds(130, 410, 130, 20);
		this.add(numberOfSharesText);
		
		addStockButton = new JButton("Add Stock");
		addStockButton.setBounds(300, 395, 100, 20);
		addStockButton.addActionListener(controller);
		addStockButton.setActionCommand("add stock");
		this.add(addStockButton);
		
		sellStockButton = new JButton("Sell Stock");
		sellStockButton.setBounds(450, 395, 100, 20);
		sellStockButton.addActionListener(controller);
		sellStockButton.setActionCommand("sell stock");
		this.add(sellStockButton);
	}

	@Override
	public void update(Observable o, Object arg)
	{		
		String stockTickerSymbol;
		String stockStockName;
		Integer stockNumberOfShares;
		Double stockPricePerShare;
		Double stockValueOfHolding;
		String stockPriceChangeIcon;
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		List<IStock> stocks = folio.getFolioStocks();
		model.setRowCount(0);
		
		for (IStock stock : stocks)
		{
			stockTickerSymbol = stock.getTicker();
			stockStockName = stock.getStockName();
			stockNumberOfShares = stock.getNumberOfShares();
			stockPricePerShare = stock.getPricePerShare();
			stockValueOfHolding = stock.getValueOfHolding();
			
			if (stock.getPriceChange() == IStock.Change.NONE)
			{
				stockPriceChangeIcon = "     -";
			}
			else if (stock.getPriceChange() == IStock.Change.UP)
			{
				stockPriceChangeIcon = "    ▲";
			}
			else
			{
				stockPriceChangeIcon = "    ▼";
			}
			
			Object[] row = {stockTickerSymbol, stockStockName, stockNumberOfShares, stockPricePerShare, stockValueOfHolding, stockPriceChangeIcon};
			
			model.addRow(row);
		}

		valueLabel.setText("Total value of folio: " + getTotalValue());

	}
	
	public String getTickerSymbol()
	{
		return tickerSymbolText.getText().toUpperCase();
	}
	
	public String getNumberOfShares()
	{
		return numberOfSharesText.getText();
	}
	
	public void resetAddTextValues()
	{
		tickerSymbolText.setText("");
		numberOfSharesText.setText("");
	}
	
	public IFolio getFolio()
	{
		return this.folio;
	}

	private String getTotalValue()
	{
		Double total = 0.0;
		
		for (int i = 0; i < table.getRowCount(); i++)
		{
			total += (Double) (table.getModel().getValueAt(i, 4));
		}
		
		return String.format("%.2f", total);
	}
}
