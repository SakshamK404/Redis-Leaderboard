@echo off
rem simulate_leaderboard.bat - simple leaderboard load tester for Redis
rem
rem Usage: simulate_leaderboard.bat [numPlayers]
rem Examples:
rem   simulate_leaderboard.bat          (default inserts 5000 entries)
rem   simulate_leaderboard.bat 10000    (insert 10 000 entries)
rem
rem Requirements:
rem   * redis-server running on localhost:6379
rem   * redis-cli available in the PATH environment variable

setlocal EnableDelayedExpansion

rem --- configuration -----------------------------------------------------
set PLAYER_COUNT=%1







































endlocalecho [DONE] simulation finished.
necho.redis-cli ZREVRANK %LEADERBOARD_KEY% player%PLAYER_COUNT%redis-cli ZREVRANK %LEADERBOARD_KEY% player%MID%set /a MID=%PLAYER_COUNT%/2redis-cli ZREVRANK %LEADERBOARD_KEY% player1echo [INFO] sample rank checks:echo.
nrem --- sample rank queries ------------------------------------------------)    echo [WARN] operations took longer than a minute.) else (    echo [OK] operations completed in under 60 seconds.if %DURATION% LSS 60 (echo [INFO] re‑inserted %PLAYER_COUNT% entries in %DURATION% seconds.    $stopwatch.Elapsed.TotalSeconds;"`) do set DURATION=%%t    $stopwatch.Stop();\    for ($k=1; $k -le %PLAYER_COUNT%; $k++) { redis-cli ZADD %LEADERBOARD_KEY% $k player$k > $null }\    $stopwatch = [System.Diagnostics.Stopwatch]::StartNew();\for /f "usebackq delims=" %%t in (`powershell -NoLogo -NoProfile -Command "\
nrem optional: measure how long it took using PowerShell (prints seconds)
necho [INFO] measuring insertion time using PowerShell...)
echo [INFO] inserted !i! records.    redis-cli ZADD %LEADERBOARD_KEY% %%j player%%j >nul    set /a i+=1set /a i=0
nfor /L %%j in (1,1,%PLAYER_COUNT%) do (
nrem --- insert loop ------------------------------------------------------
necho [INFO] starting bulk insert...redis-cli DEL %LEADERBOARD_KEY% >nul
nrem flush any existing data so the test is repeatable
necho [INFO] preparing to insert %PLAYER_COUNT% players into "%LEADERBOARD_KEY%"...)    exit /b 1    echo [ERROR] redis-cli not found on PATH. Install Redis and ensure redis-cli is accessible.if errorlevel 1 (where redis-cli >nul 2>&1rem --- sanity checks ------------------------------------------------------set LEADERBOARD_KEY=game:leaderboardnif "%PLAYER_COUNT%"=="" set PLAYER_COUNT=5000