﻿# 

[![CI/CD](https://github.com/ZhikharevAl/demoQAPlaywright/actions/workflows/ktlint.yml/badge.svg)](https://github.com/ZhikharevAl/demoQAPlaywright/actions/workflows/ktlint.yml)

# demoQAPlaywright

Этот проект представляет собой набор автоматизированных тестов для веб-приложения с использованием Selenium и Pytest. Проект включает тестирование различных функциональностей, включая загрузку изображений в форме регистрации.

## Содержание

- [Описание](#описание)
- [Требования](#требования)
- [Установка](#установка)
- [Запуск тестов](#запуск-тестов)
- [Генерация отчетов Allure](#генерация-отчетов-allure)
- [Лицензия](#лицензия)

## Описание

Проект разработан для автоматизированного тестирования веб-приложения. Включает тесты на загрузку изображений, проверку форм и другие функциональные тесты.

## Требования

- Kotlin
- Playwright
- JUnit 5
- Allure


## Установка

1. Клонируйте репозиторий:

    ```bash
    git clone https://github.com/ZhikharevAl/SimbirSoft-SDET-TEST.git
    cd demoQAPlaywright
    ```

2. Установите зависимости:

    ```bash
    ./gradlew build
    ```


## Запуск тестов

```bash
./gradlew clean test
```



https://github.com/user-attachments/assets/a8e36a15-a1eb-4cb4-96a4-9d8bfdc08956



## Генерация отчетов Allure

```bash
./gradlew allureReport
./gradlew allureServe
```

![Screenshot 2024-08-15 215557](https://github.com/user-attachments/assets/8523a568-c0e0-4892-b2c3-c7e1ddfb9fb7)
![Screenshot 2024-08-15 224155](https://github.com/user-attachments/assets/6aa50580-349f-4d77-b254-2b3edbffbe8d)


## Лицензия

Этот проект лицензирован под лицензией MIT. Подробности смотрите в файле [LICENSE](LICENSE.md)
