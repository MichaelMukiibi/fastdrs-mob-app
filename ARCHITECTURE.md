# Fast DRS Architecture Overview

The fastDRS Android application is built with **Kotlin** and **Jetpack Compose** using a clean, layered architecture designed for maintainability and modularity.

## Architecture Principles

1. **Layered Design**: Separation of concerns between Presentation, Domain, and Data layers.
2. **Decoupling**: ML and hardware interfaces are defined as abstractions, making the app extensible for future custom fundus camera hardware.
3. **Offline-First**: The ML model is bundled locally, enabling on-device inference without network dependency.

## Layer Structure

```text
com.campmap.fastdrs/
├── core/             # Shared logic and abstractions
│   ├── image/        # Fundus image model and acquisition abstractions
│   ├── ml/           # InferenceEngine, Preprocessor, and model handling
│   └── ui/           # Shared UI components and theme
├── domain/           # Business logic and data models
│   ├── model/        # Screening entity and domain objects
│   └── usecase/      # Business operations
├── feature/          # UI components grouped by feature
│   ├── home/         # Home screen
│   └── screening/    # Multi-step screening workflow screens
└── navigation/       # Compose navigation graph
```

## Key Components

### 1. Image Acquisition
The `ImageSource` interface provides an abstraction for image input.
- **Current**: Supports gallery image selection.
- **Future**: Designed to be swapped with `PhoneCameraSource` or `ExternalFundusCameraSource` without impacting the ML or UI layers.

### 2. ML Inference
The `InferenceEngine` interface ensures that the presentation layer is agnostic of the underlying ML implementation.
- **LiteRTInferenceEngine**: Bundled implementation for offline inference using `.tflite` models.
- **Preprocessing**: Implemented to match the specific training requirements (224x224 RGB, ImageNet normalization).

### 3. Screening Flow
The `ScreeningViewModel` manages the state of the screening lifecycle:
- Manages eye selection and image state.
- Orchestrates asynchronous inference via `runAnalysis()`.
- Exposes observable state to the Compose UI.

## Development Setup

1. **Model Assets**: The `.tflite` model and `model_metadata.json` are stored in `app/src/main/assets/`.
2. **Build Configuration**: `aaptOptions` are configured to prevent compression of `.tflite` files, ensuring they are readable at runtime by the `AssetManager`.
3. **Dependency Injection**: Currently handled via `ViewModelProvider.Factory` in `AppNavHost` to facilitate dependency provision.

## Future Roadmap
- Integration of hardware-based acquisition (USB/Wi-Fi cameras).
- Persistent storage of screening records via Room.
- Model update/downloading logic for over-the-air model improvements.
- Enhanced image quality verification ML module.
