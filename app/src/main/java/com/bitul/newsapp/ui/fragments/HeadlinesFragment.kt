package com.bitul.newsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.bitul.newsapp.adapters.NewsAdapter
import com.bitul.newsapp.ui.NewsActivity
import com.bitul.newsapp.ui.NewsViewModel
import com.bitul.newsapp.util.Resource
import com.bitul.newsapp.util.constants
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.databinding.FragmentHeadlinesBinding
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior.OnScrollStateChangedListener

class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {
    lateinit var newsViewModel:NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    lateinit var retryButton:Button
    lateinit var errorText:TextView
    lateinit var itemHeadlineError:CardView
    lateinit var binding: FragmentHeadlinesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeadlinesBinding.bind(view)

        itemHeadlineError=view.findViewById(R.id.itemHeadlinesError)

        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view:View=inflater.inflate(R.layout.item_error,null)

        retryButton=view.findViewById(R.id.retryButton)
        errorText=view.findViewById(R.id.errorText)

        newsViewModel=(activity as NewsActivity).newsViewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundel=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_headlinesFragment3_to_articleFragment2,bundel)

            }
          newsViewModel.headlines.observe(viewLifecycleOwner, Observer { response->
              when(response){
                  is Resource.success<*> ->{
                      hideProgressBar()
                      hideErrorMessage()
                      response.data?.let { newsresponse ->
                          newsAdapter.differ.submitList(newsresponse.articles.toList())
                          val totalPages=newsresponse.totalResults / constants.QUERY_PAGE_SIZE +2
                          isLastPage=newsViewModel.headlinesPage==totalPages
                          if (isLastPage){
                              binding.recyclerHeadlines.setPadding(0,0,0,0)

                          }

                      }
                  }
                  is Resource.Error<*> -> {
                      hideProgressBar()
                      response.message?.let { message ->
                         Toast.makeText(activity,"Sorry error: $message",Toast.LENGTH_LONG).show()
                          showErrorMessage(message)
                      }
                  }
                  is Resource.Loading<*> ->{
                      showprogressBar()
                  }
              }
          })
        retryButton.setOnClickListener {
            newsViewModel.getHeadlines("in")
        }

        }

    var isError=false
    var isLoading=false
    var isLastPage= false
    var isScrollong=false

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.INVISIBLE
        isLoading=false
    }
    private fun showprogressBar(){
        binding.paginationProgressBar.visibility=View.VISIBLE
        isLoading=true
    }
    private fun hideErrorMessage(){
        itemHeadlineError.visibility=View.INVISIBLE
        isError=false
    }
    private fun showErrorMessage(message:String){
        itemHeadlineError.visibility=View.VISIBLE
        errorText.text=message
        isError=true
    }

    val scrollListener=object :RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBegining = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= constants.QUERY_PAGE_SIZE
            val shouldPaginate =
                isNoErrors && isNotLoadingAndLastPage && isAtLastItem && isNotAtBegining && isTotalMoreThanVisible && isScrollong
            if (shouldPaginate) {
                newsViewModel.getHeadlines("in")
                isScrollong = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrollong = true
            }
        }
    }

        private fun setupRecyclerView(){
            newsAdapter = NewsAdapter()
            binding.recyclerHeadlines.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(activity)
                addOnScrollListener(this@HeadlinesFragment.scrollListener)
        }
    }
}