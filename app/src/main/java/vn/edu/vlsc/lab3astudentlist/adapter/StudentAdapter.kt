package vn.edu.vlsc.lab3astudentlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vn.edu.vlsc.lab3astudentlist.R
import vn.edu.vlsc.lab3astudentlist.databinding.ItemStudentBinding
import vn.edu.vlsc.lab3astudentlist.model.Student

class StudentAdapter(
    private val onItemClick: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(
    StudentDiffCallback
) {

    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(student: Student) {
            binding.tvName.text = student.name
            binding.tvMssv.text = binding.root.context
                .getString(R.string.student_mssv, student.mssv)
            binding.imgAvatar.setImageResource(student.avatarRes)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: StudentViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

object StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Student, newItem: Student) =
        oldItem == newItem
}