-- ============================================================================
-- FINAL DATABASE STRUCTURE - AUTO_INCREMENT FIX
-- This script adds AUTO_INCREMENT to all PRIMARY KEY columns
-- Run ONLY the ALTER statements needed for your existing tables
-- Database: global_congress (Final version Nov 13, 2025)
-- ============================================================================

-- NOTE: Only run ALTER statements for tables that are missing AUTO_INCREMENT

-- 1. abstract_submission (if needed)
ALTER TABLE `abstract_submission`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 2. important_details (if needed)
ALTER TABLE `important_details`
  MODIFY `sno` int NOT NULL AUTO_INCREMENT;

-- 3. sponsors (if needed)
ALTER TABLE `sponsors`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 4. workshop (if needed)
ALTER TABLE `workshop`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 5. committee (if needed)
ALTER TABLE `committee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 6. media_partners (if needed)
ALTER TABLE `media_partners`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 7. members (if needed)
ALTER TABLE `members`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 8. keynote_speakers (if needed)
ALTER TABLE `keynote_speakers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 9. plenary_speakers (if needed)
ALTER TABLE `plenary_speakers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 10. invited_speakers (if needed)
ALTER TABLE `invited_speakers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 11. featured_speakers (if needed)
ALTER TABLE `featured_speakers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 12. tracks (if needed)
ALTER TABLE `tracks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 13. scientific_programme (if needed)
ALTER TABLE `scientific_programme`
  MODIFY `sid` int NOT NULL AUTO_INCREMENT;

-- 14. registrations (if needed)
ALTER TABLE `registrations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 15. brochure (if needed)
ALTER TABLE `brochure`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 16. contact_us (if needed)
ALTER TABLE `contact_us`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 17. advisary_committee (if needed)
ALTER TABLE `advisary_committee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 18. attendees_from (if needed)
ALTER TABLE `attendees_from`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 19. banners (if needed)
ALTER TABLE `banners`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

-- 20. abstracts (if needed)
ALTER TABLE `abstracts`
  MODIFY `sno` int NOT NULL AUTO_INCREMENT;

-- 21. AcadBusiness (if needed)
ALTER TABLE `AcadBusiness`
  MODIFY `EID` int NOT NULL AUTO_INCREMENT;

-- ============================================================================
-- VERIFICATION QUERIES
-- Run these to check if AUTO_INCREMENT is properly set
-- ============================================================================

SHOW CREATE TABLE abstract_submission;
SHOW CREATE TABLE important_details;
SHOW CREATE TABLE sponsors;
SHOW CREATE TABLE workshop;
SHOW CREATE TABLE committee;
SHOW CREATE TABLE media_partners;
SHOW CREATE TABLE members;
SHOW CREATE TABLE registrations;

-- ============================================================================
-- NOTES:
-- 1. All tables in your database already have PRIMARY KEYs defined
-- 2. These ALTER statements only add AUTO_INCREMENT if missing
-- 3. After running, all INSERT queries will work without specifying ID
-- 4. Spring Boot entities are configured to match these exact structures
-- ============================================================================
