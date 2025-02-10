package ru.develgame.bookmarks.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BOOKMARKS", schema = "APP")
public class Bookmark {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name="FOLDER_ID")
    private BookmarkFolder folder;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LINK")
    private String link;

    public Bookmark() {}

    public Bookmark(int id, BookmarkFolder folder, String name, String link) {
        this.id = id;
        this.folder = folder;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookmarkFolder getFolder() {
        return folder;
    }

    public void setFolder(BookmarkFolder folder) {
        this.folder = folder;
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
}
