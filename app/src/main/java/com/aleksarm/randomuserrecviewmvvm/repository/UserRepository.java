package com.aleksarm.randomuserrecviewmvvm.repository;

import android.arch.lifecycle.MutableLiveData;


import com.aleksarm.randomuserrecviewmvvm.model.Users;
import com.aleksarm.randomuserrecviewmvvm.remote.ApiService;
import com.aleksarm.randomuserrecviewmvvm.remote.RetroClass;
import com.aleksarm.randomuserrecviewmvvm.viewmodel.UsersViewModel;
import com.aleksarm.randomuserrecviewmvvm.remote.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private MutableLiveData<ArrayList<UsersViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<UsersViewModel> arrayUsersViewModels;
    private ArrayList<Users> items;


    public UserRepository(){
    }

    public MutableLiveData<ArrayList<UsersViewModel>> getArrayListMutableLiveData() {
        ApiService apiService = RetroClass.getApiService();

        final Call<UserList> userListCall = apiService.getUsersList();

        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                items = response.body().getUsers();
                Users users;
                UsersViewModel usersViewModel;
                arrayUsersViewModels  = new ArrayList<>();
           //  Log.e("gender",items.get(0).getEmail());

                for (int i = 0; i < items.size(); i++) {
                    users = new Users(items.get(i).getName(),items.get(i).getEmail(),items.get(i).getDob(),items.get(i).getPhone(),items.get(i).getPicture());
                    usersViewModel = new UsersViewModel(users);

                    arrayUsersViewModels.add(usersViewModel);
                }

                arrayListMutableLiveData.setValue(arrayUsersViewModels);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });


        return arrayListMutableLiveData;
    }
}
