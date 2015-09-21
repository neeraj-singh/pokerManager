package com.mobilesaaj.pokergame.Utils;

import com.mobilesaaj.pokergame.Constants.GameConstant;
import com.mobilesaaj.pokergame.Players.Player;

/**
 * Created by neeraj.singh on 07/09/15.
 */
public class GameUtil {
    public static int winningAmount(int coins,Player player){
        int buyins = Integer.parseInt(player.getBuyin());
        int ows = (((coins*GameConstant.MoneyMapper )/ GameConstant.CoinsMapper) - buyins*GameConstant.MoneyMapper);
        return ows;
    }
}
