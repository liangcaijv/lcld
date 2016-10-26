package chapter07;

public abstract class Character {

	protected int hp;

	protected int mp;
	
	protected int power;
	
	protected int agility;
	
	protected int mentality;
	
	
	/**
	 * Character�๹�췽��
	 * 5�������ò����Ĺ��췽��
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
		System.out.println("�����ƶ�");
	}
	
	public void info(){
		System.out.println("���� [hp=" + hp + ", mp=" + mp + ", power=" + power + ", agility=" + agility + ", mentality="
				+ mentality + "]");
	}

}