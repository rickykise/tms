package ai.fassto.tms.externalservice.parcel.hanjin.helper;

import ai.fassto.tms.domain.parcel.application.vo.FmsInfo;
import lombok.val;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HanjinSlackErrorReporter {
    @Value("${parcel.hanjin.webhook.url}")
    private String webhookUrl;

    @Value("${parcel.hanjin.webhook.token}")
    private String slackToken;

    @Value("${parcel.hanjin.webhook.username}")
    private String slackUsername;

    @Value("${parcel.hanjin.webhook.title}")
    private String slackTitle;

    public void reportProblem(String message) {
        val api = new SlackApi(webhookUrl + slackToken);
        api.call(new SlackMessage(slackUsername, message));
    }

    public void reportProblem(FmsInfo fmsInfo, String message) {
        val slackMessage = getSlackMessage(fmsInfo, message);
        val api = new SlackApi(webhookUrl + slackToken);
        api.call(new SlackMessage(slackUsername, slackMessage));
    }

    private String getSlackMessage(FmsInfo fmsInfo, String errorMessage) {
        return slackTitle + System.lineSeparator() +
                " [ " +
                fmsInfo.warehouseCode() +
                " - 고객사코드: " +
                fmsInfo.customerCode() +
                " ] " +
                errorMessage;
    }
}
