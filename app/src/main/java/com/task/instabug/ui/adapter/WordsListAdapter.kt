package com.task.instabug.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.instabug.R
import kotlinx.android.synthetic.main.word_item.view.*


class WordsListAdapter : RecyclerView.Adapter<WordsListAdapter.WordViewHolder>() {

    private val wordsHashMap = LinkedHashMap<String, Int>()

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(word: String, count: Int) {
            itemView.tv_count.text = word
            itemView.tv_word.text = "$count"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.word_item, parent, false)
        return WordViewHolder(
            inflater
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordsHashMap.keys.elementAt(position), wordsHashMap.values.elementAt(position))
    }

    override fun getItemCount(): Int = wordsHashMap.count()

    fun updateTheData(map: Map<String, Int>) {
        wordsHashMap.clear()
        wordsHashMap.putAll(map)
        this.notifyDataSetChanged()
    }

}