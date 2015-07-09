import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.nnh.bean.Alert;
import org.nnh.service.GAService;

public class TEST {
	public static void main(String[] args) throws Exception {
		GAService service = new GAService("yiwangalerts1576@gmail.com", "20150706", "www-proxy.statoil.no", 80);
		System.out.println("Trying to Log in");
		boolean isLoggedIn = service.doLogin();

		if(isLoggedIn) {
			System.out.println("Logged In");
		}
		else {
			System.out.println("Cannot Log in");
			System.exit(0);
		}
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream("list.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			//Read File Line By Line
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String vendor = strLine.trim();
				
				if(vendor.length() != 0) {
					Alert alert = new Alert();
					alert.setSearchQuery(vendor);
					String id = service.createAlert(alert);
					System.out.println(id);
					System.out.println("creating new alert for vendor:" + vendor);
				}
			}

			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}	

		/*		List<Alert> alerts_list = service.getAlerts();
		System.out.println("There are " + alerts_list.size() +" alerts");
		for(int i = 0; i < alerts_list.size(); i++){
			Alert cur_alert = (Alert) alerts_list.get(i);
			System.out.println("Alert for " + cur_alert.getSearchQuery());
			System.out.println("Delivery to: " + cur_alert.getDeliveryTo());
			System.out.println("ID: " + cur_alert.getId());
			System.out.println("Language: " + cur_alert.getLanguage());
			System.out.println("====================================================");
			boolean isDeleted = service.deleteAlert(cur_alert.getId());
			if(isDeleted){
				System.out.println("successfully deleted");
			}


		}*/

		/*			
		Alert alert = new Alert();
		alert.setSearchQuery("Google");
		String id = service.createAlert(alert);
		System.out.println(id);*/

		System.out.println("Finished");
	}
}
