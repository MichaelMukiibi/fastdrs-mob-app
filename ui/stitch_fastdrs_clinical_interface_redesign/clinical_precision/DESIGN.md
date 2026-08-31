---
name: Clinical Precision
colors:
  surface: '#f8f9fa'
  surface-dim: '#d9dadb'
  surface-bright: '#f8f9fa'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f3f4f5'
  surface-container: '#edeeef'
  surface-container-high: '#e7e8e9'
  surface-container-highest: '#e1e3e4'
  on-surface: '#191c1d'
  on-surface-variant: '#424752'
  inverse-surface: '#2e3132'
  inverse-on-surface: '#f0f1f2'
  outline: '#727783'
  outline-variant: '#c2c6d4'
  surface-tint: '#005db5'
  primary: '#00488d'
  on-primary: '#ffffff'
  primary-container: '#005fb8'
  on-primary-container: '#cadcff'
  inverse-primary: '#a8c8ff'
  secondary: '#525f73'
  on-secondary: '#ffffff'
  secondary-container: '#d6e3fb'
  on-secondary-container: '#586579'
  tertiary: '#7b3200'
  on-tertiary: '#ffffff'
  tertiary-container: '#a04401'
  on-tertiary-container: '#ffd1bc'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#d6e3ff'
  primary-fixed-dim: '#a8c8ff'
  on-primary-fixed: '#001b3d'
  on-primary-fixed-variant: '#00468b'
  secondary-fixed: '#d6e3fb'
  secondary-fixed-dim: '#bac7de'
  on-secondary-fixed: '#0f1c2d'
  on-secondary-fixed-variant: '#3b485a'
  tertiary-fixed: '#ffdbcb'
  tertiary-fixed-dim: '#ffb691'
  on-tertiary-fixed: '#341100'
  on-tertiary-fixed-variant: '#783100'
  background: '#f8f9fa'
  on-background: '#191c1d'
  surface-variant: '#e1e3e4'
typography:
  display-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
    letterSpacing: 0.02em
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
  headline-md-mobile:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 26px
rounded:
  sm: 0.125rem
  DEFAULT: 0.25rem
  md: 0.375rem
  lg: 0.5rem
  xl: 0.75rem
  full: 9999px
spacing:
  base: 4px
  xs: 4px
  sm: 8px
  md: 16px
  lg: 24px
  xl: 32px
  edge-margin: 16px
  gutter: 12px
---

## Brand & Style
The design system is engineered for high-stakes medical environments where speed and accuracy are paramount. The brand personality is rooted in clinical authority, trust, and absolute efficiency. 

The aesthetic follows a **Modern Corporate/Medical** style, emphasizing utilitarian clarity over decorative elements. It utilizes high-density layouts optimized for rapid data entry and image review. The visual language is intentional and sparse, using ample whitespace to reduce cognitive load during long shifts. By prioritizing a "functional-first" approach, the UI recedes into the background, allowing the fundus images and screening data to remain the primary focus of the clinician's attention.

## Colors
The palette is strictly clinical and functional. The primary **Medical Blue** is used for interactive elements and primary actions, signaling reliability. 

- **Backgrounds:** Use a tiered neutral system. The main background is a very light grey (`#F9FAFB`) to reduce eye strain compared to pure white, while containers and cards use pure white to pop against the surface.
- **Semantic Logic:** Color is used exclusively for status and urgency.
    - **Success Green:** Indicates "No DR" or completed uploads.
    - **Warning Amber:** Indicates "Mild/Moderate DR" or pending reviews.
    - **Critical Red:** Indicates "Severe/Proliferative DR" or system errors.
- **Grayscale:** A range of cool greys is used for secondary text and borders to maintain a structured, professional hierarchy.

## Typography
This design system utilizes **Inter** for its exceptional legibility at small sizes and its neutral, systematic character. The hierarchy is designed for "scannability."

- **Headlines:** Use Semi-Bold weights to anchor sections of patient reports.
- **Body:** Standardized at 16px for optimal readability on Android handhelds.
- **Labels:** Used for data headers (e.g., "Patient ID", "Date of Birth"). These use a slightly heavier weight and increased tracking to differentiate from user-inputted data.
- **Numeric Data:** Ensure the use of tabular lining figures for clinical readings to ensure columns of numbers align perfectly for visual comparison.

## Layout & Spacing
The layout follows a **Fluid Grid** model optimized for the Android viewport. 

- **Grid:** A 4-column grid for mobile and 8-column for tablet. 
- **Margins:** 16px side margins ensure content does not hit the edge of the device bezel.
- **Rhythm:** A 4px baseline grid governs all vertical rhythm. Components are spaced in multiples of 8px (8, 16, 24, 32) to create a predictable visual cadence.
- **Offline Indicators:** A persistent 4px status bar or icon-slot is reserved at the top of the layout to communicate local sync status and battery levels, critical for field operations.

## Elevation & Depth
In this clinical context, depth is used sparingly to maintain a "flat" and focused interface. 

- **Tonal Layers:** Surface levels are the primary way to show hierarchy. Background is `Neutral-50`, while the primary working cards are `White`.
- **Low-Contrast Outlines:** Instead of heavy shadows, use 1px borders (`#EAECF0`) to define cards and input fields. 
- **Active Elevation:** Only the primary action button and active modals use a subtle, diffused shadow (4px blur, 10% opacity) to signify interactability. Fundus images are placed in recessed containers with a slight inner-stroke to simulate an "inset" viewing lightbox.

## Shapes
The shape language is **Soft (0.25rem)**. 

Clinical tools require a balance between "serious" (sharp) and "modern" (rounded). The 4px radius on buttons and input fields provides a professional, geometric look while appearing finished. Fundus image containers may use a slightly larger radius (8px) to soften the transition between the dark image and the light UI.

## Components
- **Primary Buttons:** High-contrast, solid Medical Blue with white text. Minimum tap target of 48x48dp.
- **Patient Cards:** White background, 1px grey border. Information is stacked: Patient Name (Headline-SM), ID (Label-SM), and a right-aligned Status Indicator.
- **Status Indicators (Pills):** Subtle tinted backgrounds (e.g., light green for No DR) with high-contrast text. These are the most prominent visual elements in a list view.
- **Fundus Image Containers:** Aspect-ratio locked (1:1 or 4:3). These must have a "Full Screen" toggle for detailed inspection. Use a black background within the container to maximize image contrast.
- **Input Fields:** Labeled clearly above the field. Use a 1px border that turns Medical Blue on focus. Error states use a red border and a helper text below.
- **Sync Bar:** A specialized footer component for offline-first visibility, showing "Last Synced: X mins ago" or "Offline - 4 Records Pending."