package es.carlos.monguering.chainprocessor;

import es.carlos.monguering.dto.GameSession;
import es.carlos.monguering.entities.Planet;
import es.carlos.monguering.entities.Player;

public abstract class BaseChainProcessor {

	private BaseChainProcessor next;

	private GameSession gameSession = new GameSession();

		public BaseChainProcessor getNext() {
		return next;
	}

	public BaseChainProcessor setNext(BaseChainProcessor next) {
		this.next = next;
		return this.next;
	}

	public abstract void process();

	public Player run() {
		BaseChainProcessor current = this;
		BaseChainProcessor last = this;
		do {
			current.setGameSession(last.getGameSession());
			last = current;
			current.process();
			
		} while ((current = current.getNext()) != null);
		return last.getPlayer();
	}

	public GameSession getGameSession() {
		return gameSession;
	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public Planet getPlanet() {
		return gameSession.getPlanet();
	}

	public Player getPlayer() {
		return gameSession.getPlayer();
	}
}
