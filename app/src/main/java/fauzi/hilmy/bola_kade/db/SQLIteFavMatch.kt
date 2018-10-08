package fauzi.hilmy.bola_kade.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SQLIteFavMatch(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavMatch.db", null, 1) {
    companion object {
        private var instance: SQLIteFavMatch? = null

        @Synchronized
        fun getInstance(ctx: Context): SQLIteFavMatch {
            if (instance == null) {
                instance = SQLIteFavMatch(ctx.applicationContext)
            }
            return instance as SQLIteFavMatch
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Create Table
        db?.createTable(FavMatch.TABLE_FAVORITE, true,
                FavMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavMatch.EVENT_ID to TEXT + UNIQUE,
                FavMatch.EVENT_DATE to TEXT,
                FavMatch.EVENT_TIME to TEXT,
                FavMatch.HOME_NAME to TEXT,
                FavMatch.HOME_SCORE to TEXT,
                FavMatch.AWAY_NAME to TEXT,
                FavMatch.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(FavMatch.TABLE_FAVORITE, true)
    }
}

//Access property
val Context.database: SQLIteFavMatch
    get() = SQLIteFavMatch.getInstance(applicationContext)