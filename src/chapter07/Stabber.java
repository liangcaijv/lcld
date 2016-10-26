package chapter07;

public class Stabber extends Character{

	public Stabber() {
		super(450, 150, 7, 13, 5);
	}

	/**
	 * 背刺技能
	 */
	public void backstab(){
		System.out.println("刺客发动背刺，打出双倍伤害");
	}
	
	@Override
	public void attack(Character character) {
		float excuse = this.resist();
		double harm = agility*1.5;
		System.out.println("刺客发起攻击，造成" + (harm-excuse) +"点伤害");
	}
	
	@Override
	public float resist() {
		return this.agility*0.5f;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
}
