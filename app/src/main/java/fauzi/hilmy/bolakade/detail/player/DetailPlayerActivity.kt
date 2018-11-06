package fauzi.hilmy.bolakade.detail.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.api.ApiClient
import fauzi.hilmy.bolakade.model.player.PlayerItem
import fauzi.hilmy.bolakade.model.player.ResponsePlayer
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.activity_detail_player.*
import retrofit2.Call
import retrofit2.Response

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var player: PlayerItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra("idPlayer")

        title = player.playerName
        Picasso.get().load(player.playerFanart)
                .placeholder(R.drawable.no_image)
                .fit().into(imgDetailPlayer)
        txtDetailWeight.text = player.playerWeight
        txtDetailHeight.text = player.playerHeight
        txtDetailPosition.text = player.playerPosition
        txtDetailOverview.text = player.playerDescription
    }

    private fun getPlayerDetail(id: String) {

        val callLeague = ApiClient().getInstance().getDetailPlayer(id)
        callLeague.enqueue(object : retrofit2.Callback<ResponsePlayer> {
            override fun onResponse(call: Call<ResponsePlayer>, response: Response<ResponsePlayer>) {

                if (response.isSuccessful) {
                    Log.v("Response Player", response.body()!!.player.toString())

                    val list_player: List<PlayerItem> = response.body()!!.player


                }
            }

            override fun onFailure(call: Call<ResponsePlayer>, t: Throwable) {
                Log.e("Error: ", t.message)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
