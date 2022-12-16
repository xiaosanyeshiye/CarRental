# 基础镜像使用java

from openjdk:8

# 作者

MAINTAINER  luw
WORKDIR /opt/project

#  VOLUME 指定临时文件目录 /tmp  在主机 /var/lib/docker 目录下创建一个临时文件并连接到容器的 /tmp

VOLUME  /tmp

ADD  CarRental-0.0.1-SNAPSHOT.jar   /opt/project

#  运行jar包

ENTRYPOINT ["java", "-jar", "CarRental-0.0.1-SNAPSHOT.jar"]

ENV spring.profiles.active="ali"

#  暴露端口

EXPOSE   8099