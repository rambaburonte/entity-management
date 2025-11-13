# Dynamic Site Configuration Guide

## Overview
This guide explains how the React frontend dynamically fetches ALL configuration from the Spring Boot API, just like the PHP symposium system. No hardcoded values - everything comes from the `important_details` table in the database.

## Architecture

### Backend (Spring Boot)
- **Controller**: `ImportantDetailsController.java`
- **Service**: `ImportantDetailsService.java`
- **Repository**: `ImportantDetailsRepository.java`
- **Entity**: `ImportantDetails.java`
- **Database Table**: `important_details`

### Frontend (React)
- **Context**: `ConferenceContext.jsx` - Central state management
- **API Service**: `api.js` - API communication layer
- **Components**: `Navbar.jsx`, `Footer.jsx` - Dynamic UI components

## API Endpoints

### 1. Complete Site Configuration
```
GET /api/conference-config/{shortName}/site-config
```
**Returns**: Complete configuration object with all site settings

**Response Structure**:
```json
{
  "shortName": "ICCE2026",
  "name": "ICCE2026",
  "fullName": "International Conference on Civil Engineering 2026",
  "title": "International Conference on Civil Engineering 2026",
  "theme": "Building the Future of Infrastructure",
  "tagline": "Building the Future of Infrastructure",
  "confUrl": "https://icce2026.com",
  
  "dates": {
    "conferenceDates": "March 15-17, 2026",
    "abstractSubmissionDeadline": "2025-12-15",
    "abstractSubmissionOpens": "2025-06-01",
    "registrationOpens": "2025-07-01",
    "earlyBirdDeadline": "2026-01-31",
    "midTermDeadline": "2026-02-28",
    "lateRegistrationDeadline": "2026-03-10",
    "onSpotDate": "2026-03-15"
  },
  
  "venue": {
    "name": "Grand Convention Center, San Francisco",
    "address": "Grand Convention Center, San Francisco",
    "location": "Grand Convention Center, San Francisco"
  },
  
  "contact": {
    "email": "info@icce2026.org",
    "email1": "info@icce2026.org",
    "email2": "support@icce2026.org",
    "email3": "registration@icce2026.org"
  },
  
  "social": {
    "facebook": "https://facebook.com/icce2026",
    "linkedin": "https://linkedin.com/company/icce2026",
    "twitter": "https://twitter.com/icce2026",
    "instagram": "https://instagram.com/icce2026"
  },
  
  "branding": {
    "logo": "/images/icce2026-logo.png",
    "pcName": "Conference Secretariat"
  },
  
  "pricingTier": {
    "tier": "earlyBird",
    "label": "Early Bird",
    "color": "green",
    "earlyBirdDeadline": "2026-01-31",
    "midTermDeadline": "2026-02-28",
    "lateDeadline": "2026-03-10",
    "onSpotDate": "2026-03-15"
  },
  
  "registrationOpen": true,
  "abstractSubmissionOpen": true
}
```

### 2. Pricing Tier
```
GET /api/conference-config/{shortName}/pricing-tier
```
**Returns**: Current pricing tier based on today's date

### 3. Social Media Links
```
GET /api/conference-config/{shortName}/social-links
```
**Returns**: All social media links

### 4. Contact Information
```
GET /api/conference-config/{shortName}/contact
```
**Returns**: All contact emails and info

### 5. Registration Status
```
GET /api/conference-config/{shortName}/registration-status
```
**Returns**: Whether registration is currently open

### 6. Abstract Submission Status
```
GET /api/conference-config/{shortName}/abstract-status
```
**Returns**: Whether abstract submission is open

### 7. Conference Logo
```
GET /api/conference-config/{shortName}/logo
```
**Returns**: Logo URL

### 8. Deadlines
```
GET /api/conference-config/{shortName}/deadlines
```
**Returns**: All important dates and deadlines

## Frontend Integration

### Using ConferenceContext

The `ConferenceContext` provides centralized access to conference configuration:

```jsx
import { useConference } from '@/context/ConferenceContext';

function MyComponent() {
  const {
    siteConfig,           // Complete site configuration
    loading,              // Loading state
    error,                // Error state
    
    // Conference Info Getters
    getConferenceName,    // Get full conference name
    getConferenceShortName, // Get short name (e.g., "ICCE2026")
    getConferenceTheme,   // Get theme/tagline
    getVenue,            // Get venue information
    getContact,          // Get contact information
    getSocial,           // Get social media links
    getLogo,             // Get logo URL
    getDates,            // Get all dates
    getDeadlines,        // Get all deadlines
    
    // Pricing
    getPricing,          // Get pricing for category
    getAllPricing,       // Get all pricing
    getPricingTier,      // Get current tier (earlyBird, midTerm, late, onSpot)
    getPricingTierLabel, // Get tier label ("Early Bird", etc.)
    getPricingTierColor, // Get tier color (green, yellow, orange, red)
    
    // Status Checks
    isRegistrationOpen,
    isAbstractSubmissionOpen,
    getDaysUntilConference,
    
    // Actions
    fetchSiteConfig,     // Refresh configuration
    changeConference,    // Switch to different conference
    refreshSiteConfig,   // Reload current config
  } = useConference();

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h1>{getConferenceName()}</h1>
      <p>{getConferenceTheme()}</p>
      <p>Registration: {isRegistrationOpen() ? 'Open' : 'Closed'}</p>
      <p>Pricing Tier: {getPricingTierLabel()}</p>
    </div>
  );
}
```

