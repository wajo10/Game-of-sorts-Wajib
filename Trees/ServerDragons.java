package Trees;

import javax.xml.bind.annotation.XmlRootElement;

import Entidades.Dragon;
import Logica.Game;
import disparos.ControlDisparo;
import graficos.Assets;
import bitacora.subnivel.LoggerDragon;

@XmlRootElement
public class ServerDragons<T> {
	LoggerDragon loggerDragon = new LoggerDragon();
	private Game game;
	ControlDisparo cd = new ControlDisparo(game);
	SimpleList classes = new SimpleList();
	SimpleList ages = new SimpleList();
	public LinkedlistIS dragons = new LinkedlistIS();
	SimpleList usedwaves = new SimpleList();
	int tmp = 83;
	java.util.Random random = new java.util.Random();
	public ServerDragons(Game game){
		this.game=game;
	}

	public String random_classe() {
		String[] available_classe = { "Comandante", "Infantería", "Capitanes" };
		int ran_classe = random.nextInt(available_classe.length);
		if (available_classe[ran_classe].equals("Comandante")) {
			if (in((T) "Comandante", classes)) {
				random_classe();
			} else {
				classes.push(available_classe[ran_classe]);
				return available_classe[ran_classe];
			}
		}
		return available_classe[ran_classe];
	}

	public int random_speed() {
		int speed = (int) (1 + (Math.random() * (100 - 1)));
		return speed;
	}

	public int random_resistance() {
		int resistance = (int) (1 + (Math.random() * (3 - 1)));
		return resistance;
	}

	public int random_age() {
		int age = (int) (1 + (Math.random() * (1000 - 1)));
		if (in(age, ages)) {
			random_age();
		} else {
			ages.push(age);
			return age;
		}
		return age;
	}

	public boolean in(Object tosearch, SimpleList list) {
		if (list.getSize() == 0) {
			return false;
		} else {
			for (int i = 0; i < list.getSize(); i++) {
				if (tosearch == list.getNode(i).getData()) {
					return true;
				}
			}
		}
		return false;
	}

	public Dragon setparent() {
		int parent = (int) (1 + (Math.random() * (tmp - 1)));
		if (parent == 0) {
			setparent();
		} else {
			Dragon parentnode = dragons.getNode(parent);
			if (parentnode.son1 != null && parentnode.son2 != null) {
				setparent();
			} else if (parentnode.son1 != null && parentnode.son2 == null
					|| parentnode.son2 != null && parentnode.son1 == null) {
				return parentnode;
			} else {
				return parentnode;
			}
		}
		return null;
	}

	public void generate() {
		int index = (int) (1 + (Math.random() * (27 - 1)));
		String clase = null;
		char[] waves = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char wave = waves[index];
		int dragons_num = ((20 * tmp) / 100) + tmp;
		if (in(wave, usedwaves)) {
			generate();
		} else {
			for (int cont = 0; cont < dragons_num; cont++) {
				usedwaves.push(wave);
				String name = wave + String.valueOf(cont);
				System.out.println(name);
				clase = random_classe();

				if (clase == "Comandante") {
					int VelocidadDisp = random.nextInt(100) + 45;
					Dragon dragon = new Dragon(game, name, clase, cd, setparent(), 0, 0, random_age());
					dragon.setSalud(3);
					dragon.setAlto(100);
					dragon.setAncho(100);
					dragon.setContadorDisparo(VelocidadDisp * 4);
					dragon.setVelocidadDisp(VelocidadDisp);
					dragon.setSprites(Assets.dragon1);
					dragons.push(dragon);
					loggerDragon.dragonNuevo(dragon.getClase());

					dragons.push(dragon);
				} else if (clase == "Infantería") {
					int VelocidadDisp = random.nextInt(100) + 45;
					Dragon dragon = new Dragon(game, name, clase, cd, setparent(), 0, 0, random_age());
					dragon.setSalud(2);
					dragon.setAlto(100);
					dragon.setAncho(100);
					dragon.setContadorDisparo(VelocidadDisp * 3);
					dragon.setVelocidadDisp(VelocidadDisp);
					dragon.setSprites(Assets.dragon2);
					dragons.push(dragon);
					loggerDragon.dragonNuevo(dragon.getClase());
				} else if (clase == "Capitanes") {
					int VelocidadDisp = random.nextInt(100) + 45;
					Dragon dragon = new Dragon(game, name, clase, cd, setparent(), 0, 0, random_age());
					dragon.setSalud(1);
					dragon.setAlto(100);
					dragon.setAncho(100);
					dragon.setContadorDisparo(VelocidadDisp * 2);
					dragon.setVelocidadDisp(VelocidadDisp);
					dragon.setSprites(Assets.dragon3);
					dragons.push(dragon);
					loggerDragon.dragonNuevo(dragon.getClase());
				}
			}

			this.tmp = dragons_num;
		}

	}
}
