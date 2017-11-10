package com.tfs.darkworld.effects;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.tfs.darkworld.entities.Coin;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.darkworld.states.MenuState.Particle;

public class ParticleManager {

	private static final int PARTICLE_MAX = 100;

	private Particle[] parts = new Particle[PARTICLE_MAX];

	public ParticleManager() {

		for (int i = 0; i < PARTICLE_MAX; i++) {
			parts[i] = new Particle();
		}
	}

	public void onSuspendState() {
		for (Particle particle : parts) {
			particle.life = 0;
		}
	}

	public void onRender(Graphics2D g) {
		setSkulls(g);
	}

	public void onUpdate() {
		updateSkulls();
	}

	public void showMeTheLove(int x, int y) {
		genSkulls(x, y, 16.0f, 500, 40);
	}

	public static class Particle {
		public float posX;
		public float posY;
		public float dX;
		public float dY;
		public int life = 0;
		public int lifeMax = 0;
		public float angle = 0.0f;
		public float rot = 0.0f;
		private Coin coin = new Coin(0, 0, 0, 5);
	}

	private void setSkulls(Graphics2D g) {

		AffineTransform af = new AffineTransform();

		for (Particle p : parts) {
			if (p.life <= 0)
				continue;

			af.setToIdentity();
			af.translate(p.posX, p.posY);
			//af.rotate(p.angle);
			af.translate(-8.0, -8.0);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) p.life / (float) p.lifeMax));

			g.drawImage(p.coin.getAnimation().getImage(), af, null);
		}
		// g.setTransform(old);
	}

	private void updateSkulls() {
		for (Particle p : parts) {
			if (p.life <= 0)
				continue;

			p.life--;
			p.posX += p.dX;
			p.posY += p.dY;
			p.dX *= 0.99f;
			p.dY = p.dY * 0.99f + 0.1f;
			p.angle += p.rot;
			p.rot *= 0.99f;
			p.coin.getAnimation().update();
		}
	}

	private void genSkulls(int cX, int cY, float radius, int life, int count) {
		for (Particle p : parts) {
			if (p.life <= 0) {
				p.life = p.lifeMax = (int) (Math.random() * life * 0.5) + life / 6;
				p.posX = cX;
				p.posY = cY;
				double angle = Math.random() * Math.PI * 2.0;
				double speed = Math.random() * radius;
				p.dX = (float) (Math.cos(angle) * speed);
				p.dY = (float) (Math.sin(angle) * speed);
				p.angle = (float) (Math.random() * Math.PI * 2.0);
				p.rot = (float) (Math.random() - 0.5) * 0.1f;

				count--;
				if (count <= 0)
					return;
			}
		}
	}

}
