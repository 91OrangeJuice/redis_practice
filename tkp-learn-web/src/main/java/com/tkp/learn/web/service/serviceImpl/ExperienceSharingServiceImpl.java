package com.tkp.learn.web.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tkp.learn.web.dao.*;
import com.tkp.learn.web.model.bo.PersonExperienceSharingBo;
import com.tkp.learn.web.model.bo.PraiseWorkBo;
import com.tkp.learn.web.model.enums.ExperienceSharingStatusEnum;
import com.tkp.learn.web.model.enums.IdentityEnum;
import com.tkp.learn.web.model.bo.ExperienceSharingBo;
import com.tkp.learn.web.model.enums.PraisesStatusEnum;
import com.tkp.learn.web.model.enums.SerchConditionEnum;
import com.tkp.learn.web.model.po.LearnDataLessonExperiencePo;
import com.tkp.learn.web.model.po.LearnDataPraisesPo;
import com.tkp.learn.web.model.vo.AddExperienceSharingVo;
import com.tkp.learn.web.model.vo.ExperienceSharingVo;
import com.tkp.learn.web.model.vo.PageParamVo;
import com.tkp.learn.web.model.vo.common.PageVo;
import com.tkp.learn.web.security.SecurityUser;
import com.tkp.learn.web.service.ExperienceSharingService;
import com.tkp.learn.web.utils.PageUtils;
import com.tkp.learn.web.utils.SecurityUtils;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author itw_liupeng01
 * @since 2020-06-05
 */
@Slf4j
@Service
public class ExperienceSharingServiceImpl implements ExperienceSharingService {
    //public static final long INITWONPRAISENUM = 0;
    @Autowired
    private ExperienceSharingMapper experienceSharingMapper;
    @Autowired
    private LearnLessonMapper learnLessonMapper;
    @Autowired
    private LearnDataPraisesMapper learnDataPraisesMapper;


    @Transactional
    @Override
    public int addExperienceSharing(AddExperienceSharingVo addExperienceSharingVo) {
        String content = addExperienceSharingVo.getContent();
        String lessonId = addExperienceSharingVo.getLessonId();
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String workNo = localUser.getWorkNo();
        String orgCode = localUser.getOrgCode();
        IdentityEnum identity = localUser.getIdentity();
        String baseFileName = getBaseFileName(lessonId);

        LearnDataLessonExperiencePo learnDataLessonExperiencePo = new LearnDataLessonExperiencePo();
        learnDataLessonExperiencePo.setLessonId(lessonId);
        learnDataLessonExperiencePo.setContent(EmojiParser.parseToAliases(content));
        learnDataLessonExperiencePo.setSaleNo(workNo);
        learnDataLessonExperiencePo.setCourseName(baseFileName);
        learnDataLessonExperiencePo.setBranchCode(orgCode);
        learnDataLessonExperiencePo.setStatus(ExperienceSharingStatusEnum.valid.getCode());
        learnDataLessonExperiencePo.setPublishTime(LocalDateTime.now());
        learnDataLessonExperiencePo.setUserType(identity.getCode());

        return experienceSharingMapper.insert(learnDataLessonExperiencePo);
    }

    @Override
    public PageVo<ExperienceSharingBo> getExperienceSharingList(PageParamVo<ExperienceSharingVo> pageParamVo) {
        ExperienceSharingVo condition = buildCondition(pageParamVo);
        Page<ExperienceSharingBo> ewxperienceSharingBoPage = new Page<>(pageParamVo.getPageNum(), pageParamVo.getPageSize());
        IPage<ExperienceSharingBo> experienceSharingList = experienceSharingMapper.getExperienceSharingList(ewxperienceSharingBoPage, condition);
        return PageUtils.buildPageVo(experienceSharingList);
    }

    private ExperienceSharingVo buildCondition(PageParamVo<ExperienceSharingVo> pageParamVo) {
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String workNo = localUser.getWorkNo();
        ExperienceSharingVo condition = pageParamVo.getCondition();
        String serchCondition = condition.getSearchCondition();
        condition.setWorkNo(workNo);
        if (SerchConditionEnum.myOrg.getCode().equals(serchCondition)) {
            condition.setOrgNo(localUser.getOrgCode());
        }
        return condition;
    }

