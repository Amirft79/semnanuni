package ir.ac.semnan.golestan.app.mysemnanuni.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION){



    companion object{
        private const val DATABASE_VERSION=1
        private const val DATABASE_NAME="STUDENT_CONFIG"
        private const val TABLE_NAME="STUDENT_CONFIG"
        private const val USERNAME="username"
        private const val Password="password"
        private const val id="id"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query=("CREATE TABLE"+ TABLE_NAME+"("
                +id+"INTEGER PRIMARY KEY,"+
                USERNAME+"TEXT,"+
                Password+"TEXT"+")")
        p0?.execSQL(query)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    public fun insertUserInfo(username: String,password:String){
        val values=ContentValues()
        values.put("username",username)
        values.put("password",password)
        val db=this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }



    public fun updateUser(Password:String){
        val values=ContentValues();
        values.put("password",Password)
        val db=this.writableDatabase
        db.update(TABLE_NAME,values,"id=?", arrayOf("id"))
        db.close()
    }

    /*

    public fun getUserInfo():ArrayList<String>{
        val userlist=ArrayList<String>()
        val curser:Cursor
        val database=this.readableDatabase
        curser=database.rawQuery("SELECT * FROM $TABLE_NAME",null)
        do {

        }while (curser.moveToNext())


    }

     */


}