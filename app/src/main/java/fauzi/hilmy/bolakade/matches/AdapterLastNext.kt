package fauzi.hilmy.bolakade.matches

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.model.match.DataLastNext
import kotlinx.android.extensions.LayoutContainer
import fauzi.hilmy.bolakade.util.Util
import kotlinx.android.synthetic.main.listitem.*

class AdapterLastNext(private val lastNext: List<DataLastNext>, private val listener: (DataLastNext) -> Unit) :
        RecyclerView.Adapter<AdapterLastNext.LastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false))


    override fun getItemCount(): Int = lastNext.size

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.bindItem(lastNext[position], listener)
    }

    class LastViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(lastNext: DataLastNext, listener: (DataLastNext) -> Unit) {
            txtDate.text = Util.formatDate(lastNext.dateEvent)
            txtAway.text = lastNext.strAwayTeam
            txtTime.text = Util.timeFormat(lastNext.strTime)
            txtHome.text = lastNext.strHomeTeam
            txtScoreAway.text = lastNext.intAwayScore
            txtScoreHome.text = lastNext.intHomeScore

            itemView.setOnClickListener { listener(lastNext) }
        }
    }
}