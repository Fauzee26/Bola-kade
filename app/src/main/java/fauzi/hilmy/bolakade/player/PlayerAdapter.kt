package fauzi.hilmy.bolakade.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.model.player.PlayerItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_player.*

class PlayerAdapter(private val players: List<PlayerItem>, private val listener: (PlayerItem) -> Unit) :
        RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PlayerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_player, parent, false))

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    class PlayerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(players: PlayerItem, listener: (PlayerItem) -> Unit) {
            Picasso.get()
                    .load(players.playerCutout)        
                    .placeholder(R.drawable.ball)
                    .into(imgPlayer)
            txtPlayer.text = players.playerName
            txtPosition.text = players.playerPosition
            itemView.setOnClickListener { listener(players) }

        }
    }
}