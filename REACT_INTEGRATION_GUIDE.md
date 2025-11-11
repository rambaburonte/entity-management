# React Frontend Integration Guide

## Overview
This document provides comprehensive API documentation for integrating the Spring Boot REST API with your React.js frontend. All endpoints follow REST conventions and return JSON responses.

## Base URL
```
http://localhost:8080/api
```

## CORS Configuration
CORS is enabled for frontend development. Configure allowed origins in `application.properties`:
```properties
cors.allowed-origins=http://localhost:3000,http://localhost:5173
```

## API Documentation
Interactive API documentation available at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

---

## 1. Abstract Submission API

### 1.1 Submit Abstract
**Endpoint:** `POST /api/abstracts/submit`  
**Content-Type:** `multipart/form-data`

**Request Body:**
```javascript
const formData = new FormData();
formData.append('title', 'Dr.');
formData.append('name', 'John Smith');
formData.append('email', 'john.smith@example.com');
formData.append('phno', '+1234567890');
formData.append('organization', 'University of Science');
formData.append('country', 'USA');
formData.append('category', 'Oral Presentation');
formData.append('session', 'Clinical Research');
formData.append('captchaCode', '123456');
formData.append('user', 'Conference Name');
formData.append('presentationTitle', 'Research Title');
formData.append('file', fileObject); // PDF, DOC, or DOCX (max 10MB)
```

**React Example:**
```javascript
import axios from 'axios';

const submitAbstract = async (formData) => {
  try {
    const response = await axios.post(
      'http://localhost:8080/api/abstracts/submit',
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
    );
    console.log('Success:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error:', error.response?.data);
    throw error;
  }
};
```

**Success Response (200):**
```json
{
  "status": "success",
  "message": "Abstract submitted successfully",
  "submissionId": 123,
  "attachmentUrl": "/uploads/123456789_document.pdf"
}
```

**Error Response (400):**
```json
{
  "status": "error",
  "message": "Validation failed: Email is mandatory"
}
```

### 1.2 Get All Abstracts
**Endpoint:** `GET /api/abstracts`

**React Example:**
```javascript
const getAllAbstracts = async () => {
  const response = await axios.get('http://localhost:8080/api/abstracts');
  return response.data;
};
```

### 1.3 Get Abstract by ID
**Endpoint:** `GET /api/abstracts/{id}`

