# Entity-Database Complete Mapping Guide
**Generated from final database: global_congress.sql (Nov 13, 2025)**

## Critical Rules:
1. **ALL column names MUST match database EXACTLY** (case-sensitive)
2. **All tables have PRIMARY KEY with AUTO_INCREMENT** except a few
3. **Use @Column(name = "exact_db_column_name") for all non-standard columns**
4. **Engine: InnoDB for all tables with AUTO_INCREMENT**

---

## Table-by-Table Mapping

### 1. abstracts
```sql
CREATE TABLE `abstracts` (
  `sno` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Title` varchar(50),
  `Name` varchar(50),
  `Surname` varchar(50),
  `Country` varchar(20),
  `Authors_Email` varchar(50),
  `Alternative_Email` varchar(50),
  `Abstract_Category` varchar(80),
  `Abstract` varchar(80),
  `Full_Postal_Address` varchar(200),
  `Attach_your_file` varchar(50)
)
```

### 2. abstract_submission ✅ 
```sql
CREATE TABLE `abstract_submission` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(500),
  `title` varchar(500),
  `fname` varchar(500),
  `country` varchar(500),
  `org` text,
  `email` varchar(500),
  `phno` varchar(500),
  `category` varchar(500),
  `sent_from` varchar(500),
  `track_name` varchar(500),
  `address` text,
  `date` date,
  `ipaddress` varchar(500),
  `attachment` varchar(500),
  `presentation_title` varchar(2000),
  `entity` varchar(255)
)
```

### 3. AcadBusiness
```sql
CREATE TABLE `AcadBusiness` (
  `EID` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `content` text,
  `category` varchar(50),
  `id` int
)
```

### 4. admin
```sql
CREATE TABLE `admin` (
  `username` varchar(45),
  `password` varchar(45),
  `login` datetime,
  `email` varchar(60)
)
```
**Note: No PRIMARY KEY**

### 5. advisary_committee
```sql
CREATE TABLE `advisary_committee` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `research` text,
  `pub1` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int,
  `recordListingID` int DEFAULT 0
)
```

### 6. attendees_from
```sql
CREATE TABLE `attendees_from` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `image` varchar(500),
  `user` varchar(500),
  `country` varchar(500),
  `count` int,
  `link` varchar(500)
)
```

### 7. banners
```sql
CREATE TABLE `banners` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `image` varchar(500),
  `user` varchar(500),
  `link` varchar(500)
)
```

### 8. brochure
```sql
CREATE TABLE `brochure` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tentative_program` varchar(100),
  `Brochure` varchar(100),
  `Sponsorship` varchar(100),
  `abstract_book` varchar(100),
  `cfa` varchar(100),
  `visa` varchar(100),
  `flyer` varchar(100),
  `user` int
)
```

### 9. committee ✅
```sql
CREATE TABLE `committee` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `research` text,
  `pub1` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int DEFAULT 0,
  `recordListingID` int DEFAULT 0
)
```

### 10. contact_us
```sql
CREATE TABLE `contact_us` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `email` varchar(100),
  `subject` varchar(200),
  `user` varchar(100)
)
```

### 11. featured_speakers ✅
```sql
CREATE TABLE `featured_speakers` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `research` text,
  `pub1` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int DEFAULT 0,
  `recordListingID` int DEFAULT 0
)
```

### 12. important_details ✅
```sql
CREATE TABLE `important_details` (
  `sno` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `id` int UNIQUE,
  `ShortName` varchar(500),
  `ConfUrl` varchar(500),
  `Theme` varchar(500),
  `EmailId1` varchar(500),
  `EmailId2` varchar(500),
  `EmailId3` varchar(500),
  `abstract_submission_deadline` varchar(500),
  `abstract_submission_opens` varchar(500),
  `registration_opens` varchar(500),
  `EarlyBird` varchar(500),
  `mid_term` varchar(500),
  `Late_registration` varchar(200),
  `OnSpot` varchar(500),
  `ConferenceTitle` varchar(500),
  `ConferenceVenue` varchar(500),
  `ConferenceDates` varchar(500),
  `facebook_link` varchar(500),
  `linkedin_link` varchar(500),
  `instagram_link` varchar(500),
  `twitter_link` varchar(500),
  `twitter_tweets` mediumtext,
  `date` varchar(500),
  `pc_name` varchar(500),
  `entity_logo` varchar(500)
)
```

