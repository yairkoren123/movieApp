package com.example.drawer_try.ui.FriendList;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawer_try.R;
import com.example.drawer_try.databinding.AddFriendsFragmentBinding;
import com.example.drawer_try.databinding.ListFriendsFragmentBinding;
import com.example.drawer_try.ui.AddFreind.AddFriendsViewModel;

import org.jetbrains.annotations.NotNull;

public class List_Friends_Fragment extends Fragment {

    private ListFriendsViewModel mViewModel;


    private ListFriendsViewModel list_friends_fragment;
    private ListFriendsFragmentBinding binding;


    public static List_Friends_Fragment newInstance() {
        return new List_Friends_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        list_friends_fragment =
                new ViewModelProvider(this).get(ListFriendsViewModel.class);

        binding = ListFriendsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
