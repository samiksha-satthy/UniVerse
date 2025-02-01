package model;
import javax.swing.ImageIcon;

//This is the University Object Class
public class University {

	// fields
	private String name;
	private String info;
	private String image;
	private ImageIcon logo;
	private String website;
	private int programs;

	// constructor
	public University(String name, String info, String image, String website, int programs) {
		this.name = name;
		this.info = info;
		this.image = image;
		logo = new ImageIcon(image);
		this.website = website;
		this.programs = programs;

	}

	// getters and setters
	public int getPrograms() {
		return programs;
	}

	public void setPrograms(int programs) {
		this.programs = programs;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ImageIcon getLogo() {
		return logo;
	}

	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}

}
