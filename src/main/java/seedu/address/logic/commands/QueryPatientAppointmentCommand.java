package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsPatientPredicate;

/**
 * Represents a command for querying appointments for a specific patient.
 * The command searches for appointments of patients whose NRICs/names contain any of the specified keywords
 * (case-insensitive) and displays them as a list with index numbers.
 */
public class QueryPatientAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "apptforpatient";
    private static final Logger logger = Logger.getLogger(QueryPatientAppointmentCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments of patients whose "
            + "nrics contain any of the specified keywords (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...(Keywords have to be the "
            + "exact NRICs of the patient(s) in question)\n"
            + "Example: " + COMMAND_WORD + " alice bob T1234567A S7654321A";

    private final AppointmentContainsPatientPredicate predicate;

    public QueryPatientAppointmentCommand(AppointmentContainsPatientPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.log(Level.INFO, "Executing QueryPatientAppointmentCommand");
        model.updateFilteredAppointmentList(predicate);
        int numberOfAppointments = model.getFilteredAppointmentList().size();
        logger.log(Level.INFO, "Number of appointments found: " + numberOfAppointments);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, numberOfAppointments));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QueryPatientAppointmentCommand)) {
            return false;
        }

        QueryPatientAppointmentCommand otherQueryPatientAppointmentCommand = (QueryPatientAppointmentCommand) other;
        return predicate.equals(otherQueryPatientAppointmentCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
