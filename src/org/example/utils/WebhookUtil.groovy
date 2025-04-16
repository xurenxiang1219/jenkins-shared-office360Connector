package org.example.utils

class WebhookUtil {
    static String getWebhookForBranch(String branch) {
        switch(branch) {
            case 'master':
                return 'https://prod-09.southeastasia.logic.azure.com:443/workflows/260752eb7813459598315d2b35d25aae/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=F6SB8n2qp3HAAnsOZoWHI_UgCUYjMjkDE_hTzVPcpwQ'
            case 'uat':
                return 'https://prod-09.southeastasia.logic.azure.com:443/workflows/260752eb7813459598315d2b35d25aae/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=F6SB8n2qp3HAAnsOZoWHI_UgCUYjMjkDE_hTzVPcpwQ'
            default:
                return 'https://prod-09.southeastasia.logic.azure.com:443/workflows/260752eb7813459598315d2b35d25aae/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=F6SB8n2qp3HAAnsOZoWHI_UgCUYjMjkDE_hTzVPcpwQ'
        }
    }
}
