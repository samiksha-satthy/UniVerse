package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

import model.CalculateDistance;
import model.SelectionAdapter;
import model.SelectionPainter;
import model.SwingWaypoint;
import model.SwingWaypointOverlayPainter;
import model.University;
import controller.MapController;

import org.jxmapviewer.viewer.*;
import org.jxmapviewer.*;
import java.awt.Font;

//This class displays the map on the panel 
//resources: https://github.com/msteiger/jxmapviewer2

public class MapViewerPanel extends JPanel {

	// create new map
	private JXMapViewer mapViewer = new JXMapViewer();

	// Create all other Java components needed for this frame
	// This button will provide user with instructions on how to use interactive map
	private JButton help = new JButton("HELP");

	// This button allows the user to look at their bookmarked universities
	private JButton bookmark = new JButton("BOOKMARKS");

	// This button allows the user to unbookmark Universities
	private JButton unbookmark = new JButton("UNBOOKMARK");

	// title of screen
	JLabel title = new JLabel("Interactive Map");

	// distance radius options that the users can choose from
	private String[] distanceChoice = { "30 km radius", "60 km radius", "90 km radius", "120 km+ radius" };
	private JComboBox<String> distanceRadius = new JComboBox(distanceChoice);

	// contains all the JButtons of each location
	Set<SwingWaypoint> waypoints;

