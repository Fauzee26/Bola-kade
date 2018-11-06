package fauzi.hilmy.bolakade.favorite.Favmatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.id.*
import fauzi.hilmy.bolakade.favorite.db.FavMatch
import fauzi.hilmy.bolakade.util.Util
import fauzi.hilmy.bolakade.util.invisible
import org.jetbrains.anko.find

class AdapterFavoriteMatch(private val favMatch: List<FavMatch>, private val listener: (FavMatch) -> Unit)
    : RecyclerView.Adapter<FavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
            FavViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false))

    override fun getItemCount(): Int = favMatch.size

    override fun onBindViewHolder(holder: FavViewHolder, i: Int) {
        holder.bindItem(favMatch[i], listener)
    }
}

class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtDatee: TextView = view.find(txtDate)
    private val txtTimee: TextView = view.find(txtTime)
    private val txtAwayy: TextView = view.find(txtAway)
    private val txtHomee: TextView = view.find(txtHome)
    private val txtScoreAwayy: TextView = view.find(txtScoreAway)
    private val txtScoreHomee: TextView = view.find(txtScoreHome)
    private val btnNotifyy: ImageView = view.find(btnNotify)

    fun bindItem(favMatch: FavMatch, listener: (FavMatch) -> Unit) {
        txtDatee.text = Util.formatDate(favMatch.eventDate)
        txtTimee.text = Util.timeFormat(favMatch.eventTime)
        txtHomee.text = favMatch.homeName
        txtAwayy.text = favMatch.awayName
        txtScoreAwayy.text = favMatch.awayScore
        txtScoreHomee.text = favMatch.homeScore

        btnNotifyy.invisible()
        itemView.setOnClickListener { listener(favMatch) }
    }
}
