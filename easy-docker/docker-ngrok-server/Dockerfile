# 指定基础镜像
FROM centos:7

# 维护者信息
MAINTAINER jueying hhbvictory@163.com

# 安装git, golang, openssl
RUN yum install -y git golang openssl

# clone ngrok项目
RUN git clone https://github.com/inconshreveable/ngrok.git /usr/local/ngrok

# 复制脚本文件到容器目录中
COPY entrypoint.sh /sbin/entrypoint.sh

# 运行指令
RUN chmod 755 /sbin/entrypoint.sh

# 允许指定的端口
EXPOSE 80/tcp 443/tcp 8081/tcp 8082/tcp

# 指定ngrok运行入口
ENTRYPOINT ["/sbin/entrypoint.sh"]
