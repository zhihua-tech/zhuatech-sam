/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.sam.controller;
import cn.zhuatech.sam.common.ApiResponse; import cn.zhuatech.sam.service.LicensePositionService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin/license-position") public class LicensePositionController {
    private final LicensePositionService service; public LicensePositionController(LicensePositionService service){this.service=service;}
    @PostMapping ApiResponse<LicensePositionService.PositionResult> evaluate(@Valid @RequestBody LicensePositionService.PositionRequest request){return ApiResponse.ok(service.evaluate(request));}
}
