import org.example.utils.WebhookUtil

def call(Map config = [:]) {
    def status = config.status ?: 'Success'
    def branch = config.branch ?: 'unknown'
    def project = config.project ?: 'unknown'
    def gitCommit = config.gitCommit ?: 'unknown'
    def buildTime = config.buildTime ?: '-1'
    def message = config.message ?: "Build for branch ${branch} has ${status == 'Success' ? 'succeeded' : 'failed'}."
    def message2= "****${project}****\n" +
                  "**Branch**: ${branch}\n" +
                  "**GIT MESSAGE**: ${gitCommit}\n" +
                  "**Build Time**: ${buildTime} sec\n"
    // 如果没传 webhookUrl，就自动根据分支取
    def webhook = config.webhookUrl ?: WebhookUtil.getWebhookForBranch(branch)

    office365ConnectorSend(
        webhookUrl: webhook,
        message: message,
        status: status,
        adaptiveCards: true
    )
}
