package com.github.pdimitrov97.foliotracker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface IFolioViewerController extends ActionListener, MouseListener
{
	public void actionPerformed(ActionEvent e);
	
	public void mouseClicked(MouseEvent e);
	
	public void mouseEntered(MouseEvent e);
	
	public void mouseExited(MouseEvent e);
	
	public void mousePressed(MouseEvent e);
	
	public void mouseReleased(MouseEvent e);
}