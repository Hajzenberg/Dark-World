package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.tfs.darkworld.res.GameConstants;

public class Ground implements IRenderable, IUpdatable {
	
	private double groundSpeed = -0.7;
	
	private Queue<GroundTile> recycledGroundTiles;
	private Queue<Lava> recycledLavaTiles;
	private Queue<Spikes> recycledSpikes;
	
	private Deque<Box> compositeGround;
	
	private Random rnd;
	
	private float chancesForGround = 0.5f;
	private float chancesForLava = 0.25f;
	private float chancesForSpikes = 0.25f;
	
	
	public Ground() {
		rnd = new Random();
		
		compositeGround = new LinkedList<Box>();

		recycledGroundTiles = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			GroundTile ground = new GroundTile();
			ground.setPosition(i * GroundTile.TILE_WIDTH, 500);
			recycledGroundTiles.add(ground);
		}

		recycledLavaTiles = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			Lava lava = new Lava();
			lava.setPosition(i * Lava.LAVA_WIDTH, 520);
			// compositeGround.add(lava);
			recycledLavaTiles.add(lava);
		}
		
		recycledSpikes = new LinkedList<>();
		
		for (int i = 0; i < 10; i++) {
			Spikes spikes = new Spikes();
			spikes.setPosition(i * Spikes.SPIKES_WIDTH, 480);
			// compositeGround.add(lava);
			recycledSpikes.add(spikes);
		}

		for (int i = 0; i < 5; i++) {
			compositeGround.add(recycledGroundTiles.poll());
		}

		// entities = new ArrayList<>();
		// entities.add(mPlayer);
		// entities.add(box);
		changeSpeed(groundSpeed);
		
		
	}


	@Override
	public void update() {
		for (Box b : compositeGround) {
			b.update();
		}
	}


	@Override
	public void render(Graphics2D g, int sw, int sh) {
		for (Box b : compositeGround) {
			b.render(g, sw, sh);
		}
	}
	
	/**
	 * @param gameEntity
	 */
	public void findIntersectionsWith(GameEntity gameEntity) {
		// if (mPlayer.isIsAlive()) {
		for (Box b : compositeGround) {
			gameEntity.intersect(b);
		}
		// }
	}
	
	
	public void generateTiles() {
		if (compositeGround.getLast().getX() + compositeGround.getLast().getWidth() - 200 < GameConstants.FRAME_WIDTH) {

			float n = rnd.nextFloat();

			Box box = null;

			if (!(compositeGround.getLast() instanceof GroundTile)) {
				box = recycledGroundTiles.poll();
			} else if (n < chancesForGround) {
				box = recycledGroundTiles.poll();
			} else if (n - chancesForGround < chancesForLava) {
				box = recycledSpikes.poll();
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

	public void recycleTiles() {
		if (compositeGround.getFirst().getX() + compositeGround.getFirst().getWidth() < 0) {
			if (compositeGround.getFirst() instanceof GroundTile) {
				System.out.println("recycling ground");
				recycledGroundTiles.add((GroundTile) compositeGround.pollFirst());
			} else if (compositeGround.getFirst() instanceof Lava) {
				System.out.println("recycling lava");
				recycledLavaTiles.add((Lava) compositeGround.pollFirst());
			} else if (compositeGround.getFirst() instanceof Spikes) {
				System.out.println("recycling lava");
				recycledSpikes.add((Spikes) compositeGround.pollFirst());
			}
			
		}
	}
	
	public void changeSpeed(double speed) {
		this.groundSpeed = speed;
		for (Box b : compositeGround) {
			b.setDX(speed);
		}
	}
	
	
}
