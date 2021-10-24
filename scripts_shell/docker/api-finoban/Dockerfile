FROM openjdk:11

RUN mkdir /api-finoban

ENV APP_NAME=api-finoban.jar

COPY ${APP_NAME} /api-finoban

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "api-finoban/api-finoban.jar" ]
