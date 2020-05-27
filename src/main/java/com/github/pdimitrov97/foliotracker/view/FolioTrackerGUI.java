package com.github.pdimitrov97.foliotracker.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalTime;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import com.github.pdimitrov97.foliotracker.controller.FolioTrackerGUIController;
import com.github.pdimitrov97.foliotracker.controller.IFolioTrackerGUIController;
import com.github.pdimitrov97.foliotracker.model.FolioTracker;
import com.github.pdimitrov97.foliotracker.model.IFolio;
import com.github.pdimitrov97.foliotracker.model.IFolioTracker;

public class FolioTrackerGUI implements IFolioTrackerGUI, Observer
{
	private static final String TITLE = "Folio Tracker";
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	
	private IFolioTrackerGUIController controller;
	private IFolioTracker folioTracker;
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu portfolioMenu;
	private JMenuItem menuItemNew;
	private JMenuItem menuItemOpen;
	private JMenuItem menuItemClose;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemSaveAndClose;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemAddPortfolio;
	private JMenuItem menuItemDeletePortfolio;
	private JMenuItem menuItemRenamePortfolio;
	private JMenuItem menuItemAutoRefresh;
	
	private JLabel folioSelectLabel;
	private JComboBox<String> folioSelect;
	private JButton addFolioButton;
	private JButton renameFolioButton;
	private JButton deleteFolioButton;
	private JButton refreshButton;
	private JTabbedPane tabbedPane;
	

	private JLabel lastRefreshedLabel;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					FolioTrackerGUI window = new FolioTrackerGUI();
					//window.initMainWindow();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public FolioTrackerGUI() 
	{
		this.controller = new FolioTrackerGUIController(this);
		initMainWindow();
		setFolioTracker(new FolioTracker("New Folio Tracker"));
		this.folioTracker.addObserver(this);
	}
	
