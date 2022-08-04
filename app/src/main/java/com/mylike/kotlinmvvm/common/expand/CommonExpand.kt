package com.mylike.kotlinmvvm.common.expand

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.common.GridDecoration
import com.mylike.kotlinmvvm.common.RvDividerDecoration
import com.mylike.kotlinmvvm.common.SpaceItemDecoration
import com.mylike.kotlinmvvm.utils.CommonUtil

/**
 * Created by ydh on 2022/7/7
 *
 */
fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>
): RecyclerView {
    layoutManager = layoutManger
    addItemDecoration(
        SpaceItemDecoration(
            CommonUtil.dp2px(10.0),
            SpaceItemDecoration.LINEARLAYOUT
        )
    )
    setHasFixedSize(true)//RecyclerView的高度固定的时候设置成true。如果不固定设置成false
    adapter = bindAdapter
    return this
}

fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    dividerDecoration: RvDividerDecoration
): RecyclerView {
    layoutManager = layoutManger
    addItemDecoration(dividerDecoration)
    setHasFixedSize(true)//RecyclerView的高度固定的时候设置成true。如果不固定设置成false
    adapter = bindAdapter
    return this
}

fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    dividerDecoration: SpaceItemDecoration
): RecyclerView {
    layoutManager = layoutManger
    addItemDecoration(dividerDecoration)
    setHasFixedSize(true)//RecyclerView的高度固定的时候设置成true。如果不固定设置成false
    adapter = bindAdapter
    return this
}

fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    dividerDecoration: GridDecoration
): RecyclerView {
    layoutManager = layoutManger
    addItemDecoration(dividerDecoration)
    setHasFixedSize(true)//RecyclerView的高度固定的时候设置成true。如果不固定设置成false
    adapter = bindAdapter
    return this
}

fun String.getInt(): Int {
    if (isEmpty()) return 0
    if (contains(".")) return toDouble().toInt()
    return toInt()
}
