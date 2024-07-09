[![Build Status](https://github.com/danila12315454/HW-Card-Game/actions/workflows/CI.yml/badge.svg)](https://github.com/danila12315454/HW-Card-Game/actions/workflows/CI.yml/badge.svg)
[![Qodana Status](https://img.shields.io/badge/Qodana-Passed-green?logo=quizlet)](https://github.com/danila12315454/HW-Card-Game/actions?query=workflow%3AQodana)
![GitHub last commit](https://img.shields.io/github/last-commit/danila12315454/HW-Card-Game?logo=git)
![GitHub repo size](https://img.shields.io/github/repo-size/danila12315454/HW-Card-Game?logo=files)
![Mit](https://img.shields.io/github/license/danila12315454/HW-Card-Game?logo=gitbook)
![GitHub top language](https://img.shields.io/github/languages/top/danila12315454/HW-Card-Game?logo=openmediavault)
![GitHub language count](https://img.shields.io/github/languages/count/danila12315454/HW-Card-Game?logo=codeium)
![Contributors](https://img.shields.io/github/contributors/danila12315454/HW-Card-Game?logo=cyberdefenders)
![GitHub issues](https://img.shields.io/github/issues/danila12315454/HW-Card-Game?logo=github)
![GitHub pull requests](https://img.shields.io/github/issues-pr/danila12315454/HW-Card-Game?logo=github)
![GitHub pull requests closed](https://img.shields.io/github/issues-pr-closed/danila12315454/HW-Card-Game?logo=github)
---

# Table of contents

---

## 1. [How to run](#How-to-run)

## 2. [Structure Definitions](#Structure-Definitions)

## 3. [Task Definitions](#Task-Definitions)

## 4. [Solution condition](#Solution-condition)

---

# How to run

- ### First you need to clone the repository.
- ### Then you need to have Java installed on your computer.
- ### After that to run simply run main function in App/VisualApp.java file. You can also run App/App.java file to see how to use deck and hand classes.

---

# Structure Definitions

## Project consists of directories and files:
- ### Images
  #### Directory in which all images are stored.
- ### src
  #### Directory in which all source code is stored.
- ### .gitignore
  #### File for ignoring files in git.
- ### README.md
  #### File for storing information about the project.
- ### CODE_OF_CONDUCT.md
  #### File for storing code of conduct for the project(It is just template from GitHub).
- ### Makefile
  #### File for running commands in terminal.(It is made to make it simpler to run the project)
- ### LICENSE
  #### File for storing license information about the project.(It is just template from GitHub)
- ### .github
  #### Directory for storing GitHub actions. And issue templates.

---

## Packages in src folder:

- ### [App](#App)
- ### [Cards](#Cards)
- ### [CustomComponents](#CustomComponents)
- ### [CustomEnums](#CustomEnums)
- ### [Predictions](#Predictions)
- ### [ProgressEngine](#ProgressEngine)
- ### [VisualEngine](#VisualEngine)

---

## App

### Contains main class for running the application.

- ### VisualApp.java
  Main class for running the application.
- ### App.java
  First prototype of the app used for debugging. There you can understand how to use deck and hand classes.
- ### Settings.java
  Class for storing settings of the app.

---

## Cards

### Contains classes for cards. Such as Deck, Cards and Hand.

- ### Card.java
  Class for storing data about card including value, suit and image. Also contains method for serialization.
- ### Deck.java
  Class for storing data about deck of cards. It is used for storing cards, shuffling them and drawing them to hands.
  Also, it contains logic for creating cards and adding images to them.
- ### Hand.java
  Class for storing player's hand. It is used for storing cards, drawing them and checking if hand is empty.

---

## CustomComponents

### Contains custom components for the app.

- ### CardCanvas.java
  Class for drawing cards on screen.

---

## CustomEnums

### Contains custom enums for the app. Such as Suit.

- ### Suit.java
  Enum for storing suits of cards.

---

## Predictions

### Contains classes for prediction mechanism. Such as Prediction and Predictor.

- ### Prediction.java
  Class for storing data about prediction including player and amount of steps till win.
- ### Predictor.java
  Class for predicting player's win. It is used for predicting player's win based on his hand, and hand of his opponent.

---

## ProgressEngine

### Contains classes for saving and loading game progress.

- ### ProgressEngine.java
  Class for saving and loading game progress. It is used for saving and loading game progress.

---

## VisualEngine

### Contains classes for visual representation of the app. Mainly it's responsible for getting cards images from one image.

- ### CardLoader.java
  Class for getting cards images from one image.

- ### CardLoaderVisual.java
  Class that was used for debugging. It is used for getting cards images from one image and drawing them on screen.

---

# Task Definitions

- [X] Create Hand class (Danila)
- [X] Create Deck class (Danila)
- [X] Create Images system for drawing cards (Danila)
- [X] Create system for drawing cards on screen (Danila)
- [X] Create system for game process (Danila)
- [X] Create Predictions system (Danila)
- [X] Create Spoiler system (Danila)
- [X] Create menu buttons (Danila)
- [X] Create Restart button (Danila)
- [X] Create Save system (Danila)
- [X] Create autoplay button (Danila)
- [X] Create Test class for fast testing (Danila)
- [X] Create CI workflow for automate testing on push (Danila)
- [X] Create CI workflow for automate exe building on pull request into release branches (Danila)
- [ ] Create Avatars for users (Vasiliy)
- [ ] Create Main menu (Amidah)
- [ ] Create about bar
- [ ] Create settings menu
- [ ] Create sound effects
- [ ] Create animations
- [ ] Restyle app gui

---

# Solution condition
- [ ] Based on OOP no static methods allowed except lambdas.
- [ ] All variables should be private.
- [ ] Use inheritance and abstract.
 [Solution Link](./src/App/VisualApp.java#L50)
- [ ] Use Array or List to save data.
- [ ] Uses file saving to save the game progress.
- [ ] Uses the random to shuffle and distribute the deck.
- [ ] Use an Array or list to save the user's information.
- [ ] Create Graphical user Interface.
- [ ] The GUI must have a menu bar which had at least the following:
  - (New) for the new Game
  - (Save) for saving the Game
  - (Open) to open the saved game.
- [ ] Code must have comments.
- [ ] Code must not be copied, or AI generated.
- [ ] UTF 8 character coding.
- [ ] Project must show your names, in the about Menu Bar.
- [ ] All used images must be included in the Images folder, in your Project.
- [ ] You can add sound effects if you would like to game.
- [ ] You can download card images from the Internet or create your design.
