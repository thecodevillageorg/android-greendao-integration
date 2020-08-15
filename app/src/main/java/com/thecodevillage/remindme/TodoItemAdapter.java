package com.thecodevillage.remindme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.ViewHolder> {

    List<TodoItem> todoItemList;


    public TodoItemAdapter(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TodoItem todoItem= todoItemList.get(position);
        holder.textView.setText(todoItem.getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), todoItem.getName(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return todoItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ConstraintLayout constraintLayout;


        public ViewHolder(View view){
            super(view);
            this.textView = (TextView) view.findViewById(R.id.labelItemName);
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.item_row_layout);
        }


    }
}
