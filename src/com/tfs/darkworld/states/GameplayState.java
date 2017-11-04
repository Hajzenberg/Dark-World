package com.tfs.darkworld.states;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContextChildComponentProxy;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.tfs.darkworld.entities.Background;
import com.tfs.darkworld.entities.Box;
import com.tfs.darkworld.entities.GameEntity;
import com.tfs.darkworld.entities.Ground;
import com.tfs.darkworld.entities.Lava;
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

	private double gameSpeed = -0.7;

	private Background mBackground = new Background(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);
	private Player mPlayer = new Player(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH);

	private Queue<Ground> recycledGroundTiles;
	private Queue<Lava> recycledLavaTiles;
	// private Queue<Ground> recycledSiljak;

	private Deque<Box> compositeGround;

	private final double gravity = 0.0015;
	private final double airResistance = 0.988;

	private Random rnd;

	public GameplayState(GameHost host) {
		super(host);
		rnd = new Random();

		mFonts = new Fonts();

		compositeGround = new LinkedList<Box>();

		recycledGroundTiles = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			Ground ground = new Ground();
			ground.setPosition(i * Ground.TILE_WIDTH, 500);
			recycledGroundTiles.add(ground);
		}

		recycledLavaTiles = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			Lava lava = new Lava();
			lava.setPosition(i * Lava.LAVA_WIDTH, 520);
			// compositeGround.add(lava);
			recycledLavaTiles.add(lava);
		}

		for (int i = 0; i < 5; i++) {
			compositeGround.add(recycledGroundTiles.poll());
		}

		mPlayer.setPosition(100, 400);

		// entities = new ArrayList<>();
		// entities.add(mPlayer);
		// entities.add(box);
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

		for (Box b : compositeGround) {
			b.render(g, sw, sh);
		}

		mPlayer.render(g, sw, sh);

	}

	private void changeSpeed(double speed) {
		for (Box b : compositeGround) {
			b.setDX(speed);
		}
	}

	private void generateTiles() {
		if (compositeGround.getLast().getX() + compositeGround.getLast().getWidth() - 200 < GameConstants.FRAME_WIDTH) {

			float n = rnd.nextFloat();

			Box box = null;

			if (!(compositeGround.getLast() instanceof Ground)) {
				box = recycledGroundTiles.poll();
			} else if (n < 0.75f) {
				box = recycledGroundTiles.poll();
			} else {
				box = recycledLavaTiles.poll();
			}

			if (box == null) {
				System.err.println("Recycling failed!");
				return;
			}

			Box last = compositeGround.getLast();
			box.setPosition(last.getX() + last.getWidth(), box.getY());
			box.setDX(last.getDX());
			compositeGround.addLast(box);

		}
	}

	private void recycleTiles() {
		if (compositeGround.getFirst().getX() + compositeGround.getFirst().getWidth() < 0) {
			if (compositeGround.getFirst() instanceof Ground) {
				System.out.println("recycling ground");
				recycledGroundTiles.add((Ground) compositeGround.pollFirst());
			} else if (compositeGround.getFirst() instanceof Lava) {
				System.out.println("recycling lava");
				recycledLavaTiles.add((Lava) compositeGround.pollFirst());
			}
		}
	}

	@Override
	public void update() {

		affectGraviation(mPlayer);
		affectAirResistance(mPlayer);
		recycleTiles();

		generateTiles();

		// apdejtuje trenutne pozicije za podlogu
		for (Box b : compositeGround) {
			b.update();
		}
		
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
		mBackground.update();
		
		if (!mPlayer.isIsAlive()) {
			changeSpeed(0);
		}
		
		if (mPlayer.isOfficialyDead()) {
			BufferedImage mImage = new BufferedImage(GameConstants.FRAME_WIDTH, GameConstants.FRAME_HEIGTH, BufferedImage.TYPE_3BYTE_BGR);
			renderSnapshot(mImage);
			CommonRasters.setDyingSnapshot(mImage);
			Transition.transitionTo(Strings.GAME_TO_RETRY_SATE, TransitionType.Crossfade, 1f);
		}
	}

	public void affectGraviation(GameEntity ge) {
		ge.setDY(ge.getDY() + gravity * ge.getMass());
	}

	public void affectAirResistance(GameEntity ge) {
		ge.setDY(ge.getDY() * airResistance);
		ge.setDX(ge.getDX() * airResistance);
	}

	public void findIntersections() {
//		if (mPlayer.isIsAlive()) {
			for (Box b : compositeGround) {
				mPlayer.intersect(b);
			}
//		}
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
//			mPlayer.setRight(false);
			break;
		case KeyEvent.VK_A:
			mPlayer.stop();
//			mPlayer.setLeft(false);
			break;
		default:
			break;
		}

	}

}
