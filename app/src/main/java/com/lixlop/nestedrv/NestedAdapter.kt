package com.lixlop.nestedrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bottom.view.*
import kotlinx.android.synthetic.main.item_custom.view.text_view
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author 小樓 on 2020/6/1
 */
class NestedAdapter(private val dataList: List<NestedModel>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_custom, parent, false)
            )
        } else {
            BottomViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bottom, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(dataList?.get(position))
        } else if (holder is BottomViewHolder) {
            holder.bind(dataList?.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList?.get(position)
        return if (data?.bottom == true) 1 else 0
    }
}


class NestedChildAdapter(private val dataList: List<NestedModel>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_custom, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(dataList?.get(position))
        }
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: NestedModel?) {
        itemView.text_view.text = "${data?.num}"
    }
}

class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: NestedModel?) {
        val context = itemView.context as? AppCompatActivity
        context?.lifecycleScope?.launch {
            //测试数据 start
            val list = ArrayList<NestedModel>()
            withContext(Dispatchers.IO) {
                for (i in 0..20) {
                    val model = NestedModel()
                    model.num = i
                    list.add(model)
                }
            }
            //测试数据 end
            val adapter: NestedChildAdapter? = NestedChildAdapter(list)
            itemView.recycler_view.adapter = adapter
            itemView.recycler_view.layoutManager = LinearLayoutManager(context)
        }
    }
}

class NestedModel {
    var num: Int? = null
    var bottom: Boolean? = null
}