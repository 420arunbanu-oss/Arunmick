#!/usr/bin/env bash
set -e

if [ -z "$1" ]; then
  echo "Usage: ./push-to-github.sh <YOUR_GITHUB_REPO_URL>"
  exit 1
fi

REPO_URL="$1"
git init
git branch -M main
git add .
git commit -m "SmartDrivo 100% Free Open Source Driver Suite"
git remote remove origin 2>/dev/null || true
git remote add origin "$REPO_URL"
git push -u origin main --force
