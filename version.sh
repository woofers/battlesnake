#!/usr/bin/env bash

VERSION=$(git describe --tags)

if [ $? -eq 0 ]; then
    echo $VERSION
else
    echo $HEROKU_RELEASE_VERSION-$HEROKU_SLUG_COMMIT
fi
