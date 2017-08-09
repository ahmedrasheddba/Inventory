package com.example.rashed.inventory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by RASHED on 7/27/2017.
 */
public class InventoryCursorAdapter extends CursorAdapter {

    private final MainActivity activity;

    public InventoryCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTxtView = (TextView) view.findViewById(R.id.product_name_TxtView);
        TextView quantityTxtView = (TextView) view.findViewById(R.id.quantity_TxtView);
        TextView priceTxtView = (TextView) view.findViewById(R.id.price_TxtView);
        ImageView saleImgView = (ImageView) view.findViewById(R.id.sale_ImgView);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);

        String name = cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryInput.PRODUCT_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.InventoryInput.PRODUCT_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryInput.PRODUCT_PRICE));
        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(InventoryContract.InventoryInput.IMAGE))));

        nameTxtView.setText(name);
        quantityTxtView.setText(String.valueOf(quantity));
        priceTxtView.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(InventoryContract.InventoryInput._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(id);
            }
        });

        saleImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnSale(id,
                        quantity);
            }
        });
    }
}