	// constructor
	public MapViewerPanel() {

		// set Panel
		setLayout(null);
		setSize(1440, 810);
		setBackground(new Color(188, 202, 218));

		// Set all University Locations
		MapController.locationsArray[0] = new GeoPosition(46.5010, -84.2872);
		MapController.locationsArray[1] = new GeoPosition(43.1181, -79.2477);
		MapController.locationsArray[2] = new GeoPosition(45.38883, -75.6960);
		MapController.locationsArray[3] = new GeoPosition(43.5327, -80.2262);
		MapController.locationsArray[4] = new GeoPosition(48.4211, -89.2607);
		MapController.locationsArray[5] = new GeoPosition(46.4672, -80.9757);
		MapController.locationsArray[6] = new GeoPosition(43.2609, -79.9192);
		MapController.locationsArray[7] = new GeoPosition(46.3410, -79.4908);
		MapController.locationsArray[8] = new GeoPosition(43.6532, -79.3915);
		MapController.locationsArray[9] = new GeoPosition(43.9456, -78.8968);
		MapController.locationsArray[10] = new GeoPosition(45.4231, -75.6831);
		MapController.locationsArray[11] = new GeoPosition(44.2253, -76.4951);
		MapController.locationsArray[12] = new GeoPosition(43.6634, -79.3960);
		MapController.locationsArray[13] = new GeoPosition(43.7830, -79.1874);
		MapController.locationsArray[14] = new GeoPosition(43.5483, -79.6627);
		MapController.locationsArray[15] = new GeoPosition(43.6577, -79.3788);
		MapController.locationsArray[16] = new GeoPosition(44.3559, -78.2903);
		MapController.locationsArray[17] = new GeoPosition(43.4723, -80.5449);
		MapController.locationsArray[18] = new GeoPosition(43.0096, -81.2737);
		MapController.locationsArray[19] = new GeoPosition(43.4738, -80.5275);
		MapController.locationsArray[20] = new GeoPosition(42.3043, -83.0660);
		MapController.locationsArray[21] = new GeoPosition(43.7735, -79.5019);

		// set user's Current location
		MapController.locationsArray[22] = MapController.currentLocation;

		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		mapViewer.setTileFactory(tileFactory);

		// Use 8 threads in parallel to load the tiles
		tileFactory.setThreadPoolSize(8);

		// set default location as user's current location
		mapViewer.setZoom(7);
		mapViewer.setAddressLocation(MapController.locationsArray[22]);

		// Display the viewer in Panel
		mapViewer.setBounds(80, 66, 1250, 661);
		mapViewer.setLayout(null);
		add(mapViewer);

		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);
		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));

		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer);
		SelectionPainter sp = new SelectionPainter(sa);
		mapViewer.addMouseListener(sa);
		mapViewer.addMouseMotionListener(sa);
		mapViewer.setOverlayPainter(sp);

		// Create location pins for all the University locations
		waypoints = new HashSet<SwingWaypoint>(
				Arrays.asList(new SwingWaypoint("Algoma University", MapController.locationsArray[0], 0),
						new SwingWaypoint("Brock University", MapController.locationsArray[1], 1),
						new SwingWaypoint("Carleton University ", MapController.locationsArray[2], 2),
						new SwingWaypoint("University of Guelph ", MapController.locationsArray[3], 3),
						new SwingWaypoint("Lakehead University ", MapController.locationsArray[4], 4),
						new SwingWaypoint("Laurentian University ", MapController.locationsArray[5], 5),
						new SwingWaypoint("McMaster University ", MapController.locationsArray[6], 6),
						new SwingWaypoint("Nipissing University ", MapController.locationsArray[7], 7),
						new SwingWaypoint("OCAD University ", MapController.locationsArray[8], 8),
						new SwingWaypoint("Ontario Tech University ", MapController.locationsArray[9], 9),
						new SwingWaypoint("University of Ottawa ", MapController.locationsArray[10], 10),
						new SwingWaypoint("Queen's University ", MapController.locationsArray[11], 11),
						new SwingWaypoint("University of Toronto ", MapController.locationsArray[12], 12),
						new SwingWaypoint("University of Toronto Scarborough ", MapController.locationsArray[13], 13),
						new SwingWaypoint("University of Toronto Mississauauga ", MapController.locationsArray[14], 14),
						new SwingWaypoint("Toronto Metropolitan University ", MapController.locationsArray[15], 15),
						new SwingWaypoint("Trent University ", MapController.locationsArray[16], 16),
						new SwingWaypoint("University of Waterloo ", MapController.locationsArray[17], 17),
						new SwingWaypoint("Western University ", MapController.locationsArray[18], 18),
						new SwingWaypoint("Wilfred Laurier University ", MapController.locationsArray[19], 19),
						new SwingWaypoint("University of Windsor ", MapController.locationsArray[20], 20),
						new SwingWaypoint("York University ", MapController.locationsArray[21], 21),
						new SwingWaypoint("CURRENT LOCATION", MapController.locationsArray[22])));

		// Set the overlay painter
		WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
		swingWaypointPainter.setWaypoints(waypoints);
		mapViewer.setOverlayPainter(swingWaypointPainter);

		// add locations onto map
		addLocation(false);

		// set help button on map
		help.setBackground(new Color(255, 85, 85));
		help.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		help.setBorderPainted(false);
		help.setOpaque(true);
		help.setBounds(850, 11, 133, 43);
		mapViewer.add(help);

		// set bookmark button on map
		bookmark.setBackground(new Color(0, 164, 82));
		bookmark.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		bookmark.setBounds(430, 11, 191, 43);
		bookmark.setBorderPainted(false);
		bookmark.setOpaque(true);
		mapViewer.add(bookmark);

		// set distance radius options on map
		distanceRadius.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		distanceRadius.setBackground(new Color(125, 153, 183));
		distanceRadius.setBounds(220, 11, 198, 43);
		mapViewer.add(distanceRadius);

		// set unbookmark check boxes
		unbookmark.setBackground(new Color(255, 255, 153));
		unbookmark.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 28));
		unbookmark.setBounds(640, 11, 191, 43);
		unbookmark.setBorderPainted(false);
		unbookmark.setOpaque(true);
		mapViewer.add(unbookmark);

		// set title on Panel
		title.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 50));
		title.setBounds(576, 0, 283, 73);
		add(title);

		// set visibility of Panel
		setVisible(true);

	}

	// getters and setters
	public JXMapViewer getMapViewer() {
		return mapViewer;
	}

	public void setMapViewer(JXMapViewer mapViewer) {
		this.mapViewer = mapViewer;
	}

	public JButton getHelp() {
		return help;
	}

	public void setHelp(JButton help) {
		this.help = help;
	}

	public JButton getBookmark() {
		return bookmark;
	}

	public void setBookmark(JButton bookmark) {
		this.bookmark = bookmark;
	}

	public JComboBox getDistanceRadius() {
		return distanceRadius;
	}

	public void setDistanceRadius(JComboBox distanceRadius) {
		this.distanceRadius = distanceRadius;
	}

	public String[] getDistanceChoice() {
		return distanceChoice;
	}

	public void setDistanceChoice(String[] distanceChoice) {
		this.distanceChoice = distanceChoice;
	}

	public JButton getUnbookmarked() {
		return unbookmark;
	}

	public void setUnbookmarked(JButton unbookmark) {
		this.unbookmark = unbookmark;
	}

	// This method adds each location onto the map
	public void addLocation(boolean radius) {

		// used to check for current location
		int index = -1;

		// checks to see if it is adding the locations onto map for first time or adding
		// after radius choice is picked

		// if radius distances are picked
		if (radius) {

			for (SwingWaypoint w : waypoints) {

				// checks to see which location we are are on
				for (int counter = 0; counter < MapController.universityArray.length; counter++) {

					if (w.getName().contains(MapController.universityArray[counter].getName())) {

						index = counter;
					}

				}

				// set all buttons to false
				w.getButton().setEnabled(false);

				// if we are adding the user's current location, always set it to true no matter
				// the radius selection
				if (index < 0)
					w.getButton().setEnabled(true);

				// check to see if university distance meets radius requirements
				else if (MapController.rangeDistance[index]) {

					w.getButton().setEnabled(true);

				}

				// set index back to -1 to check for current location
				index = -1;
			}

		}

		// else if adding all locations for the first time
		else {

			for (SwingWaypoint w : waypoints) {

				// add every button onto map
				mapViewer.add(w.getButton());

			}

		}

		// reinitialize all elements in array to false
		for (int element = 0; element < MapController.rangeDistance.length; element++) {

			MapController.rangeDistance[element] = false;
		}

	}

}