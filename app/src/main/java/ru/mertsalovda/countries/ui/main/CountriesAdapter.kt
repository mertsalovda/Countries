package ru.mertsalovda.countries.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import ru.mertsalovda.countries.databinding.ItemCountryBinding
import ru.mertsalovda.countries.models.data.Country

class CountriesAdapter(private val listener: (Country) -> Unit) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private var items = listOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    /**
     * Обновить список элементов.
     *
     * @param data новый список элементов.
     */
    fun updateData(data: List<Country>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].name == data[newPos].name

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean =
                items[oldPos].hashCode() == data[newPos].hashCode()

            override fun getOldListSize(): Int = items.size
            override fun getNewListSize(): Int = data.size
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items = data
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Представляет ViewHolder для списка стран.
     */
    class CountryViewHolder(var binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country, listener: (Country) -> Unit) {
            itemView.setOnClickListener { listener.invoke(item) }
            // Загружаю изображение если .svg
                GlideToVectorYou
                    .init()
                    .with(itemView.context)
                    .load(Uri.parse(item.flag), binding.ivFlag)
            binding.tvCountryName.text = item.name
        }
    }
}
