# Project Overview

The plain-English what-and-why of this project. Read by every Claude session so context doesn't have to be re-derived.

> **STATUS: needs verification** — synthesized from `README.md` on 2026-07-15. Review and edit; remove this banner when confirmed.

## What this is

Screen Locker Widget is a 1×1 Android home screen widget that instantly locks the device with a single tap, while preserving fingerprint/face unlock. It requires no root and uses Android's Accessibility Service (Android 9+) to trigger the lock exactly like the power button, so biometrics work instantly on unlock.

## Who uses it

_(README does not specify an audience beyond "you" — the individual device owner installing the widget.)_

## Core flows

1. Enable the Accessibility Service from within the app.
2. Add the 1×1 widget to the home screen.
3. Tap the widget to lock the screen instantly (biometric unlock still works).

## Stack snapshot

| Layer | Tech |
|---|---|
| Frontend | Android (Java), home screen widget (AppWidgetProvider) |
| Backend | _(none — on-device only)_ |
| Database | _(none)_ |
| Auth | Android biometrics (fingerprint/face), via device OS — not implemented by this app |
| Deploy | Manual build via Android Studio (minSdk 28) |

## What this project is **not**

- Not a device admin / root-based locker.
- No ads, no data collection (per README).
- No backend or network component.

## Constraints

- No root required — must work via Android Accessibility Service only.
- minSdk 28 (Android 9+).
- Must preserve native biometric unlock behavior (equivalent to physical power-button lock).

