package Scene;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Miscellaneous.Constants;
import Miscellaneous.Profile;

public class LogInScene extends Scene {

	private String fileName = "src/Miscellaneous/usernames";
	File file = new File(fileName);
	private String requestedUsername = Window.userInput.getAttemptedText();
	// private String requestedPassword;
	public static boolean isSigningUp = false;
	private static boolean clicked = false;
	private static boolean done = false;

	private static LogInScene scene;

	public LogInScene() {

	}

	public static void deleteScene(Panel p) {
		p.removeAll();
		LogInScene.scene = null;
	}

	public static LogInScene getScene() {
		if (LogInScene.scene == null) {
			LogInScene.scene = new LogInScene();
		}

		return LogInScene.scene;
	}

	public void checkUsernameTaken() {
		if (isSigningUp && clicked) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				if (getListSize(fileName) == 0) {
					System.out.println("should make a new profile");
					Window.profile = new Profile(getListSize(fileName), Window.userInput.getAttemptedText(),
							Window.passInput.getAttemptedText(), 0);
					writeToFile(fileName, Window.userInput.getAttemptedText(), Window.passInput.getAttemptedText(), 0);
					isSigningUp = false;
					done = true;
				} else {
					for (int i = 1; i < getListSize(fileName); i++) {
						if (getUsername(i).equals(requestedUsername)) {
							System.out.println("Username Taken");
							// only if they are trying to make a new profile
							// show that username cannot be taken
							// else log in
						} else {
							Window.profile = new Profile(getListSize(fileName), Window.userInput.getAttemptedText(),
									Window.passInput.getAttemptedText(), 0);
							writeToFile(fileName, Window.userInput.getAttemptedText(),
									Window.passInput.getAttemptedText(), 0);
						}
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loggingIn() {
		if (!done) {
			if (!isSigningUp && clicked) {
				String colon = ":";
				String user = Window.userInput.getAttemptedText();
				String pass = Window.passInput.getAttemptedText();
				try {
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					int num = 0;
					String line = br.readLine();
					while (line != null) {
						int lineSize = line.length() - 1;
						int count = 0;
						int i = 0;
						while (!line.substring(i, i + 1).equals(colon)) {
							i++;
							count++;
						}
						String user1 = line.substring(0, count);
						String pass1 = line.substring(count + 1, lineSize);
						if (user.equals(user1) && pass.equals(pass1)) {
							Window.profile = getProfile(num);
							break;
						} else {
							num++;
							line = br.readLine();
						}
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Profile getProfile(int line) {
		return new Profile(line, getUsername(line), getPassword(line), getScore(line));
	}

	private String getUsername(int line) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String str = br.readLine();
			while (line > 0) {
				str = br.readLine();
				line--;
			}
			System.out.println(str);
			int count = 0;
			int i = 0;
			while (!str.substring(i, i + 1).equals(":")) {
				i++;
				count++;
			}
			String user1 = str.substring(0, count);
			br.close();
			return user1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getPassword(int line) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String str = br.readLine();
			while (line > 0) {
				str = br.readLine();
				line--;
			}
			int count = 0;
			int i = 0;
			while (!str.substring(i, i + 1).equals(":")) {
				i++;
				count++;
			}
			i++;
			int temp = 0;
			while (!str.substring(i, i + 1).equals(";")) {
				i++;
				temp++;
			}
			String pass = str.substring(count + 1, count + temp + 1);
			br.close();
			return pass;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int getScore(int line) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String str = br.readLine();
			while (line > 0) {
				str = br.readLine();
				line--;
			}
			int lineSize = str.length();
			int i = 0;
			while (!str.substring(i, i + 1).equals(";")) {
				i++;
			}
			String score = str.substring(i + 1, lineSize);
			int sc = Integer.parseInt(score);
			br.close();
			return sc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void writeToFile(String file, String username, String password, int score) {
		try {
			FileWriter fw = new FileWriter(file);
			if(getListSize(file) == 0) {
				fw.write(username + ":" + password + ";" + score);
			}else {
				fw.write("\n" + username + ":" + password + ";" + score);
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getListSize(String file) {
		int i = 1;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				i++;
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void init() {

	}

	@Override
	public void update() {
		checkUsernameTaken();
		loggingIn();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Constants.BACKGROUND_COLOR);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

	public static boolean isClicked() {
		return clicked;
	}

	public static void setClicked(boolean clicked) {
		LogInScene.clicked = clicked;
	}

}
