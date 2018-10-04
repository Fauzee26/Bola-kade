package fauzi.hilmy.bola_kade.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bola_kade.DetailActivity
import fauzi.hilmy.bola_kade.R
import fauzi.hilmy.bola_kade.model.DataLastNext
import kotlinx.android.extensions.LayoutContainer
import fauzi.hilmy.bola_kade.util.Util
import kotlinx.android.synthetic.main.listitem.*

class AdapterLast(private val lastNext: List<DataLastNext>, private val listener: (DataLastNext) -> Unit) :
        RecyclerView.Adapter<AdapterLast.LastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false))


    override fun getItemCount(): Int = lastNext.size

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.bindItem(lastNext[position], listener)
//        holder.itemView.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, DetailActivity::class.java)
//            intent.putExtra("idHome", lastNext.get(position).idHomeTeam)
//            context.startActivity(intent)
//        }
    }

    class LastViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(lastNext: DataLastNext, listener: (DataLastNext) -> Unit) {
            txtDate.text = Util.FormatDate(lastNext.dateEvent)
            txtAway.text = lastNext.strAwayTeam
//            Util.timeFormat(txtTime, lastNext.strTime)
            txtTime.text = Util.timeFormat(lastNext.strTime)
            txtHome.text = lastNext.strHomeTeam
            txtScoreAway.text = lastNext.intAwayScore
            txtScoreHome.text = lastNext.intHomeScore

            itemView.setOnClickListener { listener(lastNext) }
        }
    }
}