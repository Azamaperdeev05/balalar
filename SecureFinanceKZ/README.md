# SecureFinance KZ

**Деректерді шифрлау арқылы жеке қаржыны есепке алуға арналған қауіпсіз Android қосымшасы**

> Дипломдық жоба — 2026 жыл  
> Платформа: Android (Kotlin)  
> Нұсқа: 1.0

---

## Жоба туралы

**SecureFinance KZ** — барлық деректерді тек құрылғыда (local-first) AES-256-GCM алгоритмімен шифрлап сақтайтын жеке қаржы есепке алу қосымшасы. Қосымша Интернетсіз жұмыс істейді, сыртқы серверге ешқандай деректер жіберілмейді.

**Негізгі айырмашылық:** Monefy, CoinKeeper, Wallet сияқты танымал қосымшалар деректерді бұлтта TLS деңгейінде ғана шифрлайды — сервер әкімшісі немесе хакер деректерді ашық түрде оқи алады. SecureFinance KZ деректерді **сақтау кезінде (at-rest)** толық шифрлайды.

---

## Жобаның мақсаты

Қазақстан Республикасының «Дербес деректер және оларды қорғау туралы» Заңына сай келетін, қазақ тілінде жұмыс істейтін, криптографиялық тұрғыдан қорғалған жеке қаржы қосымшасын іске асыру.

---

## Мүмкіндіктер

### Аутентификация және қауіпсіздік
- PIN-код орнату (4–8 таңба, PBKDF2 100 000 итерация)
- Биометриялық кіру (саусақ ізі / бет-сканер + Android Keystore)
- Автоматты құлыптау (30с / 1мин / 3мин / 5мин / ешқашан)
- Экспоненциалды кешіктіру (5 қате → 30с, 6 қате → 60с, … 10мин)
- Screenshot қорғауы (`FLAG_SECURE`)
- Google Backup өшіру (`allowBackup=false`)

### Шоттар (Accounts)
- Шот қосу / өңдеу / жою
- 4 түр: Қолма-қол, Карта, Жинақ шоты, Басқа
- Валюта: KZT, USD, EUR, RUB
- Жалпы баланс автоматты есептеледі

### Категориялар
- 12 алғашқы шығыс категориясы (Азық-түлік, Көлік, Тұрғын үй, т.б.)
- 7 алғашқы кіріс категориясы (Жалақы, Сыйлық, т.б.)
- Жеке категория қосу / өңдеу / жою

### Транзакциялар
- Кіріс / Шығыс қосу, өңдеу, жою
- Шот балансы @Transaction арқылы атомарлы жаңарту
- Сүзгі: кезең, түр, шот, категория
- Мәтін бойынша іздеу
- Күн бойынша топтастырылған тізім

### Статистика
- Жалпы баланс (басты экран)
- Ай бойынша кіріс / шығыс / айырым
- Pie chart: категориялар бойынша шығын
- Bar chart: кіріс vs шығыс
- Кезең таңдауы: Апта / Ай / Тоқсан / Жыл

### Backup / Restore
- Шифрланған экспорт `.sfkz` форматында (JSON + AES-256-GCM, бөлек құпиясөзбен)
- Шифрланған резервтен қалпына келтіру (алмастыру немесе біріктіру)
- CSV экспорт (шифрланбаған, ескертумен)

### Баптаулар
- PIN-код ауыстыру
- Биометрияны қосу / өшіру
- Тіл: Қазақша / Орысша
- Тақырып: Ашық / Қараңғы / Жүйелік
- Factory Reset

---

## Архитектура

```
┌──────────────────────────────────────────┐
│          PRESENTATION LAYER              │
│  Activities / Fragments / Adapters       │
└──────────────┬───────────────────────────┘
               │ observes LiveData / StateFlow
               ▼
┌──────────────────────────────────────────┐
│          VIEWMODEL LAYER                 │
│  DashboardVM, TransactionsVM, ...        │
└──────────────┬───────────────────────────┘
               │ invokes
               ▼
┌──────────────────────────────────────────┐
│          REPOSITORY LAYER                │
│       FinanceRepository                  │
└──────┬───────────────────────┬───────────┘
       │                       │
       ▼                       ▼
┌─────────────┐       ┌────────────────────┐
│ DATA LAYER  │       │  SECURITY LAYER    │
│ Room +      │       │  CryptoManager     │
│ SQLCipher   │       │  SessionManager    │
│ DAO         │       │  BiometricHelper   │
└─────────────┘       └────────────────────┘
```

