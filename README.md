# Jenkins Shared Library - Notification

## 用法示例

在 Jenkinsfile 中加载 Shared Library：

```groovy
@Library('jenkins-shared-lib') _

pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                echo 'Do something...'
            }
        }
    }
    post {
        success {
            notifyO365(
                status: 'Success',
                branch: env.BRANCH_NAME // 自动选取 webhook
            )
        }
        failure {
            notifyO365(
                status: 'Failure',
                branch: env.BRANCH_NAME
            )
        }
    }
}
```

## 函数参数

| 参数名      | 说明                          | 默认值           |
|-----------|-----------------------------|----------------|
| `status`  | 构建状态（Success / Failure）  | `'Success'`    |
| `branch`  | 分支名                         | `'unknown'`    |
| `message` | 自定义消息                      | 自动生成        |
| `webhookUrl` | 可选；手动指定 Webhook URL   | 根据分支自动匹配 |
