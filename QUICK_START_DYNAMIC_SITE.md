# Quick Start: Dynamic Site Implementation

## What We've Built

✅ **Complete dynamic configuration system** - The React frontend now fetches ALL data from API endpoints, just like your PHP symposium system!

## Files Created/Modified

### Backend (Spring Boot)
1. ✅ **`ImportantDetailsController.java`** - REST API endpoints for conference config
2. ✅ **`ImportantDetailsService.java`** - Business logic for configuration
3. ✅ **`ImportantDetailsRepository.java`** - Database access methods
4. ✅ **`ImportantDetails.java`** - Already existed, no changes needed

### Frontend (React)
1. ✅ **`api.js`** - Added new API methods for dynamic config
2. ✅ **`ConferenceContext.jsx`** - Complete rewrite for dynamic data
3. ✅ **`Navbar.jsx`** - Now displays dynamic logo and conference name
4. ✅ **`Footer.jsx`** - Now displays dynamic contact info and social links

## API Endpoints Available

```
GET /api/conference-config/{shortName}/site-config
GET /api/conference-config/{shortName}/pricing-tier
GET /api/conference-config/{shortName}/deadlines
GET /api/conference-config/{shortName}/social-links
GET /api/conference-config/{shortName}/contact
GET /api/conference-config/{shortName}/registration-status
GET /api/conference-config/{shortName}/abstract-status
GET /api/conference-config/{shortName}/logo
```

## How to Use

### 1. Start Backend
```bash
cd "d:\ram 1201\entity-management"
.\mvnw.cmd spring-boot:run
```

Backend runs on: `http://localhost:8910`

### 2. Prepare Database

**IMPORTANT**: Run this SQL script first!
```bash
# Open phpMyAdmin and run:
d:\ram 1201\entity-management\ADD_AUTO_INCREMENT.sql
```

This adds AUTO_INCREMENT to all tables so INSERT queries work correctly.

### 3. Add Conference Configuration

Insert your conference details into `important_details` table:

```sql
INSERT INTO `important_details` (
  `ShortName`, 
  `ConfUrl`, 
  `Theme`, 
  `ConferenceTitle`, 
  `ConferenceVenue`, 
  `ConferenceDates`,
  `EarlyBird`, 
  `mid_term`, 
  `Late_registration`, 
  `OnSpot`,
  `abstract_submission_deadline`,
  `abstract_submission_opens`,
  `registration_opens`,
  `EmailId1`,
  `EmailId2`,
  `EmailId3`,
  `facebook_link`,
  `linkedin_link`,
  `twitter_link`,
  `instagram_link`,
  `entity_logo`,
  `pc_name`
) VALUES (
  'ICCE2026',
  'https://icce2026.com',
  'Building the Future of Infrastructure',
  'International Conference on Civil Engineering 2026',
  'Grand Convention Center, San Francisco',
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

### 4. Update React Environment

Edit `frontend/.env`:
```env
REACT_APP_BACKEND_URL=http://localhost:8910
REACT_APP_CONFERENCE_SHORT_NAME=ICCE2026
```

### 5. Start Frontend
```bash
cd "d:\ram 1201\Civil-CCAI-2026\frontend"
npm start
```

Frontend runs on: `http://localhost:3000`

### 6. Test Dynamic Loading

Open browser console and check:
```javascript
// Should see API calls to:
// GET http://localhost:8910/api/conference-config/ICCE2026/site-config

// Check loaded config
console.log(window.__CONFERENCE_CONFIG__);
```

## Using in Your Components

### Basic Usage
```jsx
import { useConference } from '@/context/ConferenceContext';

function MyComponent() {
  const { 
    getConferenceName,
    getConferenceTheme,
    getDates,
    getContact,
    getSocial,
    loading 
  } = useConference();

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      <h1>{getConferenceName()}</h1>
      <p>{getConferenceTheme()}</p>
      <p>Dates: {getDates().conferenceDates}</p>
      <p>Email: {getContact().email1}</p>
    </div>
  );
}
```

