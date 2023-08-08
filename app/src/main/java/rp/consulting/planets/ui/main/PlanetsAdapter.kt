package rp.consulting.planets.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rp.consulting.planets.R

class PlanetsAdapter(val clickListener : (PlanetData) -> Unit) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private lateinit var data: List<PlanetData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planets_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(list: List<PlanetData>) {
        this.data = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(planetData: PlanetData) {
            view.findViewById<TextView>(R.id.title).text = planetData.name
            view.findViewById<TextView>(R.id.description).text = planetData.description
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            Glide
                .with(imageView)
                .load(planetData.imageUrl)
                .centerCrop()
                .into(imageView)
            view.setOnClickListener {
                clickListener(planetData)
            }
        }
    }
}
