package chapter07;

public class Magician extends Character{

	public Magician() {
		super(350, 300, 2, 5, 17);
	}

	/**
	 * 地狱火技能
	 */
	public void hellFire(){
		System.out.println("法师使用地狱火技能，造成大面积伤害");
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float resist() {
		// TODO Auto-generated method stub
		return 0;
	}
}
