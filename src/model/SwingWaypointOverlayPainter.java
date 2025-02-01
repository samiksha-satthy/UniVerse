package model;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import controller.MapController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

//This class initializes settings for the location buttons 
//resources: https://github.com/msteiger/jxmapviewer2

public class SwingWaypointOverlayPainter extends WaypointPainter<SwingWaypoint> {

	// location pin icon
	ImageIcon location = new ImageIcon("images/location pin.png");

	// used to keep track of what location button we are initializing
	private int counter = -1;

	// Paints the JButton
	@Override
	protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {

		// loops through each of the location pins
		for (SwingWaypoint swingWaypoint : super.getWaypoints()) {

			// add button onto map
			Point2D point = jxMapViewer.getTileFactory().geoToPixel(swingWaypoint.getPosition(), jxMapViewer.getZoom());
			Rectangle rectangle = jxMapViewer.getViewportBounds();
			int buttonX = (int) (point.getX() - rectangle.getX());
			int buttonY = (int) (point.getY() - rectangle.getY());
			JButton button = swingWaypoint.getButton();

			// loop through each university to see which one we are initailizing
			for (int index = 0; index < MapController.universityArray.length; index++) {

				if (swingWaypoint.getName().contains(MapController.universityArray[index].getName())) {

					counter = index;

				}

			}

			// if counter is more than 0, that means we are initializing the university
			// locations
			if (counter >= 0)

				// set the icon as the corresponding university's logo
				button.setIcon(MapController.universityArray[counter].getLogo());

			// otherwise we are initializing the user's current location
			else

				// set icon to location pin image
				button.setIcon(location);

			// reset counter
			counter = -1;

			// make button background transparent
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);

			// set size and location of button
			button.setSize(80, 60);
			button.setLocation(buttonX - button.getWidth() / 2, buttonY - button.getHeight() / 2);

		}
	}
}