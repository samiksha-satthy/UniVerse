package controller;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import model.CalculateDistance;
import model.SelectionAdapter;
import model.SelectionPainter;
import model.SwingWaypoint;
import model.SwingWaypointOverlayPainter;
import model.University;
import model.UniversityFileInput;
import view.MapViewerFrame;

//This class controls the entire program 
public class MapController implements ActionListener {

	// this array stores all the universities + their specific info
	public static University[] universityArray = new University[22];

	// this array is used to determine which universities are within the distance
	// range the user selects
	public static boolean[] rangeDistance = new boolean[23];

	// this array stores the distance between user's current location and each
	// university
	public static double[] uniDistance = new double[22];

	// this array stores all university locations + user's current location
	public static GeoPosition[] locationsArray = new GeoPosition[23];

	// this ArrayList stores the user's top 5 universities
	public static int[] bookmarked = new int[5];

	// initialize user's current location (testing purposes)
	public static GeoPosition currentLocation = new GeoPosition(43.8971, -79.2786);

	// holds university names so that users can click which ones they want to
	// unbookmark
	JRadioButton[] uniBookmarks = new JRadioButton[5];
	ButtonGroup checkBoxesUni = new ButtonGroup();

	// instructions for how to use the map
	String instructions = "Welcome to the University Interactive Map!"
			+ "To zoom in and out on the map, use your mousepad. As you can see, most University locations in Ontario are here!\n"
			+ "To learn more about a specific university, click on the icon where you can view the general and other important info!\n"
			+ "Your current location is the same as the one in your student profile, and can be identified by the red location pin. "
			+ "All of the distances between universities are based on your current location\n"
			+ "To view universities within a certain radius, feel free to click on the options on the top left of the map.\n"
			+ "To view bookmarked universities, click on the button in the middle where you will be able to see all of the University Icons you have bookmarked.\n"
			+ "To unbookmark universities, click the 'unbookmark' button. You can bookmark a MAXIMUM of 5 universities.\n"
			+ "If you would like to see these instructions again, click the 'HELP' button on the top right of the screen. Enjoy exploring the map!";

	// create instance of the map frame
	public MapViewerFrame mapFrame;

	public MapController() {

		// fills University array
		new UniversityFileInput();

		// initialize the instance
		mapFrame = new MapViewerFrame();

		// initialize each element in bookmark array to -1
		for (int index = 0; index < bookmarked.length; index++) {

			bookmarked[index] = -1;
		}

		// initialize each element in array to false
		for (int index = 0; index < rangeDistance.length; index++) {

			rangeDistance[index] = false;
		}

		// ActionListeners
		mapFrame.getMapPanel().getHelp().addActionListener(this);
		mapFrame.getMapPanel().getBookmark().addActionListener(this);
		mapFrame.getMapPanel().getDistanceRadius().addActionListener(this);
		mapFrame.getMapPanel().getUnbookmarked().addActionListener(this);

	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {

		// if help button is clicked
		if (e.getSource() == mapFrame.getMapPanel().getHelp()) {

			// display instructions
			JOptionPane.showMessageDialog(mapFrame, instructions);

		}

		// if the choicebox is pressed
		else if (e.getSource() == mapFrame.getMapPanel().getDistanceRadius()) {

			// determine which option is picked, and based on that, pass the appropriate
			// minimum and maximum values to the Class
			if (mapFrame.getMapPanel().getDistanceRadius()
					.getSelectedItem() == mapFrame.getMapPanel().getDistanceChoice()[0])
				new CalculateDistance(locationsArray[22], 1, 30);

			else if (mapFrame.getMapPanel().getDistanceRadius()
					.getSelectedItem() == mapFrame.getMapPanel().getDistanceChoice()[1])
				new CalculateDistance(locationsArray[22], 1, 60);

			else if (mapFrame.getMapPanel().getDistanceRadius()
					.getSelectedItem() == mapFrame.getMapPanel().getDistanceChoice()[2])
				new CalculateDistance(locationsArray[22], 1, 90);

			else
				new CalculateDistance(locationsArray[22], 1, 0);

			// call this method to readd all locations onto the map
			mapFrame.getMapPanel().addLocation(true);

		}

		// if bookmark button is picked
		else if (e.getSource() == mapFrame.getMapPanel().getBookmark()) {

			// create JPanel
			JPanel bookmarkedPane = new JPanel();

			// add all images to the JPanel
			for (int i = 0; i < bookmarked.length; i++) {

				// check to see if they have bookmarked a university and add all of that info
				// onto the panel
				if (bookmarked[i] != -1) {
					JLabel image = new JLabel(universityArray[bookmarked[i]].getLogo());
					image.setSize(60, 60);
					bookmarkedPane.add(image);

				}

				// if nothing bookmarked, move onto next iteration
				else
					continue;

			}

			// display in a JOptionPane
			JOptionPane.showMessageDialog(mapFrame, bookmarkedPane, "Bookmarked Universities", 1);

		}

		// if unbookmark button is clicked
		else if (e.getSource() == mapFrame.getMapPanel().getUnbookmarked()) {

			// create JPanel
			JPanel unbookmarkedPane = new JPanel();

			for (int index = 0; index < uniBookmarks.length; index++) {

				// adds new radio buttton and adds it to group
				uniBookmarks[index] = new JRadioButton("");
				uniBookmarks[index].setBounds(0, 20 + index, 10, 10);
				checkBoxesUni.add(uniBookmarks[index]);

				// if there is bookmarked university, add name to panel
				if (bookmarked[index] != -1) {
					uniBookmarks[index].setText(universityArray[bookmarked[index]].getName());
					unbookmarkedPane.add(uniBookmarks[index]);
				}

				// or else continue to next iteration
				else
					continue;

			}

			// choices on JDialog
			Object[] options = { "OK" };

			// displays general information of university
			int button = JOptionPane.showOptionDialog(null, unbookmarkedPane, "Unbookmark Universities",
					JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			if (button == 0) {

				// checks to see which radio button is picked
				for (int index = 0; index < uniBookmarks.length; index++) {

					if (uniBookmarks[index].isSelected() && uniBookmarks[index] != null) {

						// removes the university from both bookmark and unbookmark panels
						uniBookmarks[index] = null;
						bookmarked[index] = -1;
					}

				}

			}

		}

	}

}
