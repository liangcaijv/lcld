package org.lanqiao.sql.ijdbc.impl;

public class User{
  private int id;
  private String username;
  private String nickName;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getNickName() {
    return nickName;
  }
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", nickName="
        + nickName + "]";
  }
}
