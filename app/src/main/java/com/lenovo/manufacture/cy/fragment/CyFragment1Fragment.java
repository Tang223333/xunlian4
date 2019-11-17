package com.lenovo.manufacture.cy.fragment;

import android.content.Intent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.cy.activity.cyBannerActivity;

public class CyFragment1Fragment extends Fragment implements View.OnClickListener {

    private TextView fragmenttv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cy_fragment1, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmenttv1 = (TextView) view.findViewById(R.id.fragmenttv1);
        fragmenttv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragmenttv1:
                startActivity(new Intent(getActivity(), cyBannerActivity.class));
                break;
        }
    }
}
