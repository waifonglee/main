package seedu.billboard.logic.commands;

import seedu.billboard.logic.commands.exceptions.CommandException;
import seedu.billboard.model.Model;

public abstract class TagCommand extends Command{
    public static final String COMMAND_WORD = "tag";

    public abstract CommandResult execute(Model model) throws CommandException;
}
