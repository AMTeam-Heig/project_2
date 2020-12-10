
FROM openliberty/open-liberty:kernel-java11-openj9-ubi

# ---
COPY --chown=1001:0 ./liberty/config/ /config/
COPY --chown=1001:0 ./liberty/artifact/*.war /config/apps
EXPOSE 9080
