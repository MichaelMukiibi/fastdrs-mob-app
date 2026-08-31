# Project Brief: fastDRS

## 1. Executive Summary
**fastDRS** is a clinical-grade, offline-first mobile application designed for autonomous diabetic retinopathy (DR) screening. By leveraging on-device AI (LiteRT), it enables healthcare workers to perform screenings in resource-constrained environments without requiring an internet connection.

## 2. Product Vision & Goals
*   **Mission**: To prevent avoidable blindness through accessible, high-quality eye screening.
*   **Key Value Proposition**: Clinical confidence through edge AI.
*   **Core Objective**: Provide a seamless, error-resistant workflow for acquiring fundus images and delivering immediate severity classifications.

## 3. Design Philosophy: "Clinical Precision"
The interface is guided by four core principles:
1.  **Trustworthy & Professional**: A restrained, high-contrast palette (Medical Blue #005FB8) that feels like a medical instrument, not a consumer app.
2.  **Workflow-Centric**: Optimized for high-volume screening environments where efficiency is paramount.
3.  **Calm & Minimal**: Reduced cognitive load through intentional whitespace and scannable information hierarchy.
4.  **Accessible**: Large touch targets, high-legibility typography (Inter), and color-independent status communication.

## 4. User Personas
*   **Primary User**: Trained screening personnel, nurses, or ophthalmic assistants in rural or community clinics.
*   **Context**: High-throughput screenings, often on entry-to-mid-range Android hardware, with intermittent or zero connectivity.

## 5. Core Workflow
The application follows a linear, non-destructive clinical path:
1.  **Home**: Immediate access to "Start New Screening."
2.  **Patient Selection**: Finding existing records or creating a new patient.
3.  **Eye Selection**: Explicitly identifying the eye being screened (OD/OS).
4.  **Image Acquisition**: Capturing the fundus photograph with framing guidance.
5.  **Review Image**: Clinical quality check before committing to analysis.
6.  **AI Analysis**: Local inference state (on-device computational transition).
7.  **Result Presentation**: Severity classification and confidence levels.
8.  **History**: Longitudinal record of patient screenings.

## 6. Functional Requirements
### 6.1 Clinical Analysis
*   **Model**: LiteRT (formerly TFLite) performing local inference.
*   **Classification**: 5-class severity scale (No DR, Mild, Moderate, Severe, Proliferative).
*   **Confidence Metrics**: Percentage-based confidence for each classification.

### 6.2 Data & Persistence
*   **Local Storage**: Android Room database for patient and screening records.
*   **Offline Mode**: 100% functionality without network dependency.
*   **Privacy**: Patient data remains on the device, adhering to local clinical data handling standards.

## 7. Technical Constraints
*   **Platform**: Android (Jetpack Compose + Material 3).
*   **Architecture**: ViewModel-based state management with Repository pattern for Room.
*   **Hardware Integration**: Designed to support future low-cost fundus camera hardware via the `ImageSource` abstraction.

## 8. Success Metrics
*   **Screening Time**: Average time from patient selection to result presentation.
*   **Error Rate**: Frequency of image retakes vs. successful AI inferences.
*   **User Confidence**: Subjective assessment of result clarity by screening workers.
