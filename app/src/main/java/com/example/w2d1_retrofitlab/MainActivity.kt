package com.example.w2d1_retrofitlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    val repository: WikiRepository = WikiRepository()
    val query = MutableLiveData<String>()


    fun queryName(name:String) {
        Log.d("hitCountCheck","queryName")
        query.value = name }

    val hitCount = query.switchMap {
        Log.d("hitCountCheck","chekcing")
        liveData(Dispatchers.IO) { emit(repository.hitCountCheck(it)) }
    }

}



class MainActivity : AppCompatActivity() {
    lateinit var  viewModel: MainViewModel
    private val changeObserver =
        Observer<String> {
                value -> value?.let { viewModel.hitCount }
        }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener {
            viewModel.queryName("Trump")
            Log.d("apiCall", viewModel.query.value.toString())
            Log.d("apiCall", viewModel.hitCount.value.toString())
        }


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.query.observe(this, changeObserver)
        viewModel.hitCount.observe(
            this,{
                val hitCountString = it.query.searchinfo.totalhits.toString()
                textView.text = "hits${hitCountString}"
            }
        )

    }
}