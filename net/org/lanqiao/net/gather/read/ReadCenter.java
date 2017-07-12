package org.lanqiao.net.gather.read;


/**
 */
public class ReadCenter {
  
  private final GrabageReader configReader;
  
  public ReadCenter(){
    configReader= new GrabageReader();
    configReader.reLoadSubConfig();
  }
  
  public void startRead(){
    configReader.startReadPage();
  }

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    ReadCenter center = new ReadCenter();
    center.startRead();
  }
}
