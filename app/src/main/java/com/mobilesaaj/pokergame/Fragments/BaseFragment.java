package com.mobilesaaj.pokergame.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import com.mobilesaaj.pokergame.Interfaces.GameFlowInterface;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class BaseFragment extends Fragment {
    Context context ;
    GameFlowInterface callback;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        try {
            callback = (GameFlowInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement GameFlowInterface");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean handleBackPressed(){
        return false;
    }

    public void showError(TextView textView,String message){
        textView.setText(message);
        textView.setVisibility(View.VISIBLE);
    }

    public void hideError(TextView textView){
        textView.setVisibility(View.GONE);
    }

}
