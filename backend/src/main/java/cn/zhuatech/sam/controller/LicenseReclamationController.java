/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.sam.controller;

import cn.zhuatech.sam.common.ApiResponse;
import cn.zhuatech.sam.service.LicenseReclamationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sam/insights")
public class LicenseReclamationController {
    private final LicenseReclamationService service;
    public LicenseReclamationController(LicenseReclamationService service) { this.service = service; }

    @PostMapping("/license-reclamation")
    public ApiResponse<LicenseReclamationService.Result> evaluate(
        @Valid @RequestBody LicenseReclamationService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
