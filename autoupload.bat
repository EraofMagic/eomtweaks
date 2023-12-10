@echo off
set /p version=Please enter the version (needed for github, also check gradle.properties and mods.toml): 

:: add mcversion here
set mcversions="1.18.2"
:: add loader here
set modloader="forge"

:: loops
for %%m in (%mcversions%) DO (
    git checkout "%%m"
    gh release create "%version%-%%m" --generate-notes

    .\gradlew publish
    for %%l in (%modloader%) DO (
        call .\gradlew %%l:build
        .\gradlew %%l:curseforge
        move "%%l\build\libs\eomtw-%version%-%%l.jar" "%%l\build\eomtw-%%m-%%l-%version%.jar"
        rmdir /s /q "%%l\build\libs"
        gh release upload "%version%-%%m" "%%l\build\eomtw-%%m-%%l-%version%.jar"
    )
)
