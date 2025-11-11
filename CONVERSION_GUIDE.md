# Symposium PHP to Spring Boot REST API Conversion - Implementation Guide

## Project Overview
This document outlines the conversion of the PHP-based Symposium project to a Spring Boot REST API with Java.

## ✅ Completed Work

### 1. Project Configuration
- **Updated pom.xml** with required dependencies:
  - Spring Boot Starter Web, JPA, Mail, Validation
  - Stripe Java SDK for payment processing
  - iText7 for PDF generation
  - Springdoc OpenAPI for API documentation
  - Apache Commons IO for file operations
  - WebFlux for HTTP client operations (Brevo API)

- **Configured application.properties**:
  - Database connection (MySQL)
  - File upload settings (10MB max)
  - Mail configuration (SMTP/Gmail)
  - Brevo API configuration for email
  - Stripe API configuration
  - CORS configuration
  - API documentation paths

### 2. Configuration Classes
- **AppConfig.java**: CORS, file upload handling, static resource serving

### 3. DTOs Created
- **AbstractSubmissionRequest.java**: Validation for abstract submission form
- **AbstractSubmissionResponse.java**: Response structure for abstract submissions
- **BrochureDownloadRequest.java**: Validation for brochure download requests

### 4. Service Layer
- **AbstractSubmissionService.java**:
  - Abstract submission with file upload
  - File validation (PDF, DOC, DOCX)
  - IP address capture
  - Email confirmation sending
  - Query methods (by user, email, category)

- **EmailService.java**:
  - Brevo API integration for sending emails
  - HTML email templates
  - File attachment support (Base64 encoding)
  - Abstract confirmation emails
  - Brochure download confirmation emails

- **FileStorageService.java**:
  - File upload/download management
  - File existence checking
  - File deletion

### 5. Repositories Updated
- **AbstractSubmissionRepository.java**: Custom queries (findByUser, findByEmail, etc.)
- **BrochureRepository.java**: Custom queries (findByUser, findByEmail)

### 6. REST Controllers
- **AbstractSubmissionController.java**:
  - POST /api/abstracts/submit (multipart/form-data)
  - GET /api/abstracts
  - GET /api/abstracts/{id}
  - GET /api/abstracts/conference/{user}
  - GET /api/abstracts/email/{email}

- **BrochureController.java**:
  - POST /api/brochure/download
  - GET /api/brochure
  - GET /api/brochure/conference/{userId}

## 🔄 PHP to Java Mapping

### Abstract Submission (submit-abstract.php)
| PHP Feature | Java Implementation |
|-------------|---------------------|
| Session captcha validation | Can be implemented with Spring Session + Redis |
| $_FILES file upload | MultipartFile with Spring MVC |
| move_uploaded_file() | Files.copy() with Java NIO |
| MySQL INSERT with prepared statements | JPA save() method |
| Brevo API cURL calls | WebClient (Spring WebFlux) |
| HTML email templates | String formatting (can use Thymeleaf) |

### Registration (registration.php)
| PHP Feature | Java Implementation Status |
|-------------|---------------------------|
| Early bird/standard/onspot pricing logic | Needs implementation |
| Form submission to personal-details | Needs DTO + Controller |
| Sponsorship plans (Platinum, Gold, etc.) | Needs implementation |
| Ad-sponsor redirection | Needs implementation |

### File Download (download.php)
| PHP Feature | Java Implementation |
|-------------|---------------------|
| File download with headers | ResponseEntity with Resource |
| File existence check | FileStorageService.fileExists() |

## 📋 Remaining Work (Priority Order)

### High Priority
1. **Registration Endpoints** (registration.php):
   - Create RegistrationRequest/Response DTOs
   - Implement pricing calculation service (early bird, standard, final)
   - Create RegistrationController with POST /api/registrations
   - Handle different registration types (speaker, delegate, poster, student)
   - Handle sponsorship registration (Platinum, Gold, Silver, etc.)

2. **Payment Processing** (payment.php, checkout.php):
   - Create PaymentService with Stripe integration
   - Implement payment initiation endpoint
   - Implement payment confirmation/webhook endpoint
   - Create PaymentController

3. **Captcha Service**:
   - Implement captcha generation (or use Google reCAPTCHA)
   - Add captcha validation in AbstractSubmissionService

4. **File Download Controller**:
   - Create FileDownloadController
   - Implement GET /api/files/download?file={filename}
   - Add security checks for file access

### Medium Priority
5. **Conference/Event Management**:
   - Create DTOs for conference data
   - Implement ConferenceService
   - Create ConferenceController (CRUD operations)

