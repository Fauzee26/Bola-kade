package fauzi.hilmy.bolakade.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.model.team.TeamsItem
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamsAdapter(private val listTeam: List<TeamsItem>, private val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(FavTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, i: Int) {
        holder.bindItem(listTeam[i], listener)
    }
}

class FavTeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.teamLogo
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.teamNama
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.teamLogo)
    private val teamName: TextView = view.find(R.id.teamNama)

    fun bindItem(listTeam: TeamsItem, listener: (TeamsItem) -> Unit) {
        Picasso.get()
                .load(listTeam.teamLogo)
                                .placeholder(R.drawable.no_image)
                .into(teamBadge)
        teamName.text = listTeam.teamName
        itemView.onClick { listener(listTeam) }

    }
}
