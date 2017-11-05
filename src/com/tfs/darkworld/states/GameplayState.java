package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.tfs.darkworld.entities.Background;
import com.tfs.darkworld.entities.GameEntity;
import com.tfs.darkworld.entities.Ground;
import com.tfs.darkworld.entities.Player;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class GameplayState extends GameState {


	private double gameSpeed = -0.7;

	private Background mBackground;
	private Player mPlayer;

	private Ground ground;

	

	private final double gravity = 0.0015;
	private final double airResistance = 0.989;


	public GameplayState(GameHost host) {
		super(host);
		
		mBackground = new Background(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
		mPlayer = new Player(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
		ground = new Ground();
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		mBackground.render(g, sw, sh);

		ground.render(g, sw, sh);

		mPlayer.render(g, sw, sh);

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

		ground.findIntersectionsWith(mPlayer);

		// if (mPlayer.isIsAlive()) {
		if (host.isKeyDown(KeyEvent.VK_W)) {
			mPlayer.jump();
		}
		// }
		mPlayer.update();
		mBackground.update();

		if (!mPlayer.isIsAlive()) {
			changeSpeed(0);
		}

		if (mPlayer.isOfficialyDead()) {
			BufferedImage mImage = new BufferedImage(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH,
					BufferedImage.TYPE_3BYTE_BGR);
			renderSnapshot(mImage);
			CommonRasters.setDyingSnapshot(mImage);
			host.setState(Strings.GAME_TO_RETRY_SATE);
			// Transition.transitionTo(Strings.GAME_TO_RETRY_SATE, TransitionType.Crossfade,
			// 0.2f);
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

			host.setState(Strings.GAME_TO_PAUSE_SATE);
			// Transition.transitionTo(Strings.GAME_TO_PAUSE_SATE, null, 0.5f);
			break;
		case KeyEvent.VK_ESCAPE:
			Transition.transitionTo(Strings.MENU_SATE, TransitionType.ZoomOut, 0.5f);
			mPlayer.reset();
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
			// mPlayer.setRight(false);
			break;
		case KeyEvent.VK_A:
			mPlayer.stop();
			// mPlayer.setLeft(false);
			break;
		case KeyEvent.VK_R:
			if (!mPlayer.isIsAlive()){
				mPlayer.reset();
			}
			break;
		default:
			break;
		}

	}

}
