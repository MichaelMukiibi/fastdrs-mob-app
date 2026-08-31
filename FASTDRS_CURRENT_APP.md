# fastDRS — Current Application Specification

## 1. Product Overview
fastDRS is an Android application designed to perform diabetic retinopathy (DR) screening using on-device machine learning. It allows clinicians to acquire fundus images from a device gallery and immediately analyze them using an embedded LiteRT model. The application operates entirely offline, ensuring data privacy and utility in low-connectivity settings.

## 2. Target User
Medical practitioners or screening personnel conducting diabetic retinopathy examinations.

## 3. Current Capabilities
- Create and manage patient profiles.
- Capture/Select fundus images from device storage.
- Perform on-device, offline DR severity classification (5-class).
- View analysis results with confidence metrics.
- Persist screening records and history locally.

## 4. Application Architecture
The app follows a clean, layered architecture:
- **Presentation**: Jetpack Compose UI with MVVM (ViewModel).
- **Domain**: Kotlin data models (`Patient`, `Screening`, `DRClass`, etc.).
- **Data**: Room persistence layer for local storage.
- **Core**: Contains `ImageSource` and `InferenceEngine` abstractions, with `LiteRTInferenceEngine` for model execution and `ImagePreprocessor` for data transformation.

## 5. User Journey
1. **Home**: Start new screening.
2. **Setup**: Select patient (existing or new) and eye (Left/Right).
3. **Acquisition**: Pick image from device gallery.
4. **Review**: Preview image; retake or analyze.
5. **Analysis**: Loading state during asynchronous inference.
6. **Result**: Display of predicted class, confidence, and probabilities.
7. **History**: Retrieve past screenings.

## 6. Screen Inventory
- `HomeScreen`
- `PatientSelectionScreen`
- `NewPatientScreen`
- `ScreeningSetupScreen`
- `ImageAcquisitionScreen`
- `ImageReviewScreen`
- `AnalysisScreen`
- `ResultScreen`

## 7. Screen-by-Screen Specification
- **HomeScreen**: Simple screen with a "New Screening" button.
- **Patient Management (Selection/Creation)**: Basic forms to list existing patients or input age/sex for new patients.
- **Setup**: Button-based selection for "Left Eye" or "Right Eye".
- **Acquisition**: Uses system file picker to select images.
- **Review**: Displays selected image (using Coil) with Retake/Analyze buttons.
- **Analysis**: Loading indicator with status text.
- **Result**: Displays classification class, confidence, and full probability list.

## 8. Patient Management
- **Implemented**: Patients can be created (ID, age, sex) and selected from a list.
- **Persistence**: Saved via `PatientDao` (Room).
- **Relationship**: Each `Screening` is linked to a `patientId`.

## 9. Screening Workflow
A stateful workflow managed by `ScreeningViewModel`, transitioning through `ScreeningStatus` (CREATED, IMAGE_SELECTED, ANALYZING, COMPLETED, FAILED).

## 10. Image Acquisition
- **Method**: System gallery picker.
- **Abstraction**: `ImageSource` interface exists to facilitate future camera hardware integration.

## 11. Image Quality
- **Implementation**: Basic (not currently automated). The user is responsible for reviewing image quality before analyzing.

## 12. LiteRT / ML Inference
- **Model**: `fastdrs_model.tflite` (bundled in assets).
- **Logic**: `LiteRTInferenceEngine` performs preprocessing (resize to 224x224, normalize RGB) and runs the interpreter.
- **Output**: 5-class classification mapped to `DRClass` (No DR, Mild, Moderate, Severe, Proliferative).
- **Normalization**: Logits are converted to probabilities using Softmax.

## 13. Results
Displays result summary. Note: Result screen currently uses a simple `Text` display for the prediction object during V2 development.

## 14. Screening History
A list view (using `LazyColumn`) querying Room for `Screening` records sorted by timestamp.

## 15. Persistence
- **Technology**: Room database.
- **Scope**: Patients and Screenings (including prediction metadata). Images stored as URI references.

## 16. Offline Operation
- **Status**: FULLY OFFLINE. Model, database, and inference all run locally.

## 17. Current UI / Visual Design
- **Status**: Utilitarian / Minimalist.
- **Components**: Basic Material 3 buttons, text, and list items.
- **Consistency**: High technical consistency, low visual refinement.

## 18. Current UX Limitations
- Data input (Patient) is manual and rudimentary.
- Lack of clear visual guidance during image capture/quality check.
- Result display is basic text-based.

## 19. Technical Constraints
- Must remain offline.
- Must preserve the `InferenceEngine` / `ImageSource` abstractions for future modularity.
- Jetpack Compose / Material 3.

## 20. Future Fundus Camera Context
- `ImageSource` interface provides the hook for future camera integration (USB/Wi-Fi).

## 21. Future Product/UI Requirements
- Clinical-grade result presentation.
- Improved patient record longitudinal visualization.
- Automated image quality feedback.

## 22. Important Things the UI Redesign Must Preserve
- The functional navigation graph.
- The `ViewModel` state management.
- The `InferenceEngine` abstraction layer.
- Offline-only operation logic.
