package ru.develgame.bookmarks.mappers;

import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkMapper {
    public BookmarkNode toNode(BookmarkFolder bookmarkFolder) {
        if (bookmarkFolder == null) {
            return null;
        }
        return new BookmarkNode(bookmarkFolder.getName(), null, true, bookmarkFolder.getId());
    }
}
