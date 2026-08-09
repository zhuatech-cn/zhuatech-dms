/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.dms.controller;

import cn.zhuatech.dms.common.ApiResponse;
import cn.zhuatech.dms.service.ExternalShareRiskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dms/insights")
public class ExternalShareRiskController {
    private final ExternalShareRiskService service;

    public ExternalShareRiskController(ExternalShareRiskService service) {
        this.service = service;
    }

    @PostMapping("/external-share-risk")
    public ApiResponse<ExternalShareRiskService.Result> evaluate(
        @Valid @RequestBody ExternalShareRiskService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
