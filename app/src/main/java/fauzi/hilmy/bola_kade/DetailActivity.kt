package fauzi.hilmy.bola_kade

import android.app.AlertDialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import fauzi.hilmy.bola_kade.api.ApiClient
import fauzi.hilmy.bola_kade.db.FavMatch
import fauzi.hilmy.bola_kade.db.database
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.ResponseLastNext
import fauzi.hilmy.bola_kade.util.MyConstant
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.ID_EVENT
import fauzi.hilmy.bola_kade.util.Util
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var id: String
    private var isFavMatch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = "Detail Match"
        id = intent.getStringExtra(ID_EVENT)
        getDetail()
        favoriteState()
        setFavMatch()

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavMatch.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavMatch>())
            if (!favorite.isEmpty()) isFavMatch = true
        }
    }

    private fun getDetail() {
        val callLeague = ApiClient().getInstance().getDetail(MyConstant.API_KEY, id)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    setup(dataLastNext as List<DataLastNext>)

                }
            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: ", t.message)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun setup(lastNext: List<DataLastNext>) {
        val txtDetailDate = find<TextView>(R.id.txtDetailDate)
        val txtDetailTime = find<TextView>(R.id.txtDetailTime)
        val imgDetailHome = find<ImageView>(R.id.imgDetailHome)
        val txtDetailHome = find<TextView>(R.id.txtDetailHome)
        val txtDetailScoreHome = find<TextView>(R.id.txtDetailScoreHome)
        val txtDetailScoreAway = find<TextView>(R.id.txtDetailScoreAway)
        val imgDetailAway = find<ImageView>(R.id.imgDetailAway)
        val txtDetailAway = find<TextView>(R.id.txtDetailAway)
        val txtDetailGoalHome = find<TextView>(R.id.txtDetailGoalHome)
        val txtDetailGoalAway = find<TextView>(R.id.txtDetailGoalAway)
        val txtDetailYellowHome = find<TextView>(R.id.txtDetailYellowHome)
        val txtDetailYellowAway = find<TextView>(R.id.txtDetailYellowAway)
        val txtDetailRedHome = find<TextView>(R.id.txtDetailRedHome)
        val txtDetailRedAway = find<TextView>(R.id.txtDetailRedAway)
        val btnLineup = find<Button>(R.id.btnLineup)

        floatFav.setOnClickListener {
            if (isFavMatch) removeFromFavMatch() else addToFavMatch(lastNext[0])

            isFavMatch = !isFavMatch
            setFavMatch()
        }

        lastNext.forEach {
            txtDetailDate.text = Util.FormatDate(it.dateEvent)
            txtDetailTime.text = Util.timeFormat(it.strTime)
            txtDetailHome.text = it.strHomeTeam
            txtDetailAway.text = it.strAwayTeam
            txtDetailScoreHome.text = it.intHomeScore
            txtDetailScoreAway.text = it.intAwayScore
            txtDetailGoalHome.text = Util.formatNumPlayer(it.strHomeGoalDetails)
            txtDetailGoalAway.text = Util.formatNumPlayer(it.strAwayGoalDetails)
            txtDetailYellowHome.text = Util.formatNumPlayer(it.strHomeYellowCards)
            txtDetailYellowAway.text = Util.formatNumPlayer(it.strAwayYellowCards)
            txtDetailRedHome.text = Util.formatNumPlayer(it.strHomeRedCards)
            txtDetailRedAway.text = Util.formatNumPlayer(it.strAwayRedCards)
            Util.loadBadge(it.idHomeTeam, imgDetailHome)
            Util.loadBadge(it.idAwayTeam, imgDetailAway)

            if (it.strAwayLineupDefense != null) {
                btnLineup.setOnClickListener {
                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog, null)
                    val mBuilder = AlertDialog.Builder(this)
                            .setView(dialog)

                    lastNext.forEach {
                        Util.loadBadge(it.idHomeTeam, dialog.imgdialoghome)
                        Util.loadBadge(it.idAwayTeam, dialog.imgdialogaway)
                        dialog.txtdialogkiperhome.text = Util.formatPlayer(it.strHomeLineupGoalkeeper)
                        dialog.txtdialogkiperaway.text = Util.formatPlayer(it.strAwayLineupGoalkeeper)
                        dialog.txtdialogdefenderhome.text = Util.formatPlayer(it.strHomeLineupDefense)
                        dialog.txtdialogdefenderaway.text = Util.formatPlayer(it.strAwayLineupDefense)
                        dialog.txtdialogmidfielderhome.text = Util.formatPlayer(it.strHomeLineupMidfield)
                        dialog.txtdialogmidfielderaway.text = Util.formatPlayer(it.strAwayLineupMidfield)
                        dialog.txtdialogforwardhome.text = Util.formatPlayer(it.strHomeLineupForward)
                        dialog.txtdialogforwardaway.text = Util.formatPlayer(it.strAwayLineupForward)
                        dialog.txtdialogsubtituteshome.text = Util.formatPlayer(it.strHomeLineupSubstitutes)
                        dialog.txtdialogsubtitutesaway.text = Util.formatPlayer(it.strAwayLineupSubstitutes)
                    }
                    val alert = mBuilder.create()
                    alert.show()
                }
            } else if (it.strAwayLineupDefense == null) {
                btnLineup.setOnClickListener {
                    Toast.makeText(applicationContext, "Lineups is have not been made", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addToFavMatch(lastNext: DataLastNext) {
        try {
            database.use {
                insert(FavMatch.TABLE_FAVORITE,
                        FavMatch.EVENT_ID to lastNext.idEvent,
                        FavMatch.EVENT_DATE to lastNext.dateEvent,
                        FavMatch.EVENT_TIME to lastNext.strTime,
                        FavMatch.HOME_NAME to lastNext.strHomeTeam,
                        FavMatch.HOME_SCORE to lastNext.intHomeScore,
                        FavMatch.AWAY_NAME to lastNext.strAwayTeam,
                        FavMatch.AWAY_SCORE to lastNext.intAwayScore)
            }
            Toast.makeText(applicationContext, "Added To Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavMatch() {
        try {
            database.use {
                delete(FavMatch.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to id)
            }
            Toast.makeText(applicationContext, "Removed from Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavMatch() {
        if (isFavMatch)
            floatFav?.image = ContextCompat.getDrawable(this, R.drawable.added)
        else
            floatFav?.image = ContextCompat.getDrawable(this, R.drawable.add)
    }
}
