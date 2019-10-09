package seedu.billboard.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.billboard.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.billboard.model.person.Person;


public class UniqueTagList {
    private HashMap<String, TagMap> tagMaps = new HashMap<>();

    public Set<Tag> CheckTagExistence(List<String> tagList) {
        requireNonNull(tagList);
        Set<Tag> toReturn = new HashSet<>();
        int i = 0;
        while (i < tagList.size()) {
            String current = tagList.get(i);
            if (tagMaps.containsKey(current)) {
                toReturn.add(tagMaps.get(current).getTag());
            } else {
                Tag newTag = new Tag(current);
                addNew(newTag);
                toReturn.add(newTag);
            }
            i++;
        }

        return toReturn;
    }

    /**
     * Adds a tagmap to tagMaps.
     * The tagmap must not already exist in the list.
     */
    public void addNew(Tag tag) {
        requireNonNull(tag);
        TagMap toAdd = new TagMap(tag);
        tagMaps.put(tag.tagName, toAdd);
    }

    /**
     * Adds expense to the existing tagmap.
     * @param tag tag which already exists.
     * @param expense expense to add.
     */
    public void addExisting(Tag tag, Person expense) {
        requireAllNonNull(tag, expense);
        tagMaps.get(tag.tagName).addExpense(expense);
    }

    /**
     * Deletes expense from the existing tagmap.
     * if expense list in its tagmap is empty after deletion, remove it from tagmaps.
     * @param tag tag which already exists.
     * @param expense expense to delete.
     */
    public void deleteExisting(Tag tag, Person expense) {
        requireAllNonNull(tag, expense);
        TagMap current = tagMaps.get(tag.tagName);
        current.deleteExpense(expense);
        if (current.isEmptyList()) {
            tagMaps.remove(tag.tagName);
        }
    }


}
