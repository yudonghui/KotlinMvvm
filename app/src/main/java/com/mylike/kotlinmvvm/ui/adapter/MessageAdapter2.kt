package com.mylike.kotlinmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mylike.kotlinmvvm.databinding.ActivityMainBinding
import com.mylike.kotlinmvvm.databinding.ActivitySecondBinding
import com.mylike.kotlinmvvm.databinding.ItemMessageIBinding
import com.mylike.kotlinmvvm.ui.bean.MessageEntity

/**
 * Created by ydh on 2022/7/14
 *
 */
class MessageAdapter2(val list: ArrayList<MessageEntity>,val mBinding: ActivitySecondBinding) :
    RecyclerView.Adapter<MessageAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMessageIBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    inner class ViewHolder(private val itemMessageIBinding: ItemMessageIBinding) :
        RecyclerView.ViewHolder(itemMessageIBinding.root) {
        fun bindingData(messageEntity: MessageEntity) {
            itemMessageIBinding.messageEntity = messageEntity
            mBinding.executePendingBindings()
        }
    }

}