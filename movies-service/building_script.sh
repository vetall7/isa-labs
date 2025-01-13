#!/bin/bash

# Find all directories from the current directory
find . -type d | while read -r dir; do
  # Check if pom.xml exists in the directory
  if [[ -f "$dir/pom.xml" ]]; then
    echo "Running 'mvn package' in $dir"
    (cd "$dir" && mvn package)
  fi
done

