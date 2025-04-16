import org.example.utils.WebhookUtil

def call(Map config = [:]) {
    def status = currentBuild.currentResult ?: 'SUCCESS'
    def branch = env.BRANCH_NAME ?: 'master'
    def project = env.JOB_NAME ?: 'Unknown'
    def gitCommit = sh(script: "git log -1 --pretty=%B", returnStdout: true)?.trim() ?: 'Unknown'
    def buildTime = currentBuild.duration / 1000 ?: '-1'
    def message = "**GIT MESSAGE**: ${gitCommit}\n\n" +
                  "**Build Time**: ${buildTime} sec\n\n"

    def webhook = config.webhookUrl ?: WebhookUtil.getWebhookForBranch(branch)

    office365ConnectorSend(
        webhookUrl: webhook,
        message: message,
        status: status=='SUCCESS' ? '✅ SUCCESS' : '🔴 FAILURE',
        adaptiveCards: true,
    )
}
