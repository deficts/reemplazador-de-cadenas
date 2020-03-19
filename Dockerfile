FROM openjdk:8
ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin
COPY ./out/artifacts/reemplazador_de_cadenas_jar /usr/local/myJarFolder
ENTRYPOINT ["java", "-jar", "/usr/local/myJarFolder/reemplazador-de-cadenas.jar"]