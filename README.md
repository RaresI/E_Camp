# E_Camp: Educational Camp Management Platform

E_Camp is a full-stack web application designed to simplify the management of educational camps. It provides dedicated dashboards for parents, children, instructors, admins, and the camp owner.

---

# Features

# Authentication & Roles
- Secure login system with Spring Security.
- User roles:
  - Parent: Registers and manages children, selects camps.
  - Child: Uploads feedback and photos.
  - Instructor: Views assigned activities and daily schedule.
  - Admin: Manages camps, groups, activities, and registrations.
  - Owner: Has full control, including dashboards and analytics.

# Parent Functionality
- Register as a parent (email + phone login).
- Register multiple children.
- Select and register for camps.
- View child progress, scores, and milestones.

# Child Functionality
- Login with credentials created by parent.
- Upload photos and submit camp feedback.

# Instructor Functionality
- View assigned activities.
- Manage daily schedules.

# Admin & Owner Functionality
- Create and manage:
  - Camps
  - Activities
  - Trips
  - Groups
- Assign instructors to activities.
- Oversee registration and score assignment.
- Optional: Leaderboards, dashboards, and group regeneration logic.

---

