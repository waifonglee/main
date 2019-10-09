package seedu.billboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.billboard.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.billboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.stream.Stream;

import seedu.billboard.commons.core.index.Index;
import seedu.billboard.commons.exceptions.IllegalValueException;
import seedu.billboard.logic.commands.AddTagCommand;
import seedu.billboard.logic.parser.exceptions.ParseException;

public class AddTagCommandParser {

    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), ive);
        }

        List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);

        return new AddTagCommand(index, tagList);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
