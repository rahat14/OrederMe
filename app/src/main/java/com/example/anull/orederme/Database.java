package com.example.anull.orederme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

private static final  String DB_NAME="Db.db" ;
private static final int DB_VER = 1 ;
    public Database(Context context) {

        super(context, DB_NAME , null , DB_VER);

    }
    public List<Order>getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder() ;



        String[] sqlSelect={"ProductName","Quantity","Price"};
        String sqlTable = "OrderDetail" ;
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect,null ,null,null,null ,null);

        final List<Order>result = new ArrayList<>();
        if(c.moveToFirst()){


            do{
                result.add(new Order(c.getString(c.getColumnIndex("ProductName")),
                c.getString(c.getColumnIndex("Quantity")),
                c.getString(c.getColumnIndex("Price"))
                ));
            } while (c.moveToNext());

        }
        return result ;

    }



public  void  addToCart(Order oreder ){

                SQLiteDatabase db = getReadableDatabase();
                String Query = String.format("INSERT INTO OrderDetail(ProductName,Quantity,Price) VALUES('%s','%s','%s');",
                       oreder.getProductName(),
                        oreder.getProductQuantity(),
                        oreder.getPrice());
                db.execSQL(Query);

}

public  void cleanCart(){

    SQLiteDatabase db = getReadableDatabase();
    String Query = String.format("DELETE FROM OrderDetail");

    db.execSQL(Query);




}

public  Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor res = db.rawQuery("select * from "+"OrderDetail",null);
        return res ;




}




}
