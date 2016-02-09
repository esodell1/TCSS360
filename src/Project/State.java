package Project;

import java.util.ArrayList;
import java.util.List;

public enum State {
	LOGIN {
		State nextState(UserInterface ui, Control ctrl, User currentUser) {
			String input = ui.detailsString("Login", "Enter an email address:");
			int result = ctrl.login(input);
			if(result >= 0) {
				currentUser = ctrl.getCurrentUser();
				ui.setUser(	currentUser.getFirstName(), 
							currentUser.getLastName(), 
							currentUser.getUserType());
				return MAIN;
			}
			else {
				return LOGIN;
			}
		}
	},
	MAIN {
		State nextState(UserInterface ui, Control ctrl, User currentUser) {
			int command;
			List<String> options = new ArrayList<String>();
			if (currentUser instanceof Staff) {
				options.add("View all Jobs");
				options.add("Search volunteers");
				options.add("Logout");
				command = ui.optionsInt("Main Menu", options);
				if (command == 2) {
					// Search volunteers
				} else if (command == 3) {
					// Logout
				}
			} else if(currentUser instanceof Manager) {
				options.add("View all Jobs");
				options.add("View my Jobs");
				options.add("Submit new Job");
				options.add("Logout");
				command = ui.optionsInt("Main Menu", options);
			} else {
				options.add("View all Jobs");
				options.add("View my Jobs");
				options.add("Logout");
				command = ui.optionsInt("Main Menu", options);
			}
			
			if (command == 1) {
				return VIEW_ALL_JOBS;
			} else {
				return MAIN;
			}
		}
	},
	VIEW_ALL_JOBS {
		State nextState(UserInterface ui, Control ctrl, User currentUser) {
			List<String> opts = ctrl.getAllJobs();
			int command = ui.optionsInt("View All Jobs", opts);
			System.out.println(command);
			if (command < 5) {
				return LOGIN;
			} else {
				return VIEW_ALL_JOBS;
			}
		}
	};
	
	abstract State nextState(UserInterface ui, Control ctrl, User currentUser);

}
