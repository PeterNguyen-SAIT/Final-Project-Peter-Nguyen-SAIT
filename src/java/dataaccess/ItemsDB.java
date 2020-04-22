/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Categories;
import domain.Items;
import domain.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 810585
 */
public class ItemsDB {

    public int insert(Items items, Users owner, Categories categories) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            Users user = em.find(Users.class, owner.getUsername());
            items.setOwner(user);
            Categories cat = em.find(Categories.class, categories.getCategoryID());
            items.setCategory(cat);

            trans.begin();
            em.persist(items);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot insert " + items.toString(), ex);
            throw new NotesDBException("Error inserting items");
        } finally {
            em.close();
        }
    }

    public int update(Items items) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(items);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot update " + items.toString(), ex);
            throw new NotesDBException("Error updating items");
        } finally {
            em.close();
        }
    }

    public List<Items> getAll(Users owner) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Items> temp = em.createNamedQuery("Items.findAll", Items.class).getResultList();
            List<Items> items = new ArrayList<>();
            for (Items i : temp) {
                if (i.getOwner().equals(owner)) {
                    items.add(i);
                }
            }
            return items;
        } finally {
            em.close();
        }
    }

    public List<Items> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Items> items = em.createNamedQuery("Items.findAll", Items.class).getResultList();
            return items;
        } finally {
            em.close();
        }
    }

    public Items getItem(int itemsID) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Items items = em.find(Items.class, itemsID);
            return items;
        } finally {
            em.close();
        }
    }

    public int delete(Items items) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            System.out.println("here");
            trans.begin();
            em.remove(em.merge(items));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot delete " + items.toString(), ex);
            throw new NotesDBException("Error deleting items");
        } finally {
            em.close();
        }
    }
}
