# 给数据库test添加用户
```sh
use test
db.createUser({user:"codesafe",pwd:"codesafe",roles[{role:"readWrite",db:"test"}]})
```

# 带有访问控制启动
```sh
mongod --auth --dbpath /data/db1 --logpath /data/log/db1.log --fork
```

# 配置复制集
## 新建复制集
```sh
mongod --dbpath /data/set1 --logpath /data/log/set1.log --replSet repset --fork --port 27101
mongod --dbpath /data/set2 --logpath /data/log/set2.log --replSet repset --fork --port 27102
```
## 初始化复制集
```sh
mongod --port 27101
config={
    _id: "repset",members: [
        {_id: 0,host: "127.0.0.1:27101"},
        {_id: 1,host: "127.0.0.1:27102"}
    ]
}
rs.initiate(config)
```
## 查看状态
rs.status()
## 查看配置
rs.conf()
## 设置允许从SECONDARY读数据
rs.slaveOk()

# 配置分片
```sh
mongod --port 27101 --dbpath /data/db1 --logpath /data/log/db1.log --shardsvr --fork
mongod --port 27102 --dbpath /data/db2 --logpath /data/log/db2.log --shardsvr --fork
mongod --port 27103 --dbpath /data/c1 --logpath /data/c1.log --configsvr --replSet repset --fork
mongod --port 27104 --dbpath /data/c2 --logpath /data/c2.log --configsvr --replSet repset --fork
mongod --port 27105 --dbpath /data/c3 --logpath /data/c3.log --configsvr --replSet repset --fork
mongo --port 27103
config={
    _id: "repset",members: [
        {_id: 0,host: "127.0.0.1:27103"},
        {_id: 1,host: "127.0.0.1:27104"},
        {_id: 2,host: "127.0.0.1:27105"}
    ]
}
rs.initiate(config)
mongos --configdb repset/127.0.0.1:27103,127.0.0.1:27104,127.0.0.1:27105 --logpath /data/route.log --port 40000 --fork
mongo --port 40000
sh.shardCollection('test.user',{'_id':1})
sh.enableSharding('test')
```