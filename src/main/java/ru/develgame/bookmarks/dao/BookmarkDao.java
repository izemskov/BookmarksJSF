package ru.develgame.bookmarks.dao;

import ru.develgame.bookmarks.entity.Bookmark;
import ru.develgame.bookmarks.entity.BookmarkFolder;

import java.util.List;

public interface BookmarkDao {
    List<Bookmark> findAllByParentIdIn(List<BookmarkFolder> parents);

    boolean createBookmark(String name, String link, int folderId);

    boolean deleteBookmark(int id);
}
