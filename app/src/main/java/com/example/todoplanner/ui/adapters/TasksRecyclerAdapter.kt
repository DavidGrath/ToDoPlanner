package com.example.todoplanner.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoplanner.R
import com.example.todoplanner.databinding.RecyclerviewTaskBinding
import com.example.todoplanner.domain.entities.ui.TaskUI

class TasksRecyclerAdapter(val categories: Map<Int, String>, var items: List<TaskUI>,
                           var addButtonListener: AddButtonListener? = null) : RecyclerView.Adapter<TasksRecyclerAdapter.TasksViewHolder>() {

    val VIEW_TYPE_ITEM = 1
    val VIEW_TYPE_ADD_BUTTON = 2

    interface AddButtonListener {
        fun onAddClicked()
    }


    override fun getItemViewType(position: Int): Int {
        return if (position < items.size) VIEW_TYPE_ITEM else VIEW_TYPE_ADD_BUTTON
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = RecyclerviewTaskBinding.inflate(LayoutInflater.from(parent.context))
                return TasksViewHolder.Task(binding)
            }
            VIEW_TYPE_ADD_BUTTON -> {
                return TasksViewHolder.AddButton(LayoutInflater.from(parent.context).inflate(R.layout.task_add_button, null, false))
            }
            else -> {
                val binding = RecyclerviewTaskBinding.inflate(LayoutInflater.from(parent.context))
                return TasksViewHolder.Task(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        if (position == items.size) {
            with(holder as TasksViewHolder.AddButton) {
                itemView.setOnClickListener {
                    addButtonListener?.onAddClicked()
                }
            }
            return
        }
        val item = items[position]
        with(holder as TasksViewHolder.Task) {
            taskTitle.text = item.name
            val timesliceText = "${item.start} - ${item.end}"
            taskTimeslice.text = timesliceText
            taskCategory.text = categories[item.category]
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    open class TasksViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        class Task(binding: RecyclerviewTaskBinding) : TasksViewHolder(binding.root) {
            val taskTimeslice = binding.textviewTaskTimeslice
            val taskTitle = binding.textviewTaskTitle
            val taskCategory = binding.textviewTaskCategory
        }

        class AddButton(val v: View) : TasksViewHolder(v) {

        }
    }
}