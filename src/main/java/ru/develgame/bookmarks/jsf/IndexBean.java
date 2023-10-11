package ru.develgame.bookmarks.jsf;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ru.develgame.bookmarks.dao.BookmarkFolderDao;
import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.exception.BookmarkFolderNotFoundException;
import ru.develgame.bookmarks.jsf.model.BookmarkNode;
import ru.develgame.bookmarks.mappers.BookmarkMapper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("index")
@ViewScoped
public class IndexBean implements Serializable {
    @Inject
    private transient BookmarkMapper bookmarkMapper;

    @Inject
    private transient BookmarkFolderDao bookmarkFolderDao;

    @Inject
    private transient Logger logger;

    private TreeNode<BookmarkNode> root;

    @PostConstruct
    public void init() {
        bookmarkFolderDao.createRootIfNotExists();

        final List<TreeNode<BookmarkNode>> allBookmarks = new ArrayList<>();

        List<BookmarkFolder> bookmarks = bookmarkFolderDao.findAllByUsername();

        for (BookmarkFolder elem : bookmarks) {
            BookmarkNode node = bookmarkMapper.toNode(elem);

            if (root == null) {
                root = new DefaultTreeNode<>(node, null);
                allBookmarks.add(root);

                logger.warning(root.getData().getName());
            }
            else {
                DefaultTreeNode<BookmarkNode> defaultTreeNode = new DefaultTreeNode<>(node, allBookmarks.stream()
                        .filter(t -> t.getData().getId() == elem.getParentId())
                        .findFirst()
                        .orElseThrow(() -> new BookmarkFolderNotFoundException(String.format("Cannot find parent for node %s", node.getName()))));
                allBookmarks.add(defaultTreeNode);
            }
        }
    }

    public TreeNode<BookmarkNode> getRoot() {
        return root;
    }
}