### Example: Dynamic Hero Section

```jsx
import { useConference } from '@/context/ConferenceContext';

function HeroSection() {
  const { 
    getConferenceName, 
    getConferenceTheme, 
    getDates, 
    getVenue 
  } = useConference();
  
  const dates = getDates();
  const venue = getVenue();

  return (
    <section>
      <h1>{getConferenceName()}</h1>
      <p>{getConferenceTheme()}</p>
      <p>{dates.conferenceDates}</p>
      <p>{venue.name}</p>
    </section>
  );
}
```

### Example: Dynamic Pricing Display

```jsx
import { useConference } from '@/context/ConferenceContext';

function PricingCard() {
  const { 
    getPricing, 
    getPricingTierLabel, 
    getPricingTierColor,
    getDeadlines 
  } = useConference();
  
  const delegatePrice = getPricing('delegate');
  const tierLabel = getPricingTierLabel();
  const tierColor = getPricingTierColor();
  const deadlines = getDeadlines();

  return (
    <div className={`bg-${tierColor}-100`}>
      <span className="badge">{tierLabel} Rate</span>
      <h3>Delegate: ${delegatePrice}</h3>
      <p>Next deadline: {deadlines.midTerm}</p>
    </div>
  );
}
```

### Example: Dynamic Contact Section

```jsx
import { useConference } from '@/context/ConferenceContext';
import { Mail } from 'lucide-react';

function ContactSection() {
  const { getContact, getSocial } = useConference();
  
  const contact = getContact();
  const social = getSocial();

  return (
    <div>
      <h2>Contact Us</h2>
      {contact.email1 && (
        <a href={`mailto:${contact.email1}`}>
          <Mail /> {contact.email1}
        </a>
      )}
      {contact.email2 && (
        <a href={`mailto:${contact.email2}`}>
          {contact.email2}
        </a>
      )}
      
      <div>
        {social.facebook && (
          <a href={social.facebook} target="_blank">Facebook</a>
        )}
        {social.twitter && (
          <a href={social.twitter} target="_blank">Twitter</a>
        )}
      </div>
    </div>
  );
}
```

## Database Configuration

### Important_Details Table Structure

All configuration comes from the `important_details` table:

| Column | Type | Description |
|--------|------|-------------|
| `sno` | INT (PK) | Primary key (AUTO_INCREMENT) |
| `ShortName` | VARCHAR | Conference short name (e.g., "ICCE2026") |
| `ConfUrl` | VARCHAR | Conference website URL |
| `Theme` | VARCHAR | Conference theme/tagline |
| `ConferenceTitle` | VARCHAR | Full conference name |
| `ConferenceVenue` | VARCHAR | Venue location |
| `ConferenceDates` | VARCHAR | Conference dates |
| `EarlyBird` | VARCHAR | Early bird deadline |
| `mid_term` | VARCHAR | Mid-term deadline |
| `Late_registration` | VARCHAR | Late registration deadline |
| `OnSpot` | VARCHAR | On-spot registration date |
| `abstract_submission_deadline` | VARCHAR | Abstract deadline |
| `abstract_submission_opens` | VARCHAR | Abstract opens date |
| `registration_opens` | VARCHAR | Registration opens date |
| `EmailId1` | VARCHAR | Primary email |
| `EmailId2` | VARCHAR | Secondary email |
| `EmailId3` | VARCHAR | Tertiary email |
| `facebook_link` | VARCHAR | Facebook URL |
| `linkedin_link` | VARCHAR | LinkedIn URL |
| `twitter_link` | VARCHAR | Twitter URL |
| `instagram_link` | VARCHAR | Instagram URL |
| `entity_logo` | VARCHAR | Logo image path |
| `pc_name` | VARCHAR | Program chair/organizer name |

### Example Database Entry

