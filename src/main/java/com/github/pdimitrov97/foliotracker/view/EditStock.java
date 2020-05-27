package com.github.pdimitrov97.foliotracker.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.pdimitrov97.foliotracker.controller.EditStockController;
import com.github.pdimitrov97.foliotracker.controller.IEditStockController;
import com.github.pdimitrov97.foliotracker.model.IStock;

public class EditStock extends JFrame implements IEditStock
{
	private static final String TITLE = "Stock Editor";
	private static final int WINDOW_WIDTH = 240;
	private static final int WINDOW_HEIGHT = 190;
	
	private IStock stock;
	private IEditStockController controller;
	
	private JFrame frame;
	private JLabel tickerSymbolLabel;
	private JLabel stockNameLabel;
	private JLabel numberOfSharesLabel;
	private JLabel pricePerShareLabel;
	private JTextField stockNameText;
	//private JTextField numberOfSharesText;
	private JButton okButton;
	private JButton cancelButton;
		
	public EditStock(IStock stock)
	{
		this.stock = stock;
		this.controller = new EditStockController(this);
		
		initEditStock();
	}

	private void initEditStock()
	{
		frame = new JFrame(TITLE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		tickerSymbolLabel = new JLabel("Ticker Symbol:  " + stock.getTicker());
		tickerSymbolLabel.setBounds(10, 10, 210, 20);
		frame.getContentPane().add(tickerSymbolLabel);
		
		stockNameLabel = new JLabel("Stock Name:");
		stockNameLabel.setBounds(10, 40, 80, 20);
		frame.getContentPane().add(stockNameLabel);
		
		stockNameText = new JTextField(stock.getStockName());
		stockNameText.setBounds(90, 40, 135, 20);
		frame.getContentPane().add(stockNameText);
		
		numberOfSharesLabel = new JLabel("Number Of Shares:  " + stock.getNumberOfShares());
		numberOfSharesLabel.setBounds(10, 70, 210, 20);
		frame.getContentPane().add(numberOfSharesLabel);
		
		/*numberOfSharesText = new JTextField(Integer.toString(stock.getNumberOfShares()));
		numberOfSharesText.setBounds(125, 70, 100, 20);
		frame.getContentPane().add(numberOfSharesText);*/
		
		pricePerShareLabel = new JLabel("Price Per Share:  " + stock.getPricePerShare());
		pricePerShareLabel.setBounds(10, 100, 210, 20);
		frame.getContentPane().add(pricePerShareLabel);
	
		okButton = new JButton("OK");
		okButton.setBounds(10, 130, 100, 20);
		okButton.addActionListener(controller);
		okButton.setActionCommand("ok pressed");
		frame.getContentPane().add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(120, 130, 100, 20);
		cancelButton.addActionListener(controller);
		cancelButton.setActionCommand("cancel pressed");
		frame.getContentPane().add(cancelButton);
	}
	
	public IStock getStock()
	{
		return this.stock;
	}
	
	public String getStockName()
	{
		return stockNameText.getText();
	}
	
	/*public String getNumberOfShares()
	{
		return numberOfSharesText.getText();
	}*/
	
	public JFrame getFrame()
	{
		return this.frame;
	}
}
