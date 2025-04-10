#!/bin/bash

# VS Code 실행중인지 확인
if pgrep -x "Code" > /dev/null
then
    echo "VS Code가 실행 중입니다. 설정 변경을 위해 종료하세요."
    exit 1
fi

# 설정 파일 경로
SETTINGS_FILE="$HOME/Library/Application Support/Code/User/settings.json"

# 설정 파일 존재 확인
if [ ! -f "$SETTINGS_FILE" ]; then
    echo "설정 파일이 없습니다. 새로 생성합니다."
    mkdir -p "$(dirname "$SETTINGS_FILE")"
    echo "{}" > "$SETTINGS_FILE"
fi

# 백업 생성
cp "$SETTINGS_FILE" "${SETTINGS_FILE}.backup"
echo "기존 설정 파일 백업을 생성했습니다: ${SETTINGS_FILE}.backup"

# Java 자동완성 비활성화 설정 추가
TMP_FILE=$(mktemp)
cat "$SETTINGS_FILE" | jq '. + {
    "java.completion.enabled": false,
    "java.suggestImports.enabled": false,
    "editor.suggestOnTriggerCharacters": false,
    "java.signatureHelp.enabled": false,
    "editor.quickSuggestions": {
        "other": false,
        "comments": false,
        "strings": false
    },
    "editor.suggest.showKeywords": false
}' > "$TMP_FILE"

# 새 설정 적용
mv "$TMP_FILE" "$SETTINGS_FILE"

echo "VS Code의 Java 자동완성 설정이 비활성화되었습니다."
echo "변경사항을 적용하려면 VS Code를 재시작하세요."
