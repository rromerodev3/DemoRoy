package com.example.demoroy.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroy.databinding.UserItemBinding
import com.example.demoroy.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.binding.apply {
            txtVwUserName.text = "Name: ${user.name}"
            txtVwUserEmail.text = "Email: ${user.email}"
        }
    }

    fun setUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root)
}