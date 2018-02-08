# SQLBrite
SQLBrite is a reactive wrapper around the SQLiteOpenHelper and ContentResolver. We can subscribe to the changes of DB by make observables of queries. In this example we subscribe around the change in DB reactively and as soon as some data is inserted in DB the data is appended in text view.
<br><br>
The sqliteOpenHelper can be wrapped in following way:
<pre>
val db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io())
</pre>
After wrapping we can react to the queries to the database.
<br>
Documentation:https://github.com/square/sqlbrite
<br> 
<b>Screenshots:</b>
<br><br>
<img src="sqlbrite.gif"/>
