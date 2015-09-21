package com.mobilesaaj.pokergame.Constants;

import com.mobilesaaj.pokergame.Players.Player;
import com.mobilesaaj.pokergame.States.GameState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class GameConstant {
    public static int CoinsMapper = 200;
    public static int MoneyMapper = 1000;
    public static GameState gameState = GameState.PreInit;
    public static ArrayList<Player> playerList = new ArrayList<Player>();
}
