package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import controller.MapController;

//This class reads data from csv file into application and fills it into university array
public class UniversityFileInput {

	// constructor method
	public UniversityFileInput() {

		fillUniversity();

	}

	// This method fills the array with laptop characteristics
	private static void fillUniversity() {

		// tries to read file into program
		try {

			// stores file as Scanner Object
			Scanner inputFile = new Scanner(new File("university.csv"));

			inputFile.useDelimiter(",|\r\n");

			// loops through each element in file
			for (int index = 0; index < MapController.universityArray.length; index++) {

				// reads one line from file and converts it to corresponding type in University
				// Object Class
				String name = inputFile.next();
				String info = inputFile.next();
				String image = inputFile.next();
				String website = inputFile.next();
				int programs = inputFile.nextInt();

				// fills the university array with elements
				MapController.universityArray[index] = new University(name, info, image, website, programs);

			}

			// closes file
			inputFile.close();

			// catches exception and prints out corresponding text
		} catch (FileNotFoundException e) {

			System.out.println("File Error");

		}

	}

}