### 13. invited_speakers ✅
```sql
CREATE TABLE `invited_speakers` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `abstract` text,
  `research` text,
  `pub1` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int DEFAULT 0,
  `recordListingID` int DEFAULT 0
)
```

### 14. keynote_speakers ✅
```sql
CREATE TABLE `keynote_speakers` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `abstract` text,
  `research` text,
  `pub1` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int DEFAULT 0,
  `recordListingID` int DEFAULT 0
)
```

### 15. media_partners ✅
```sql
CREATE TABLE `media_partners` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `mediapartner_name` varchar(500),
  `link` varchar(500),
  `description` text,
  `user` varchar(500),
  `photo` varchar(500)
)
```

### 16. members ✅
```sql
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(500),
  `affiliation` text,
  `email` varchar(500),
  `photo` varchar(100),
  `speaker_category` varchar(500),
  `biography` text,
  `research` text,
  `abstract` text,
  `category` varchar(500) DEFAULT 'Plenary',
  `user` int,
  `recordListingID` int DEFAULT 0
)
```

### 17. plenary_speakers ✅
```sql
CREATE TABLE `plenary_speakers` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100),
  `affiliation` text,
  `photo` varchar(100),
  `biography` text,
  `abstract` text,
  `research` text,
  `pub2` text,
  `pub3` text,
  `pub4` text,
  `network` text,
  `user` int DEFAULT 0,
  `recordListingID` int DEFAULT 0
)
```
**Note: pub2, pub3, pub4 (no pub1)**

### 18. registrations ✅
```sql
CREATE TABLE `registrations` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(500),
  `name` varchar(500),
  `email` varchar(500),
  `phone` varchar(20),
  `country` varchar(500),
  `address` text,
  `org` text,
  `price` double,
  `checkin_date` varchar(500),
  `date` date,
  `conf` varchar(500),
  `token` text,
  `confirm_no` varchar(500),
  `status` int,
  `description` text,
  `payment_type` varchar(255),
  `category` varchar(500),
  `entity_assigned` varchar(500),
  `invoice_number` varchar(500),
  `t_id` varchar(200),
  `entity` varchar(255)
)
```

### 19. scientific_programme
```sql
CREATE TABLE `scientific_programme` (
  `sid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `conference` varchar(500),
  `day` varchar(500),
  `date` varchar(500),
  `user` int
)
```
**Note: PRIMARY KEY is `sid` not `id`**

### 20. sponsors ✅
```sql
CREATE TABLE `sponsors` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `sponsor_name` varchar(200),
  `link` varchar(200),
  `description` text,
  `user` varchar(200),
  `photo` varchar(200)
)
```

### 21. tracks
```sql
CREATE TABLE `tracks` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `track_name` text,
  `session_name` varchar(200),
  `session_affiliation` text,
  `session_photo` varchar(120),
  `session_logo` varchar(120),
  `cosession_name` varchar(200),
  `cosession_affiliation` text,
  `cosession_photo` varchar(120),
  `cosession_logo` varchar(120),
  `sa_name` varchar(100),
  `sa_affiliation` text,
  `sa_photo` varchar(80),
  `sa_logo` varchar(80),
  `sid` int,
  `track_time` varchar(100),
  `track_place` varchar(100),
  `track_date` date,
  `user` int,
  `recordListingID` int DEFAULT 0
)
```

### 22. workshop ✅
```sql
CREATE TABLE `workshop` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` int,
  `image` varchar(500),
  `heading` varchar(500),
  `para` text,
  `recordListingID` int,
  `link` varchar(500)
)
```

---

## Action Items:

1. ✅ Update all entities to use **exact column names** from database
2. ✅ Ensure all PRIMARY KEY fields use `@GeneratedValue(strategy = GenerationType.IDENTITY)`
3. ✅ Use `@Column(name = "exact_name")` for all columns
4. ✅ Match data types exactly (int → Integer, text → String with @Lob, date → LocalDate)
5. ✅ Keep recordListingID as Integer (not int primitive)
6. ✅ Update application.properties: `spring.jpa.hibernate.ddl-auto=none`

