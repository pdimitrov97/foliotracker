package com.github.pdimitrov97.foliotracker.view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import com.github.pdimitrov97.foliotracker.model.IFolioTracker;

public interface IFolioTrackerGUI
{
	public IFolioTracker getFolioTracker();
	
	public void setFolioTracker(IFolioTracker folioTracker);
	
	public void createNewFolioTracker(String folioTrackerName);
	
	public JFrame getFrame();
	
	public JTabbedPane getTabbedPane();
	
	public JComboBox<String> getComboBox();
	
	public void setLastRefreshedLabel();
}
