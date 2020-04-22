/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Categories;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 810585
 */
public class CategoryDB {

    public int insert(Categories categories) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(categories);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, "Cannot insert " + categories.toString(), ex);
            throw new Exception("Error inserting categories");
        } finally {
            em.close();
        }
    }

    public int update(Categories categories) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(categories);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, "Cannot update " + categories.toString(), ex);
            throw new Exception("Error updating categories");
        } finally {
            em.close();
        }
    }

    public List<Categories> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Categories> categoriess = em.createNamedQuery("Categories.findAll", Categories.class).getResultList();
            return categoriess;
        } finally {
            em.close();
        }
    }

    public Categories getCategories(int categoryID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Categories categories = em.find(Categories.class, categoryID);
            return categories;
        } finally {
            em.close();
        }
    }

    public int delete(Categories categories) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(categories));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CategoryDB.class.getName()).log(Level.SEVERE, "Cannot delete " + categories.toString(), ex);
            throw new Exception("Error deleting categories");
        } finally {
            em.close();
        }
    }
}
