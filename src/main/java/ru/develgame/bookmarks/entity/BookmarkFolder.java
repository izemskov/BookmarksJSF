package ru.develgame.bookmarks.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOKMARKS_FOLDER", schema = "APP")
public class BookmarkFolder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private BookmarkFolder parent;

    @Column(name = "USERNAME")
    private String username;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    public BookmarkFolder() {}

    public BookmarkFolder(int id, String name, BookmarkFolder parent, String username) {
        this.id = id;
        this.name = name;
        this.parent = parent;
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

    public BookmarkFolder getParent() {
        return parent;
    }

    public void setParent(BookmarkFolder parent) {
        this.parent = parent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
