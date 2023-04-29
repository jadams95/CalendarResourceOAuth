FROM openjdk:17-alpine
MAINTAINER jadams
VOLUME docker_lavishAPIServer_1
RUN mkdir -p "/var/www"
RUN mkdir .spring_config
ADD .spring_config /.spring_config
ADD "./CalendarResourceOAuth-0.0.1-SNAPSHOT.jar" "/var/www/CalendarResource-1.0.0.jar"
WORKDIR "/var/www"
ENV prod-env-driver=$abc
ENV prod-env-url=$abc
ENV prod-env-user=$abc
ENV prod-env-psswrd=$abc
ENTRYPOINT ["java","-jar","/var/www/CalendarResource-1.0.0.jar"]