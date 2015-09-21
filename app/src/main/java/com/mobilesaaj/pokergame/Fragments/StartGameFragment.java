package com.mobilesaaj.pokergame.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
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
public class StartGameFragment extends BaseFragment {

    AutoCompleteTextView etName;
    AutoCompleteTextView etBuyin;
    Button btnAddPlayer;
    ListView listViewPlayer;
    ArrayList<Player> arrayList;
    PlayerAdapter adapter  ;
    Button startGame;

    public static StartGameFragment instance(){
        return new StartGameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_game, container, false);
        etName = (AutoCompleteTextView)view.findViewById(R.id.et_name);
        etBuyin = (AutoCompleteTextView)view.findViewById(R.id.et_buyin);
        btnAddPlayer = (Button)view.findViewById(R.id.btn_addplayer);
        listViewPlayer = (ListView)view.findViewById(R.id.lv_players);
        arrayList = new ArrayList<Player>();
        startGame = (Button)view.findViewById(R.id.btnStartGame);
        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayerTolist();
            }
        });
        adapter = new PlayerAdapter(context,arrayList);
        listViewPlayer.setAdapter(adapter);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameConstant.playerList = arrayList;
                GameConstant.gameState = GameState.Running;
                callback.MoveToNextState(GameConstant.gameState);
            }
        });
        return view;
    }

    private void addPlayerTolist() {
        String name = etName.getText().toString();
        String buyin = etBuyin.getText().toString();
        Player player = new Player();
        player.setName(name);
        player.setBuyin(buyin);
        arrayList.add(player);
        adapter.notifyDataSetChanged();
        etName.setText("");
        etBuyin.setText("1");
    }
}
