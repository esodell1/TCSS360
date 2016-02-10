package Project;

import java.util.List;

public enum State {
	LOGIN {
		State nextState(UserInterface ui, Control ctrl) {
			String input = ui.detailsString("Login", "Enter an email address:");
			int result = ctrl.login(input);
			if(result >= 0) {
				User currentUser = ctrl.getCurrentUser();
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
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			List<String> options = currentUser.getMenuOptions(MAIN);
			int command = ui.optionsInt("Main Menu", options);
			return currentUser.getNextState(MAIN, command);
		}
	},
	VIEW_ALL_JOBS {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			List<String> opts = ctrl.getAllJobs();
			int command = ui.optionsInt("View All Jobs", opts);
			return currentUser.getNextState(VIEW_ALL_JOBS, command);
		}
	},
	SEARCH_VOLUNTEERS {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			
			return MAIN;
		}
	},
	LOGOUT {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			
			return MAIN;
		}
	};
	
	abstract State nextState(UserInterface ui, Control ctrl);

}
