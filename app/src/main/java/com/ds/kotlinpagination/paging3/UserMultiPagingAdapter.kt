package com.ds.kotlinpagination.paging3

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.kotlinpagination.R
import com.ds.kotlinpagination.base.toBinding
import com.ds.kotlinpagination.data.model.User
import com.ds.kotlinpagination.databinding.RowItemSeparatorBinding
import com.ds.kotlinpagination.databinding.RowItemUserBinding

class UserMultiPagingAdapter :
    PagingDataAdapter<UserUiModel, RecyclerView.ViewHolder>(UIMODEL_COMPARATOR) {

    inner class SeparatorViewHolder(private val binding: RowItemSeparatorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(separatorText: String) {
            binding.textView.text = separatorText
        }

    }

    inner class UserViewHolder(private val binding: RowItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: User) {
            Glide.with(binding.root.context).load(item.avatar)
                .into(binding.ivProfile)

            binding.tvEmail.text = item.email
            binding.tvName.text = "${item.firstName} ${item.lastName}"
        }

    }

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<UserUiModel>() {
            override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
                return (oldItem is UserUiModel.UserItem && newItem is UserUiModel.UserItem &&
                        oldItem.user.email == newItem.user.email) ||
                        (oldItem is UserUiModel.SeparatorItem && newItem is UserUiModel.SeparatorItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean =
                oldItem == newItem
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UserUiModel.UserItem -> R.layout.row_item_user
            is UserUiModel.SeparatorItem -> R.layout.row_item_separator
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel?.let {
            when (it) {
                is UserUiModel.UserItem -> (holder as UserViewHolder).bind(it.user)
                is UserUiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(it.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.row_item_user) {
            UserViewHolder(parent.toBinding())
        } else {
            SeparatorViewHolder(parent.toBinding())
        }
    }
}