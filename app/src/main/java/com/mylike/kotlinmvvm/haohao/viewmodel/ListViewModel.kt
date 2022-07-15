package com.mylike.kotlinmvvm.haohao.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylike.kotlinmvvm.haohao.bean.StudentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @description
 * @author: created by peihao.feng
 * @date: 2022/7/9
 */
class ListViewModel: ViewModel() {

    val studentList = MutableLiveData<StudentList>()

    fun getStudentList(){
        viewModelScope.launch {
            //模拟网络请求
            delay(2000)
            val list = ArrayList<StudentList.StudentBean>()
            val students = StudentList(list)
            list.add(StudentList.StudentBean("张三",13,"上海市浦东新区"))
            list.add(StudentList.StudentBean("李四",14,"上海市普陀区"))
            list.add(StudentList.StudentBean("王五",12,"上海是宝山区"))
            list.add(StudentList.StudentBean("赵六",13,"上海是闵行区"))
            studentList.postValue(students)
        }
    }
}