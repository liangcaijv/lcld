对jdbc进行半orm的封装

传入查询sql，获取对象或对象列表
  - 连接池
  - 参数外化
  - 约定列名和pojo的属性名有某种对应关系
  如user表的id、name字段将会对应到User类的id、name属性