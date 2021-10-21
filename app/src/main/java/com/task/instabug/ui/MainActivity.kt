package com.task.instabug.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.task.instabug.R
import com.task.instabug.app.InstaBugApp
import com.task.instabug.data.models.WordPresentationModel
import com.task.instabug.di.AppDi
import com.task.instabug.di.PresentationDI
import com.task.instabug.presentation.models.WordsListEventData
import com.task.instabug.presentation.viewmodels.WordsFragmentViewModel
import com.task.instabug.ui.adapter.WordsListAdapter
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    private lateinit var appDi: AppDi
    private lateinit var viewModel: WordsFragmentViewModel
    private lateinit var myResult:  WordPresentationModel
    private lateinit var searchResult:  WordPresentationModel
    private lateinit  var sortedResult :Map<String, Int>

    private val wordsAdapter = WordsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDi = (application as InstaBugApp).appDi
        appDi.presentationDi = PresentationDI(appDi.dataDi)
        appDi.presentationDi?.viewModelFactory?.create()?.let {
            viewModel = it
        }

        viewModel.fetchMappedResponse() // calling it from the view model
        viewModel.receivedData.observe(this, Observer {
            when (it) {
                WordsListEventData.LoadingState -> loadingStatus(true)

                is WordsListEventData.SuccessState -> handleSuccessState(it.data)

                is WordsListEventData.ErrorState -> handleErrorState()
            }
        })
        editTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!= null && s.isNotEmpty()){
                    searchOnTheKeyWord(s.toString())
                }
                else   {   wordsAdapter.updateTheData(myResult.data)
            }
            }
        })


    }
 fun  searchOnTheKeyWord(KeyWord: String ){
   var result: HashMap<String , Int> = hashMapOf()
     if (!this::sortedResult.isInitialized)
       for (i in myResult.data){
         if(i.key.contains(KeyWord)) {
             result.put(i.key, i.value)
         }
       }
     else {
         for (i in sortedResult){
             if(i.key.contains(KeyWord)) {
                 result.put(i.key, i.value)
             }
         }
     }
     searchResult =WordPresentationModel(result)
     wordsAdapter.updateTheData(searchResult.data)
 }

    private fun loadingStatus(status: Boolean) {
        when (status) {
            true -> progressBar.visibility = View.VISIBLE

            false -> progressBar.visibility = View.GONE
        }
    }

    private fun handleSuccessState(data: WordPresentationModel) {
        loadingStatus(false)
        myResult = data
       wordsRV.adapter = wordsAdapter
        wordsAdapter.updateTheData(data.data)
    }



    private fun sortTheResult() {
        if (this::myResult.isInitialized){
            sortedResult = myResult.data.toList()
                    .sortedBy { (_, value) -> value }
                    .toMap()
            wordsAdapter.updateTheData(sortedResult)
        }
    }

    private fun handleErrorState() {
        loadingStatus(false)
        val errorHandler = com.task.instabug.ui.errors.ErrorHandler(this)
        errorHandler.handleError()
    }

    fun searchClick(view: View) {
    app_name.visibility  = View.GONE
    search_icon.visibility  = View.GONE
        editTxt.visibility = View.VISIBLE
        close_icon.visibility= View.VISIBLE
    }

    fun sortClick(view: View) {
        sortTheResult()
    }

    fun closeSearch(view: View) {
        app_name.visibility  = View.VISIBLE
        search_icon.visibility  = View.VISIBLE
        editTxt.visibility = View.GONE
        close_icon.visibility= View.GONE
        if(this::sortedResult.isInitialized)
        wordsAdapter.updateTheData(sortedResult)
        else         wordsAdapter.updateTheData(myResult.data)

    }
}