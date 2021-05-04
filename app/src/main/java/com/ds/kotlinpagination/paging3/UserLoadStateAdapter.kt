package com.ds.kotlinpagination.paging3

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ds.kotlinpagination.base.toBinding
import com.ds.kotlinpagination.databinding.UsersLoadStateFooterViewItemBinding

class UserLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<UserLoadStateAdapter.UserLoadStateViewHolder>() {

    inner class UserLoadStateViewHolder(private val binding: UsersLoadStateFooterViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {

            binding.btnRetry.setOnClickListener {
                retry.invoke()
            }

            if (loadState is LoadState.Error) {
                binding.tvErrorMessage.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.tvErrorMessage.isVisible = loadState is LoadState.Error
        }

    }

    override fun onBindViewHolder(holder: UserLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UserLoadStateViewHolder {
        return UserLoadStateViewHolder(parent.toBinding())
    }

}