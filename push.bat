@echo off
setlocal
set /p commit_message="«Î ‰»Î commit –≈œ¢: "

git add .
git commit -m "%commit_message%"
git push -u origin main

pause
