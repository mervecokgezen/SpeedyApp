package com.merveakgormus.speedy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vestel on 08.03.2018.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_contactname;
        public TextView tv_contactphone;
        public CardView card_view;


        public ViewHolder(View view) {
            super(view);

            card_view       = (CardView)view.findViewById(R.id.card_view);
            tv_contactname  = (TextView)view.findViewById(R.id.tv_contactname);
            tv_contactphone = (TextView)view.findViewById(R.id.tv_contactphone);
        }
    }

    List<Contact> list_contact;
    Contact.CustomItemClickListener listener;
    public SimpleRecyclerAdapter(List<Contact> list_contact, Contact.CustomItemClickListener listener) {

        this.list_contact = list_contact;
        this.listener     = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_contactname.setText(list_contact.get(position).getContact_name());
        holder.tv_contactphone.setText(list_contact.get(position).getContact_phone());
    }

    @Override
    public int getItemCount() {
        return list_contact.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
