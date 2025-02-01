package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class creates the navigation bar that is shared with all the frames 
public class NavigationBarPanel extends JPanel implements ActionListener {

	private JButton homeBtn, browseProgramsBtn, universityMapBtn, surveyBtn, browseJobsBtn, profileBtn;

	public NavigationBarPanel() {

		setOpaque(false);
		setPreferredSize(new Dimension(1440, 115));

		// create buttons
		homeBtn = createButton(null, "images/logoBtn.png");
		browseProgramsBtn = createButton("Browse Programs", null);
		universityMapBtn = createButton("University Map", null);
		surveyBtn = createButton("Survey", null);
		browseJobsBtn = createButton("Browse Jobs", null);
		profileBtn = createButton(null, "images/profileBtn.png");

		// set font size for text buttons
		Font buttonFont = new Font("Arial", Font.BOLD, 18);
		Color fontColor = Color.decode("#587494"); // Set your desired hex color code

		browseProgramsBtn.setFont(buttonFont);
		browseProgramsBtn.setForeground(fontColor);
		universityMapBtn.setFont(buttonFont);
		universityMapBtn.setForeground(fontColor);
		surveyBtn.setFont(buttonFont);
		surveyBtn.setForeground(fontColor);
		browseJobsBtn.setFont(buttonFont);
		browseJobsBtn.setForeground(fontColor);
		profileBtn.setFont(buttonFont);
		profileBtn.setForeground(fontColor);

		// set layout manager and space out buttons
		setLayout(new FlowLayout(FlowLayout.CENTER, 70, 10));

		// add buttons to panel
		add(homeBtn);
		add(browseProgramsBtn);
		add(universityMapBtn);
		add(surveyBtn);
		add(browseJobsBtn);
		add(profileBtn);

		// add action listeners
		homeBtn.addActionListener(this);
		browseProgramsBtn.addActionListener(this);
		universityMapBtn.addActionListener(this);
		surveyBtn.addActionListener(this);
		browseJobsBtn.addActionListener(this);
		profileBtn.addActionListener(this);
	}

	// this method creates and styles the buttons with either a text or image
	// and returns the styled button
	private JButton createButton(String text, String image) {
		JButton button;
		if (image != null) {
			ImageIcon icon = new ImageIcon(image);
			button = new JButton(text, icon);
		} else {
			button = new JButton(text);
		}

		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);

		return button;
	}

	// action performed method handles all user actions in the frame
	@Override
	public void actionPerformed(ActionEvent e) {
		// open home frame
		if (e.getSource() == homeBtn) {
			System.out.println("homoe btn clicked"); // replace

			// open browse programs
		} else if (e.getSource() == browseProgramsBtn) {
			System.out.println("browse programs btn clicked"); // replace
		}

		// open interactive map
		else if (e.getSource() == universityMapBtn) {
			System.out.println("uni map btn clicked"); // replace
		}

		// open survey button
		else if (e.getSource() == surveyBtn) {
			System.out.println("survey btn clicked"); // replace
		}

		// open browse jobs button
		else if (e.getSource() == browseJobsBtn) {
			System.out.println("browse jobs button Clicked"); // replace
		}

		// open profile button
		else if (e.getSource() == profileBtn) {
			System.out.println("profile btn blicked"); // replace
		}
	}

}
