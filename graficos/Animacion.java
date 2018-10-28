package graficos;

import java.awt.image.BufferedImage;

public class Animacion {
	private int speed,index;
	private long lastTime,timer;
	private BufferedImage[]frames;

	public  Animacion(int speed,BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	/*
	 * loop que alterna entre las sprites del jugador
	 */
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (timer > speed) {
			index++;
			timer =0;
			if (index > frames.length){
				index = 0;
			}
		}
	}
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}
