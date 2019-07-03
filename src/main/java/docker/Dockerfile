#FROM frolvlad/alpine-oraclejdk8:slim

#拉取基础镜像位置
FROM java:8-jre
VOLUME /tmp

#拷贝jar到基础镜像容器  需要根据 pom 中的 artifactId jar.version 做变更
ADD MyEmail-0.0.1-SNAPSHOT.jar /myemail.jar
#修改时区但是这是linux下的
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

#RUN bash -c 'touch /myemail.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myemail.jar"]

#容器运行默认启动命令
#ENTRYPOINT ["java","-Djava.security.egd=fil
