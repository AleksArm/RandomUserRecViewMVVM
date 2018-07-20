package com.aleksarm.randomuserrecviewmvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.aleksarm.randomuserrecviewmvvm.R;
import com.aleksarm.randomuserrecviewmvvm.model.Users;
import com.aleksarm.randomuserrecviewmvvm.repository.UserRepository;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class UsersViewModel extends ViewModel{

    private String name;
    private String email;
    private int age;
    private String dateOfBirth;
    private String phoneNumber;
    private String imagePath;

    private UserRepository userRepository;

    private static   MutableLiveData<ArrayList<UsersViewModel>> liveData = new MutableLiveData<>();


    public UsersViewModel(){
        userRepository = new UserRepository();
        liveData = userRepository.getArrayListMutableLiveData();
    }

    public  UsersViewModel(Users users){
        this.name = users.getName().getFirst();
        this.email = users.getEmail();
        this.age = users.getDob().getAge();
        this.dateOfBirth = users.getDob().getDate().substring(0,10);
        this.phoneNumber = users.getPhone();
        this.imagePath = users.getPicture().getMedium();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImg(ImageView imageView,String imgUrl){
        Picasso.with(imageView.getContext()).load(imgUrl).placeholder(R.drawable.ic_launcher_background).into(imageView);
    }


    public MutableLiveData<ArrayList<UsersViewModel>> getLiveData() {
        return liveData;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

}