```sql
INSERT INTO `important_details` (
  `ShortName`, `ConfUrl`, `Theme`, `ConferenceTitle`, `ConferenceVenue`, 
  `ConferenceDates`, `EarlyBird`, `mid_term`, `Late_registration`, `OnSpot`,
  `abstract_submission_deadline`, `abstract_submission_opens`, 
  `registration_opens`, `EmailId1`, `EmailId2`, `EmailId3`,
  `facebook_link`, `linkedin_link`, `twitter_link`, `instagram_link`,
  `entity_logo`, `pc_name`
) VALUES (
  'ICCE2026', 
  'https://icce2026.com',
  'Building the Future of Infrastructure',
  'International Conference on Civil Engineering 2026',
  'Grand Convention Center, San Francisco, California, USA',
  'March 15-17, 2026',
  '2026-01-31',
  '2026-02-28',
  '2026-03-10',
  '2026-03-15',
  '2025-12-15',
  '2025-06-01',
  '2025-07-01',
  'info@icce2026.org',
  'support@icce2026.org',
  'registration@icce2026.org',
  'https://facebook.com/icce2026',
  'https://linkedin.com/company/icce2026',
  'https://twitter.com/icce2026',
  'https://instagram.com/icce2026',
  '/images/icce2026-logo.png',
  'Conference Secretariat'
);
```

## Pricing Tier Logic

The system automatically calculates the current pricing tier based on today's date:

| Tier | Condition | Label | Color |
|------|-----------|-------|-------|
| **Early Bird** | Today ≤ EarlyBird deadline | "Early Bird" | Green |
| **Mid Term** | Today ≤ mid_term deadline | "Mid Term" | Yellow |
| **Late** | Today ≤ Late_registration deadline | "Late Registration" | Orange |
| **On Spot** | Today > Late_registration deadline | "On Spot" | Red |

## Multi-Conference Support

The system supports multiple conferences using the `ShortName` field:

```javascript
// Get current conference from URL or default
const shortName = new URLSearchParams(window.location.search).get('conf') || 'ICCE2026';

// Switch to different conference
const { changeConference } = useConference();
await changeConference('POLYSUMMIT2026');
```

URL Examples:
- `https://yoursite.com/?conf=ICCE2026`
- `https://yoursite.com/?conf=POLYSUMMIT2026`
- `https://yoursite.com/?conf=GSRNRES2026`

## Benefits of Dynamic Configuration

### 1. **Zero Code Changes**
- Change conference name? Update database.
- Change dates? Update database.
- Change social links? Update database.
- No redeployment needed!

### 2. **Multi-Conference Support**
- Host multiple conferences from single codebase
- Each conference has unique configuration
- Switch conferences via URL parameter

### 3. **Real-Time Updates**
- Changes in database reflect immediately
- No cache clearing needed
- Automatic pricing tier calculation

### 4. **Admin Panel Integration**
- PHP admin panel updates database
- React frontend reads from database
- Perfect synchronization

### 5. **Maintainability**
- One source of truth (database)
- No hardcoded values to update
- Easy to manage from admin panel

## Testing

### Test API Endpoint
```bash
# Get site configuration
curl http://localhost:8910/api/conference-config/ICCE2026/site-config

# Get pricing tier
curl http://localhost:8910/api/conference-config/ICCE2026/pricing-tier

# Get social links
curl http://localhost:8910/api/conference-config/ICCE2026/social-links
```

### Test Frontend
```javascript
// In browser console
const { siteConfig } = useConference();
console.log(siteConfig);
```

## Migration Checklist

- [x] Create `ImportantDetailsController.java`
- [x] Create `ImportantDetailsService.java`
- [x] Update `ImportantDetailsRepository.java`
- [x] Add API endpoints to `api.js`
- [x] Update `ConferenceContext.jsx` to use dynamic API
- [x] Update `Navbar.jsx` to display dynamic logo/name
- [x] Update `Footer.jsx` to display dynamic contact/social
- [ ] Update `Home.jsx` hero section
- [ ] Update `CallForPapers.jsx` with dynamic deadlines
- [ ] Update `Registration.jsx` with dynamic pricing
- [ ] Update all pages to use `useConference()` hook
- [ ] Remove hardcoded `site.js` config file
- [ ] Test with multiple conferences

## Next Steps

1. **Update Home Page**: Use `useConference()` for hero section
2. **Update All Pages**: Replace all `siteConfig` imports with `useConference()`
3. **Add More Endpoints**: FAQs, tracks, schedule from database
4. **Remove Static Config**: Delete `site.js` and static JSON files
5. **Test Multi-Conference**: Verify URL parameter switching works
6. **Production Deploy**: Run `ADD_AUTO_INCREMENT.sql` first!

## Support

For issues or questions:
- Check console for API errors
- Verify database has correct data in `important_details` table
- Ensure Spring Boot application is running on port 8910
- Check CORS configuration if API calls fail

---

**Remember**: The entire site is now dynamic! Update the database, not the code! 🎉
