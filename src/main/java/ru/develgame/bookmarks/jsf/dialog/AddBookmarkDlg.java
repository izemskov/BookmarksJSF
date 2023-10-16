package ru.develgame.bookmarks.jsf.dialog;

import ru.develgame.bookmarks.dao.BookmarkDao;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;

@Dependent
public class AddBookmarkDlg implements Serializable {
    @Inject
    private transient BookmarkDao bookmarkDao;

    private String bookmarkName;

    private String bookmarkLink;

    private BookmarkNode bookmarkFolderNode;

    public void addBookmark() {
        if (bookmarkName == null || bookmarkName.isEmpty()) {
            return;
        }

        if (bookmarkLink == null || bookmarkLink.isEmpty()) {
            return;
        }

        if (bookmarkFolderNode == null) {
            return;
        }

        bookmarkDao.createBookmark(bookmarkName, bookmarkLink, bookmarkFolderNode.getId());

        bookmarkName = null;
        bookmarkLink = null;
        bookmarkFolderNode = null;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getBookmarkLink() {
        return bookmarkLink;
    }

    public void setBookmarkLink(String bookmarkLink) {
        this.bookmarkLink = bookmarkLink;
    }

    public BookmarkNode getBookmarkFolderNode() {
        return bookmarkFolderNode;
    }

    public void setBookmarkFolderNode(BookmarkNode bookmarkFolderNode) {
        this.bookmarkFolderNode = bookmarkFolderNode;
    }
}