**Паттерн:** MVVM + Repository  
**DB:** Room + SQLCipher 4.5.4 (AES-256-CBC + HMAC-SHA512)

---

## Кілт иерархиясы (Key Hierarchy)

```
PIN (4–8 таңба)
    │
    ▼ PBKDF2-SHA256 (100 000 итерация, 256 бит)
K_user  (жадта ғана)
    │
    ▼ AES-256-GCM шифрлау
Enc(K_db)  → SharedPreferences-та сақталады
    │
    ▼ AES-256-GCM шешу
K_db  (сессия ішінде ғана жадта)
    │
    ▼ SQLCipher
Шифрланған SQLite (.db файлы)
```

**Қауіп моделі (Threat Model):**

| Қауіп | Қорғаныс |
|---|---|
| Ұрланған құрылғыдан DB оқу | SQLCipher AES-256 |
| Root арқылы `/data/data/` | SQLCipher file-level шифрлау |
| PIN брут-форс | PBKDF2 100к + экспоненциалды кешіктіру |
| RAM dump арқылы кілт алу | `ByteArray.fill(0)` жадтан өшіру |
| Биометрия ауыстыру атаkасы | `setInvalidatedByBiometricEnrollment(true)` |
| Recents screen ағуы | `FLAG_SECURE` |
| Google Backup ағуы | `allowBackup=false` |
| Backup файл ашық сақталуы | `.sfkz` AES-GCM + бөлек пароль |

---

## Жоба құрылымы

```
app/src/main/java/kz/diploma/securefinance/
├── FinanceApp.kt                    # Application класы
├── data/
│   ├── AppDatabase.kt               # Room + SQLCipher конфигурациясы
│   ├── FinanceRepository.kt         # Барлық DB операциялары
│   ├── dao/
│   │   ├── AccountDao.kt
│   │   ├── CategoryDao.kt
│   │   └── TransactionDao.kt
│   └── entity/
│       ├── Account.kt
│       ├── Category.kt
│       └── Transaction.kt
├── security/
│   ├── CryptoManager.kt             # PBKDF2 + AES-GCM кілт жүйесі
│   ├── SessionManager.kt            # Сессия + автоқұлыптау
│   └── BiometricHelper.kt           # BiometricPrompt + Keystore
├── ui/
│   ├── SplashActivity.kt
│   ├── MainActivity.kt              # Bottom Navigation хосты
│   ├── auth/
│   │   ├── SetupActivity.kt         # PIN орнату
│   │   └── AuthActivity.kt          # PIN / биометрия кіру
│   ├── dashboard/
│   ├── transactions/
│   ├── accounts/
│   ├── categories/
│   ├── statistics/
│   └── settings/
└── util/
    ├── Extensions.kt                # formatMoney, toFormattedDate, т.б.
    └── DefaultCategories.kt         # 19 алғашқы категория
```

---

## Экрандар

| № | Экран | Мақсаты |
|---|---|---|
| 1 | Splash | Аутентификация күйін тексеру |
| 2 | Setup | PIN орнату (алғашқы іске қосу) |
| 3 | Auth | PIN / биометрия кіру |
| 4 | Dashboard | Жалпы баланс, pie chart, соңғы операциялар |
| 5 | Transactions | Операциялар тізімі, сүзгі, іздеу |
| 6 | Add/Edit Transaction | Операция қосу / өңдеу |
| 7 | Accounts | Шоттар тізімі |
| 8 | Account Edit | Шот қосу / өңдеу |
| 9 | Categories | Кіріс/Шығыс категориялар |
| 10 | Category Edit | Категория қосу / өңдеу |
| 11 | Statistics | Bar/Pie chart, кезең таңдауы |
| 12 | Settings | Барлық баптаулар |
| 13 | Change PIN | PIN ауыстыру |
| 14 | Backup | Экспорт / Импорт |
| 15 | About | Қосымша туралы |

---

## Технологиялық стек

| Қабат | Технология | Нұсқа |
|---|---|---|
| Тіл | Kotlin | 1.9.22 |
| Min SDK | Android 7.0 | API 24 |
| Target SDK | Android 14 | API 34 |
| DB | Room + SQLCipher | 2.6.1 + 4.5.4 |
| Шифрлау | AES-256-GCM, PBKDF2-SHA256 | JCE (Android) |
| Биометрия | BiometricPrompt + Keystore | 1.1.0 |
| UI | Material Design 3 | 1.11.0 |
| Архитектура | MVVM + LiveData | Lifecycle 2.7.0 |
| Навигация | Navigation Component | 2.7.7 |
| Асинхрондылық | Kotlin Coroutines | 1.7.3 |
| Графиктер | MPAndroidChart | 3.1.0 |
| JSON | Gson | 2.10.1 |

