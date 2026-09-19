/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.sam.controller;
import cn.zhuatech.sam.common.ApiResponse; import cn.zhuatech.sam.service.LicensePositionService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin/license-position") public class LicensePositionController {
    private final LicensePositionService service; /**
                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                   */
public LicensePositionController(LicensePositionService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping ApiResponse<LicensePositionService.PositionResult> evaluate(@Valid @RequestBody LicensePositionService.PositionRequest request){return ApiResponse.ok(service.evaluate(request));}
}
