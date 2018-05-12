package com.arthas.finefragment.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.arthas.finefragment.anno.Layout;


/**
 * Created by Administrator on 2016/8/18.
 */
public abstract class BaseFragment extends Fragment {
    public View mainView;

    public Activity context;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        context = context == null ? getActivity() : context;
        if (mainView == null) {
            Layout layoutAnnotation = getClass().getAnnotation(Layout.class);

            int layoutId = layoutAnnotation.value();
            mainView = inflater.inflate(layoutId, null);

            onViewBind();
            onFinishedCreate();





        } else {
            ViewGroup parent = (ViewGroup) mainView.getParent();
            if (parent != null) {
                parent.removeView(mainView);
            }
        }

        return mainView;
    }

    protected void onViewBind() {

    }


    @Override
    public void onDestroy() {

        if (mainView != null) {
            ViewGroup parent = (ViewGroup) mainView.getParent();
            if (parent != null) {
                parent.removeView(mainView);
            }
            mainView = null;
        }
        super.onDestroy();
    }





    protected abstract void onFinishedCreate();


    protected Intent getIntent() {
        return context.getIntent();
    }

    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }



    public <E extends View> E findViewById(int id) {
        return (E) mainView.findViewById(id);
    }


    public void finish() {

        getActivity().finish();
    }


}
