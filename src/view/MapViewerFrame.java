package view;

import java.awt.Color;

import javax.swing.JFrame;

//This class puts both the map and navbar together
public class MapViewerFrame extends JFrame {

	// fields
	private NavigationBarPanel navBarPanel = new NavigationBarPanel();
	private MapViewerPanel mapPanel = new MapViewerPanel();

	// constructor
	public MapViewerFrame() {

		// set navbar
		navBarPanel.setBounds(0, 0, 1440, 90);
		add(navBarPanel);

		// set map
		mapPanel.setBounds(0, 90, 1440, 810);
		add(mapPanel);

		// set JFrame
		setLayout(null);
		setSize(1440, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(188, 202, 218));
		setVisible(true);

	}

	// getters and setters
	public NavigationBarPanel getNavBarPanel() {
		return navBarPanel;
	}

	public void setNavBarPanel(NavigationBarPanel navBarPanel) {
		this.navBarPanel = navBarPanel;
	}

	public MapViewerPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapViewerPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

}
