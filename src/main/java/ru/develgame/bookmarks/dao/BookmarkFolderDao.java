package ru.develgame.bookmarks.dao;

import ru.develgame.bookmarks.entity.BookmarkFolder;

import java.util.List;

public interface BookmarkFolderDao {
    List<BookmarkFolder> findAllByUsername();

    BookmarkFolder findRoot();

    boolean createBookmarkFolder(String name, Integer parentId);

    boolean createRootIfNotExists();

    boolean deleteBookmarkFolder(int id);
}
