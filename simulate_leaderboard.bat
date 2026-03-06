@echo off
rem simple leaderboard tester without timing block

setlocal EnableDelayedExpansion

set PLAYER_COUNT=%1
if "%PLAYER_COUNT%"=="" set PLAYER_COUNT=5000
set LEADERBOARD_KEY=game:leaderboard

where redis-cli >nul 2>&1 || (
echo [ERROR] redis-cli not found on PATH. Install Redis and ensure redis-cli is accessible.
exit /b 1
)

echo [INFO] inserting %PLAYER_COUNT% players into "%LEADERBOARD_KEY%"...
redis-cli DEL %LEADERBOARD_KEY% >nul

set /a i=0
for /L %%j in (1,1,%PLAYER_COUNT%) do (
    set /a i+=1
    redis-cli ZADD %LEADERBOARD_KEY% %%j player%%j >nul
)
echo [INFO] inserted !i! records.

echo [INFO] sample rank checks:
redis-cli ZREVRANK %LEADERBOARD_KEY% player1
set /a MID=%PLAYER_COUNT%/2
redis-cli ZREVRANK %LEADERBOARD_KEY% player%MID%
redis-cli ZREVRANK %LEADERBOARD_KEY% player%PLAYER_COUNT%

endlocal
