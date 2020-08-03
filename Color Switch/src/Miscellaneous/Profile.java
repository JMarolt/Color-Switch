package Miscellaneous;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Profile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int ID;
	private String username, password;
	private int score;
	private final String DATA_FILE = "src/Miscellaneous/profile";
	private boolean loggedIn = false;
	
	private static Profile profile;

	public Profile(int ID, String username, String password, int score) {
		this.ID = ID;
		this.username = username;
		this.password = password;
		this.score = score;
	}
	
	public Profile getUser() {
		if(loggedIn && Profile.profile == null) {
			Profile.profile = new Profile(ID, username, password, score);
		}
		return Profile.profile;
	}
	
	public void save(Profile profile) {
		try {
			FileOutputStream f = new FileOutputStream(new File(DATA_FILE));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(profile);

			o.close();
			f.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Profile loadProfile() {
		try {
			FileInputStream f = new FileInputStream(new File(DATA_FILE));
			ObjectInputStream o = new ObjectInputStream(f);
			Profile temp = (Profile) o.readObject();

			o.close();
			f.close();
			return temp;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}
