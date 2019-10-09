package seedu.billboard.logic.commands;

import static seedu.billboard.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.billboard.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.billboard.commons.core.Messages;
import seedu.billboard.commons.core.index.Index;
import seedu.billboard.logic.commands.exceptions.CommandException;
import seedu.billboard.model.Model;
import seedu.billboard.model.person.Person;
import seedu.billboard.model.tag.Tag;

public class AddTagCommand extends TagCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "[TAG]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "SCHOOL";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag(s) to Person: %1$s";

    private Index index;
    private List<String> tagList;

    public AddTagCommand(Index index, List<String> tagList) {
        this.index = index;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> existTags = personToEdit.getTags();
        Set<Tag> toAdd = model.checkTagExistence(tagList);
        getUniqueSet(existTags, toAdd);

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), toAdd);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPerson));
    }

    private void getUniqueSet(Set<Tag> original, Set<Tag> toReturn) {
        Iterator<Tag> value = original.iterator();
        while (value.hasNext()) {
            Tag current = value.next();
            if (!toReturn.contains(current)) {
                toReturn.add(current);
            }
        }
    }

}
