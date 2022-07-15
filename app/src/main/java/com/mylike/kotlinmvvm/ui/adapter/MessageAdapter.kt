package com.mylike.kotlinmvvm.ui.adapter

import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.ui.bean.MessageEntity

/**
 * Created by ydh on 2022/7/7
 *
 */
class MessageAdapter : BaseQuickAdapter<MessageEntity, BaseViewHolder>(R.layout.item_message) {
    override fun convert(holder: BaseViewHolder, item: MessageEntity) {
        holder.setText(R.id.tv_title, item.msgTypeName)
        holder.setText(R.id.tv_content, item.msgContent)
    }
}