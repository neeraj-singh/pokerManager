package com.mobilesaaj.pokergame.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mobilesaaj.pokergame.Adapters.PlayerAdapter;
import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.Players.Player;
import com.mobilesaaj.pokergame.R;
import com.mobilesaaj.pokergame.States.GameState;

import java.util.ArrayList;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class RunnigGameFragment extends BaseFragment{
    ListView listViewPlayer;
    ArrayList<Player> arrayList;
    Button btnEndGame;
    PlayerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.running_game,container,false);
        listViewPlayer = (ListView)view.findViewById(R.id.lv_running_players);
        arrayList = GameConstant.playerList;
        adapter = new PlayerAdapter(context,arrayList);
        btnEndGame = (Button)view.findViewById(R.id.btnEndGame);
        listViewPlayer.setAdapter(adapter);

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameConstant.gameState = GameState.Ended;
                callback.MoveToNextState(GameConstant.gameState);
            }
        });
        return view;
    }

    public static RunnigGameFragment instance() {
        return new RunnigGameFragment();
    }
}
