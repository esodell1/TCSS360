package Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This enumeration implements all of the available system states. This
 * controls what resources are requested and sends requisite output to
 * the UserInterface for console output.
 * 
 * @author Eric Odell
 * @date 2/10/2016
 */
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
			List<String> opts = ctrl.getAllJobs();
			int size = opts.size();
			opts.add("Return to Main Menu");
			int command = ui.optionsInt("View All Jobs", opts);
			if (command <= size) {
				ctrl.setCurrentJob(command - 1);
				return VIEW_JOB;
			} else {
				return MAIN;
			}
		}
	},
	VIEW_JOB {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			String job = ctrl.getCurrentJob().toString();
			List<String> opts = currentUser.getMenuOptions(VIEW_JOB);
			int command = ui.detailsInt("View Job", job, opts);
			return currentUser.getNextState(VIEW_JOB, command);
		}
	},
	DELETE_JOB {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			String job = ctrl.getCurrentJob().toString();
			job = job + "\n\n\tAre you sure you want to delete this job?\n";
			List<String> opts = new ArrayList<String>();
			opts.add("No");
			opts.add("Yes");
			int command = ui.detailsInt("Confirm Job Deletion", job, opts);
			if(command == 1) {
				return MAIN;
			} else {
				ctrl.deleteCurrentJob();
				return VIEW_ALL_JOBS;
			}
		}
		
	},
	CREATE_JOB {
		State nextState(UserInterface ui, Control ctrl) {
			String name = ui.detailsString("Create a New Job", "Please enter a job name: ");
			ctrl.setCurrentJob(new Job());
			ctrl.getCurrentJob().setName(name);
			return CREATE_JOB_2;
		}
	},
	CREATE_JOB_2 {
		State nextState(UserInterface ui, Control ctrl) {
			String name = ui.detailsString("Create a New Job", "Please enter a job description: ");
			ctrl.getCurrentJob().setDescription(name);
			return CREATE_JOB_3;
		}
	},
	CREATE_JOB_3 {
		State nextState(UserInterface ui, Control ctrl) {
			Administrator currentUser = (Administrator) ctrl.getCurrentUser();
			List<Park> opts = currentUser.getParks();
			if (opts.size() <= 0) {
				opts = ctrl.getParks();
			}
			int park = ui.optionsInt("Create a New Job", opts);
			ctrl.setCurrentPark(park - 1);
			ctrl.getCurrentJob().setPark(ctrl.getCurrentPark());
			return CREATE_JOB_4;
		}
	},
	CREATE_JOB_4 {
		State nextState(UserInterface ui, Control ctrl) {
			String date = ui.detailsString("Create a New Job", "Please enter a job date: MM/DD/YYYY ");
			String[] tokens = date.split("/");
			if(tokens.length != 3) {
				return CREATE_JOB_4;
			}
			ctrl.getCurrentJob().setDate(new Date(Integer.parseInt(tokens[2]), 
										Integer.parseInt(tokens[0]), 
										Integer.parseInt(tokens[1])));
			return CONFIRM_JOB;
		}
	},
	CONFIRM_JOB {
		State nextState(UserInterface ui, Control ctrl) {
			String job = ctrl.getCurrentJob().toString();
			List<String> opts = new ArrayList<String>();
			opts.add("No");
			opts.add("Yes");
			int command = ui.detailsInt("View Job", job, opts);
			if(command == 1) {
				ctrl.setCurrentJob(-1);
				return MAIN;
			} else {
				ctrl.saveCurrentJob();
				return VIEW_JOB;
			}
		}
	},
	SEARCH_VOLUNTEERS {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			
			return MAIN;
		}
	},
	JOB_SIGNUP{
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			
			return null;
		}
		
	},
	LOGOUT {
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			
			return MAIN;
		}
	};
	
	
	/**
	 * This abstracts out the nextState functionality, which is required to determine
	 * the next state of the system, depending on user input.
	 * @param ui - reference to the UserInterface class
	 * @param ctrl - reference to the Control class
	 * @return State - returns the next state
	 */
	abstract State nextState(UserInterface ui, Control ctrl);
}