**React Example:**
```javascript
const getAbstract = async (id) => {
  const response = await axios.get(`http://localhost:8080/api/abstracts/${id}`);
  return response.data;
};
```

### 1.4 Get Abstracts by Conference
**Endpoint:** `GET /api/abstracts/conference/{conferenceUser}`

### 1.5 Get Abstracts by Email
**Endpoint:** `GET /api/abstracts/email/{email}`

---

## 2. Brochure Download API

### 2.1 Request Brochure Download
**Endpoint:** `POST /api/brochure/download`  
**Content-Type:** `application/json`

**Request Body:**
```json
{
  "name": "John Smith",
  "email": "john.smith@example.com",
  "phone": "+1234567890",
  "organization": "University of Science",
  "country": "USA",
  "conferenceId": "conf123"
}
```

**React Example:**
```javascript
const requestBrochure = async (data) => {
  try {
    const response = await axios.post(
      'http://localhost:8080/api/brochure/download',
      data,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
    return response.data;
  } catch (error) {
    console.error('Error:', error.response?.data);
    throw error;
  }
};
```

**Success Response (200):**
```json
{
  "status": "success",
  "message": "Brochure request submitted successfully"
}
```

---

## 3. Registration API

### 3.1 Calculate Pricing
**Endpoint:** `GET /api/registration/pricing?conferenceId={id}&type={type}`

**Query Parameters:**
- `conferenceId`: Conference identifier
- `type`: Registration type (Speaker, Delegate, Poster, Student, Sponsor)

**React Example:**
```javascript
const calculatePricing = async (conferenceId, registrationType) => {
  const response = await axios.get(
    `http://localhost:8080/api/registration/pricing`,
    {
      params: {
        conferenceId,
        type: registrationType
      }
    }
  );
  return response.data;
};

// Usage
const pricing = await calculatePricing('conf123', 'Speaker');
console.log(pricing);
```

**Success Response (200):**
```json
{
  "registrationCategory": "EarlyBird",
  "categoryEndDate": "2024-03-31",
  "prices": {
    "Speaker": 779.00,
    "Delegate": 899.00,
    "Poster": 449.00,
    "Student": 329.00,
    "Platinum": 10000.00,
    "Gold": 7500.00,
    "Silver": 5000.00,
    "Exhibitor": 3000.00,
    "Promotional": 1000.00
  },
  "conferenceId": "conf123",
  "conferenceName": "International Medical Conference 2024"
}
```

**Pricing Categories:**
- **EarlyBird**: Lowest prices, before early bird deadline
- **Standard**: Medium prices, before standard deadline
- **Final**: Highest prices, until conference starts

### 3.2 Submit Registration
**Endpoint:** `POST /api/registration`  
**Content-Type:** `application/json`

**Request Body:**
```json
{
  "registrationType": "Speaker",
  "name": "Dr. John Smith",
  "email": "john.smith@example.com",
  "phone": "+1234567890",
  "organization": "University of Science",
  "country": "USA",
  "address": "123 Main St, City, State 12345",
  "conferenceId": "conf123",
  "title": "Dr.",
  "designation": "Professor",
  "specialRequirements": "Vegetarian meal",
  "sponsorPlan": null
}
```

**For Sponsors:**
```json
{
  "registrationType": "Sponsor",
  "sponsorPlan": "Gold",
  "name": "ABC Corporation",
  "email": "contact@abccorp.com",
  "phone": "+1234567890",
  "organization": "ABC Corporation",
  "country": "USA",
  "address": "456 Corporate Blvd",
  "conferenceId": "conf123"
}
```

**React Example:**
```javascript
const submitRegistration = async (registrationData) => {
  try {
    const response = await axios.post(
      'http://localhost:8080/api/registration',
      registrationData,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    );
    return response.data;
  } catch (error) {
    console.error('Error:', error.response?.data);
    throw error;
  }
};
```

**Success Response (200):**
```json
{
  "status": "success",
  "message": "Registration submitted successfully. Please proceed with payment.",
  "registrationId": 456,
  "amount": 779.00,
  "registrationCategory": "EarlyBird",
  "paymentIntentId": null,
  "clientSecret": null
}
```

### 3.3 Get Registration by ID
**Endpoint:** `GET /api/registration/{id}`

### 3.4 Get Registrations by Conference
**Endpoint:** `GET /api/registration/conference/{conferenceId}`

### 3.5 Get Registrations by Email
**Endpoint:** `GET /api/registration/email/{email}`

---

## 4. Payment API (Stripe Integration)

### 4.1 Create Payment Intent
**Endpoint:** `POST /api/payment/create-intent`  
**Content-Type:** `application/json`

**Request Body:**
```json
{
  "amount": 779.00,
  "currency": "usd",
  "email": "john.smith@example.com",
  "description": "Speaker Registration - Conference 2024",
  "registrationId": 456,
  "conferenceId": "conf123",
  "metadata": {
    "customerName": "Dr. John Smith",
    "registrationType": "Speaker"
  }
}
```

**React Example with Stripe:**
```javascript
import { loadStripe } from '@stripe/stripe-js';
import { Elements, PaymentElement, useStripe, useElements } from '@stripe/react-stripe-js';

const stripePromise = loadStripe('your_stripe_publishable_key');

const PaymentForm = ({ registrationId, amount }) => {
  const stripe = useStripe();
  const elements = useElements();

  const handlePayment = async () => {
    // Step 1: Create payment intent
    const paymentIntent = await axios.post(
      'http://localhost:8080/api/payment/create-intent',
      {
        amount: amount,
        currency: 'usd',
        email: 'john.smith@example.com',
        description: 'Speaker Registration',
        registrationId: registrationId,
        conferenceId: 'conf123'
      }
    );

    const { clientSecret } = paymentIntent.data;

    // Step 2: Confirm payment with Stripe
    const { error, paymentIntent: confirmedPayment } = await stripe.confirmPayment({
      elements,
      clientSecret,
      confirmParams: {
        return_url: 'http://localhost:3000/payment-success',
      },
    });

    if (error) {
      console.error('Payment failed:', error);
    } else if (confirmedPayment.status === 'succeeded') {
      // Step 3: Confirm payment on backend
      await axios.post(
        `http://localhost:8080/api/payment/confirm/${confirmedPayment.id}`
      );
      console.log('Payment successful!');
    }
  };

  return (
    <Elements stripe={stripePromise}>
      <form onSubmit={handlePayment}>
        <PaymentElement />
        <button type="submit">Pay ${amount}</button>
      </form>
    </Elements>
  );
};
```

**Success Response (200):**
```json
{
  "status": "success",
  "message": "Payment intent created successfully",
  "paymentIntentId": "pi_3ABC123XYZ",
  "clientSecret": "pi_3ABC123XYZ_secret_xyz",
  "amount": 779.00,
  "currency": "usd",
  "registrationId": 456,
  "paymentStatus": "requires_payment_method"
}
```

### 4.2 Confirm Payment
**Endpoint:** `POST /api/payment/confirm/{paymentIntentId}`

This endpoint is called automatically after Stripe payment confirmation to update the registration status and send confirmation email.

**React Example:**
```javascript
const confirmPayment = async (paymentIntentId) => {
  const response = await axios.post(
    `http://localhost:8080/api/payment/confirm/${paymentIntentId}`
  );
  return response.data;
};
```

### 4.3 Webhook (For Stripe Events)
**Endpoint:** `POST /api/payment/webhook`

This endpoint handles Stripe webhook events for payment status updates. Configure in Stripe Dashboard:
```
Webhook URL: https://yourdomain.com/api/payment/webhook
Events: payment_intent.succeeded, payment_intent.payment_failed
```

---

## 5. Conference API

### 5.1 Get All Conferences
**Endpoint:** `GET /api/conferences`

**React Example:**
```javascript
const getAllConferences = async () => {
  const response = await axios.get('http://localhost:8080/api/conferences');
  return response.data;
};
```

### 5.2 Get Active Conferences
**Endpoint:** `GET /api/conferences/active`

Returns only conferences with future dates.

### 5.3 Get Conference Details with Pricing
**Endpoint:** `GET /api/conferences/{id}/details`

**React Example:**
```javascript
const getConferenceDetails = async (conferenceId) => {
  const response = await axios.get(
    `http://localhost:8080/api/conferences/${conferenceId}/details`
  );
  return response.data;
};
```

**Success Response (200):**
```json
{
  "conferenceId": "conf123",
  "conferenceName": "International Medical Conference 2024",
  "conferenceDate": "2024-06-15",
  "location": "New York",
  "country": "USA",
  "earlyBirdDeadline": "2024-03-31",
  "standardDeadline": "2024-05-15",
  "abstractDeadline": "2024-04-30",
  "currentPricingCategory": "EarlyBird",
  "nextDeadline": "2024-03-31",
  "isActive": true
}
```

---

## 6. Discount Code API

### 6.1 Validate Discount Code
**Endpoint:** `POST /api/discount/validate`  
**Content-Type:** `application/json`

**Request Body:**
```json
{
  "discountCode": "EARLY2024",
  "conferenceId": "conf123",
  "originalAmount": 779.00,
  "registrationType": "Speaker"
}
```

**React Example:**
```javascript
const validateDiscount = async (discountData) => {
  try {
    const response = await axios.post(
      'http://localhost:8080/api/discount/validate',
      discountData
    );
    return response.data;
  } catch (error) {
    console.error('Invalid discount code:', error.response?.data);
    return error.response?.data;
  }
};

// Usage
const discountResult = await validateDiscount({
  discountCode: 'EARLY2024',
  conferenceId: 'conf123',
  originalAmount: 779.00,
  registrationType: 'Speaker'
});

if (discountResult.isValid) {
  console.log('Final Amount:', discountResult.finalAmount);
}
```

**Success Response (200):**
```json
{
  "status": "success",
  "message": "Discount code applied successfully",
  "isValid": true,
  "discountCode": "EARLY2024",
  "discountType": "percentage",
  "discountValue": 10.00,
  "originalAmount": 779.00,
  "discountAmount": 77.90,
  "finalAmount": 701.10
}
```

**Error Response (400):**
```json
{
  "status": "error",
  "message": "Invalid or expired discount code",
  "isValid": false,
  "originalAmount": 779.00,
  "finalAmount": 779.00
}
```

---

## 7. Complete Registration + Payment Flow

### React Component Example

```javascript
import React, { useState } from 'react';
import axios from 'axios';
import { loadStripe } from '@stripe/stripe-js';
import { Elements, PaymentElement, useStripe, useElements } from '@stripe/react-stripe-js';

const stripePromise = loadStripe('pk_test_your_key');

const RegistrationFlow = () => {
  const [step, setStep] = useState(1);
  const [pricing, setPricing] = useState(null);
  const [registrationData, setRegistrationData] = useState({});
  const [registrationId, setRegistrationId] = useState(null);
  const [clientSecret, setClientSecret] = useState(null);
  const [discountApplied, setDiscountApplied] = useState(null);

  // Step 1: Calculate Pricing
  const handleCalculatePricing = async () => {
    try {
      const response = await axios.get(
        'http://localhost:8080/api/registration/pricing',
        {
          params: {
            conferenceId: 'conf123',
            type: registrationData.registrationType
          }
        }
      );
      setPricing(response.data);
      setStep(2);
    } catch (error) {
      console.error('Error calculating pricing:', error);
    }
  };

  // Step 1.5: Apply Discount (Optional)
  const handleApplyDiscount = async (discountCode) => {
    try {
      const response = await axios.post(
        'http://localhost:8080/api/discount/validate',
        {
          discountCode: discountCode,
          conferenceId: registrationData.conferenceId,
          originalAmount: pricing.prices[registrationData.registrationType],
          registrationType: registrationData.registrationType
        }
      );
      
      if (response.data.isValid) {
        setDiscountApplied(response.data);
      }
    } catch (error) {
      console.error('Invalid discount code:', error);
    }
  };

  // Step 2: Submit Registration
  const handleSubmitRegistration = async () => {
    try {
      // Add discount code to registration if applied
      if (discountApplied) {
        registrationData.discountCode = discountApplied.discountCode;
      }

      const response = await axios.post(
        'http://localhost:8080/api/registration',
        registrationData
      );
      
      if (response.data.status === 'success') {
        setRegistrationId(response.data.registrationId);
        setStep(3);
        
        // Create payment intent with final amount
        const finalAmount = discountApplied ? discountApplied.finalAmount : response.data.amount;
        
        const paymentResponse = await axios.post(
          'http://localhost:8080/api/payment/create-intent',
          {
            amount: finalAmount,
            currency: 'usd',
            email: registrationData.email,
            description: `${registrationData.registrationType} Registration`,
            registrationId: response.data.registrationId,
            conferenceId: registrationData.conferenceId
          }
        );
        
        setClientSecret(paymentResponse.data.clientSecret);
      }
    } catch (error) {
      console.error('Error submitting registration:', error);
    }
  };

  // Step 3: Payment Form Component
  const PaymentForm = () => {
    const stripe = useStripe();
    const elements = useElements();
    const [processing, setProcessing] = useState(false);

    const handleSubmit = async (e) => {
      e.preventDefault();
      setProcessing(true);

      const { error, paymentIntent } = await stripe.confirmPayment({
        elements,
        redirect: 'if_required',
      });

      if (error) {
        console.error('Payment error:', error);
        setProcessing(false);
      } else if (paymentIntent.status === 'succeeded') {
        // Confirm on backend
        await axios.post(
          `http://localhost:8080/api/payment/confirm/${paymentIntent.id}`
        );
        setStep(4); // Success page
      }
    };

    const finalAmount = discountApplied ? discountApplied.finalAmount : pricing.prices[registrationData.registrationType];

    return (
      <form onSubmit={handleSubmit}>
        <PaymentElement />
        <button type="submit" disabled={!stripe || processing}>
          {processing ? 'Processing...' : `Pay $${finalAmount}`}
        </button>
      </form>
    );
  };

  return (
    <div>
      {step === 1 && (
        <div>
          <h2>Step 1: Registration Details</h2>
          {/* Registration form fields */}
          <button onClick={handleCalculatePricing}>Continue</button>
        </div>
      )}

      {step === 2 && (
        <div>
          <h2>Step 2: Review & Apply Discount</h2>
          <p>Category: {pricing.registrationCategory}</p>
          <p>Original Amount: ${pricing.prices[registrationData.registrationType]}</p>
          
          {/* Discount code input */}
          <input 
            type="text" 
            placeholder="Discount Code (Optional)" 
            onBlur={(e) => handleApplyDiscount(e.target.value)}
          />
          
          {discountApplied && (
            <div>
              <p>Discount: -${discountApplied.discountAmount}</p>
              <p><strong>Final Amount: ${discountApplied.finalAmount}</strong></p>
            </div>
          )}
          
          <button onClick={handleSubmitRegistration}>Proceed to Payment</button>
        </div>
      )}

      {step === 3 && clientSecret && (
        <div>
          <h2>Step 3: Payment</h2>
          <Elements stripe={stripePromise} options={{ clientSecret }}>
            <PaymentForm />
          </Elements>
        </div>
      )}

      {step === 4 && (
        <div>
          <h2>Success!</h2>
          <p>Your registration is complete. Confirmation email sent.</p>
          <p>Registration ID: {registrationId}</p>
        </div>
      )}
    </div>
  );
};

export default RegistrationFlow;
```

---

## 8. Validation Rules

### Email Format
```regex
^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
```

### Phone Format
```regex
^\+?[1-9]\d{1,14}$
```

### Name Format (2-50 characters)
```regex
^[a-zA-Z\s]{2,50}$
```

---

## 9. Error Handling

All endpoints return consistent error responses:

```json
{
  "status": "error",
  "message": "Detailed error message",
  "errors": [
    {
      "field": "email",
      "message": "Email is mandatory"
    }
  ]
}
```

**React Error Handling Example:**
```javascript
const handleAPICall = async () => {
  try {
    const response = await axios.post('/api/registration', data);
    // Handle success
  } catch (error) {
    if (error.response) {
      // Server responded with error
      const { status, message } = error.response.data;
      console.error(`Error ${status}: ${message}`);
      
      // Display validation errors
      if (error.response.data.errors) {
        error.response.data.errors.forEach(err => {
          console.error(`${err.field}: ${err.message}`);
        });
      }
    } else if (error.request) {
      // Request made but no response
      console.error('Network error:', error.request);
    } else {
      // Something else happened
      console.error('Error:', error.message);
    }
  }
};
```

---

## 10. Environment Setup

### Backend Configuration
```properties
# application.properties

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/global_congress
spring.datasource.username=root
spring.datasource.password=yourpassword

# File Upload
file.upload-dir=./uploads/
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Email (Brevo)
brevo.api.key=your_brevo_api_key
brevo.sender.email=noreply@yourconference.com
brevo.sender.name=Conference Team

# Stripe
stripe.api.key=sk_test_your_stripe_secret_key

# CORS
cors.allowed-origins=http://localhost:3000,http://localhost:5173
```

### React Environment Variables
```env
# .env.local

REACT_APP_API_BASE_URL=http://localhost:8080/api
REACT_APP_STRIPE_PUBLIC_KEY=pk_test_your_stripe_publishable_key
```

### Axios Configuration
```javascript
// src/api/axios.js

import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor for authentication (if needed)
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Add response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Handle unauthorized
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
```

---

## 11. Testing Endpoints with cURL

### Abstract Submission
```bash
curl -X POST http://localhost:8080/api/abstracts/submit \
  -F "title=Dr." \
  -F "name=John Smith" \
  -F "email=john.smith@example.com" \
  -F "phno=+1234567890" \
  -F "organization=University" \
  -F "country=USA" \
  -F "category=Oral" \
  -F "session=Research" \
  -F "captchaCode=123456" \
  -F "user=Conference" \
  -F "presentationTitle=Title" \
  -F "file=@document.pdf"
```

### Registration
```bash
curl -X POST http://localhost:8080/api/registration \
  -H "Content-Type: application/json" \
  -d '{
    "registrationType": "Speaker",
    "name": "Dr. John Smith",
    "email": "john.smith@example.com",
    "phone": "+1234567890",
    "organization": "University",
    "country": "USA",
    "conferenceId": "conf123"
  }'
