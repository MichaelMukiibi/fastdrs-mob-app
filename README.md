# fastDRS Android

Android mobile application for **on-device diabetic retinopathy screening** using models trained and exported with the [`fastdrs`](https://pypi.org/project/fastdrs/) Python package.

The application is built with **Kotlin and Jetpack Compose** and is designed to run diabetic retinopathy inference locally on Android devices using **LiteRT**.

> **Project status:** Early development

---

## Overview

This repository contains the Android deployment component of the fastDRS diabetic retinopathy screening project.

The machine learning training and experimentation pipeline lives in the main repository:

**[diabetic-retinopathy-screening](https://github.com/MichaelMukiibi/diabetic-retinopathy-screening)**

The Android application is maintained as a **separate Git repository** and is linked to the main project as a Git submodule.

```text
diabetic-retinopathy-screening/
│
├── fastdrs/                    # Python ML package
│   ├── models/
│   ├── preprocessing/
│   ├── training/
│   ├── evaluation/
│   └── export/
│
└── deployment/
    └── android/                # This repository
```

The separation allows the ML pipeline and Android application to evolve independently.

---

## Features

The application is intended to provide:

* 📷 Fundus image acquisition
* 🖼️ Fundus image selection from device storage
* 🤖 On-device diabetic retinopathy inference
* ⚡ LiteRT-powered model execution
* 📱 Android-native user interface with Jetpack Compose
* 🔒 Local inference without requiring an inference server
* 📊 Five-class diabetic retinopathy classification

### Classification classes

The model predicts five diabetic retinopathy severity levels:

| Class | Severity      |
| ----: | ------------- |
|     0 | No DR         |
|     1 | Mild          |
|     2 | Moderate      |
|     3 | Severe        |
|     4 | Proliferative |

---

## Architecture

The overall system consists of two independently maintained components.

```text
                 ML Repository
        ┌─────────────────────────┐
        │        fastdrs           │
        │                           │
        │ Model training            │
        │ Evaluation                │
        │ Preprocessing             │
        │ LiteRT export             │
        └────────────┬──────────────┘
                     │
                     │ .tflite
                     ▼
        ┌─────────────────────────┐
        │     Android App         │
        │                           │
        │ Jetpack Compose          │
        │ Image preprocessing      │
        │ LiteRT inference         │
        │ Result presentation      │
        └─────────────────────────┘
```

The Python package is responsible for **training and exporting models**.

The Android application is responsible for **model execution and user interaction**.

---

## Technology Stack

### Android

* Kotlin
* Jetpack Compose
* Android SDK
* Gradle
* LiteRT
* CameraX *(planned/if enabled)*
* AndroidX

### Machine Learning

Models are trained using:

* Python
* PyTorch
* Torchvision

The model is exported for mobile deployment using the `fastdrs` export functionality and executed on Android using LiteRT.

---

## Model Deployment

The trained model is exported from the Python package as a `.tflite` artifact.

For example:

```python
from fastdrs.export import export_litert

export_litert(
    checkpoint="models/mobilenet_v2_best.pth",
    architecture="mobilenet_v2",
    output="model.tflite",
    img_size=224,
)
```

The resulting model can then be included in the Android application's assets.

```text
app/
└── src/
    └── main/
        └── assets/
            └── model.tflite
```

The Android application loads the model locally and performs inference on the device.

---

## Model Input

The exported model currently expects an image corresponding to the training pipeline's input configuration.

The standard configuration uses:

```text
Image size: 224 × 224
Channels:   3
Format:     RGB
```

The Android preprocessing pipeline must remain consistent with the preprocessing used during model training.

The current training pipeline uses ImageNet normalization:

```text
Mean:
[0.485, 0.456, 0.406]

Standard deviation:
[0.229, 0.224, 0.225]
```

Any changes to the model's training preprocessing should therefore be reflected in the Android preprocessing implementation.

---

## Model Output

The model produces five classification outputs corresponding to:

```text
0 → No DR
1 → Mild
2 → Moderate
3 → Severe
4 → Proliferative
```

The Android application is responsible for converting the model output into a user-facing screening result.

> **Important:** This application is intended as an AI-assisted screening/triage tool and should not be interpreted as a standalone clinical diagnosis.

---

## Repository Structure

The Android project follows a standard Gradle/Android application structure.

A typical structure is:

```text
fastdrs-android/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │
│           ├── res/
│           │   └── ...
│           │
│           ├── assets/
│           │   └── model.tflite
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
└── README.md
```

The exact structure may evolve as the application develops.

---

## Getting Started

### Requirements

You will need:

* Android Studio
* JDK compatible with the project's Android Gradle Plugin
* Android SDK
* An Android device or emulator
* A trained/exported LiteRT model

### Clone

```bash
git clone https://github.com/MichaelMukiibi/fastdrs-android.git
cd fastdrs-android
```

Open the project in Android Studio.

Gradle will resolve the required Android dependencies.

---

## Adding a Model

Place the exported `.tflite` model inside:

```text
app/src/main/assets/
```

For example:

```text
app/src/main/assets/
└── model.tflite
```

The application should load the model from the Android assets directory rather than downloading it at runtime.

---

## Development Workflow

The ML and Android repositories are intentionally separate.

### 1. Train

Train a model using `fastdrs`:

```bash
python train.py
```

or through the package's training API.

### 2. Evaluate

Evaluate the trained model using the existing evaluation pipeline.

Important deployment metrics include:

* Accuracy
* Macro F1
* Macro sensitivity
* Specificity
* Model size
* Inference latency

### 3. Export

Export the selected model to LiteRT:

```python
from fastdrs.export import export_litert

export_litert(
    checkpoint="models/mobilenet_v2_best.pth",
    architecture="mobilenet_v2",
    output="model.tflite",
)
```

### 4. Deploy

Copy the resulting artifact into:

```text
app/src/main/assets/
```

### 5. Build Android application

Build and run the application using Android Studio or Gradle.

---

## Relationship with the ML Repository

The Android project is maintained independently from the training pipeline.

The main ML repository:

**[https://github.com/MichaelMukiibi/diabetic-retinopathy-screening](https://github.com/MichaelMukiibi/diabetic-retinopathy-screening)**

contains:

* Dataset handling
* Preprocessing
* Model architectures
* Training
* Evaluation
* Benchmarking
* LiteRT export

This repository contains:

* Android UI
* Image acquisition
* Android preprocessing
* LiteRT inference
* Screening result presentation

This separation keeps the Android application independent from the Python training environment.

---

## Git Submodule

The Android repository is linked to the main ML repository as a Git submodule.

From the main repository:

```bash
git submodule add https://github.com/MichaelMukiibi/fastdrs-android.git deployment/android
```

Clone the complete project with:

```bash
git clone --recurse-submodules https://github.com/MichaelMukiibi/diabetic-retinopathy-screening.git
```

If the repository has already been cloned:

```bash
git submodule update --init --recursive
```

---

## Model Versioning

Model artifacts should be treated as deployment artifacts rather than source code.

The Android application should clearly associate a deployed model with:

* Model architecture
* Model version
* Training configuration
* Input resolution
* Preprocessing configuration
* Dataset
* Evaluation metrics

This makes it possible to determine exactly which model produced a screening result.

---

## Clinical Disclaimer

This application is a **research and engineering project** for AI-assisted diabetic retinopathy screening.

It is **not a medical device** and should not be used as a replacement for examination or diagnosis by a qualified healthcare professional.

Model predictions may be incorrect, particularly for images that differ from the training distribution or do not meet the expected image quality requirements.

---

## Related Project

### fastdrs

Python package for diabetic retinopathy model development and deployment:

**GitHub:**
[https://github.com/MichaelMukiibi/diabetic-retinopathy-screening](https://github.com/MichaelMukiibi/diabetic-retinopathy-screening)

**PyPI:**
[https://pypi.org/project/fastdrs/](https://pypi.org/project/fastdrs/)

---

## License

See the repository license for licensing information.

---

## Author

**Michael Mukiibi**

Diabetic Retinopathy Screening — Android Deployment
Built with Kotlin, Jetpack Compose, PyTorch, and LiteRT.
