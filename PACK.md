# 打包命令

```
./gradlew :app:assembleLatestArm64Release -x :app:validateApiTokens   # 只出包
./gradlew :app:installLatestArm64Release  -x :app:validateApiTokens   # 出包并直接装
```