```

### Payment Intent
```bash
curl -X POST http://localhost:8080/api/payment/create-intent \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 779.00,
    "currency": "usd",
    "email": "john.smith@example.com",
    "description": "Speaker Registration",
    "registrationId": 456,
    "conferenceId": "conf123"
  }'
```

---

## 12. Next Steps

1. **Run the Spring Boot application:**
   ```bash
   cd entity-management
   mvnw spring-boot:run
   ```

2. **Verify API is running:**
   - Visit: http://localhost:8080/swagger-ui.html
   - Check health: http://localhost:8080/actuator/health

3. **Configure Stripe:**
   - Get API keys from https://dashboard.stripe.com/apikeys
   - Update `application.properties` with your secret key
   - Use publishable key in React app

4. **Configure Brevo (Email):**
   - Sign up at https://www.brevo.com
   - Get API key from Settings > SMTP & API
   - Update `application.properties`

5. **Test with React:**
   - Install Stripe library: `npm install @stripe/stripe-js @stripe/react-stripe-js`
   - Install Axios: `npm install axios`
   - Copy example components and customize

---

## Support

For questions or issues:
- Check API docs: http://localhost:8080/swagger-ui.html
- Review logs: `tail -f logs/application.log`
- Database queries: Check `RegistrationRepository`, `AbstractSubmissionRepository`

---

**Last Updated:** 2024-01-20  
**API Version:** 1.0  
**Spring Boot Version:** 3.5.6
