package com.example.rashed.inventory;
import android.provider.BaseColumns;
/**
 * Created by RASHED on 7/27/2017.
 */
public class InventoryContract {
    public InventoryContract() {
    }
    public static final class InventoryInput implements BaseColumns {

        public static final String TABLE_NAME = "Inventory";
        public static final String _ID = BaseColumns._ID;
        public static final String PRODUCT_NAME = "name";
        public static final String PRODUCT_PRICE = "price_TxtView";
        public static final String PRODUCT_QUANTITY = "quantity_TxtView";
        public static final String SUPPLIER_NAME = "supplier_name";
        public static final String SUPPLIER_PHONE = "supplier_phone";
        public static final String SUPPLIER_EMAIL = "supplier_email";
        public static final String IMAGE = "image";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                InventoryContract.InventoryInput.TABLE_NAME + "(" +
                InventoryContract.InventoryInput._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryContract.InventoryInput.PRODUCT_NAME + " TEXT NOT NULL," +
                InventoryContract.InventoryInput.PRODUCT_PRICE + " TEXT NOT NULL," +
                InventoryContract.InventoryInput.PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventoryContract.InventoryInput.SUPPLIER_NAME + " TEXT NOT NULL," +
                InventoryContract.InventoryInput.SUPPLIER_PHONE + " TEXT NOT NULL," +
                InventoryContract.InventoryInput.SUPPLIER_EMAIL + " TEXT NOT NULL," +
                InventoryInput.IMAGE + " TEXT NOT NULL" + ");";
    }
}
