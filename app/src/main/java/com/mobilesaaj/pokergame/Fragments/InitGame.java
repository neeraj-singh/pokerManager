package com.mobilesaaj.pokergame.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.R;
import com.mobilesaaj.pokergame.States.GameState;
import com.mobilesaaj.pokergame.Utils.StringUtils;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class InitGame extends BaseFragment {

    private EditText etCoins;
    private EditText etMoney;
    private Button btnInitGame;
    private TextView errorText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.init_game, container, false);
        etCoins = (EditText)view.findViewById(R.id.et_coinsmapper);
        etMoney = (EditText)view.findViewById(R.id.et_moneymapper);
        btnInitGame = (Button)view.findViewById(R.id.btn_init);
        errorText = (TextView)view.findViewById(R.id.tv_errortext);
        btnInitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
            }
        });
        return view;
    }

    private void getValues() {
        String coins = etCoins.getText().toString();
        String money = etMoney.getText().toString();
        if(!StringUtils.isNullOrEmpty(coins) && !StringUtils.isNullOrEmpty(money)) {
            initSetting(coins, money);
        }else{
            showError(errorText,context.getResources().getString(R.string.emptyerror));
        }
    }

    private void initSetting(String coins, String money) {
        try {
            System.out.println(coins+"---->"+money);
            int coin = Integer.parseInt(coins);
            int rupees = Integer.parseInt(money);
            GameConstant.CoinsMapper = coin;
            GameConstant.MoneyMapper = rupees;
            GameConstant.gameState = GameState.Init;
            callback.MoveToNextState(GameConstant.gameState);
        }catch (Exception e){
            Toast.makeText(context,"Unable to convert "+e.getMessage() ,Toast.LENGTH_LONG).show();
        }
    }

    public static InitGame instance() {
        InitGame fragment = new InitGame();
        return fragment;
    }
}
