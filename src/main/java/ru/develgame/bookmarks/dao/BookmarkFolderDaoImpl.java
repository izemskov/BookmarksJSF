package ru.develgame.bookmarks.dao;

import ru.develgame.bookmarks.entity.BookmarkFolder;
import ru.develgame.bookmarks.jsf.UserBean;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookmarkFolderDaoImpl implements BookmarkFolderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private UserBean userBean;

    @Inject
    private Logger logger;

    @Override
    public List<BookmarkFolder> findAllByUsername() {
        Query query = entityManager.createNativeQuery("SELECT * FROM APP.BOOKMARKS_FOLDER WHERE USERNAME=?1 ORDER BY ID ASC",
                BookmarkFolder.class);
        query.setParameter(1, userBean.getUsername());
        return query.getResultList();
    }

    @Override
    public BookmarkFolder findRoot() {
        Query query = entityManager.createNativeQuery("SELECT * FROM APP.BOOKMARKS_FOLDER WHERE USERNAME=?1 AND PARENT_ID IS NULL",
                BookmarkFolder.class);
        query.setParameter(1, userBean.getUsername());
        List<BookmarkFolder> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    @Override
    public boolean createBookmarkFolder(String name, Integer parentId) {
        try {
            userTransaction.begin();

            BookmarkFolder bookmarkFolder = new BookmarkFolder();
            bookmarkFolder.setName(name);
            bookmarkFolder.setParentId(parentId);
            bookmarkFolder.setUsername(userBean.getUsername());

            entityManager.persist(bookmarkFolder);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot save bookmark folder", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean createRootIfNotExists() {
        try {
            userTransaction.begin();

            if (findRoot() != null) {
                userTransaction.rollback();
                return false;
            }

            BookmarkFolder bookmarkFolder = new BookmarkFolder();
            bookmarkFolder.setName("Root folder");
            bookmarkFolder.setUsername(userBean.getUsername());

            entityManager.persist(bookmarkFolder);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot save bookmark folder", e);
            return false;
        }

        return true;
    }


}
