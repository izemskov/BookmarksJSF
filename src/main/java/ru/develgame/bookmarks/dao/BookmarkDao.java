package ru.develgame.bookmarks.dao;

public interface BookmarkDao {
    boolean createBookmark(String name, String link, int folderId);

    boolean deleteBookmark(int id);
}
