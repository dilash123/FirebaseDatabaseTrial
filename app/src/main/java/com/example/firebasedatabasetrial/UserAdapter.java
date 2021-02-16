package com.example.firebasedatabasetrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<Students> studentsList;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvUserNumber,  tvUserName;

        public MyViewHolder(View view) {
            super(view);
            tvUserNumber = (TextView) view.findViewById(R.id.tv_mobile);
            tvUserName = (TextView) view.findViewById(R.id.tv_name);

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
         /*   switch (id) {
                case R.id.rl_student_order_parent: {
                    rVItemClickListener.onStudentOrderClick(v, getAdapterPosition());
                    break;
                }

            }*/

        }
    }

    public UserAdapter(List<Students> studentsList, Context context) {
        this.studentsList = studentsList;
        this.context = context;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user_design, parent, false);

        return new UserAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserAdapter.MyViewHolder holder, int position) {
        Students student = studentsList.get(position);
        holder.tvUserName.setText(student.name);
        holder.tvUserNumber.setText(student.contact);
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public void updateList(List<Students> list){
        studentsList = list;
        notifyDataSetChanged();
    }

}

