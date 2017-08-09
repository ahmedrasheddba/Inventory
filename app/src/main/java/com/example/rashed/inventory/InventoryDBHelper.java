package com.example.rashed.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by RASHED on 7/27/2017.
 */
public class InventoryDBHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = InventoryDBHelper.class.getCanonicalName();

    public InventoryDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(InventoryContract.InventoryInput.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertItem(InventoryItem item) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryInput.PRODUCT_NAME, item.getProductName());
        contentValues.put(InventoryContract.InventoryInput.PRODUCT_PRICE, item.getPrice());
        contentValues.put(InventoryContract.InventoryInput.PRODUCT_QUANTITY, item.getQuantity());
        contentValues.put(InventoryContract.InventoryInput.SUPPLIER_NAME, item.getSupplierName());
        contentValues.put(InventoryContract.InventoryInput.SUPPLIER_PHONE, item.getSupplierPhone());
        contentValues.put(InventoryContract.InventoryInput.SUPPLIER_EMAIL, item.getSupplierEmail());
        contentValues.put(InventoryContract.InventoryInput.IMAGE, item.getImage());
        long id = sqLiteDatabase.insert(InventoryContract.InventoryInput.TABLE_NAME, null, contentValues);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.InventoryInput._ID,
                InventoryContract.InventoryInput.PRODUCT_NAME,
                InventoryContract.InventoryInput.PRODUCT_PRICE,
                InventoryContract.InventoryInput.PRODUCT_QUANTITY,
                InventoryContract.InventoryInput.SUPPLIER_NAME,
                InventoryContract.InventoryInput.SUPPLIER_PHONE,
                InventoryContract.InventoryInput.SUPPLIER_EMAIL,
                InventoryContract.InventoryInput.IMAGE
        };
        return db.query(
                InventoryContract.InventoryInput.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                InventoryContract.InventoryInput._ID,
                InventoryContract.InventoryInput.PRODUCT_NAME,
                InventoryContract.InventoryInput.PRODUCT_PRICE,
                InventoryContract.InventoryInput.PRODUCT_QUANTITY,
                InventoryContract.InventoryInput.SUPPLIER_NAME,
                InventoryContract.InventoryInput.SUPPLIER_PHONE,
                InventoryContract.InventoryInput.SUPPLIER_EMAIL,
                InventoryContract.InventoryInput.IMAGE
        };
        String selection = InventoryContract.InventoryInput._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};
        return db.query(
                InventoryContract.InventoryInput.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryInput.PRODUCT_QUANTITY, quantity);
        String selection = InventoryContract.InventoryInput._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(currentItemId)};
        sqLiteDatabase.update(InventoryContract.InventoryInput.TABLE_NAME,
                contentValues, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity - 1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.InventoryInput.PRODUCT_QUANTITY, newQuantity);
        String selection = InventoryContract.InventoryInput._ID + "=?";
        String[] selectionArgs = new String[]{String.valueOf(itemId)};
        db.update(InventoryContract.InventoryInput.TABLE_NAME,
                contentValues, selection, selectionArgs);
    }
}
