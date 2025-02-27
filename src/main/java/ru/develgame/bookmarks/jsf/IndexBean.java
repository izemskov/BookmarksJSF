package ru.develgame.bookmarks.jsf;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ru.develgame.bookmarks.dao.BookmarkDao;
import ru.develgame.bookmarks.dao.BookmarkFolderDao;
import ru.develgame.bookmarks.entity.Bookmark;
import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.exception.BookmarkFolderNotFoundException;
import ru.develgame.bookmarks.jsf.dialog.AddBookmarkDlg;
import ru.develgame.bookmarks.jsf.dialog.AddBookmarkFolderDlg;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;
import ru.develgame.bookmarks.mappers.BookmarkMapper;

import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
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

    @Inject
    private AddBookmarkDlg addBookmarkDlg;

    @Inject
    private AddBookmarkFolderDlg addBookmarkFolderDlg;

    private TreeNode<BookmarkNode> root;

    private TreeNode<BookmarkNode> selectedNode;

    private List<BookmarkNode> bookmarkFolderNodes;

    public void loadModel() {
        root = null;

        bookmarkFolderDao.createRootIfNotExists();

        final List<TreeNode<BookmarkNode>> allBookmarkFolders = new ArrayList<>();
        final List<BookmarkFolder> allFolderIds = new ArrayList<>();

        List<BookmarkFolder> bookmarkFolders = bookmarkFolderDao.findAllByUsername();

        for (BookmarkFolder elem : bookmarkFolders) {
            BookmarkNode node = bookmarkMapper.toNode(elem);

            if (root == null) {
                root = new DefaultTreeNode<>(node, null);
                root.setExpanded(true);
                allBookmarkFolders.add(root);

                for (Bookmark bookmark : elem.getBookmarks()) {
                    BookmarkNode bookmarkNode = bookmarkMapper.toNode(bookmark);
                    new DefaultTreeNode<>(bookmarkNode, root);
                }
            }
            else {
                DefaultTreeNode<BookmarkNode> defaultTreeNode = new DefaultTreeNode<>(node, allBookmarkFolders.stream()
                        .filter(t -> t.getData().getId() == elem.getParent().getId())
                        .findFirst()
                        .orElseThrow(() -> new BookmarkFolderNotFoundException(String.format("Cannot find parent for node %s", node.getName()))));
                defaultTreeNode.setExpanded(true);
                allBookmarkFolders.add(defaultTreeNode);

                for (Bookmark bookmark : elem.getBookmarks()) {
                    BookmarkNode bookmarkNode = bookmarkMapper.toNode(bookmark);
                    new DefaultTreeNode<>(bookmarkNode, defaultTreeNode);
                }
            }

            allFolderIds.add(elem);
        }

        bookmarkFolderNodes = allBookmarkFolders.stream()
                .map(t -> t.getData())
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void init() {
        loadModel();
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

    public void deleteNode() {
        if (!selectedNode.getData().isFolder()) {
            if (!bookmarkDao.deleteBookmark(selectedNode.getData().getId())) {
                return;
            }
        }
        else {
            if (!bookmarkFolderDao.deleteBookmarkFolder(selectedNode.getData().getId())) {
                return;
            }
        }

        loadModel();
        selectedNode = null;
    }

    public TreeNode<BookmarkNode> getRoot() {
        return root;
    }

    public TreeNode<BookmarkNode> getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode<BookmarkNode> selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<BookmarkNode> getBookmarkFolderNodes() {
        return bookmarkFolderNodes;
    }

    public AddBookmarkDlg getAddBookmarkDlg() {
        return addBookmarkDlg;
    }

    public AddBookmarkFolderDlg getAddBookmarkFolderDlg() {
        return addBookmarkFolderDlg;
    }
}
