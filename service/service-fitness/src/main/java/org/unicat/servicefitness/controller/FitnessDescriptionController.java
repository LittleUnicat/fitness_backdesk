package org.unicat.servicefitness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.unicat.commonutils.R;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author unicat
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/fitness/description")
@Api(tags = "简介表")
@CrossOrigin
public class FitnessDescriptionController {
    
//    @ApiOperation(value = "获取全部简介")
//    @GetMapping("/")
//    public R getAlldescription(){
//        
//    }
}

