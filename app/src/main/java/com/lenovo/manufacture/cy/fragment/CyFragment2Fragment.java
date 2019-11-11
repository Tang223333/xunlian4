package com.lenovo.manufacture.cy.fragment;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;

public class CyFragment2Fragment extends Fragment {

    private TextView fragmenttv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cy_fragment2, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmenttv1 = (TextView) view.findViewById(R.id.fragmenttv2);
    }

}
