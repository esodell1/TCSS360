package Project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String input = ui.detailsString("Login", "Enter an email address:");
			int result = ctrl.login(input);
			if(result >= 0) {
				User currentUser = ctrl.getCurrentUser();
				ui.setUser(currentUser);
				return MAIN;
			}
			else {
				return LOGIN;
			}
		}
	},
	MAIN {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			User currentUser = ctrl.getCurrentUser();
			List<String> options = currentUser.getMenuOptions(MAIN);
			int command = ui.optionsInt("Main Menu", options);
			return currentUser.getNextState(MAIN, command);
		}
	},
	VIEW_ALL_JOBS {
		@Override
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
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			ctrl.jobEdit = false;
			User currentUser = ctrl.getCurrentUser();
			String job = ctrl.getCurrentJob().toString();
			List<String> opts = currentUser.getMenuOptions(VIEW_JOB);
			int command = ui.detailsInt("View Job", job, opts);
			return currentUser.getNextState(VIEW_JOB, command);
		}
	},
	DELETE_JOB {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
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
				ctrl.updatePersistence();
				return VIEW_ALL_JOBS;
			}
		}
		
	},
	CREATE_JOB {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			if (!ctrl.jobEdit && !ctrl.allowedJobCount()) {
				ctrl.userMessage = "You have reached the maximum number of jobs (30).";
				return ERROR_MSG;
			}
			String name = ui.detailsString(title, "Please enter a job name: ");
			if (!ctrl.jobEdit) ctrl.setCurrentJob(new Job());
			ctrl.getCurrentJob().setName(name);
			return (ctrl.jobEdit) ? VIEW_JOB : CREATE_JOB_2;
		}
	},
	CREATE_JOB_2 {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			String name = ui.detailsString(title, "Please enter a job description: ");
			ctrl.getCurrentJob().setDescription(name);
			return (ctrl.jobEdit) ? VIEW_JOB : CREATE_JOB_3;
		}
	},
	CREATE_JOB_3 {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			Administrator currentUser = (Administrator) ctrl.getCurrentUser();
			List<String> opts = currentUser.getParkNames();
			int park = ui.optionsInt(title, opts);
			ctrl.setCurrentPark(park - 1);
			ctrl.getCurrentJob().setPark(ctrl.getCurrentPark());
			return (ctrl.jobEdit) ? VIEW_JOB : CREATE_JOB_4;
		}
	},
	CREATE_JOB_4 {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			String date = ui.detailsString(title, 
					"Please enter a job start date and time: MM/DD/YYYY HH:MM");
			String[] tokens = date.split("/| |:");
			if(tokens.length != 5) {
				return CREATE_JOB_4;
			} 
			Calendar start = new GregorianCalendar(Integer.parseInt(tokens[2]), 
					Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]),
					Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
			if (ctrl.isJobPast(start)) {
				ctrl.userMessage = "Must schedule jobs for future dates, and no more than 3 months in advance.";
				return ERROR_MSG;
			}
			ctrl.getCurrentJob().setStartDate(start);
			return CREATE_JOB_5;
		}
	},
	CREATE_JOB_5 {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			String date = ui.detailsString(title, 
					"Please enter a job end date and time: MM/DD/YYYY HH:MM");
			String[] tokens = date.split("/| |:");
			if(tokens.length != 5) {
				return CREATE_JOB_5;
			}
			Calendar start = ctrl.getCurrentJob().getStartDate();
			Calendar end = new GregorianCalendar(Integer.parseInt(tokens[2]), 
					Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]),
					Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
			if (!ctrl.isWeekOpen(start)) {
				ctrl.userMessage = "This week is already full (5).";
				return ERROR_MSG;
			}  else if (ctrl.isJobPast(end)) {
				ctrl.userMessage = "Must schedule jobs for future dates, and no more than 3 months in advance.";
				return ERROR_MSG;
			} else if (!ctrl.isDurationAllowed(start, end)) {
				ctrl.userMessage = "That job duration is too long (Max 2 days).";
				return ERROR_MSG;
			}
			return (ctrl.jobEdit) ? VIEW_JOB : CREATE_JOB_6;
		}
	},
	CREATE_JOB_6 {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String title = (ctrl.jobEdit) ? "Edit job details" : "Create a New Job";
			String date = ui.detailsString(title, "Please enter a number for each required work load: H/M/L");
			String[] tokens = date.split("/");
			if(tokens.length != 3) {
				return CREATE_JOB_5;
			}
			ctrl.getCurrentJob().high = Integer.parseInt(tokens[0]);
			ctrl.getCurrentJob().medium = Integer.parseInt(tokens[1]);
			ctrl.getCurrentJob().low = Integer.parseInt(tokens[2]);
			return (ctrl.jobEdit) ? VIEW_JOB : CONFIRM_JOB;
		}
	},
	CONFIRM_JOB {
		@Override
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
				ctrl.getCurrentPark().addJob(ctrl.getCurrentJob());
				ctrl.getCurrentUser().getMyJobs().add(ctrl.getCurrentJob());
				ctrl.saveCurrentJob();
				return VIEW_JOB;
			}
		}
	},
	SEARCH_LAST_NAME {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String name = ui.detailsString("User Search", "Search for a volunteer by Last Name");
			ctrl.searchUsers("last", name);
			String details = "\n";
			if (ctrl.search.size() > 0) {
				for (User vol : ctrl.search) {
					details = details + "\t" + vol.getLastName() + ", " + vol.getFirstName()
						+ " \t" + vol.getEmail() + "\n";
				}
			} else {
				details = details + "\tThere we no matches from the search.\n";
			}
			ctrl.search.clear();
			List<String> options = new ArrayList<String>();
			options.add("Search again");
			options.add("Return to Main Menu");
			int command = ui.detailsInt("Search Results", details, options);
			if (command == 1) return SEARCH_LAST_NAME;
			else return MAIN;
		}
	},
	JOB_SIGNUP{
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			// Check if job is past:
			if (ctrl.getCurrentJob().getStartDate().compareTo(Calendar.getInstance()) < 0) {
				ctrl.userMessage = "Cannot sign up for a job that has passed.";
				return ERROR_MSG;
			}
			// Check if the volunteer is already signed up on this day:
			if (!ctrl.isDayOpen(ctrl.getCurrentJob().getStartDate())) {
				ctrl.userMessage = "Cannot sign up for multiple jobs on the same day.";
				return ERROR_MSG;
			}
			String job = ctrl.getCurrentJob().toString();
			job = job + "\n\tWhat work grade would you like to sign up for?";
			List<String> opts = ctrl.getCurrentUser().getMenuOptions(JOB_SIGNUP);
			int command = ui.detailsInt("Job Sign Up", job, opts);
			// TODO Push this logic somewhere more appropriate:
			if (command == 4) return VIEW_JOB;
			else if (command == 5) return MAIN;
			else {
				boolean success = false;
				if (command == 1) success = ctrl.getCurrentJob()
						.addVolunteer((Volunteer)ctrl.getCurrentUser(), WorkLoad.LOW);
				else if (command == 2) success = ctrl.getCurrentJob()
						.addVolunteer((Volunteer)ctrl.getCurrentUser(), WorkLoad.MEDIUM);
				else success = ctrl.getCurrentJob()
						.addVolunteer((Volunteer)ctrl.getCurrentUser(), WorkLoad.HIGH);
				
				if (success) {
					ctrl.getCurrentUser().getMyJobs().add(ctrl.getCurrentJob());
					ctrl.updatePersistence();
					return VIEW_JOB;
				} else {
					ctrl.userMessage = "This job is full based on your selected work grade.";
					return ERROR_MSG;
				}
			}
		}
		
	},
	VIEW_JOB_VOL {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			String str = "Volunteers signed up for: " + ctrl.getCurrentJob().getName() + "\n";
			List<String> opts = new ArrayList<String>();
			opts.add("Return to job view");
			opts.add("Return to jobs list");
			opts.add("Return to Main Menu");
			int command = ui.detailsInt("View Job", str + 
					ctrl.getCurrentJob().getEnrolledVolunteersString(), opts);
			if (command == 1) return VIEW_JOB;
			else if (command == 2) return VIEW_ALL_JOBS;
			else return MAIN;
		}
	},
	LOGOUT {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			ctrl.setCurrentUser(-1);
			ui.setUser(null);
			return LOGIN;
		}
	},
	MY_JOBS {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			List<String> jobs = ctrl.getCurrentUser().getMyJobNames();
			if (jobs.size() > 0) {
				int size = jobs.size();
				jobs.add("Return to Main Menu");
				int command = ui.optionsInt("My Jobs", jobs);
				if (command <= size) {
					ctrl.setCurrentJob(ctrl.getCurrentUser().getMyJobs().get(command - 1));
					return VIEW_JOB;
				} else {
					return MAIN;
				}
			} else {
				List<String> opts = new ArrayList<String>();
				opts.add("View all jobs");
				opts.add("Return to Main Menu");
				int command = ui.detailsInt("My Jobs", "You don't have any jobs.", opts);
				if (command == 1) {
					return VIEW_ALL_JOBS;
				} else {
					return MAIN;
				}
			}
		}
	},
	EDIT_JOB_DETAILS {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			List<String> opts = new ArrayList<String>();
			String details = ctrl.getCurrentJob().toString() + "What would you like to change?";
			opts.add("Name");
			opts.add("Description");
			opts.add("Park");
			opts.add("Date/Time");
			opts.add("Workload");
			opts.add("Return to job");
			opts.add("Return to Main Menu");
			int command = ui.detailsInt("Edit Job Details", details, opts);
			if (command == 6) return VIEW_JOB;
			else if (command == 7) return MAIN;
			else {
				ctrl.jobEdit = true;
				if(command == 1) return CREATE_JOB;
				else if(command == 2) return CREATE_JOB_2;
				else if(command == 3) return CREATE_JOB_3;
				else if(command == 4) return CREATE_JOB_4;
				else return CREATE_JOB_6;
			}
		}
			
	},
	ERROR_MSG {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			List<String> options = new ArrayList<String>();
			options.add("Return to Main Menu");
			ui.detailsInt("Error", "\n\t" + ctrl.userMessage + "\n\n", options);
			ctrl.userMessage = null;
			return MAIN;
		}
	},
	SUCCESS_MSG {
		@Override
		State nextState(UserInterface ui, Control ctrl) {
			List<String> options = new ArrayList<String>();
			options.add("Return to Main Menu");
			ui.detailsInt("Success", "\n\t" + ctrl.userMessage + "\n\n", options);
			ctrl.userMessage = null;
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
