package ru.develgame.bookmarks.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOKMARKS", schema = "APP")
public class Bookmark {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FOLDER_ID")
    private int folderId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LINK")
    private String link;

    public Bookmark() {}

    public Bookmark(int id, int folderId, String name, String link) {
        this.id = id;
        this.folderId = folderId;
        this.name = name;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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
