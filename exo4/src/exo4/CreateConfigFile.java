package exo4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateConfigFile {

	public void createPropertiesFile(String driver, String url, String login, String password) throws IOException {
		try {
			File file = new File("credentials.properties");
			FileWriter fr;
			fr = new FileWriter(file, false);

			BufferedWriter br = new BufferedWriter(fr);
			
			br.write("driver = "+driver+"\n");
			br.write("url = "+url+"\n");
			br.write("login = "+login+"\n");
			br.write("password = "+password+"\n");
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
