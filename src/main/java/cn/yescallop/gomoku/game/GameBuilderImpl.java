package cn.yescallop.gomoku.game;

import cn.yescallop.gomoku.event.GameListener;
import cn.yescallop.gomoku.player.Player;
import cn.yescallop.gomoku.rule.Rule;

import java.util.concurrent.TimeUnit;

/**
 * @author Scallop Ye
 */
class GameBuilderImpl implements Game.Builder {

    Rule rule;
    final ListenerGroup listenerGroup = new ListenerGroup();
    final Player[] players = new Player[2];
    long gameTimeout = 0;
    long moveTimeout = 0;
    boolean strict = false;

    @Override
    public GameBuilderImpl rule(Rule rule) {
        this.rule = rule;
        return this;
    }

    @Override
    public GameBuilderImpl listener(GameListener listener) {
        listenerGroup.add(listener);
        return this;
    }

    @Override
    public GameBuilderImpl player(Side side, Player player) {
        players[side.ordinal()] = player;
        return this;
    }

    @Override
    public GameBuilderImpl gameTimeout(long timeout, TimeUnit unit) {
        if (timeout < 0)
            throw new IllegalArgumentException("Negative timeout");
        this.gameTimeout = unit.toMillis(timeout);
        return this;
    }

    @Override
    public GameBuilderImpl moveTimeout(long timeout, TimeUnit unit) {
        if (timeout < 0)
            throw new IllegalArgumentException("Negative timeout");
        this.moveTimeout = unit.toMillis(timeout);
        return this;
    }

    @Override
    public Game.Builder strict(boolean strict) {
        this.strict = strict;
        return this;
    }

    @Override
    public Game build() {
        return new GameImpl(this);
    }
}
