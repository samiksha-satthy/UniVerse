package model;

import org.jxmapviewer.viewer.GeoPosition;

import controller.MapController;

//This class calculates distance between two location points using their latitudes and longitudes 
public class CalculateDistance {

	private GeoPosition currLocation;
	private int minimum;
	private int maximum;

	// Constructor 1 - calculates distance between current location and all
	// universities based on radius range picked
	public CalculateDistance(GeoPosition currLocation, int minimum, int maximum) {

		this.currLocation = currLocation;
		this.minimum = minimum;
		this.maximum = maximum;
		setUniDistance();

	}

	// Constructor 2 - calculates the distance between the current location and a
	// university
	public CalculateDistance(GeoPosition currLocation, int index) {

		this.currLocation = currLocation;
		calculate(index);
	}

	// This method calculates the distance between current location and one
	// particular university
	public double calculate(int index) {

		return haversine(MapController.locationsArray[index].getLatitude(),
				MapController.locationsArray[index].getLongitude(), currLocation.getLatitude(),
				currLocation.getLongitude());

	}

	// add values to university distance array
	public void setUniDistance() {

		// for each index in the array,
		for (int index = 0; index < MapController.uniDistance.length; index++) {

			// caluclate the distance between the current location and the university
			// loaction at that index
			double distance = haversine(MapController.locationsArray[index].getLatitude(),
					MapController.locationsArray[index].getLongitude(), currLocation.getLatitude(),
					currLocation.getLongitude());

			// save the distance value returned from formula in the same index in university
			// distance array
			MapController.uniDistance[index] = distance;

			// based on the radius range picked

			// if maximum value sent is 0, make sure that we are only checking that the
			// distance is more than minimum value
			if (maximum == 0) {

				if (distance >= minimum) {

					// if it is, set that index in the array to true
					MapController.rangeDistance[index] = true;
				}

			}

			// else check that the distance is in between the minimum and maximum values
			else {

				if (distance >= minimum && distance <= maximum) {

					// if it is, set that index in the array to true
					MapController.rangeDistance[index] = true;
				}

			}

		}

	}

	// This method calculates the distance between one location point and other
	// using latitude and longitude
	public double haversine(double lat1, double lon1, double lat2, double lon2) {

		// distance between latitudes and longitudes
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		// convert to radians
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// apply formulae
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;

	}

}
