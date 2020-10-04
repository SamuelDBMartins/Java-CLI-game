package ch.supsi.game.ui.elements;

import ch.supsi.game.logic.Constants;
import ch.supsi.game.logic.GameLogic;
import ch.supsi.game.ui.TextualUI;
import ch.supsi.game.ui.elements.players.Player;

public class Rock extends Obstacle {
	public static final int N_INITIAL_ROCKS = Constants.N_INITIAL_ROCKS;
	private static final char SYMBOL = '@';
	
	public Rock() {
		super(SYMBOL);
	} 
	
	@Override
	public void collision(GameLogic logic, Player player, int newY, int newX) {
		System.out.println(TextualUI.obstacle());
		
		if (playerCanAvoidObstacle(player)) {
			System.out.println(TextualUI.obstacleAvoided());
			super.collision(logic, player, newY, newX);
			return;
		}
		
		behaviorPlayer(logic, player, newY, newX);
	}

	@Override
	protected void behaviorPlayer(GameLogic logic, Player player, int newY, int newX) {
		//se la roccia esplode allora poi scompare, mentre lalbero non scompare dopo aver ucciso un giocatore
		if (isDangerous()) {
			System.out.println(TextualUI.rockDangerous());
			player.dead(logic);
			logic.getCell()[newY][newX] = null;
		} else {
			System.out.println(TextualUI.rock());
			logic.move(player, logic.getScanner());
		}
	}
}
