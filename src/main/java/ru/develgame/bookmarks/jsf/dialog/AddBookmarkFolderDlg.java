package ru.develgame.bookmarks.jsf.dialog;

import ru.develgame.bookmarks.dao.BookmarkFolderDao;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.Serializable;

@Dependent
public class AddBookmarkFolderDlg implements Serializable {
    @Inject
    private BookmarkFolderDao bookmarkFolderDao;

    private String bookmarkFolderName;

    private BookmarkNode bookmarkParentNode;

    public void addBookmarkFolder() {
        if (bookmarkFolderName == null || bookmarkFolderName.isEmpty()) {
            return;
        }

        if (bookmarkParentNode == null) {
            return;
        }

        bookmarkFolderDao.createBookmarkFolder(bookmarkFolderName, bookmarkParentNode.getId());

        bookmarkFolderName = null;
        bookmarkParentNode = null;
    }

    public String getBookmarkFolderName() {
        return bookmarkFolderName;
    }

    public void setBookmarkFolderName(String bookmarkFolderName) {
        this.bookmarkFolderName = bookmarkFolderName;
    }

    public BookmarkNode getBookmarkParentNode() {
        return bookmarkParentNode;
    }

    public void setBookmarkParentNode(BookmarkNode bookmarkParentNode) {
        this.bookmarkParentNode = bookmarkParentNode;
    }
}