	private void initMainWindow()
	{
		// Set up the main frame
		frame = new JFrame(TITLE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Add a menu bar to the main window
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// Add File menu to the menu bar
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// Add New button to the File menu
		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(controller);
		menuItemNew.setActionCommand("new foliotracker");
		fileMenu.add(menuItemNew);

		// Add Open button to the File menu
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(controller);
		menuItemOpen.setActionCommand("open foliotracker");
		fileMenu.add(menuItemOpen);

		// Add a separator to the File menu
		fileMenu.add(new JSeparator());

		// Add Close button to the File menu
		menuItemClose = new JMenuItem("Close");
		menuItemClose.addActionListener(controller);
		menuItemClose.setActionCommand("close foliotracker");
		fileMenu.add(menuItemClose);

		// Add Save button to the File menu
		menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(controller);
		menuItemSave.setActionCommand("save foliotracker");
		fileMenu.add(menuItemSave);

		// Add Save and Close button to the File menu
		menuItemSaveAndClose = new JMenuItem("Save and Close");
		menuItemSaveAndClose.addActionListener(controller);
		menuItemSaveAndClose.setActionCommand("save and close foliotracker");
		fileMenu.add(menuItemSaveAndClose);

		// Add a separator to the File menu
		fileMenu.add(new JSeparator());

		// Add Exit button to the File menu
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(controller);
		menuItemExit.setActionCommand("exit");
		fileMenu.add(menuItemExit);

		// Add Portfolio menu to the menu bar
		portfolioMenu = new JMenu("Folio");
		menuBar.add(portfolioMenu);

		// Add Add Portfolio button to the File menu
		menuItemAddPortfolio = new JMenuItem("Add Folio");
		menuItemAddPortfolio.addActionListener(controller);
		menuItemAddPortfolio.setActionCommand("add folio");
		portfolioMenu.add(menuItemAddPortfolio);

		// Add Delete Portfolio button to the File menu
		menuItemDeletePortfolio = new JMenuItem("Delete Folio");
		menuItemDeletePortfolio.addActionListener(controller);
		menuItemDeletePortfolio.setActionCommand("delete folio");
		portfolioMenu.add(menuItemDeletePortfolio);

		// Add Rename Portfolio button to the File menu
		menuItemRenamePortfolio = new JMenuItem("Rename Folio");
		menuItemRenamePortfolio.addActionListener(controller);
		menuItemRenamePortfolio.setActionCommand("rename folio");
		portfolioMenu.add(menuItemRenamePortfolio);
		
		//Add refresh button to menu
		menuItemAutoRefresh = new JCheckBoxMenuItem("Auto-Refresh");
		menuItemAutoRefresh.addActionListener(controller);
		menuItemAutoRefresh.setActionCommand("autorefresh");
		menuItemAutoRefresh.setToolTipText("Automatically refresh folio every 3 minutes.");
		menuItemAutoRefresh.setSelected(false);
		portfolioMenu.add(menuItemAutoRefresh);
		
		folioSelectLabel = new JLabel("Folio:");
		folioSelectLabel.setBounds(10, 10, 40, 20);
		frame.getContentPane().add(folioSelectLabel);

		folioSelect = new JComboBox<String>();
		folioSelect.setBounds(50, 10, 130, 20);
		folioSelect.addActionListener(controller);
		folioSelect.setActionCommand("folio select");
		frame.getContentPane().add(folioSelect);
		
		addFolioButton = new JButton("Add Folio");
		addFolioButton.setBounds(195, 10, 85, 20);
		addFolioButton.addActionListener(controller);
		addFolioButton.setActionCommand("add folio");
		frame.getContentPane().add(addFolioButton);
		
		renameFolioButton = new JButton("Rename Folio");
		renameFolioButton.setBounds(285, 10, 110, 20);
		renameFolioButton.addActionListener(controller);
		renameFolioButton.setActionCommand("rename folio");
		frame.getContentPane().add(renameFolioButton);
		
		deleteFolioButton = new JButton("Delete Folio");
		deleteFolioButton.setBounds(400, 10, 100, 20);
		deleteFolioButton.addActionListener(controller);
		deleteFolioButton.setActionCommand("delete folio");
		frame.getContentPane().add(deleteFolioButton);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(505, 10, 80, 20);
		refreshButton.addActionListener(controller);
		refreshButton.setActionCommand("refresh");
		frame.getContentPane().add(refreshButton);

		// Add Tabbed Panel to the main window
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 40, frame.getWidth(), frame.getHeight() - 105);
		frame.getContentPane().add(tabbedPane);
		
		//Add "Last refreshed: 00:00:00" text to main window
		lastRefreshedLabel = new JLabel("Last refreshed: " + LocalTime.now().toString().substring(0, 8));
		lastRefreshedLabel.setForeground(Color.GRAY);
		lastRefreshedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastRefreshedLabel.setBounds(0, 530, frame.getWidth(), 20);
		frame.getContentPane().add(lastRefreshedLabel);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//folioTracker = (IFolioTracker) o;
		String folioName = (String) arg;
		List<IFolio> folios = folioTracker.getFolios();
		
		if (tabbedPane.getTabCount() < folios.size()) // New folio was added
		{
			JPanel newTab = new JPanel();
			IFolio newFolio = folioTracker.getFolio(folioName);
			FolioViewer folioViewer = new FolioViewer(newFolio);
			newTab.setLayout(null);
			folioViewer.setSize(580, 470);
			folioViewer.setLayout(null);
			newTab.add(folioViewer);
			tabbedPane.addTab(folioName, null, newTab, null);
			folioSelect.addItem(folioName);
		}
		else if (tabbedPane.getTabCount() == folios.size()) // Folio was renamed
		{
			for (int i = 0 ; i < folios.size() ; i++)
			{
				if (folios.get(i).getFolioName().equals(folioName))
				{
					tabbedPane.setTitleAt(i, folioName);
					break;
				}
			}
			
			for (int i = 0 ; i < folioSelect.getItemCount() ; i++)
			{
				if (folios.get(i).getFolioName().equals(folioName))
				{
					folioSelect.removeItemAt(i);
					folioSelect.insertItemAt(folioName, i);
					folioSelect.setSelectedIndex(i);
					break;
				}
			}
		}
		else // Folio was deleted
		{
			for (int i = 0 ; i < tabbedPane.getTabCount() ; i++)
			{
				if (tabbedPane.getTitleAt(i).equals(folioName))
				{
					tabbedPane.remove(i);
					break;
				}
			}
			
			for (int i = 0 ; i < folioSelect.getItemCount() ; i++)
			{
				if (folioSelect.getItemAt(i).equals(folioName))
				{
					folioSelect.removeItemAt(i);
					break;
				}
			}
		}
	}
	
	public IFolioTracker getFolioTracker()
	{
		return this.folioTracker;
	}
	
	public void setFolioTracker(IFolioTracker folioTracker)
	{
		this.folioTracker = folioTracker;
		frame.setTitle(TITLE + " - " + this.folioTracker.getFolioTrackerName());
	}
	
	public void createNewFolioTracker(String folioTrackerName)
	{
		IFolioTracker newFolioTracker = new FolioTracker(folioTrackerName);
		newFolioTracker.addObserver(this);
		setFolioTracker(newFolioTracker);
		getTabbedPane().removeAll();
		getComboBox().removeAllItems();
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	
	public JComboBox<String> getComboBox()
	{
		return folioSelect;
	}

	public void setLastRefreshedLabel()
	{
		lastRefreshedLabel.setText("Last refreshed: " + (LocalTime.now().toString().substring(0, 8)));
	}
}
