package com.ulternativetechnology.kotlinwithkointest.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.dagger2.model.RecyclerList
import com.ulternativetechnology.kotlinwithkointest.util.LogUtil
import kotlinx.android.synthetic.main.activity_dagger.*

/* https://www.youtube.com/watch?v=_B0skaOiVCU */
class DaggerActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel
    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DaggerActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getLiveDataObserver().observe(this) { t ->
            if (t != null) {
                recyclerViewAdapter.setUpdatedData(t.items)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "데이터 불러오는 중 에러 발생", Toast.LENGTH_SHORT).show()
            }
        }

        // call.enqueue() 수행
        mainActivityViewModel.makeApiCall()
    }

}