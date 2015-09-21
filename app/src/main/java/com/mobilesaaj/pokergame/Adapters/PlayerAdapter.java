package com.mobilesaaj.pokergame.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.Players.Player;
import com.mobilesaaj.pokergame.R;
import com.mobilesaaj.pokergame.States.GameState;
import com.mobilesaaj.pokergame.Utils.GameUtil;
import com.mobilesaaj.pokergame.Utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class PlayerAdapter extends BaseAdapter {

    Context context;
    ArrayList<Player> players;
    public PlayerAdapter(Context context,ArrayList<Player> players){
        super();
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(!(GameConstant.gameState == GameState.Ended)) {
            return getRunningView(position, parent);
        } else{
          return getGameEndView(position,parent);
        }
    }

    private View getGameEndView(final int position, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gameover_row_player, parent, false);
        TextView name = (TextView)view.findViewById(R.id.tv_name);
        TextView buyin = (TextView)view.findViewById(R.id.tv_buyin);
        System.out.println("Players in not null" + players.size() + players);
        name.setText(players.get(position).getName());
        buyin.setText(players.get(position).getBuyin());
        final ViewSwitcher switcher = (ViewSwitcher) view.findViewById(R.id.vs_ended_game);
        Button btnPlayer = (Button)view.findViewById(R.id.btn_player_over);
        final EditText etReturnedCoins = (EditText)view.findViewById(R.id.et_returned_coins);
        final TextView tvReturnedCoins = (TextView)view.findViewById(R.id.tv_returned_coins);
        final TextView tvWinningAmount = (TextView)view.findViewById(R.id.tv_winning_amount);
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coinstr = etReturnedCoins.getText().toString();
                if(!StringUtils.isNullOrEmpty(coinstr)){
                    int coins = Integer.parseInt(coinstr);
                    int winningAmount = GameUtil.winningAmount(coins,players.get(position));
                    tvReturnedCoins.setText(coinstr);
                    tvWinningAmount.setText("Total winning amount is \u20B9 " + winningAmount);
                    if(winningAmount < 0)
                        tvWinningAmount.setTextColor(context.getResources().getColor(R.color.red));
                    else
                        tvWinningAmount.setTextColor(context.getResources().getColor(R.color.green));
                    switcher.showNext();
                }else{
                    Toast.makeText(context,"Enter coins", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @NonNull
    private View getRunningView(final int position, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_row_view, parent, false);
        TextView name = (TextView)view.findViewById(R.id.tv_name);
        TextView buyin = (TextView)view.findViewById(R.id.tv_buyin);
        System.out.println("Players in not null"+ players.size()+players);
        name.setText(players.get(position).getName());
        buyin.setText(players.get(position).getBuyin());
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Do you really want to delete this player?", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.change_player_state);
                Button btnMoreCash = (Button) dialog.findViewById(R.id.btn_addbuyin);
                Button btnCheckout = (Button) dialog.findViewById(R.id.btn_player_endgame);
                final ViewSwitcher switcher = (ViewSwitcher) dialog.findViewById(R.id.vs_checkout_switcher);
                Button btnReturnToGame = (Button) dialog.findViewById(R.id.btn_return_to_game);
                Button btnPlayerEndGame = (Button) dialog.findViewById(R.id.btn_checkout);
                final EditText etReturnedCoins = (EditText) dialog.findViewById(R.id.et_returned_coins);
                TextView tvName = (TextView) dialog.findViewById(R.id.tv_name);
                final TextView tvBuyin = (TextView) dialog.findViewById(R.id.tv_buyin_till);
                tvName.setText(players.get(position).getName());
                tvBuyin.setText(players.get(position).getBuyin());

                btnMoreCash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentBuyins = Integer.parseInt(players.get(position).getBuyin());
                        players.get(position).setBuyin((currentBuyins + 1) + "");
                        tvBuyin.setText((currentBuyins + 1) + "");
                        notifyDataSetChanged();
                    }
                });

                btnCheckout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            switcher.showNext();
                    }
                });

                btnReturnToGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            switcher.showPrevious();
                    }
                });

                btnPlayerEndGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String coins = etReturnedCoins.getText().toString();
                        if (!StringUtils.isNullOrEmpty(coins)) {
                            int chips = Integer.parseInt(coins);
                            int wonMoney = GameUtil.winningAmount(chips, players.get(position));
                            players.get(position).setWinningAmount(wonMoney);
                            GameConstant.playerList.set(position, players.get(position));
                            Toast.makeText(context,"Total winning amount"+wonMoney,Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();
                        }
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}
