package ru.develgame.bookmarks.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOKMARKS_FOLDER", schema = "APP")
public class BookmarkFolder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private Integer parentId;

    @Column(name = "USERNAME")
    private String username;

    public BookmarkFolder() {}

    public BookmarkFolder(int id, String name, Integer parentId, String username) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
