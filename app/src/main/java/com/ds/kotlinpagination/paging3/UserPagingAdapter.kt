package com.ds.kotlinpagination.paging3

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.kotlinpagination.base.toBinding
import com.ds.kotlinpagination.data.model.User
import com.ds.kotlinpagination.databinding.RowItemUserBinding

class UserPagingAdapter :
    PagingDataAdapter<User, UserPagingAdapter.UserViewHolder>(UserComparator) {

    var onUserClicked: ((User) -> Unit)? = null


    companion object UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem == newItem
    }

    inner class UserViewHolder(private val binding: RowItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: User) {
            Glide.with(binding.root.context).load(item.avatar)
                .into(binding.ivProfile)

            binding.tvEmail.text = item.email
            binding.tvName.text = "${item.firstName} ${item.lastName}"

            binding.root.setOnClickListener {
                onUserClicked?.invoke(item)
            }
        }

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent.toBinding())
    }

}