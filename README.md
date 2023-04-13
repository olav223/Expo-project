# DAT109: EXPO Prosjekt

---

### Programvare
- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/en/download/)
- [PostgreSQL](https://www.postgresql.org/download/)

### Oppsett av database
Opprett en ny database i PostgreSQL, og sett opp en bruker med tilgang til denne databasen.

Deretter legg inn innloggingsinformasjonen i application.yaml filen. Brukernavn, passord og url til databasen.

```yaml
spring:
    datasource:
        hikari:
            connection-timeout: '200000'
            maximum-pool-size: '5'
            max-lifetime: '1800000'
        password: your_password
        username: your_username
        url: your_database_url
```

Etter det kan sql skriptet expo-init.sql kjøres for å opprette tabellene.

### Kjøre prosjektet lokalt
Backend og frontend kan kjøres separat, eller samtidig på ulikee porter. Hendholdsvis :8080 og :3000.

Backend:
```bash
mvn spring-boot:run
```

Frontend:

npm install trenger kun å kjøres første gang, eller når det er endringer i package.json.
```bash
cd ./src/main/app
npm install
npm start
```

### Bygge og deploye prosjektet
Bygging av koden og genering av en .war fil kan gjøres ved å kjøre følgende kommando i prosjektets rotmappe:

```bash
node setup.js
```
Trykk 'y' for å generere .war filen, og 'n' for å kun bygge koden.
Etter filen er generert kan den deployes på en tomcat eller tomEE server.
