package fauzi.hilmy.bolakade.favorite.Favteam

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.id.teamLogo
import fauzi.hilmy.bolakade.R.id.teamNama
import fauzi.hilmy.bolakade.favorite.db.FavTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AdapterFavoriteTeam(private val favTeam: List<FavTeam>, private val listener: (FavTeam) -> Unit)
    : RecyclerView.Adapter<FavTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamViewHolder {
        return FavTeamViewHolder(FavTeamUI().createView(AnkoContext.create(parent.context, parent)))
    }
    override fun getItemCount(): Int = favTeam.size

    override fun onBindViewHolder(holder: FavTeamViewHolder, i: Int) {
        holder.bindItem(favTeam[i], listener)
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

class FavTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(teamLogo)
    private val teamName: TextView = view.find(teamNama)

    fun bindItem(favteam: FavTeam, listener: (FavTeam) -> Unit) {
        Picasso.get()
                .load(favteam.teamLogo)
                                .placeholder(R.drawable.no_image)
                .into(teamBadge)
        teamName.text = favteam.teamName
        itemView.onClick { listener(favteam) }

    }
}
