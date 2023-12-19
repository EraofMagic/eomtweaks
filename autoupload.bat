@echo off
set /p version=Please enter the version (needed for github, also check gradle.properties and mods.toml): 

:: add mcversion here
set mcversions="1.18.2"

:: loops
for %%m in (%mcversions%) DO (
    git checkout "%%m"
    gh release create "%version%-%%m" --generate-notes

    .\gradlew publish
        call .\gradlew %%l:build
        .\gradlew curseforge
        move "build\libs\eomtw-%version%-%%l.jar" "\build\eomtw-%%m-%%l-%version%.jar"
        rmdir /s /q "%%l\build\libs"
        gh release upload "%version%-%%m" "build\eomtw-%%m-%version%.jar"
)