---

## Unit тесттер

| Тест класы | Тест саны | Мазмұны |
|---|---|---|
| `CryptoManagerTest` | 7 | PBKDF2 детерминизмі, AES-GCM reversibility, кілт ұзындығы |
| `SessionManagerTest` | 9 | unlock/lock, shouldLock timeout, touch логикасы |
| `NavGraphArgTest` | 2 | nav_graph.xml `long` аргументтер тексеруі |
| `FormatterTest` | 4 | Валюта және күн форматтауы |
| **Барлығы** | **22** | **0 сәтсіздік** |

### Тесттерді іске қосу

```bash
./gradlew test
```

Нәтижелер: `app/build/reports/tests/testDebugUnitTest/index.html`

---

## Жобаны жинау (Build)

### Алдын-ала талаптар

- Android Studio Hedgehog немесе жоғары
- JDK 17+ (Android Studio JBR)
- Android SDK (API 24–34)

### Debug APK

```bash
cd SecureFinanceKZ
./gradlew assembleDebug
```

APK орналасады: `app/build/outputs/apk/debug/app-debug.apk` (~21 МБ)

### Android Studio арқылы

1. `File → Open` → `SecureFinanceKZ` папкасын таңдаңыз
2. Gradle sync автоматты басталады
3. `Run → Run 'app'`

> **Маңызды:** Жоба жолында кирилл немесе ASCII емес таңбалар болмауы керек.  
> Дұрыс: `C:\Projects\SecureFinanceKZ\`  
> Қате: `C:\Users\ОРЫНБАСАР\SecureFinanceKZ\`

---

## Белгілі шешілген мәселелер

| Мәселе | Себеп | Шешімі |
|---|---|---|
| Nav graph crash | `app:argType="long"` + `defaultValue="-1"` → Navigation integer деп оқиды | `<argument>` тегтерін алып тастап, Bundle арқылы ID беру |
| Process death crash | Фоннан қайтқанда Fragment `repository=null` кезінде ашылады | `MainActivity.onCreate()` + барлық Fragment-та `?: return` guard |
| EncryptedSharedPreferences crash | Кейбір құрылғыларда Android Keystore init сәтсіздігі | Қарапайым `SharedPreferences` + AES-GCM шифрмен ауыстырылды |
| SQLCipher import қатесі | `net.zetetic:sqlcipher-android` 4.5.4-та package жолы өзгерген | `net.zetetic.database.sqlcipher.SupportOpenHelperFactory` |
| `withTransaction` compile қатесі | `androidx.room.withTransaction` import жетіспеді | Import қосылды |
| `colorBackground` resource қатесі | `?attr/colorBackground` → app namespace ізделді | `?android:attr/colorBackground` |
| Adaptive icon minSdk қатесі | `<adaptive-icon>` API 26+ талап етеді, minSdk=24 | `mipmap-anydpi-v26/` жасалды |

---

## Деректер базасы схемасы

```sql
-- accounts
CREATE TABLE accounts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    type TEXT NOT NULL,        -- CASH | CARD | SAVINGS | OTHER
    balance REAL NOT NULL DEFAULT 0,
    currency TEXT NOT NULL DEFAULT 'KZT',
    color INTEGER NOT NULL,
    createdAt INTEGER NOT NULL
);

-- categories
CREATE TABLE categories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    type TEXT NOT NULL,        -- INCOME | EXPENSE
    color INTEGER NOT NULL
);

-- transactions
CREATE TABLE transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    amount REAL NOT NULL,
    type TEXT NOT NULL,        -- INCOME | EXPENSE
    accountId INTEGER NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    categoryId INTEGER REFERENCES categories(id) ON DELETE SET NULL,
    note TEXT NOT NULL DEFAULT '',
    date INTEGER NOT NULL
);
```

---

## APK мөлшері

| Конфигурация | Мөлшері |
|---|---|
| Debug APK | ~21 МБ |
| ТЗ талабы | ≤ 30 МБ |
| Нәтиже | ✅ Сәйкес |

---

## Лицензия

Бұл жоба дипломдық жұмыс ретінде әзірленген. Барлық құқықтар авторда.

---

*SecureFinance KZ — барлық деректеріңіз тек сіздің құрылғыңызда.*
