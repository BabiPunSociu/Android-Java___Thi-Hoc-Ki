package com.example.hoangminhtuan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Sqlite_201200391 extends SQLiteOpenHelper {
    public static final String TableName="Taxi_HoangTuan";
    public static final String soXe="soXe";
    public static final String quangDuong="quangDuong";
    public static final String donGia="donGia";
    public static final String khuyenMai="khuyenMai";

    public Sqlite_201200391(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create table if not exists "+TableName+" ("+soXe+" Text Primary key,"+
                quangDuong+" REAL, "
                +donGia+" Interger,"
                +khuyenMai+" Interger )";
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+TableName);
        //Tạo lại
        onCreate(db);
    }
    public List<Taxi_hoangminhtuan> getAllUser(){
        List<Taxi_hoangminhtuan> list=new ArrayList<>();
        String sql="Select * from "+TableName;
        SQLiteDatabase db=this.getReadableDatabase();
        //chạy câu lệnh truy vấn trả về dạng cursor
        Cursor cursor=db.rawQuery(sql,null);
        //tạo Arraylist<User> để trả về
        if(cursor!=null)
            while(cursor.moveToNext()){
                Taxi_hoangminhtuan user=new Taxi_hoangminhtuan(cursor.getString(0),
                        cursor.getDouble(1),cursor.getInt(2),cursor.getInt(3));
                list.add(user);
            }
        return list;
    }
    //Thêm phần tử vào sqlite
    public void addUser(Taxi_hoangminhtuan User){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(soXe,User.getSoXe());
        value.put(quangDuong,User.getQuangDuong());
        value.put(donGia,User.getDonGia());
        value.put(khuyenMai,User.getKhuyenMai());
        db.insert(TableName,null,value);
        db.close();
    }
    //Update phần tử trong sqlite
//    public void updateUser(User User){
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues value=new ContentValues();
//        value.put(Id,User.getId());
//        value.put(Image,User.getImages());
//        value.put(Fullname,User.getName());
//        value.put(Phonenumber,User.getPhone());
//        return db.update(TableName,null,value);
//    }
}

