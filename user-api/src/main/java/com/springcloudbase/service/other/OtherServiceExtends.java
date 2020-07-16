package com.springcloudbase.service.other;

import com.springcloud.other.provider.service.OtherService;
import com.springcloudbase.vo.result.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "other-api")
public interface OtherServiceExtends extends OtherService {


}