6. **Call for Abstracts Management**:
   - Repository already exists, needs service layer
   - Create CallForAbstractsController
   - Implement track/session management

7. **Admin Functionality**:
   - Authentication/Authorization (Spring Security + JWT)
   - Admin CRUD operations for all entities
   - Dashboard statistics endpoints

8. **Email Templates**:
   - Move to Thymeleaf templates (currently using String formatting)
   - Create template directory structure
   - Support for multiple conference templates

### Low Priority
9. **Attendees Management**:
   - Create AttendeesFromController
   - Implement CRUD operations

10. **Committee Management**:
    - Create CommitteeController, AdvisaryCommitteeController
    - CRUD operations for committee members

11. **Media Partners & Sponsors**:
    - Controllers for media partners, sponsors
    - File upload for logos

12. **Testing**:
    - Unit tests for services
    - Integration tests for controllers
    - API endpoint testing

## 🗄️ Database Tables Status

### ✅ Entities Created & Repositories Ready
- `abstract_submission`
- `abstracts` (no PK - uses `sno` as ID without auto-increment)
- `AcadBusiness`
- `admin` (no PK - needs composite key or custom ID strategy)
- `advisary_committee`
- `attendees_from`
- `banners`
- `brochure`
- `callforabstracts`

### ⏳ Needs Service & Controller Implementation
- All remaining tables in global_congress.sql (50+ tables)
- Priority: `conferences`, `deadlines`, `login_details`, `registration`, `payment_details`

## 🔐 Security Considerations

### To Implement
1. **Spring Security** for authentication
2. **JWT tokens** for stateless authentication
3. **Role-based access control** (Admin, User, Guest)
4. **API rate limiting**
5. **Input sanitization** (already using Bean Validation)
6. **File upload security** (size limits, type validation - partially done)
7. **SQL injection prevention** (JPA handles this)
8. **CORS configuration** (already configured)

## 📝 API Documentation

- Swagger UI will be available at: `http://localhost:8080/swagger-ui.html`
- API Docs (JSON): `http://localhost:8080/api-docs`

## 🚀 Next Steps to Run the Application

1. **Update application.properties**:
   ```properties
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   stripe.api.key=your_stripe_secret_key
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Create uploads directory**:
   ```bash
   mkdir uploads
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Test the endpoints**:
   - Open Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Test abstract submission: POST `http://localhost:8080/api/abstracts/submit`

## 📊 Progress Summary

- **Entities**: 9/50+ created
- **Repositories**: 9/50+ created
- **Services**: 3/20+ created
- **Controllers**: 2/15+ created
- **DTOs**: 3/30+ created
- **Overall Progress**: ~15%

## 🔧 Additional Features Needed

1. **PDF Generation** (replacing FPDF):
   - Abstract confirmation PDFs
   - Registration receipts
   - Certificates

2. **Multi-tenancy Support**:
   - Support for multiple conferences
   - Domain-based routing (entity field)

3. **Notification System**:
   - Email notifications for deadlines
   - Admin notifications for new submissions

4. **Reporting**:
   - Submission statistics
   - Registration reports
   - Revenue reports

5. **Frontend Integration**:
   - CORS is configured
   - API documentation ready
   - Frontend can consume REST endpoints

## 📚 Code Examples

### Testing Abstract Submission (cURL)
```bash
curl -X POST http://localhost:8080/api/abstracts/submit \
  -F "title=Dr." \
  -F "name=John Doe" \
  -F "email=john@example.com" \
  -F "phno=1234567890" \
  -F "organization=University" \
  -F "country=USA" \
  -F "category=Oral Presentation" \
  -F "session=Track 1" \
  -F "captchaCode=ABCD" \
  -F "user=CONFERENCE2025" \
  -F "uploadfile=@abstract.pdf"
```

### Testing Brochure Request (cURL)
```bash
curl -X POST http://localhost:8080/api/brochure/download \
  -H "Content-Type: application/json" \
  -d '{
    "prof": "Dr.",
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "organization": "University",
    "country": "USA",
    "message": "Please send brochure",
    "user": 1
  }'
```

## 🎯 Immediate Action Items

1. Build the Maven project: `mvn clean install`
2. Fix any compilation errors
3. Test the two implemented controllers
4. Implement Registration functionality (highest business priority)
5. Implement Payment processing (required for registration flow)
6. Add Spring Security for admin endpoints

---

**Note**: This is a substantial conversion project. The foundation is now in place with proper Spring Boot architecture, dependency injection, and REST API best practices. The remaining work involves replicating the business logic from the remaining PHP files into Java services and controllers.
