package com.tkp.learn.web.controller;

import com.tkp.learn.web.actuator.model.ViewBean;
import com.tkp.learn.web.model.bo.ExperienceSharingBo;
import com.tkp.learn.web.model.bo.PersonExperienceSharingBo;
import com.tkp.learn.web.model.vo.AddExperienceSharingVo;
import com.tkp.learn.web.model.vo.ExperienceSharingVo;
import com.tkp.learn.web.model.vo.MyLearnDataLessonExperienceVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.service.ExperienceSharingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author itw_liupeng01
 * @since 2020-06-05
 */
@Slf4j
@RestController
@RequestMapping("/experiencesharing")
public class ExperienceSharingController {
    @Autowired
    private ExperienceSharingService experienceSharingService;

    @PostMapping("/addExperienceSharing.auth")
    public ViewBean<Integer> addExperienceSharing(@RequestBody AddExperienceSharingVo addExperienceSharingVo) {
        return ViewBean.createSuccess(experienceSharingService.addExperienceSharing(addExperienceSharingVo));
    }

    @PostMapping("/getExperienceSharingList.auth")
    public ViewBean<PageVo<ExperienceSharingBo>> getExperienceSharingList(@RequestBody PageParamVo<ExperienceSharingVo> pageParamVo) {
        PageVo<ExperienceSharingBo> pageVo = experienceSharingService.getExperienceSharingList(pageParamVo);
        return ViewBean.createSuccess(pageVo);
    }

    @GetMapping("/deleteExperienceSharing.auth")
    public ViewBean<Integer> deleteExperienceSharing(@RequestParam("courseExperienceId") String courseExperienceId) {
        return ViewBean.createSuccess(experienceSharingService.deleteExperienceSharing(courseExperienceId));
    }

    @GetMapping("/addPraises.auth")
    public ViewBean<Integer> addPraises(@RequestParam("courseExperienceId") String courseExperienceId) {
        return ViewBean.createSuccess(experienceSharingService.addPraises(courseExperienceId));
    }

    @PostMapping("/getMyLearnDataLessonExperienceList.auth")
    public ViewBean<PageVo<PersonExperienceSharingBo>> getMyLearnDataLessonExperienceList(@RequestBody PageParamVo<MyLearnDataLessonExperienceVo> pageParamVo) {
        PageVo<PersonExperienceSharingBo> pageVo = experienceSharingService.getMyLearnDataLessonExperienceList(pageParamVo);
        return ViewBean.createSuccess(pageVo);
    }


    @GetMapping("/getThisLearnDataLessonExperience.auth")
    public ViewBean<PersonExperienceSharingBo> getThisLearnDataLessonExperience(@RequestParam("courseExperienceId") String courseExperienceId) {
        PersonExperienceSharingBo personExperienceSharingBo = experienceSharingService.getThisLearnDataLessonExperience(courseExperienceId);
        return ViewBean.createSuccess(personExperienceSharingBo);
    }

}
