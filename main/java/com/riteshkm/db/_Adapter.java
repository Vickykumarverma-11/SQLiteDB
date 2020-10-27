package com.riteshkm.db;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class _Adapter extends RecyclerView.Adapter<_Adapter._Holder> {

    List<Person> personList;
    Context context;

    _Adapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public _Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);

        return new _Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull _Holder holder, int position) {

        if(personList != null) {
            Person person = personList.get(position);
            holder.tvname.setText(person.getName());
            holder.tvmob.setText("" + person.getMob());
        }

    }

    @Override
    public int getItemCount() {

        if(personList != null) return personList.size();
        else return 0;

    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public Person getPersonAt(int position) {
        return personList.get(position);
    }

    class _Holder extends RecyclerView.ViewHolder {
        public TextView tvname,tvmob;

        public _Holder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tv1);
            tvmob = itemView.findViewById(R.id.tv2);


        }
    }
}
