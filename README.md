## seleniumwd-java-restassured-miasta-api-tests


![CI](https://github.com/piotrapl/seleniumwd-java-restassured-miasta-api-tests/actions/workflows/ci.yml/badge.svg?branch=main)

**EN (short abstract)**  
A lightweight, data-driven API test project for `https://local-gov-units.polandapi.com`.  
Built with Java, TestNG and RestAssured, following DRY principles.

---

## Repozytorium – cel i praktyczne użycie

Repozytorium prezentuje **prostą, czytelną i utrzymywalną** strukturę testów automatycznych API:
- **Java + TestNG** jako framework testowy,
- **RestAssured** do wykonywania zapytań HTTP i asercji na JSON,
- zasada **DRY** – wspólna konfiguracja i wspólne asercje w klasie bazowej,
- **podejście data-driven** – jeden test uruchamiany dla wielu danych wejściowych.

Zależność **Selenium WebDriver** jest obecna w projekcie zgodnie z wymaganym tech-stackiem, ale w samych testach API nie jest używana.

---

## Zakres projektu i scenariusze testowe

### Testowany endpoint (przypadki pozytywne)
- `GET /api/v1/municipalities/name/{name}`

### Wykonywane asercje
Dla każdej odpowiedzi:
1. Pole **`success` ma wartość `true`**
2. Pole **`data` istnieje i jest niepuste**
3. (Dodatkowo) response ma status **HTTP 200**

Test jest uruchamiany wielokrotnie z różnymi danymi dzięki `@DataProvider`.

---

## Wymagania środowiskowe

- **JDK 17+**
- **Maven 3.8+**
- Dostęp do internetu i API  
  `https://local-gov-units.polandapi.com`

## Uruchomienie testów:
```bash
mvn clean test
```
Uwaga: w przypadku problemów z certyfikatem HTTPS (SSLHandshake / PKIX) możliwe jest tymczasowe uruchomienie testów z wyłączoną walidacją SSL na środowisku testowym.

### Struktura projektu
```bash
src/test/java/com/polandapi
├─ base
│  └─ BaseApiTest.java        # konfiguracja RestAssured + wspólne asercje
├─ data
│  └─ MunicipalitiesData.java # dane testowe (DataProvider)
└─ tests
│ └─ MunicipalitiesByNameTest.java
├─ pom.xml
├─ testng.xml
├─ README.md
└─ .gitignore
```

### Taka architektura zakłada:

jedno miejsce konfiguracji requestów,

jedno miejsce logiki asercji,

minimalny kod w klasach testowych.

## Możliwe rozszerzenia
testy negatywne (nieistniejące nazwy, puste wartości),

wczytywanie danych testowych z CSV / JSON,

walidacja schematu JSON,

raportowanie (np. Allure),

obsługa wielu środowisk (dev / stage).

## RestAssured vs alternatywy
### RestAssured

bardzo czytelna składnia, szybkie pisanie testów

świetna integracja z TestNG i CI
− przy większych projektach wymaga dobrej struktury (klienci, modele)

### Alternatywy: 
Apache HttpClient, OkHttp, Spring WebTestClient, Karate DSL

Inne rozwiazania mają swoje zalety, jednak RestAssured oferuje najlepszy kompromis prostoty i możliwości dla klasycznych testów API w Javie.

