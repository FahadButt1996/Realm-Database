package com.example.realmdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realmdatabase.database.User

class UserAdapter(
    var list: List<User>,
    var context: Context,
    var genericAdapterCallback: GenericAdapterCallback
) :
    RecyclerView.Adapter<UserAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StatusViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.user_child_item, parent, false)
        return StatusViewHolder(v)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.username.setText(list.get(position).name)
        holder.email.setText(list.get(position).email)
        holder.age.setText(list.get(position).age)
        holder.cnic.setText(list.get(position).cnic)
        if (!list.get(position).nationality.isNullOrEmpty()) {
            holder.nationality.setText(list.get(position).nationality)
        } else {
            holder.nationality.setText("--")
        }

        holder.delete.setOnClickListener {
            genericAdapterCallback.getClickedObject(list.get(position), position, "User")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = list.size

    class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView =
            itemView.findViewById(R.id.child_name)
        val email: TextView =
            itemView.findViewById(R.id.child_email)

        val age: TextView =
            itemView.findViewById(R.id.child_age)

        val cnic: TextView =
            itemView.findViewById(R.id.child_cnin)

        val nationality: TextView =
            itemView.findViewById(R.id.child_nationality)

        val delete: Button =
            itemView.findViewById(R.id.child_delete)


    }
}
