package ru.develgame.bookmarks.dao;

import ru.develgame.bookmarks.entity.Bookmark;
import ru.develgame.bookmarks.entity.BookmarkFolder;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class BookmarkDaoImpl implements BookmarkDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    @Inject
    private BookmarkFolderDao bookmarkFolderDao;

    @Override
    public boolean createBookmark(String name, String link, int folderId) {
        try {
            userTransaction.begin();

            Bookmark bookmark = new Bookmark();
            bookmark.setLink(link);
            bookmark.setName(name);
            BookmarkFolder folder = entityManager.find(BookmarkFolder.class, folderId);
            if (folder == null) {
                userTransaction.rollback();
                return false;
            }
            bookmark.setFolder(folder);

            entityManager.persist(bookmark);
            folder.getBookmarks().add(bookmark);
            entityManager.persist(folder);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot save bookmark", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteBookmark(int id) {
        try {
            userTransaction.begin();

            Bookmark bookmark = entityManager.find(Bookmark.class, id);
            if (bookmark == null) {
                userTransaction.rollback();
                return false;
            }

            entityManager.remove(bookmark);

            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot delete bookmark", e);
            return false;
        }

        return true;
    }
}
