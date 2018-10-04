package fauzi.hilmy.bola_kade

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import fauzi.hilmy.bola_kade.api.ApiClient
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.ResponseLastNext
import fauzi.hilmy.bola_kade.util.MyConstant
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.ID_EVENT
import fauzi.hilmy.bola_kade.util.Util
import kotlinx.android.synthetic.main.dialog.view.*
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    var id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle("Detail Match")
        id = intent.getStringExtra(ID_EVENT)
        getDetail()
    }

    fun getDetail() {
        val callLeague = ApiClient().getInstance().getDetail(MyConstant.API_KEY, id)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    setup(dataLastNext as List<DataLastNext>)

                }
            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: Load Last = ", t.message)
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
            Util.loadBadge(it.idHomeTeam, imgDetailHome, applicationContext)
            Util.loadBadge(it.idAwayTeam, imgDetailAway, applicationContext)
            if (it.strAwayLineupDefense != null) {
                btnLineup.setOnClickListener {
                    val dialog = LayoutInflater.from(this).inflate(R.layout.dialog, null)
                    val mBuilder = AlertDialog.Builder(this)
                            .setView(dialog)
                    //show dialog
//                    val  mAlertDialog = mBuilder.show()
                    lastNext.forEach {
                        Util.loadBadge(it.idHomeTeam, dialog.imgdialoghome, applicationContext)
                        Util.loadBadge(it.idAwayTeam, dialog.imgdialogaway, applicationContext)
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
//                    mAlertDialog.dismiss()
                }
            } else if (it.strAwayLineupDefense == null){
                btnLineup.isEnabled = false
                btnLineup.setOnClickListener {
                    Toast.makeText(applicationContext, "Lineups is have not been made", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
