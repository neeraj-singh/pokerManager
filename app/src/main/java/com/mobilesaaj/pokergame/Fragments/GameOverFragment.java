package com.mobilesaaj.pokergame.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mobilesaaj.pokergame.Adapters.PlayerAdapter;
import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.Players.Player;
import com.mobilesaaj.pokergame.R;

import java.util.ArrayList;

/**
 * Created by neeraj.singh on 09/09/15.
 */
public class GameOverFragment extends BaseFragment {
    ListView listView ;
    Button btnGameOver;
    PlayerAdapter adapter;
    ArrayList<Player> arrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_ended,container,false);
        listView = (ListView)view.findViewById(R.id.lv_gameover);
        btnGameOver = (Button)view.findViewById(R.id.btn_game_over);
        arrayList = GameConstant.playerList;
        adapter = new PlayerAdapter(context,arrayList);
        listView.setAdapter(adapter);
        btnGameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Game Over",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }

    public static GameOverFragment instance() {
        return new GameOverFragment();
    }
}
