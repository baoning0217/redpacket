# Mysql主从复制

1. 环境信息

   ```xml
   centos7
   
   mysql-community-server-5.7.27-1.el7.x86_64.rpm
   mysql-community-client-5.7.27-1.el7.x86_64.rpm
   
   ```


2. 安装mysql

   ```shell
   1.检查系统中默认安装包,有则删除，重新安装
   rpm -qa | grep mysql
   find / -name mysql
   
   2.强制安装命令
   安装server
   rpm -ivh mysql-community-server-5.7.27-1.el7.x86_64.rpm --force --nodeps
   安装client
   rpm -ivh mysql-community-client-5.7.27-1.el7.x86_64.rpm --force --nodeps
   
   3.修改mysql登录密码
   a./etc/my.cnf
   b.[mysqlId]下添加 skip-grant-tables
   c.直接登录，不需要密码，然后再修改密码
   d.flush privileges;
   e.set password for root@localhost = password('mysql.123456');(mysql5.7校验问题，密码设置复杂点)
   ### 也可以卸载校验 uninstall plugin validate_password;
   f.再注释掉skip-grant-tables
   g.重启mysqld
   
   ```

3. 配置主从同步

   ```shell
   # 日志文件名称
   log-bin=master-a-bin
   # 二进制日志的格式，有row，statement，mixed三种类型
   binlog-format=ROW
   #要求各个服务器的这个id必须不一样
   server-id=1 
   #同步的数据库名称
   binlog-do-db=redpacket
   ```

4. 配置从服务器登录主服务器的账号授权

   ```sql
   主服务器
   a. grant replication slave on *.* to 'root'@'192.168.207.10' identified by 'mysql.123456';
   b. flush privileges;
   ```

5. 从服务器的配置(my.cnf)

   ```shel
   # 日志文件名称
   log-bin=master-a-bin
   # 二进制日志的格式，有row，statement，mixed三种类型
   binlog-format=ROW
   #要求各个服务器的这个id必须不一样
   server-id=2
   # 中继日志执行后，这些变化是否需要计入自己的binarylog，当你的从服务器需要作为另一个服务器的主服务时需要打开，就是双主互备，或者多主多备份。
   log-slave-updates=true
   ```

6. 重启主服务器

   ```sql
   systemctl restart mysqld
   
   查看主服务器状态
   show master status;
   ```

7. 从服务器配置

   ```sql
   #重启从服务器
   systemctl restart mysqld
   
   #配置
   change master to master_host='192.168.207.10',master_user='root',master_password='mysql.123456',master_port=3306,master_log_file='master-a-bin.000006',master_log_pos=154;
   
   #开启从服务器
   start slave;
   
   #查看状态
   show slave status \G;
   
   ```

8. 测试主从服务