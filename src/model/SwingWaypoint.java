package model;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import controller.MapController;

//This class controls the University location pins and what happens when you click on them 
//resources: https://github.com/msteiger/jxmapviewer2

public class SwingWaypoint extends DefaultWaypoint {

	// fields
	private final JButton button;
	private final String name;
	private final String info;
	private int index;
	private JButton location;
	private boolean tracker;

	// Constructor 1 - used to initialize University locations
	public SwingWaypoint(String name, GeoPosition coord, int index) {

		super(coord);
		this.name = name;
		this.index = index;
		info = MapController.universityArray[index].getInfo();
		button = new JButton(info.substring(0, 1));
		button.setSize(24, 24);
		button.setPreferredSize(new Dimension(24, 24));
		tracker = true;
		button.addMouseListener(new SwingWaypointMouseListener());
		button.setVisible(true);

	}

	// Constructor 2 - used to initialize user's current location
	public SwingWaypoint(String name, GeoPosition coord) {

		super(coord);
		this.name = name;
		info = null;
		button = new JButton();
		button.setSize(24, 24);
		button.setPreferredSize(new Dimension(24, 24));
		tracker = false;
		button.addMouseListener(new SwingWaypointMouseListener());
		button.setVisible(true);

	}

	// getters
	public JButton getButton() {
		return button;
	}

	public JButton getLocation() {

		return location;

	}

	public String getName() {

		return name;
	}

	private class SwingWaypointMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			// if a University location is clicked
			if (tracker)
				showUniInfo();

			// if user's current location is picked
			else

				// display this message
				JOptionPane.showMessageDialog(null, "This is your current location", "Current Location",
						JOptionPane.INFORMATION_MESSAGE);

		}

		// shows university information
		private void showUniInfo() {

			// choices on JDialog
			Object[] options = { "OK", "VIEW DISTANCE", "WEBSITE", "NUMBER OF PROGRAMS", "BOOKMARK" };

			// displays general information of university
			int button = JOptionPane.showOptionDialog(null,
					"<html><center><body><p style='width: 550px;'>" + info + "</p></body></center></html>",
					name + " General Information", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
					MapController.universityArray[index].getLogo(), options, options[0]);

			// if user clicks ok button
			if (button == 0)

				// dispose of JDialog
				JOptionPane.getRootFrame().dispose();

			// if user clicks on view distance button
			else if (button == 1) {

				// dispose of JDialog
				JOptionPane.getRootFrame().dispose();

				// calculate distance of current location and university picked by user
				CalculateDistance c1 = new CalculateDistance(MapController.locationsArray[22], index);
				double distance = c1.calculate(index);

				// choices on new JDialog
				Object[] choicesDistance = { "OK", "BACK" }; // only next

				// display message
				int fillerDistance = JOptionPane.showOptionDialog(null,
						String.format("The Approximate Distance from your current location to " + name + "is: %.2f km",
								distance),
						name + "University Distance", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
						MapController.universityArray[index].getLogo(), choicesDistance, choicesDistance[0]);

				// if ok button is clicked, dispose of JDialog
				if (fillerDistance == 0)
					JOptionPane.getRootFrame().dispose();

				// if back button is clicked, show the general info JDialog again
				else
					showUniInfo();

			}

			// if website button is clicked
			else if (button == 2) {

				// open website in browser
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {

					// try and catch any errors that pop up
					try {
						Desktop.getDesktop().browse(new URI(MapController.universityArray[index].getWebsite()));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}

				}

			}

			// if number of programs button is clicked
			else if (button == 3) {

				// dispose of JDialog box
				JOptionPane.getRootFrame().dispose();

				// options on JDialog
				Object[] choicesPrograms = { "OK", "BACK" }; // only next

				// display message
				int fillerPrograms = JOptionPane.showOptionDialog(null,
						"The number of programs at " + MapController.universityArray[index].getName() + " is: "
								+ MapController.universityArray[index].getPrograms(),
						name + "University Programs", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE,
						MapController.universityArray[index].getLogo(), choicesPrograms, choicesPrograms[0]);

				// if ok button is clicked, dispose of JDialog
				if (fillerPrograms == 0)
					JOptionPane.getRootFrame().dispose();

				// if back button is clicked, show the general info JDialog
				else
					showUniInfo();

			}

			// if bookmark button is clicked
			else if (button == 4) {

				// dispose of JDialog
				JOptionPane.getRootFrame().dispose();

				// keeps track of already bookmarked universities
				boolean duplicate = false;
				boolean space = false;
				int indexSpace = -1;

				// loops through each of the already bookmarked universities and sees if the
				// university the user wants to bookmark has already been bookmarked
				for (int bookmark = 0; bookmark < MapController.bookmarked.length; bookmark++) {

					// if it is already bookmarked, display message and change tracker
					if (MapController.bookmarked[bookmark] == index) {

						duplicate = true;
						JOptionPane.showMessageDialog(null, "University Already Bookmarked", "Bookmark Error",
								JOptionPane.ERROR_MESSAGE);

						// set it to true so that the other JDialog does not appear
						space = true;

					}

					// check to see if user has bookmarked 5 universities already or if there is
					// still space
					if (MapController.bookmarked[bookmark] == -1) {
						space = true;
						indexSpace = bookmark;
						break;

					}
				}

				// if university hasn't been bookmarked and there is space, bookmark it and
				// display the message
				if (!duplicate && space) {

					MapController.bookmarked[indexSpace] = index;
					JOptionPane.showMessageDialog(null, "University Successfully Bookmarked!", "University Bookmarked",
							JOptionPane.INFORMATION_MESSAGE);

				}

				// if there is no space, display message
				else if (!space) {

					JOptionPane.showMessageDialog(null, "No more rooom! Remove a bookmark", "Bookmark Error",
							JOptionPane.ERROR_MESSAGE);

				}

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
