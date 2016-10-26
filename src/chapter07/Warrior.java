package chapter07;

public class Warrior extends Character{
	
	
	/**
	 * 战士类构造方法
	 */
	public Warrior() {
		//因为父类Character没有默认无参的构造方法，需要子类在构造期间显示调用父类构造方法
		super(600, 100, 15, 5, 3);//设置战士基础属性
	}
	
	@Override
	public void attack() {
		double harm = power*1.2;
		System.out.println("战士发动攻击，造成" +harm+"点伤害");
	}
	
	/**
	 * 重击
	 */
	public void thump(){
		System.out.println("战士使用重击，造成眩晕");
	}
	
	@Override
	public void attack(Character character) {
		float excuse = character.resist();
		double harm = power*1.2;
		character.hp = (int) (character.hp-(harm-excuse));
		System.out.println("战士发起攻击，造成" + (harm-excuse) +"点伤害");
	}
	
	@Override
	public float resist() {
		return this.power*0.7f;
	}
	
	public static void main(String[] args) {
		
//		Character character = new Warrior();
//		character.attack();
		
		Warrior warrior = new Warrior();
		Warrior warrior2 = new Warrior();
		
		warrior.attack(warrior2);
		warrior2.info();
		
		Stabber stabber = new Stabber();
		warrior.attack(stabber);
		stabber.info();
	}
	
}