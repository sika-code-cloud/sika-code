# 指定基础镜像
FROM centos:7

# 维护者信息
MAINTAINER jueying hhbvictory@163.com

# 复制脚本文件到容器目录中
COPY entrypoint.sh /sbin/entrypoint.sh

# 运行指令
RUN chmod 755 /sbin/entrypoint.sh \
  && yum install -y git golang openssl \
  && git clone https://gitee.com/jueyinghua/compiled-ngrok.git /usr/local/compiled-ngrok \
  && tar -zxvf /usr/local/compiled-ngrok/ngrok.tar.gz -C /usr/local \
  && yum remove -y git \
  && rm -rf /usr/local/compiled-ngrok

# 允许指定的端口
EXPOSE 80/tcp 443/tcp 8081/tcp 8082/tcp

# 指定ngrok运行入口
ENTRYPOINT ["/sbin/entrypoint.sh"]
