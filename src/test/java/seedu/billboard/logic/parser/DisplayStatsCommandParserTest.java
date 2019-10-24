package seedu.billboard.logic.parser;

import static seedu.billboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.billboard.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static seedu.billboard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.billboard.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.billboard.commons.core.date.DateInterval;
import seedu.billboard.logic.commands.DisplayStatsCommand;
import seedu.billboard.model.statistics.formats.StatisticsFormat;
import seedu.billboard.model.statistics.formats.StatisticsFormatOptions;

public class DisplayStatsCommandParserTest {
    private static final String BREAKDOWN_ARG = StatisticsFormat.BREAKDOWN.getName();
    private static final String TIMELINE_ARG = StatisticsFormat.TIMELINE.getName();
    private static final String WEEK_INTERVAL_OPTION = " " + PREFIX_INTERVAL + DateInterval.WEEK.getName();

    private DisplayStatsCommandParser parser = new DisplayStatsCommandParser();

    @Test
    public void parse_validArgs_returnsDisplayStatsCommand() {
        String args = BREAKDOWN_ARG;
        StatisticsFormatOptions emptyOptions = StatisticsFormatOptions.emptyOption();
        DisplayStatsCommand expectedCommand = new DisplayStatsCommand(StatisticsFormat.BREAKDOWN, emptyOptions);
        assertParseSuccess(parser, args, expectedCommand);

        String trailingWhitespace = TIMELINE_ARG + "  ";
        DisplayStatsCommand expectedCommand2 = new DisplayStatsCommand(StatisticsFormat.TIMELINE, emptyOptions);
        assertParseSuccess(parser, trailingWhitespace, expectedCommand2);

        String leadingWhitespace = "    " + TIMELINE_ARG;
        assertParseSuccess(parser, leadingWhitespace, expectedCommand2);

        String argsWithOptions = TIMELINE_ARG + WEEK_INTERVAL_OPTION;
        DisplayStatsCommand expectedCommandWithOptions =
                new DisplayStatsCommand(
                        StatisticsFormat.TIMELINE, StatisticsFormatOptions.withOptions(DateInterval.WEEK));
        assertParseSuccess(parser, argsWithOptions, expectedCommandWithOptions);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String emptyInput = "";
        assertParseFailure(parser, emptyInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayStatsCommand.MESSAGE_USAGE));

        String whitespaceOnly = "   ";
        assertParseFailure(parser, whitespaceOnly,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayStatsCommand.MESSAGE_USAGE));

        String randomInput = "qjoij qkpo1d";
        assertParseFailure(parser, randomInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DisplayStatsCommand.MESSAGE_USAGE));
    }
}
