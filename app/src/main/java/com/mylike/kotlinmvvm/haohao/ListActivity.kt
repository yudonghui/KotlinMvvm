package com.mylike.kotlinmvvm.haohao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.databinding.ActivityListBinding
import com.mylike.kotlinmvvm.databinding.ItemStudentBinding
import com.mylike.kotlinmvvm.haohao.bean.StudentList
import com.mylike.kotlinmvvm.haohao.viewmodel.ListViewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initAdapter()
        initObserver()
        viewModel.getStudentList()
    }

    private fun initObserver() {
        viewModel.studentList.observe(this) { studentList ->
            studentList.list?.let {
                adapter.list.clear()
                adapter.list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        adapter = Adapter(ArrayList())
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        )
    }

    inner class Adapter(val list: ArrayList<StudentList.StudentBean>) :
        RecyclerView.Adapter<Adapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding =
                ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindingData(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class ViewHolder(private val itemStudentBinding: ItemStudentBinding) :
            RecyclerView.ViewHolder(itemStudentBinding.root) {

            fun bindingData(itemData: StudentList.StudentBean) {
                itemStudentBinding.itemData = itemData
                binding.executePendingBindings()
            }
        }
    }
}