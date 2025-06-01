#!/bin/bash

JBOSS_HOME=$1

if [ -z "$JBOSS_HOME" ]; then
  echo "Usage: $0 /path/to/jboss-eap"
  exit 1
fi

MODULE_DIR="$JBOSS_HOME/modules/com/example/security/main"
mkdir -p "$MODULE_DIR"

echo "Copying module.xml..."
cp jboss-module/module.xml "$MODULE_DIR/module.xml"

echo "Copying simple-login-module.jar..."
cp simple-login-module.jar "$MODULE_DIR/"

echo "Module installed at $MODULE_DIR"
