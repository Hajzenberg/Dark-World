package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.tfs.darkworld.entities.Background;
import com.tfs.darkworld.entities.Coin;
import com.tfs.darkworld.entities.Explosion;
import com.tfs.darkworld.entities.GameEntity;
import com.tfs.darkworld.entities.Ground;
import com.tfs.darkworld.entities.Player;
import com.tfs.darkworld.entities.Rocket;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.states.Transition.TransitionType;

import jaco.mp3.player.MP3Player;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class GameplayState extends GameState {

	private final double gravity = 0.0055;
	private final double airResistance = 0.989;

	private double gameSpeed = -1.7;

	private Ground ground;
	private Player mPlayer;

	private String lastStateTransitionedTo = "";

	private Background background;

	private ArrayList<Rocket> rockets;
	private ArrayList<Explosion> explosions;

	// private MP3Player moonwalkSong = new MP3Player(new
	// File("music/moonwalk.mp3"));
	private MP3Player gameSong;

	public GameplayState(GameHost host) {
		super(host);
		// gameSong = new MP3Player(new File("music/sanity.mp3"));
		// gameSong = new MP3Player(new File("music/moonwalk.mp3"));
		// gameSong.setRepeat(true);

		background = new Background(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
		mPlayer = new Player(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
		ground = new Ground();
		rockets = new ArrayList<>();
		explosions = new ArrayList<>();

		changeSpeed(gameSpeed);

	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		return Strings.GAMEPLAY_SATE;
	}

	@Override
	public void resumeState() {
		System.out.println("RESUME STATE");
		if (lastStateTransitionedTo.equals(Strings.GAME_TO_RETRY_SATE)) {
			System.out.println("DOSAO IZ RETRY STATEA");
			mPlayer.reset();
		} else {
			System.out.println("NIJE DOSAO IZ RETRY STATE-a");
		}
		// if (gameSong.isPaused() || gameSong.isStopped()) {
		// gameSong.play();
		// }
	}

	@Override
	public void suspendState() {
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		background.render(g, sw, sh);

		ground.render(g, sw, sh);

		mPlayer.render(g, sw, sh);

		//Fix for concurent modifier exception
		ArrayList<Rocket> rocketsToIterate = new ArrayList<>(rockets);
		for (Rocket rocket : rocketsToIterate) {
			rocket.render(g, sw, sh);
		}
		
		for (Explosion explosion : explosions) {
			explosion.render(g, sw, sh);
		}

	}

	private void changeSpeed(double speed) {
		ground.changeSpeed(speed);
	}

	@Override
	public void update() {

		affectGraviation(mPlayer);
		affectAirResistance(mPlayer);

		ground.update();

		ground.recycleTiles();
		ground.generateTiles();

		if (host.isKeyDown(KeyEvent.VK_A)) {
			mPlayer.left();
		}
		if (host.isKeyDown(KeyEvent.VK_D)) {
			mPlayer.right();
		}
		if (host.isKeyDown(KeyEvent.VK_S)) {
			mPlayer.down();
		}
		ground.findIntersectionsWith(mPlayer);
		background.findIntersectionsWith(mPlayer);

		// if (mPlayer.isIsAlive()) {
		if (host.isKeyDown(KeyEvent.VK_W)) {
			mPlayer.jump();
		}
		// }
		mPlayer.update();
		background.update();

		if (!mPlayer.isIsAlive()) {
			//change speed se vraca kad lik ozivi na restart
			changeSpeed(0);
		}

		if (mPlayer.isOfficialyDead()) {
			BufferedImage mImage = new BufferedImage(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH,
					BufferedImage.TYPE_3BYTE_BGR);
			renderSnapshot(mImage);
			CommonRasters.setDyingSnapshot(mImage);
			host.setState(Strings.GAME_TO_RETRY_SATE);
			lastStateTransitionedTo = Strings.GAME_TO_RETRY_SATE;
		}

		Iterator<Rocket> rIterator = rockets.iterator();

		while (rIterator.hasNext()) {
			Rocket rocket = rIterator.next();
			rocket.update();

			//0.7 ground speed
			if (!rocket.isAlive()) {
				explosions.add(new Explosion(rocket.getX() - 40, rocket.getY() - 60, 0.7));
				rIterator.remove();
				
			}
		}
		
		Iterator<Explosion> eIterator = explosions.iterator();
		
		while (eIterator.hasNext()){
			Explosion explosion = eIterator.next();
			explosion.update();
			
			if (!explosion.isAlive()){
				eIterator.remove();
			}
		}
	}

	public void affectGraviation(GameEntity ge) {
		ge.setDY(ge.getDY() + gravity * ge.getMass());
	}

	public void affectAirResistance(GameEntity ge) {
		ge.setDY(ge.getDY() * airResistance);
		ge.setDX(ge.getDX() * airResistance);
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyDown(int keyCode) {

		switch (keyCode) {
		case KeyEvent.VK_P:

			BufferedImage mImage = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
			renderSnapshot(mImage);
			CommonRasters.setLastScreenCapture(mImage);
			gameSong.pause();
			host.setState(Strings.GAME_TO_PAUSE_SATE);
			lastStateTransitionedTo = Strings.GAME_TO_PAUSE_SATE;
			break;
		case KeyEvent.VK_ESCAPE:
			gameSong.pause();
			Transition.transitionTo(Strings.MENU_SATE, TransitionType.Doom, 0.5f);
			lastStateTransitionedTo = Strings.MENU_SATE;
			break;
		default:
			break;
		}
	}

	@Override
	public void handleKeyUp(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_D:
			mPlayer.stop();
			break;
		case KeyEvent.VK_A:
			mPlayer.stop();
			break;
		case KeyEvent.VK_L:
			if (mPlayer.getCurrentAction() == 4 || mPlayer.getCurrentAction() == 6) {
				rockets.add(new Rocket(mPlayer.getX() + mPlayer.getWidth(),
						mPlayer.getY() + mPlayer.getHeight() / 2 + 15, 3));
			}
			break;
		default:
			break;
		}

	}

}
