package com.springcloudbase.service.other;

import com.springcloud.other.provider.service.OtherService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "other-api")
public interface OtherServiceExtends extends OtherService {


}
