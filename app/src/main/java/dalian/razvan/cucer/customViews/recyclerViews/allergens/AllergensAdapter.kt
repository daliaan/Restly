package dalian.razvan.cucer.customViews.recyclerViews.allergens

import android.view.LayoutInflater
import android.view.ViewGroup
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewAdapter
import dalian.razvan.cucer.models.product.Allergen

class AllergensAdapter: BaseRecyclerViewAdapter<Allergen, AllergensViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergensViewHolder
            = AllergensViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.allergen_cell, parent, false))

    fun convertStringsArray(strings: ArrayList<String>) : ArrayList<Allergen> {
        val allergens = arrayListOf<Allergen>()

        for (i in 0 until strings.size) {
            allergens.add(Allergen(strings[i]))
        }

        return allergens
    }
}