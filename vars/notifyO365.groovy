import org.example.utils.WebhookUtil

def call(Map config = [:]) {
    // 如果未提供 gitCommit，则自动获取最新提交的 commit message
    if (!config.gitCommit || config.gitCommit == 'unknown') {
        // 注意：请确保此步骤在有 node 环境下执行
        config.gitCommit = sh(script: "git log -1 --pretty=%B", returnStdout: true)?.trim()
    }
    // 如果未提供 buildTime，则尝试从 currentBuild 中计算（单位秒）
    if ((config.buildTime == '-1' || !config.buildTime) && currentBuild?.duration) {
        config.buildTime = currentBuild.duration / 1000
    }
    def status = config.status ?: 'Success'
    def branch = config.branch ?: 'unknown'
    def project = config.project ?: 'unknown'
    def gitCommit = config.gitCommit ?: 'unknown'
    def buildTime = config.buildTime ?: '-1'
    def message = config.message ?: "Build for branch ${branch} has ${status == 'Success' ? 'succeeded' : 'failed'}."
    def message2= "****${project}****\n\n" +
                  "**Branch**: ${branch}\n\n" +
                  "**GIT MESSAGE**: ${gitCommit}\n\n" +
                  "**Build Time**: ${buildTime} sec\n\n"
    // 如果没传 webhookUrl，就自动根据分支取
    def webhook = config.webhookUrl ?: WebhookUtil.getWebhookForBranch(branch)

    office365ConnectorSend(
        webhookUrl: webhook,
        message: message2,
        status: status,
        adaptiveCards: true,
    )
}
