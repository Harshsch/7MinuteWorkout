package com.example.a7minuteworkout

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter (val items :ArrayList<ExcerciseModel>)
    :RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>()
{
    class ViewHolder (binding: ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){

        val tvItem = binding.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExcerciseModel = items [position]
       holder.tvItem.text=model.getId().toString()


            if(model.getisSelected()) {
                holder.tvItem.background =
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.item_circular_thin_color_accent_border
                    )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))


                if (model.getisCompleted()) {
                    holder.tvItem.background =
                        ContextCompat.getDrawable(
                            holder.itemView.context,
                            R.drawable.item_circular_color_accent_background
                        )
                    holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }


        }

    }

