/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemsDB;
import domain.Categories;
import domain.Items;
import domain.Users;
import java.util.List;

/**
 *
 * @author 810585
 */
public class Inventory {

    private ItemsDB itemDB;
    private CategoryDB categoryDB;

    public Inventory() {
        itemDB = new ItemsDB();
        categoryDB = new CategoryDB();
    }

    public Items get(int itemID) throws Exception {
        return itemDB.getItem(itemID);
    }
    
    public Categories getCategory(int categories) throws Exception {
        return categoryDB.getCategories(categories);
    }

    public List<Items> getAll(Users owner) throws Exception {
        return itemDB.getAll(owner);
    }

    public List<Items> getAll() throws Exception {
        return itemDB.getAll();
    }

    public int delete(Items item) throws Exception {
        System.out.println("here1");
        Items deletedItem = itemDB.getItem(item.getItemID());
        return itemDB.delete(deletedItem);
    }

    public int insert(String itemName, double price, Users owner, Categories categories) throws Exception {
        Items item = new Items(categories, itemName, price, owner);
        return itemDB.insert(item, owner, categories);
    }
}
