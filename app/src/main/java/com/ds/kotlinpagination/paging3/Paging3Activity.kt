package com.ds.kotlinpagination.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.ds.kotlinpagination.databinding.ActivityPaging3Binding
import com.ds.kotlinpagination.utils.observeFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class Paging3Activity : AppCompatActivity() {

    private val userAdapter by lazy { UserPagingAdapter() }

    private val userMultiAdapter by lazy { UserMultiPagingAdapter() }

    private val viewModel by viewModel<Paging3ViewModel>()

    lateinit var binding: ActivityPaging3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaging3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        observeState()
        initAdapter()
        viewModel.getUsers()
    }

    private fun initAdapter() {
        binding.rvUser.setHasFixedSize(true)
        //binding.rvUser.adapter = userAdapter
        /*binding.rvUser.adapter = userAdapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter { userAdapter.retry() },
            footer = UserLoadStateAdapter { userAdapter.retry() }
        )*/
        binding.rvUser.adapter = userMultiAdapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter { userMultiAdapter.retry() },
            footer = UserLoadStateAdapter { userMultiAdapter.retry() }
        )

        userAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && userAdapter.itemCount == 0
            showEmptyList(userAdapter.itemCount == 0)
            checkLoadState(loadState)
        }
    }

    private fun checkLoadState(loadState: CombinedLoadStates) {
        /*if (loadState.refresh is LoadState.Loading) {

            mainBinding.btnRetry.visibility = View.GONE

            // Show ProgressBar
            mainBinding.progressBar.visibility = View.VISIBLE
        }
        else {
            // Hide ProgressBar
            mainBinding.progressBar.visibility = View.GONE

            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    mainBinding.btnRetry.visibility = View.VISIBLE
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.let {
                Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
            }
        }*/
    }

    private fun showEmptyList(listEmpty: Boolean) {
        if (listEmpty) {
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.tvEmpty.visibility = View.INVISIBLE
        }
    }

    private fun observeState() {
        observeFlow(viewModel.viewState) {
            userAdapter.submitData(lifecycle, it)
        }

        observeFlow(viewModel.getUserList()) {
            userMultiAdapter.submitData(lifecycle, it)
        }

        /*lifecycleScope.launch {
            userAdapter.loadStateFlow.collectLatest { loadStates ->
                *//*progressBar.isVisible = loadStates.refresh is LoadState.Loading
                retry.isVisible = loadStates.refresh !is LoadState.Loading
                errorMsg.isVisible = loadStates.refresh is LoadState.Error*//*
            }
        }*/
    }
}