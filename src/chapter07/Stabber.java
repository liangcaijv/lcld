package chapter07;

public class Stabber extends Character{

	public Stabber() {
		super(450, 150, 7, 13, 5);
	}

	/**
	 * ���̼���
	 */
	public void backstab(){
		System.out.println("�̿ͷ������̣����˫���˺�");
	}
	
	@Override
	public void attack(Character character) {
		float excuse = this.resist();
		double harm = agility*1.5;
		System.out.println("�̿ͷ��𹥻������" + (harm-excuse) +"���˺�");
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
