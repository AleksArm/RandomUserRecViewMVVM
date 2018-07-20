package com.aleksarm.randomuserrecviewmvvm.adapter;

import android.content.Context;
import android.databinding.*;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aleksarm.randomuserrecviewmvvm.R;
import com.aleksarm.randomuserrecviewmvvm.databinding.UsersBinding;
import com.aleksarm.randomuserrecviewmvvm.viewmodel.UsersViewModel;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomView>{


    private ArrayList<UsersViewModel> modelArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context,ArrayList<UsersViewModel> usersViewModels){
        this.context = context;
        this.modelArrayList = usersViewModels;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        UsersBinding categoryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innerlayout,parent,false);

        return new CustomView(categoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        UsersViewModel usersViewModel = modelArrayList.get(position);
        holder.bind(usersViewModel);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }



        class CustomView extends RecyclerView.ViewHolder {
            private  UsersBinding usersBinding;

            public CustomView(UsersBinding usersBinding) {
                super(usersBinding.getRoot());

                this.usersBinding = usersBinding;
            }

            public void bind(UsersViewModel usersViewModel){
                this.usersBinding.setUsermodel(usersViewModel);
                usersBinding.executePendingBindings();
            }

            public UsersBinding getUsersBinding() {
                return usersBinding;
            }
        }
}
