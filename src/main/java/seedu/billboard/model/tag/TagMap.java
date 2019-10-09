package seedu.billboard.model.tag;

import java.util.HashSet;

import seedu.billboard.model.person.Person;

/**
 * A TagMap maps a tag to the expenses under it.
 */
public class TagMap {
    private Tag tag;
    private HashSet<Person> expenses = new HashSet<>();

    public TagMap(Tag tag) {
        this.tag = tag;
    }

    public void addExpense(Person expense) {
        expenses.add(expense);
    }

    public void deleteExpense(Person expense) {
        expenses.remove(expense);
    }

    public Tag getTag() {
        return tag;
    }

    public boolean isEmptyList() {
        return expenses.isEmpty();
    }

}
