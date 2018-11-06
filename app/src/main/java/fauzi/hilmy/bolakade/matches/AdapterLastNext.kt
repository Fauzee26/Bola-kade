package fauzi.hilmy.bolakade.matches

import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.model.match.DataLastNext
import kotlinx.android.extensions.LayoutContainer
import fauzi.hilmy.bolakade.util.Util
import fauzi.hilmy.bolakade.util.dateTimeToFormat
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.listitem.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.concurrent.TimeUnit

class AdapterLastNext(private val lastNext: List<DataLastNext>, private val isPrev: Boolean, private val listener: (DataLastNext) -> Unit) :
        RecyclerView.Adapter<AdapterLastNext.LastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false))


    override fun getItemCount(): Int = lastNext.size

    override fun onBindViewHolder(holder: LastViewHolder, position: Int) {
        holder.bindItem(lastNext[position], isPrev, listener)
    }

    class LastViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(lastNext: DataLastNext, isNext: Boolean, listener: (DataLastNext) -> Unit) {
            txtDate.text = Util.formatDate(lastNext.dateEvent)
            txtAway.text = lastNext.strAwayTeam
            txtTime.text = Util.timeFormat(lastNext.strTime)
            txtHome.text = lastNext.strHomeTeam
            txtScoreAway.text = lastNext.intAwayScore
            txtScoreHome.text = lastNext.intHomeScore
            if (isNext) btnNotify.invisible() else btnNotify.visible()
            btnNotify.onClick {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = "vnd.android.cursor.item/event"

                val dateTime = lastNext.dateEvent + " " + lastNext.strTime
                Log.d("dateTime", "DateTime adalah " + dateTime)
                val startTime = dateTime.dateTimeToFormat()
                Log.d("startTime", "startTime adalah " + startTime)
                val endTime = startTime + TimeUnit.MINUTES.toMillis(90)
                Log.d("endTime", "endTime adalah " + endTime)

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, TimeUnit.MINUTES.toMillis(90))

                intent.putExtra(CalendarContract.Events.TITLE,
                        "${lastNext.strHomeTeam} vs ${lastNext.strAwayTeam}")

                intent.putExtra(CalendarContract.Events.DESCRIPTION,
                        "Reminder ${lastNext.strHomeTeam} vs ${lastNext.strAwayTeam} from My Football App")


                itemView.context.startActivity(intent)
            }
            itemView.setOnClickListener { listener(lastNext) }
        }
    }
}