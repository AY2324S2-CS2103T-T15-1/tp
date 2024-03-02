package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.Indices;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "or alternatively\n"
            + "Parameters: INDEX (must be a positive integers separated by commas) \n"
            + "Example: " + COMMAND_WORD + " 1,2,3\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;
    private final Indices targetIndices;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetIndices = null;
    }

    public DeleteCommand(Indices targetIndices) {
        this.targetIndex = null;
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        } else { // targetIndices != null
            Set<Integer> setOfIntegers = new HashSet<Integer>();
            for (int i = 0; i < targetIndices.getZeroBased().length; i++) {
                if (targetIndices.getZeroBased()[i] >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
                setOfIntegers.add(i);
            }
            Person[] personsToDelete = new Person[setOfIntegers.size()];
            int counter = 0;
            for (int num : setOfIntegers) {
                Person personToDelete = lastShownList.get(num);
                personsToDelete[counter] = personToDelete;
                model.deletePerson(personToDelete);
                counter++;
            }
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personsToDelete)));
        }


    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex)
                && targetIndices.equals(otherDeleteCommand.targetIndices);
    }

    @Override
    public String toString() {
        if (targetIndex != null) {
            return new ToStringBuilder(this)
                    .add("targetIndex", targetIndex)
                    .toString();
        } else if (targetIndices != null) {
            return new ToStringBuilder(this)
                    .add("targetIndices", targetIndices)
                    .toString();
        } else {
            assert(false);
            return "ERROR";
        }
    }
}
