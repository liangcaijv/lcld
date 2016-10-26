package chapter07;

public class Warrior extends Character{
	
	
	/**
	 * սʿ�๹�췽��
	 */
	public Warrior() {
		//��Ϊ����Characterû��Ĭ���޲εĹ��췽������Ҫ�����ڹ����ڼ���ʾ���ø��๹�췽��
		super(600, 100, 15, 5, 3);//����սʿ��������
	}
	
	@Override
	public void attack() {
		double harm = power*1.2;
		System.out.println("սʿ�������������" +harm+"���˺�");
	}
	
	/**
	 * �ػ�
	 */
	public void thump(){
		System.out.println("սʿʹ���ػ������ѣ��");
	}
	
	@Override
	public void attack(Character character) {
		float excuse = character.resist();
		double harm = power*1.2;
		character.hp = (int) (character.hp-(harm-excuse));
		System.out.println("սʿ���𹥻������" + (harm-excuse) +"���˺�");
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