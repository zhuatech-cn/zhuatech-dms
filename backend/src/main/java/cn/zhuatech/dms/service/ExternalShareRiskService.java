/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.dms.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalShareRiskService {
    public Result evaluate(Request request) {
        int score = switch (request.classification()) {
            case "RESTRICTED" -> 50;
            case "CONFIDENTIAL" -> 30;
            case "INTERNAL" -> 10;
            default -> 0;
        };
        if (request.externalRecipients() > 10) score += 15;
        if (request.expiryHours() > 168) score += 15;
        if (!request.watermarkEnabled()) score += 10;
        if (!request.passwordProtected()) score += 10;
        if (request.downloadAllowed()) score += 10;
        if (request.personalData()) score += 20;
        score = Math.min(100, score);
        String decision = score >= 70 ? "BLOCK" : score >= 35 ? "REVIEW" : "ALLOW";

        List<String> actions = new ArrayList<>();
        if (!request.watermarkEnabled()) actions.add("启用收件人水印与访问审计");
        if (!request.passwordProtected()) actions.add("启用独立口令并通过其他渠道发送");
        if (request.expiryHours() > 168) actions.add("将外链有效期缩短至 7 天以内");
        if (request.personalData()) actions.add("完成个人信息最小化与外发授权复核");
        if (actions.isEmpty()) actions.add("允许创建受控外链并保留访问日志");
        return new Result(request.documentCode(), score, decision,
            !"BLOCK".equals(decision), actions);
    }

    public record Request(@NotBlank String documentCode,
                          @Pattern(regexp = "PUBLIC|INTERNAL|CONFIDENTIAL|RESTRICTED") String classification,
                          @Min(1) int externalRecipients, @Min(1) int expiryHours,
                          boolean watermarkEnabled, boolean passwordProtected,
                          boolean downloadAllowed, boolean personalData) {}

    public record Result(String documentCode, int riskScore, String decision,
                         boolean shareLinkAllowed, List<String> actions) {}
}
