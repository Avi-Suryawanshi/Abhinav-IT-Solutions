package com.avi.abhinavtask

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MemberDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "MembersDB"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "Members"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_MOBILE = "mobile"
        private const val COLUMN_ROLE = "role"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_DOB = "dob"
        private const val COLUMN_DOJ = "doj"
        private const val COLUMN_SUBSCRIPTION_FEE = "subscription_fee"
        private const val COLUMN_DEPOSIT_AMOUNT = "deposit_amount"
        private const val COLUMN_LOAN_AMOUNT = "loan_amount"
        private const val COLUMN_MARITAL_STATUS = "marital_status"
        private const val COLUMN_DATE_OF_MARRIAGE = "date_of_marriage"
        private const val COLUMN_CASTE = "caste"
        private const val COLUMN_RELIGION = "religion"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_AADHAR = "aadhar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_MOBILE TEXT,
                $COLUMN_ROLE TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_DOB TEXT,
                $COLUMN_DOJ TEXT,
                $COLUMN_SUBSCRIPTION_FEE REAL,
                $COLUMN_DEPOSIT_AMOUNT REAL,
                $COLUMN_LOAN_AMOUNT REAL,
                $COLUMN_MARITAL_STATUS TEXT,
                $COLUMN_DATE_OF_MARRIAGE TEXT,
                $COLUMN_CASTE TEXT,
                $COLUMN_RELIGION TEXT,
                $COLUMN_CATEGORY TEXT,
                $COLUMN_AADHAR TEXT
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertMember(
        name: String,
        mobile: String,
        role: String,
        gender: String,
        dob: String,
        doj: String,
        subscriptionFee: Double,
        depositAmount: Double,
        loanAmount: Double,
        maritalStatus: String,
        dateOfMarriage: String?,
        caste: String,
        religion: String,
        category: String,
        aadhar: String
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_MOBILE, mobile)
            put(COLUMN_ROLE, role)
            put(COLUMN_GENDER, gender)
            put(COLUMN_DOB, dob)
            put(COLUMN_DOJ, doj)
            put(COLUMN_SUBSCRIPTION_FEE, subscriptionFee)
            put(COLUMN_DEPOSIT_AMOUNT, depositAmount)
            put(COLUMN_LOAN_AMOUNT, loanAmount)
            put(COLUMN_MARITAL_STATUS, maritalStatus)
            put(COLUMN_DATE_OF_MARRIAGE, dateOfMarriage)
            put(COLUMN_CASTE, caste)
            put(COLUMN_RELIGION, religion)
            put(COLUMN_CATEGORY, category)
            put(COLUMN_AADHAR, aadhar)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllMembers(): List<Member> {
        val members = mutableListOf<Member>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val mobile = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MOBILE))
                val role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE))
                val gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER))
                val dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB))
                val doj = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOJ))
                val subscriptionFee = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SUBSCRIPTION_FEE))
                val depositAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_DEPOSIT_AMOUNT))
                val loanAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LOAN_AMOUNT))
                val maritalStatus = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MARITAL_STATUS))
                val dateOfMarriage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_OF_MARRIAGE))
                val caste = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CASTE))
                val religion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELIGION))
                val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
                val aadhar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AADHAR))

                members.add(
                    Member(
                        id = id,
                        name = name,
                        role = role,
                        gender = gender,
                        dob = dob,
                        doj = doj,
                        mobile = mobile,
                        subscriptionFee = subscriptionFee,
                        depositAmount = depositAmount,
                        loanAmount = loanAmount,
                        maritalStatus = maritalStatus,
                        dateOfMarriage = dateOfMarriage,
                        caste = caste,
                        religion = religion,
                        category = category,
                        aadhar = aadhar
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return members
    }
}

data class Member(
    val id: Int,
    val name: String,
    val role: String,
    val gender: String,
    val dob: String,
    val doj: String,
    val mobile: String,
    val subscriptionFee: Double,
    val depositAmount: Double,
    val loanAmount: Double,
    val maritalStatus: String,
    val dateOfMarriage: String?,
    val caste: String,
    val religion: String,
    val category: String,
    val aadhar: String
)
