# DAT109: EXPO Prosjekt - Gruppe 1

<div style="display: flex; justify-content: center">
    <img src="https://www.hvl.no/internett/images/logo-no.png" alt="Høgskulen på Vestlandet">
</div>

Laget av:

- [Espen](https://github.com/599007)
- [Hans](https://github.com/h602509)
- [Martin](https://github.com/emberal)
- [Stein Olav](https://github.com/olav223)
- [Tobias](https://github.com/h600879)
- [Torbjørn](https://github.com/torvat)
- [Tore](https://github.com/108152)
- [Øyvind](https://github.com/oyvindinst)

---

## Programvare som kreves for å kjøre prosjektet

- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/en/download/)
- [PostgreSQL](https://www.postgresql.org/download/) database
- [Git](https://git-scm.com/downloads)

## Oppsett av egen database

Opprett en ny database i PostgreSQL, og sett opp en bruker med tilgang til denne databasen.

Deretter legg inn innloggingsinformasjonen i application.yaml filen. Brukernavn, passord og url til databasen.

```yaml
spring:
  datasource:
    hikari:
      connection-timeout: '200000'
      maximum-pool-size: '5'
      max-lifetime: '1800000'
    password: ditt_passord
    username: ditt_brukernavn
    url: din_database_url/ditt_brukernavn
```

Etter det kan sql skriptet expo-init.sql kjøres på databasen for å opprette tabellene.

## Kjøre prosjektet lokalt

Backend og frontend kan kjøres separat, eller samtidig på ulike porter. Hendholdsvis :8080 og :3000.

Backend:

```shell
mvn spring-boot:run
```

Frontend:

Merk: `npm install` trenger kun å kjøres første gang, eller når det er endringer i package.json.

```shell
cd ./src/main/app
npm install
npm start
```

## Bygge og deploye prosjektet

Bygging av koden og genering av en .war fil kan gjøres ved å kjøre følgende kommando i prosjektets rotmappe:

```shell
echo yes | node ./setup.js
```

Eller:

```shell
node ./setup.js
```

Ved input kan 'yes' eller 'y' skrives inn for å kjøre bygging av .war filen. Eller 'n' for å kun kjøre `git pull`
og `npm install`.

Filen blir lagret i /target mappen, med navnet 'DAT109-expo-gruppe1'. Da kan filen brukes for å deploye til en tomcat
eller tomEE server.
