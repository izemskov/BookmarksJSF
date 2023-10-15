package ru.develgame.bookmarks.jsf;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ru.develgame.bookmarks.dao.BookmarkDao;
import ru.develgame.bookmarks.dao.BookmarkFolderDao;
import ru.develgame.bookmarks.entity.Bookmark;
import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.exception.BookmarkFolderNotFoundException;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;
import ru.develgame.bookmarks.mappers.BookmarkMapper;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("index")
@ViewScoped
public class IndexBean implements Serializable, Converter {
    @Inject
    private transient BookmarkMapper bookmarkMapper;

    @Inject
    private transient BookmarkFolderDao bookmarkFolderDao;

    @Inject
    private transient BookmarkDao bookmarkDao;

    private TreeNode<BookmarkNode> root;

    private List<BookmarkNode> bookmarkFolderNodes;

    private BookmarkNode bookmarkFolderNode;

    private String bookmarkName;

    private String bookmarkLink;

    public void loadModel() {
        root = null;

        bookmarkFolderDao.createRootIfNotExists();

        final List<TreeNode<BookmarkNode>> allBookmarkFolders = new ArrayList<>();
        final List<Integer> allFolderIds = new ArrayList<>();

        List<BookmarkFolder> bookmarkFolders = bookmarkFolderDao.findAllByUsername();

        for (BookmarkFolder elem : bookmarkFolders) {
            BookmarkNode node = bookmarkMapper.toNode(elem);

            if (root == null) {
                root = new DefaultTreeNode<>(node, null);
                root.setExpanded(true);
                allBookmarkFolders.add(root);
            }
            else {
                DefaultTreeNode<BookmarkNode> defaultTreeNode = new DefaultTreeNode<>(node, allBookmarkFolders.stream()
                        .filter(t -> t.getData().getId() == elem.getParentId())
                        .findFirst()
                        .orElseThrow(() -> new BookmarkFolderNotFoundException(String.format("Cannot find parent for node %s", node.getName()))));
                defaultTreeNode.setExpanded(true);
                allBookmarkFolders.add(defaultTreeNode);
            }

            allFolderIds.add(elem.getId());
        }

        List<Bookmark> bookmarks = bookmarkDao.findAllByParentIdIn(allFolderIds);
        for (Bookmark elem : bookmarks) {
            BookmarkNode node = bookmarkMapper.toNode(elem);

            DefaultTreeNode<BookmarkNode> defaultTreeNode = new DefaultTreeNode<>(node, allBookmarkFolders.stream()
                    .filter(t -> t.getData().getId() == elem.getFolderId())
                    .findFirst()
                    .orElseThrow(() -> new BookmarkFolderNotFoundException(String.format("Cannot find parent for node %s", node.getName()))));
        }

        bookmarkFolderNodes = allBookmarkFolders.stream().skip(1).map(t -> t.getData()).collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        loadModel();
    }

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

    public TreeNode<BookmarkNode> getRoot() {
        return root;
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

    public List<BookmarkNode> getBookmarkFolderNodes() {
        return bookmarkFolderNodes;
    }

    public BookmarkNode getBookmarkFolderNode() {
        return bookmarkFolderNode;
    }

    public void setBookmarkFolderNode(BookmarkNode bookmarkFolderNode) {
        this.bookmarkFolderNode = bookmarkFolderNode;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int id = Integer.parseInt(s);

        return bookmarkFolderNodes.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookmarkFolderNotFoundException(String.format("Cannot find parent for node id %s", s)));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Integer.toString(((BookmarkNode) o).getId());
    }
}
