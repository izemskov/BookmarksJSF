package ru.develgame.bookmarks.mappers;

import ru.develgame.bookmarks.entity.Bookmark;
import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookmarkMapper {
    public BookmarkNode toNode(BookmarkFolder bookmarkFolder) {
        if (bookmarkFolder == null) {
            return null;
        }
        return new BookmarkNode(bookmarkFolder.getName(), null, true, bookmarkFolder.getId());
    }

    public BookmarkNode toNode(Bookmark bookmark) {
        if (bookmark == null) {
            return null;
        }
        return new BookmarkNode(bookmark.getName(), bookmark.getLink(), false, bookmark.getId());
    }
}
