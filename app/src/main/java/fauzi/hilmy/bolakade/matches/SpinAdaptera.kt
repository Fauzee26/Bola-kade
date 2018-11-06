package fauzi.hilmy.bolakade.matches

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import fauzi.hilmy.bolakade.model.League

class SpinAdaptera(
        context: Context,
        contextViewResourceId: Int,
        private val values: List<League>) : ArrayAdapter<League>(context, contextViewResourceId, values) {

    override fun getCount(): Int {
        return values.size
    }


    override fun getItem(position: Int): League? {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].leagueName

        return label
    }

    override fun getDropDownView(position: Int, convertView: View?,
                                 parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].leagueName

        return label
    }
}
