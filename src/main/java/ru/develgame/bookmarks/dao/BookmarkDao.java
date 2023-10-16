package ru.develgame.bookmarks.dao;

import ru.develgame.bookmarks.entity.Bookmark;

import java.util.List;

public interface BookmarkDao {
    List<Bookmark> findAllByParentIdIn(List<Integer> parents);

    boolean createBookmark(String name, String link, int folderId);

    boolean deleteBookmark(int id);
}