    @Override
    @Transactional
    public int deleteExperienceSharing(String courseExperienceId) {
        LearnDataLessonExperiencePo learnDataLessonExperienceSalesmanPo = new LearnDataLessonExperiencePo();
        learnDataLessonExperienceSalesmanPo.setId(courseExperienceId);
        learnDataLessonExperienceSalesmanPo.setStatus(ExperienceSharingStatusEnum.invalid.getCode());
        return experienceSharingMapper.updateById(learnDataLessonExperienceSalesmanPo);
    }

    @Override
    @Transactional
    public int addPraises(String courseExperienceId) {
        int count = getPraisesCountBycourseExperienceId(courseExperienceId);
        if (count > 0) {
            return 0;
        }
        addWonPraiseNum(courseExperienceId);
        LearnDataPraisesPo learnDataPraisesPo = buildLearnDataPraisesBo(courseExperienceId);

        return learnDataPraisesMapper.insert(learnDataPraisesPo);
    }

    @Override
    @Transactional
    public PageVo<PersonExperienceSharingBo> getMyLearnDataLessonExperienceList(PageParamVo pageParamVo) {
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String workNo = localUser.getWorkNo();
        Page<PersonExperienceSharingBo> ewxperienceSharingBoPage = new Page<>(pageParamVo.getPageNum(), pageParamVo.getPageSize());
        IPage<PersonExperienceSharingBo> myLearnDataLessonExperienceList = experienceSharingMapper.getMyLearnDataLessonExperienceList(ewxperienceSharingBoPage,workNo);
        List<PersonExperienceSharingBo> records = myLearnDataLessonExperienceList.getRecords();
        updatePraisesStatus(records);
        return PageUtils.buildPageVo(myLearnDataLessonExperienceList);

    }

    private void updatePraisesStatus(List<PersonExperienceSharingBo> records) {
        ArrayList<String> idList = new ArrayList<>();
        for (PersonExperienceSharingBo pel: records) {
            List<PraiseWorkBo> praiseWorkList = pel.getPraiseWorkList();
            for (PraiseWorkBo p: praiseWorkList) {
                if (p.getStatus().equals("01")){
                    idList.add(p.getId());
                }
            }
        }
        if(idList!=null&&idList.size()>0){
            learnDataPraisesMapper.updateStatusById(idList, PraisesStatusEnum.READED.getCode());
        }
    }

    @Override
    public PersonExperienceSharingBo getThisLearnDataLessonExperience(String courseExperienceId) {
        PersonExperienceSharingBo myLearnDataLessonExperienceList = experienceSharingMapper.getThisLearnDataLessonExperience(courseExperienceId);
        return myLearnDataLessonExperienceList;

    }

    private void addWonPraiseNum(String courseExperienceId) {
        experienceSharingMapper.addWonPraiseNum(courseExperienceId);
    }


    private LearnDataPraisesPo buildLearnDataPraisesBo(String courseExperienceId) {
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String username = localUser.getUsername();
        String workNo = localUser.getWorkNo();

        String PissSaleNo = getPissSaleNoBycourseExperienceId(courseExperienceId);

        LearnDataPraisesPo learnDataPraisesPo = new LearnDataPraisesPo();
        learnDataPraisesPo.setCourseExperienceId(courseExperienceId);
        learnDataPraisesPo.setWorkNo(workNo);
        learnDataPraisesPo.setName(username);
        learnDataPraisesPo.setCreateTime(LocalDateTime.now());
        learnDataPraisesPo.setStatus(PraisesStatusEnum.UNREAD.getCode());
        learnDataPraisesPo.setWonPraiseWorkNo(PissSaleNo);

        return learnDataPraisesPo;
    }

    private String getPissSaleNoBycourseExperienceId(String courseExperienceId) {

        LearnDataLessonExperiencePo learnDataLessonExperienceSalesmanPo = experienceSharingMapper.selectById(courseExperienceId);
        return learnDataLessonExperienceSalesmanPo.getSaleNo();

    }

    private int getPraisesCountBycourseExperienceId(String courseExperienceId) {
        SecurityUser localUser = SecurityUtils.getLoginUser();
        String workNo = localUser.getWorkNo();
        QueryWrapper<LearnDataPraisesPo> learnDataPraisesBoWrapper = new QueryWrapper<>();
        learnDataPraisesBoWrapper.eq("course_experience_id", courseExperienceId)
                .eq("work_no", workNo);
        return learnDataPraisesMapper.selectCount(learnDataPraisesBoWrapper);
    }


    private String getBaseFileName(String lessonId) {
        return learnLessonMapper.queryBaseFileNameById(lessonId);
    }


}
