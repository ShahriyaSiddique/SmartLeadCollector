package com.foresight.smartleadcollector.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.foresight.smartleadcollector.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberListAddMemberFragment extends Fragment {


    public MemberListAddMemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_member_list_add_member, container, false);
    }

}
