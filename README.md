## 1 What is SQLite?
 SQLite is a lightweight, embedded, open-source database used to store structured data locally on the device. It doesn't require a separate server.

## 2 What is SQLiteOpenHelper?

It’s an Android helper class to manage database creation, version management, and providing easy access to SQLiteDatabase through getWritableDatabase() and getReadableDatabase() methods.

## 3    Difference between getWritableDatabase() and getReadableDatabase()?

- getWritableDatabase() provides read and write access to the database.

- getReadableDatabase() provides read-only access when the database is not writable (e.g., disk full).

  

 ## 4 How do you create a table in SQLite in Android?

By executing an SQL CREATE TABLE statement using db.execSQL() inside the onCreate() method of SQLiteOpenHelper.
example :
> db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)")

## 5 How do you insert data into SQLite in Android?
Answer:
We insert data into an SQLite database in Android by creating a ContentValues object, putting the values we want to insert into it, and then calling the insert() method on the SQLiteDatabase instance.

### Step-by-Step Explanation:
#### What is ContentValues --> 
  - ContentValues is a key-value pair data structure used to pass column names and their corresponding values.
  - It acts like a map where the key is the column name, and the value is the value you want to store.

#### How is insert() used?
- The insert() method of SQLiteDatabase inserts a new row into the specified table.
- It takes three arguments:

1. Table name

2. nullColumnHack (usually null — it's a trick to avoid empty row issues)

3. ContentValues containing the data


####  What does insert() return?
- It returns the row ID of the newly inserted row, or -1 if an error occurred.

## 6  How do you retrieve data from SQLite in Android?
Answer:
We retrieve data from an SQLite database using the rawQuery() or query() methods of SQLiteDatabase, both of which return a Cursor object that allows us to read the result set row by row

#### What is a Cursor in Android?
A Cursor is an interface that provides random read access to the result set returned by a database query.
It acts like a pointer to a particular row in the result set and allows moving across rows and retrieving column values.

#### Ways to Retrieve Data:
- rawQuery() Method 
  - Used when you want to run a custom SQL query.

  - Returns a Cursor object with the results.
  - Syntax :
  > fun rawQuery(sql: String, selectionArgs: Array<String>?): Cursor
  - Example :
  > val cursor = db.rawQuery("SELECT * FROM employees", null)
 
- query() Method
  - Used for structured and safer querying without writing raw SQL.

  - You can specify columns, selection criteria, grouping, ordering, etc.
  - Syntax :
  > fun query(
    table: String,
    columns: Array<String>?,
    selection: String?,
    selectionArgs: Array<String>?,
    groupBy: String?,
    having: String?,
    orderBy: String?
): Cursor
  
  - Example :
  > val cursor = db.query(
    "employees", 
    arrayOf("id", "name", "position"), 
    null, 
    null, 
    null, 
    null, 
    null

)

#  When is onCreate() and onUpgrade() called in SQLiteOpenHelper?

- onCreate()
  - This method is called automatically when the database is created for the first time.

  - It’s where you define your database tables, indexes, or any initial data setup by executing SQL statements.

  - It runs only once per app installation, unless the app data is cleared or uninstalled.

  > When called:
    When you call getWritableDatabase() or getReadableDatabase() for the first time and the database file does not exist.

- onUpgrade()
  - This method is called automatically when the database version number is increased.

  - It’s used to handle changes in the database schema — like adding new tables, modifying columns, or dropping tables.

  - It allows you to migrate or upgrade the database without deleting existing data (if you handle it properly).

  > When called:
   When you call getWritableDatabase() or getReadableDatabase() and the database version has increased compared to the version stored in the database.


> "In Android’s SQLiteOpenHelper, the onCreate() method is automatically called the first time the database is created, and it’s used to set up tables and initial data. The onUpgrade() method is called when the database version is incremented. This is used to handle schema changes or database upgrades without losing existing data. I can write logic inside onUpgrade() to migrate data safely or modify table structures based on the version difference."

- What about Downgrade?
  - There’s also onDowngrade() which is called when the database version number is decreased.
  - You can override it if you want to handle downgrade scenarios.
