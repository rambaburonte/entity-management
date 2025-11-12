# REST API Endpoints - Quick Reference

Base URL: `https://api.ccai2026.com`

## Abstract Submission API

### Submit Abstract
**POST** `/api/abstracts/submit`

**Content-Type**: `multipart/form-data`

**Request Body** (form-data):
```
user: string (conference username, e.g., "CIVILCONGRESS2026")
title: string (Dr., Mr., Mrs., Prof., etc.)
name: string (full name)
email: string
phno: string (phone number)
country: string
organization: string
category: string (Speaker, Poster, etc.)
session: string (track name)
sentFrom: string (default: "Global Page")
entity: string (domain name)
uploadfile: file (PDF, DOC, DOCX - max file types allowed: gif, png, jpg, pdf, doc, docx)
```

**Response** (201 Created / 400 Bad Request):
```json
{
  "id": 123,
  "message": "Thank you for your interest in submitting Abstract. We will get back to you soon...",
  "status": "success",
  "email": "user@example.com",
  "attachmentUrl": "/uploads/123-Abstract.pdf"
}
```

### Get All Abstracts
**GET** `/api/abstracts`

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "user": "CIVILCONGRESS2026",
    "title": "Dr.",
    "fname": "John Doe",
    "email": "john@example.com",
    "phno": "+1234567890",
    "country": "USA",
    "org": "MIT",
    "category": "Speaker",
    "trackName": "Civil Engineering",
    "date": "2025-11-12",
    "attachment": "1-Abstract.pdf",
    "entity": "globalpolysciencesummit.com"
  }
]
```

### Get Abstract by ID
**GET** `/api/abstracts/{id}`

### Get Abstracts by Conference/User
**GET** `/api/abstracts/conference/{user}`

Example: `/api/abstracts/conference/CIVILCONGRESS2026`

### Get Abstracts by Email
**GET** `/api/abstracts/email/{email}`

Example: `/api/abstracts/email/john@example.com`

---

## Registration API

### Calculate Pricing
**GET** `/api/registration/pricing`

**Query Parameters**:
- `conferenceId`: string (conference ID from important_details table)
- `type`: string (Speaker, Delegate, Poster, Student)

**Example**: `/api/registration/pricing?conferenceId=13&type=Speaker`

**Response** (200 OK):
```json
{
  "registrationCategory": "Standard",
  "categoryEndDate": "2025-10-28",
  "prices": {
    "Speaker": 879.00,
    "Delegate": 999.00,
    "Poster": 549.00,
    "Student": 429.00,
    "Platinum": 10000.00,
    "Gold": 7500.00,
    "Silver": 5000.00,
    "Exhibitor": 3000.00,
    "Promotional": 1000.00
  },
  "conferenceId": "13",
  "conferenceName": "Global Civil & Environmental Congress"
}
```

**Pricing Categories**:
- **EarlyBird**: Speaker=$779, Delegate=$899, Poster=$449, Student=$329
- **Standard**: Speaker=$879, Delegate=$999, Poster=$549, Student=$429
- **Final/OnSpot**: Speaker=$979, Delegate=$1099, Poster=$649, Student=$529

### Submit Registration
**POST** `/api/registration`

**Content-Type**: `application/json`

**Request Body**:
```json
{
  "conferenceId": "13",
  "registrationType": "Speaker",
  "title": "Dr.",
  "name": "Jane Smith",
  "email": "jane@example.com",
  "phone": "+1234567890",
  "country": "USA",
  "organization": "Stanford University",
  "address": "123 Main St, CA"
}
```

**Response** (200 OK / 400 Bad Request):
```json
{
  "status": "success",
  "message": "Registration submitted successfully. Please proceed with payment.",
  "registrationId": 456,
  "amount": 879.00,
  "registrationCategory": "Standard"
}
```

### Get Registration by ID
**GET** `/api/registration/{id}`

**Response** (200 OK):
```json
{
  "id": 456,
  "title": "Dr.",
  "name": "Jane Smith",
  "email": "jane@example.com",
  "phone": "+1234567890",
  "country": "USA",
  "org": "Stanford University",
  "price": 879.00,
  "date": "2025-11-12",
  "conf": "13",
  "category": "Speaker",
  "status": 0,
  "invoiceNumber": "CIVILCONGRESS2026456",
  "paymentType": null,
  "tId": null
}
```

### Get Registrations by Conference
**GET** `/api/registration/conference/{conferenceId}`

Example: `/api/registration/conference/CIVILCONGRESS2026`

### Get Registrations by Email
**GET** `/api/registration/email/{email}`

Example: `/api/registration/email/jane@example.com`

---

## Conference API

### Get All Conferences
**GET** `/api/conference`

### Get Conference by ID
**GET** `/api/conference/{id}`

### Get Conference by Name
**GET** `/api/conference/name/{name}`

### Create Conference
**POST** `/api/conference`

### Update Conference
**PUT** `/api/conference/{id}`

### Delete Conference
**DELETE** `/api/conference/{id}`

---

## Workshops API

### Get All Workshops
**GET** `/api/workshops`

### Get Workshop by ID
**GET** `/api/workshops/{id}`

### Get Workshops by User
**GET** `/api/workshops/user/{user}`

---

## Sponsors API

### Get All Sponsors
**GET** `/api/sponsors`

### Get Sponsor by ID
**GET** `/api/sponsors/{id}`

### Get Sponsors by User
**GET** `/api/sponsors/user/{user}`

---

## Committee API

### Get All Committee Members
**GET** `/api/committee`

### Get Committee Member by ID
**GET** `/api/committee/{id}`

### Get Committee Members by User
**GET** `/api/committee/user/{user}`

---

## Tracks API

### Get All Tracks
**GET** `/api/tracks`

### Get Track by ID
**GET** `/api/tracks/{id}`

### Get Tracks by User
**GET** `/api/tracks/user/{user}`

---

## Speaker Info API

### Get All Speakers
**GET** `/api/speakers`

### Get Speaker by ID
**GET** `/api/speakers/{id}`

### Get Speakers by User
**GET** `/api/speakers/user/{user}`

---

## Error Responses

All endpoints may return error responses in the following format:

**400 Bad Request**:
```json
{
  "status": "error",
  "message": "Detailed error message"
}
```

**404 Not Found**:
```json
{
  "status": "error",
  "message": "Resource not found"
}
```

**500 Internal Server Error**:
```json
{
  "status": "error",
  "message": "Internal server error"
}
```

---

## Axios Examples for React

### Abstract Submission
```javascript
const submitAbstract = async (formData, file) => {
  const data = new FormData();
  data.append('user', formData.user);
  data.append('title', formData.title);
  data.append('name', formData.name);
  data.append('email', formData.email);
  data.append('phno', formData.phone);
  data.append('country', formData.country);
  data.append('organization', formData.organization);
  data.append('category', formData.category);
  data.append('session', formData.trackName);
  data.append('sentFrom', 'Global Page');
  data.append('entity', 'globalpolysciencesummit.com');
  data.append('uploadfile', file);
  
  const response = await axios.post(
    'https://api.ccai2026.com/api/abstracts/submit',
    data,
    {
      headers: { 'Content-Type': 'multipart/form-data' }
    }
  );
  return response.data;
};
```

### Get Pricing
```javascript
const getPricing = async (conferenceId, type) => {
  const response = await axios.get(
    'https://api.ccai2026.com/api/registration/pricing',
    {
      params: { conferenceId, type }
    }
  );
  return response.data;
};
```

### Submit Registration
```javascript
const submitRegistration = async (formData) => {
  const response = await axios.post(
    'https://api.ccai2026.com/api/registration',
    formData,
    {
      headers: { 'Content-Type': 'application/json' }
    }
  );
  return response.data;
};
```

---

## Testing with cURL

### Submit Abstract
```bash
curl -X POST https://api.ccai2026.com/api/abstracts/submit \
  -H "Content-Type: multipart/form-data" \
  -F "user=CIVILCONGRESS2026" \
  -F "title=Dr." \
  -F "name=John Doe" \
  -F "email=john@example.com" \
  -F "phno=+1234567890" \
  -F "country=USA" \
  -F "organization=MIT" \
  -F "category=Speaker" \
  -F "session=Civil Engineering" \
  -F "sentFrom=Global Page" \
  -F "entity=globalpolysciencesummit.com" \
  -F "uploadfile=@/path/to/abstract.pdf"
```

### Get Pricing
```bash
curl -X GET "https://api.ccai2026.com/api/registration/pricing?conferenceId=13&type=Speaker"
```

### Submit Registration
```bash
curl -X POST https://api.ccai2026.com/api/registration \
  -H "Content-Type: application/json" \
  -d '{
    "conferenceId": "13",
    "registrationType": "Speaker",
    "title": "Dr.",
    "name": "Jane Smith",
    "email": "jane@example.com",
    "phone": "+1234567890",
    "country": "USA",
    "organization": "Stanford University",
    "address": "123 Main St, CA"
  }'
```

---

**Note**: Replace `https://api.ccai2026.com` with your actual backend URL if different.
