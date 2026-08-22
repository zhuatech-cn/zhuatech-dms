/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.dms.controller;
import cn.zhuatech.dms.common.ApiResponse; import cn.zhuatech.dms.service.RetentionDecisionService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") public class RetentionDecisionController {private final RetentionDecisionService service; public RetentionDecisionController(RetentionDecisionService service){this.service=service;} @PostMapping("/retention-decision") public ApiResponse<RetentionDecisionService.Result> evaluate(@Valid @RequestBody RetentionDecisionService.Request request){return ApiResponse.ok(service.evaluate(request));}}

