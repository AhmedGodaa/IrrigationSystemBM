FROM openjdk:17
WORKDIR /app
COPY /build/libs/alertservice-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","-XX:MaxRAMPercentage=60.0", \
                                                 "-XX:+UnlockDiagnosticVMOptions", \
                                                 "-XX:NativeMemoryTracking=summary", \
                                     "-XX:+PrintNMTStatistics"]