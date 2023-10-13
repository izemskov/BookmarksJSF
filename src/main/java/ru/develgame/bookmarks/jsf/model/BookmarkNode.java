package ru.develgame.bookmarks.jsf.model;

import java.util.Objects;

public class BookmarkNode {
    private String name;

    private String link;

    private boolean folder;

    private int id;

    public BookmarkNode(String name, String link, boolean folder, int id) {
        this.name = name;
        this.link = link;
        this.folder = folder;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkNode that = (BookmarkNode) o;
        return folder == that.folder && id == that.id && Objects.equals(name, that.name) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, folder, id);
    }
}
