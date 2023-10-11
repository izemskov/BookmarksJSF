package ru.develgame.bookmarks.exception;

public class BookmarkFolderNotFoundException extends RuntimeException {
    public BookmarkFolderNotFoundException(String message) {
        super(message);
    }
}
