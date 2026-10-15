#!/usr/bin/env bash
# SmartDrivo 100% Free Open Source Push Script
# Usage: ./push-to-github.sh https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

set -e

if [ -z "$1" ]; then
  echo "=========================================================="
  echo "SmartDrivo Open Source - GitHub Push Helper"
  echo "=========================================================="
  echo "பயன்படுத்தும் முறை (Usage):"
  echo "  ./push-to-github.sh <YOUR_GITHUB_REPO_URL>"
  echo ""
  echo "உதாரணம் (Example):"
  echo "  ./push-to-github.sh https://github.com/myname/smartdrivo-free.git"
  echo "=========================================================="
  exit 1
fi

REPO_URL="$1"

echo "Initializing Git repository..."
git init
git branch -M main

echo "Adding all files..."
git add .

echo "Creating initial commit..."
git commit -m "Initial commit: SmartDrivo 100% Free Open Source Driver Suite (All Paywalls Removed)"

echo "Setting remote origin to $REPO_URL..."
git remote remove origin 2>/dev/null || true
git remote add origin "$REPO_URL"

echo "Pushing code to GitHub..."
git push -u origin main --force

echo ""
echo "=========================================================="
echo "வெற்றிகரமாக GitHub-ல் பதிவேற்றப்பட்டது!"
echo "இப்போது உங்கள் GitHub Repository பக்கத்தில் 'Actions' Tab-க்கு செல்லுங்கள்."
echo "2 நிமிடங்களில் இலவச SmartDrivo APK தயாராகிவிடும்!"
echo "=========================================================="
