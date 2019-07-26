package cn.yescallop.gomoku.rule.standard;

import cn.yescallop.gomoku.game.*;
import cn.yescallop.gomoku.rule.RuleHelper;

/**
 * A judge of the rule "Tarannikov".
 *
 * @author Scallop Ye
 */
public class Tarannikov extends StandardRenju {

    @Override
    public void processMove(int index, Board.Grid grid, Side side) throws IllegalMoveException {
        if (index > 5) {
            super.processMove(index, grid, side);
            return;
        }
        int dist = RuleHelper.chebyshevDistToCenter(grid);
        switch (index) {
            case 1:
                if (dist != 0)
                    throw new IllegalMoveException("The first move not in the center");
                break;
            case 2:
                if (dist > 1)
                    throw new IllegalMoveException("The second move outside central 3x3 area");
                break;
            case 3:
                if (dist > 2)
                    throw new IllegalMoveException("The third move outside central 5x5 area");
                break;
            case 4:
                if (dist > 3)
                    throw new IllegalMoveException("The fourth move outside central 7x7 area");
                break;
            case 5:
                if (dist > 4)
                    throw new IllegalMoveException("The fifth move outside central 9x9 area");
                break;
        }
        controller.makeMove(grid);
        controller.requestChoice(
                ChoiceSet.ofStrings("Choose Black", "Choose White"),
                side.opposite());
    }

    @Override
    public void processChoice(int index, int choice, Side side) {
        if ((choice == 0) ^ (game.stoneTypeBySide(side) == StoneType.BLACK)) {
            controller.swap();
            controller.switchSide();
        }
    }
}