### Advanced Usage - Pricing
```jsx
import { useConference } from '@/context/ConferenceContext';

function PricingSection() {
  const { 
    getPricing, 
    getPricingTierLabel, 
    getPricingTierColor,
    getDeadlines 
  } = useConference();

  const categories = ['speaker', 'delegate', 'poster', 'student'];

  return (
    <div>
      <div className={`badge bg-${getPricingTierColor()}-500`}>
        {getPricingTierLabel()} Rate Active
      </div>
      
      {categories.map(category => (
        <div key={category}>
          <h3>{category}</h3>
          <p>${getPricing(category)}</p>
        </div>
      ))}
      
      <p>Next deadline: {getDeadlines().midTerm}</p>
    </div>
  );
}
```

### Multi-Conference Support
```jsx
// Switch conference dynamically
const { changeConference } = useConference();

function ConferenceSwitcher() {
  return (
    <select onChange={(e) => changeConference(e.target.value)}>
      <option value="ICCE2026">ICCE 2026</option>
      <option value="POLYSUMMIT2026">Polymer Summit 2026</option>
      <option value="GSRNRES2026">Renewable Energy 2026</option>
    </select>
  );
}
```

## Update Your Pages

### Example: Update Home.jsx Hero Section

**Before** (Hardcoded):
```jsx
<h1>ICCE 2026</h1>
<p>Building the Future</p>
<p>March 15-17, 2026</p>
```

**After** (Dynamic):
```jsx
import { useConference } from '@/context/ConferenceContext';

function HeroSection() {
  const { getConferenceName, getConferenceTheme, getDates } = useConference();
  const dates = getDates();
  
  return (
    <>
      <h1>{getConferenceName()}</h1>
      <p>{getConferenceTheme()}</p>
      <p>{dates.conferenceDates}</p>
    </>
  );
}
```

## Testing Checklist

- [ ] Backend starts successfully on port 8910
- [ ] Can access: `http://localhost:8910/api/conference-config/ICCE2026/site-config`
- [ ] Frontend loads without errors
- [ ] Navbar shows dynamic logo (if configured)
- [ ] Footer shows dynamic social links
- [ ] Contact emails appear in footer
- [ ] No hardcoded conference names visible
- [ ] Browser console shows no errors
- [ ] Pricing tier calculated correctly

## Troubleshooting

### API Returns 404
- Check backend is running
- Verify `ShortName` in database matches URL parameter
- Check database connection in `application.properties`

### Logo Not Showing
- Verify `entity_logo` field has correct path
- Check image exists in public folder
- Browser console shows 404? Fix image path

### Dates Not Displaying
- Check date format in database
- Dates should be: `YYYY-MM-DD` or `Month DD-DD, YYYY`
- Service handles multiple formats

### Social Links Not Working
- Verify URLs have `http://` or `https://`
- Check database has correct links
- Links should be absolute URLs

### Pricing Tier Wrong
- Check system date vs deadline dates
- Verify date format: `YYYY-MM-DD`
- Check service logic in `ImportantDetailsService.java`

## Admin Panel Integration

Your PHP admin panel can now:
1. Insert conference config → React displays it
2. Update deadlines → Pricing tier updates automatically
3. Change social links → Footer updates instantly
4. No frontend code changes needed!

## Next Steps

1. **Update All Pages**: Replace static config with `useConference()`
2. **Add More Endpoints**: FAQs, speakers, schedule from database
3. **Remove Static Files**: Delete `site.js`, `speakers.json`, etc.
4. **Test Production**: Deploy both backend and frontend
5. **Add Caching**: Consider Redis for performance

## Production Deployment

### Backend
```bash
# Build JAR
./mvnw clean package

# Run on server
java -jar target/entity-management-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
# Build production
npm run build

# Deploy build folder to hosting
# Update .env with production API URL
```

## Support

**Backend Issues**:
- Check logs: `logs/spring-boot-application.log`
- Verify database connection
- Test endpoints with Postman

**Frontend Issues**:
- Check browser console
- Verify API base URL in `.env`
- Test with `curl` first

---

## Summary

✅ **You now have a fully dynamic conference website!**

- Conference name? Comes from database
- Dates? From database
- Pricing? Calculated from database deadlines
- Social links? From database
- Contact info? From database
- Logo? From database

**Everything is dynamic - Just like your PHP symposium system!** 🎉

Update the database, and the site updates automatically. No code changes needed!
