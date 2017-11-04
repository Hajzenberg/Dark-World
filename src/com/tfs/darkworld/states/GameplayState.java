package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import com.tfs.darkworld.entities.Background;
import com.tfs.darkworld.entities.Box;
import com.tfs.darkworld.entities.GameEntity;
import com.tfs.darkworld.entities.Ground;
import com.tfs.darkworld.entities.Player;
import com.tfs.darkworld.res.Colors;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.res.GameConstants;
import com.tfs.darkworld.res.Strings;
import com.tfs.darkworld.res.fonts.Fonts;
import com.tfs.darkworld.states.Transition.TransitionType;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class GameplayState extends GameState {

	private Fonts mFonts;

	private double gameSpeed = 1;

	private Background mBackground = new Background(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
	private Player mPlayer = new Player(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);

	private Queue<Ground> recycledGroundTiles;
	// private Queue<Ground> recycledLavaTiles;
	// private Queue<Ground> recycledSiljak;

	private Deque<Box> compositeGround;

	// private List<GameEntity> entities;

	// private GameEntity[] intersectableEntities;

	// private GameEntity[] renderableEntities = {mBackground, mPlayer};

	// private GameEntity[] gravityAffectedEntities = {mPlayer};

	private final double gravity = 0.0015;
	private final double airResistance = 0.99;
	
	public GameplayState(GameHost host) {
		super(host);
		mFonts = new Fonts();
		compositeGround = new LinkedList<Box>();
		recycledGroundTiles = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Ground ground = new Ground();
			ground.setPosition(i * Ground.TILE_WIDTH, 440);
			compositeGround.add(ground);
		}
		mPlayer.setPosition(100, 200);

		// entities = new ArrayList<>();
		// entities.add(mPlayer);
		// entities.add(box);
		changeSpeed(1);
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

		for (Box b : compositeGround) {
			b.render(g, sw, sh);
		}
		mPlayer.render(g, sw, sh);
	}

	private void changeSpeed(int speed) {
		for (Box b : compositeGround) {
			b.setDX(speed);
		}
	}

	@Override
	public void update() {

		affectGraviation(mPlayer);

		if (compositeGround.getFirst().getX() + compositeGround.getFirst().getWidth() < 0) {
			if (compositeGround.getFirst() instanceof Ground) {
				recycledGroundTiles.add((Ground) compositeGround.pollFirst());
			}
		}
		
		if (compositeGround.getLast().getX()+compositeGround.getLast().getWidth() - 200 < GameConstants.FRAME_WIDTH) {
			if (!recycledGroundTiles.isEmpty()) {
				Box box = recycledGroundTiles.poll();
				Box last = compositeGround.getLast();
				box.setPosition(last.getX()+last.getWidth(), box.getY());
				compositeGround.addLast(box);
			}
		}
		
		

		for (Box b : compositeGround) {
			b.update();
		}

		// for (GameEntity ge : gravityAffectedEntities) {
		//// affectAirResistance(ge);
		//
		// }

		if (host.isKeyDown(KeyEvent.VK_A)) {
			mPlayer.left();
		}
		if (host.isKeyDown(KeyEvent.VK_D)) {
			mPlayer.right();
		}

		findIntersections();

		if (host.isKeyDown(KeyEvent.VK_W)) {
			mPlayer.jump();
		}

		mPlayer.update();
	}

	public void affectGraviation(GameEntity ge) {
		ge.setDY(ge.getDY() + gravity * ge.getMass());
	}

	public void affectAirResistance(GameEntity ge) {
		ge.setDY(ge.getDY() * airResistance);
		ge.setDX(ge.getDX() * airResistance);
	}

	public void findIntersections() {

		for (Box b : compositeGround) {
			mPlayer.intersect(b);
		}

		// for (int i = 0; i < intersectableEntities.length; i++) {
		//// for (int j = i+1; j < intersectableEntities.length; j++ ) {
		//// intersectableEntities[i].intersect(intersectableEntities[j]);
		// mPlayer.intersect(intersectableEntities[i]);
		//// }
		// }
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

		TransitionType transType = null;

		switch (keyCode) {
		case KeyEvent.VK_P:
			transType = TransitionType.Crossfade;

			BufferedImage mImage = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
			renderSnapshot(mImage);
			CommonRasters.setLastScreenCapture(mImage);

			host.setState(Strings.GAME_TO_PAUSE_SATE);
			// Transition.transitionTo(Strings.GAME_TO_PAUSE_SATE, null, 0.5f);
			break;
		case KeyEvent.VK_ESCAPE:
			transType = TransitionType.ZoomOut;
			Transition.transitionTo(Strings.MENU_SATE, transType, 0.5f);
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
			mPlayer.setRight(false);
			break;
		case KeyEvent.VK_A:
			mPlayer.stop();
			mPlayer.setLeft(false);
			break;
		default:
			break;
		}

	}

}
