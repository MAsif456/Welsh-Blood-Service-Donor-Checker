## Welsh Blood Service Donor Checker

A system that assesses blood donor eligibility from a health and travel
questionnaire and tells people instantly whether they can donate, with
an admin and clinician portal for staff.

*Commits in this project appear under my former university username,
MASAsif.*

**Team:** 4 | **Tech:** Java, Spring Boot, JUnit, Checkstyle, Jenkins

## My contribution

This was a team project. My work covered the travel screening, staff
features, security and the build pipeline.

### Travel screening
- Added the travel history questions to the database, with test data
  for them.
- Built the travel form logic and testing, including support for
  entering multiple countries and date validation.
- Changed the form so that only donor-specific travel questions are
  asked.
- Built the staff view that shows updated country risks alongside the
  donor summary.
- Added further input validation so wrong input is handled safely.

### Staff and admin features
- Built the staff login to the clinician-admin portal, and fixed
  login and routing issues.
- Built the admin feature for adding new diseases and updating
  disease information, with tests.
- Built the clinician feature that recommends deferral periods for
  different diseases, so clinicians can reach a well-informed decision.
  Includes tests.
- Built the navigation bar and styled the donor form and portal pages.

### Security
- Added backend input validation, including sensible schema fields and
  restrictions in the database tier.
- Implemented countermeasures against CSRF attacks.
- Implemented authorisation checks so no user can access a page
  without the right permissions.

### Build pipeline and quality
- Set up a Jenkins pipeline running JUnit tests and Checkstyle, with
  the Checkstyle threshold set to the 50% compliance level required by
  the module.
- Added database indexing and test plans.
- Resolved merge conflicts and added comments to explain tests.
