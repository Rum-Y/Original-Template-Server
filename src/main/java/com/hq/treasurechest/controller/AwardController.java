package com.hq.treasurechest.controller;

import com.hq.treasurechest.common.CustomConstant;
import com.hq.treasurechest.common.ResultEntity;
import com.hq.treasurechest.service.AwardInfoService;
import com.hq.treasurechest.service.StudentService;
import com.hq.treasurechest.service.TeacherService;
import com.hq.treasurechest.vo.AwardInfoVo;
import com.hq.treasurechest.vo.AwardeeVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/award")
public class AwardController {

    @Resource
    private AwardInfoService awardInfoService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;

    @GetMapping("/awardInfo")
    public ResultEntity<List<AwardInfoVo>> getAwardInfoList(@RequestParam String schoolCode) {
        List<AwardInfoVo> awardInfoVoList = awardInfoService.findAllAwardPerSchool(schoolCode);
        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "获取获奖名单成功", awardInfoVoList);
    }

    @GetMapping("/awardee")
    public ResultEntity<List<AwardeeVo>> getAwardee(@RequestParam String schoolCode, @RequestParam String projectCode, @RequestParam String teamIndex) {
        List<AwardeeVo> awardeeList = awardInfoService.findAwardee(schoolCode, projectCode, teamIndex);

        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "成功根据SPT获取获奖人员名单", awardeeList);
    }

    @PostMapping("/updateAwardee")
    public ResultEntity<Integer> updateAwardee(@RequestBody AwardeeVo awardeeVo){
        System.out.println(awardeeVo.getAwardeeId());
        // 检查是否是教师id
        int count = teacherService.idIsExist(awardeeVo.getAwardeeId());
        // 根据id进行修改
        Integer result = count > 0 ? teacherService.updateTeacherById(awardeeVo) : studentService.updateStudentById(awardeeVo);
        System.out.println(result);
        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "执行成功", result);
    }
}
