package chapter07;

public abstract class Character {

	protected int hp;

	protected int mp;
	
	protected int power;
	
	protected int agility;
	
	protected int mentality;
	
	
	/**
	 * Character类构造方法
	 * 5属性设置参数的构造方法
	 */
	public Character(int hp, int mp, int power, int agility, int mentality) {
		this.hp = hp;
		this.mp = mp;
		this.power = power;
		this.agility = agility;
		this.mentality = mentality;
	}

	public abstract void attack();
	
	public abstract void attack(Character character);
	
	public abstract float resist();

	public void move() {
		System.out.println("人物移动");
	}
	
	public void info(){
		System.out.println("属性 [hp=" + hp + ", mp=" + mp + ", power=" + power + ", agility=" + agility + ", mentality="
				+ mentality + "]");
	}

}