package com.tkp.learn.archive.task.service.serviceImpl;

import com.tkp.learn.archive.task.dao.TaskTopListMapper;
import com.tkp.learn.archive.task.enums.DurationUintEnum;
import com.tkp.learn.archive.task.model.po.*;
import com.tkp.learn.archive.task.service.TaskTolListService;
import com.tkp.learn.archive.task.utils.DateUtils;
import com.tkp.learn.archive.task.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: itw_wangsc01
 * @createDate: 2020/6/5
 * @version: 1.0
 */
@Service
@Slf4j
public class TaskTolListServiceImpl implements TaskTolListService {
    @Value("${task.topListTask.deviationValue}")
    private int deviationValue;
    @Autowired
    private TaskTopListMapper taskTopListMapper;

    private long durationTotal = 0;
    private int ranking = 0;

    @Override
    public List<TaskTopListPo> getSalesDurationDetail(String begin, String end, int startIndex, int size) {
        List<TaskTopListPo> bos = taskTopListMapper.getSalesDurationDetail(startIndex, size);
        return bos;
    }

    @Override
    @Transactional
    public void handleDetailToMonthYear(List<VideoBehaviorHisPo> bos) throws Exception {
        List<LearnDataVideoMonthPo> monthInsertPos = new ArrayList<>();
        List<LearnDataVideoMonthPo> monthUpdatePos = new ArrayList<>();
        List<LearnDataVideoYearPo> yearInsertPos = new ArrayList<>();
        List<LearnDataVideoYearPo> yearUpdatePos = new ArrayList<>();
        List<VideoBehaviorHisPo> insertHis = new ArrayList<>();
        List<VideoBehaviorHisPo> deleteHisTemp = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bos)) {
            for (VideoBehaviorHisPo bo : bos) {
                LocalDateTime lastStopAt = bo.getLastStopAt();
                int boYear = lastStopAt.getYear();
                int boMonth = lastStopAt.getDayOfMonth();
                //1.判断月表此年此月此人是否有数据，没有则新增，有则更新
                LearnDataVideoMonthPo monthPo = taskTopListMapper.queryLearnDataVideoMonth(boYear, boMonth, bo.getSalesmanId());
                if (ObjectUtils.isEmpty(monthPo)) {
                    LearnDataVideoMonthPo temp = monthInsertPos.stream().filter(po -> boYear == po.getYear() && boMonth == po.getMonth() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(temp)) {
                        int newDuration = temp.getDuration() + bo.getDuration();
                        temp.setDuration(newDuration);
                    } else {
                        buildLearnDataVideoMonthPos(monthInsertPos, bo, boYear, boMonth);
                    }
                } else {
                    LearnDataVideoMonthPo updateTemp = monthUpdatePos.stream().filter(po -> boYear == po.getYear() && boMonth == po.getMonth() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(updateTemp)) {
                        int newDuration = updateTemp.getDuration() + bo.getDuration();
                        updateTemp.setDuration(newDuration);
                    } else {
                        int duration = monthPo.getDuration() + bo.getDuration();
                        monthPo.setDuration(duration);
                        monthPo.setUpdateTime(DateUtils.getNowDate());
                        monthUpdatePos.add(monthPo);
                    }
                }
                //2.判断年表此年此人是否有数据，没有则新增，有则更新
                LearnDataVideoYearPo yearPo = taskTopListMapper.queryLearnDataVideoYear(boYear, bo.getSalesmanId());
                if (ObjectUtils.isEmpty(yearPo)) {
                    LearnDataVideoYearPo temp = yearInsertPos.stream().filter(po -> boYear == po.getYear() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(temp)) {
                        int newDuration = temp.getDuration() + bo.getDuration();
                        temp.setDuration(newDuration);
                    } else {
                        buildLearnDataVideoYearPos(yearInsertPos, bo, boYear);
                    }
                } else {
                    LearnDataVideoYearPo updateTemp = yearUpdatePos.stream().filter(po -> boYear == po.getYear() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(updateTemp)) {
                        int newDuration = updateTemp.getDuration() + bo.getDuration();
                        updateTemp.setDuration(newDuration);
                    } else {
                        int duration = yearPo.getDuration() + bo.getDuration();
                        yearPo.setDuration(duration);
                        yearPo.setUpdateTime(DateUtils.getNowDate());
                        yearUpdatePos.add(yearPo);
                    }
                }
                //3.将明细临时表已处理数据迁移至真正明细表中
                insertHis.add(bo);
                deleteHisTemp.add(bo);
            }
            if (!CollectionUtils.isEmpty(monthInsertPos)) {
                taskTopListMapper.insertLearnDataVideoMonth(monthInsertPos);
            }
            if (!CollectionUtils.isEmpty(monthUpdatePos)) {
                taskTopListMapper.updateLearnDataVideoMonthById(monthUpdatePos);
            }
            if (!CollectionUtils.isEmpty(yearInsertPos)) {
                taskTopListMapper.insertLearnDataVideoYear(yearInsertPos);
            }
            if (!CollectionUtils.isEmpty(yearUpdatePos)) {
                taskTopListMapper.updateLearnDataVideoYearById(yearUpdatePos);
            }
            if (!CollectionUtils.isEmpty(insertHis)) {
                taskTopListMapper.insertVideoBehaviorHis(insertHis);
            }
            if (!CollectionUtils.isEmpty(deleteHisTemp)) {
                taskTopListMapper.deleteVideoBehaviorHisTemp(deleteHisTemp);
            }
        }
    }

    @Override
    @Transactional
    public void handleDetailToMonthYear4Init(List<VideoBehaviorHisPo> bos) throws Exception {
        List<LearnDataVideoMonthPo> monthInsertPos = new ArrayList<>();
        List<LearnDataVideoMonthPo> monthUpdatePos = new ArrayList<>();
        List<LearnDataVideoYearPo> yearInsertPos = new ArrayList<>();
        List<LearnDataVideoYearPo> yearUpdatePos = new ArrayList<>();
        List<VideoBehaviorHisPo> deleteHisBak = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bos)) {
            for (VideoBehaviorHisPo bo : bos) {
                LocalDateTime lastStopAt = bo.getLastStopAt();
                int boYear = lastStopAt.getYear();
                int boMonth = lastStopAt.getDayOfMonth();
                //1.判断月表此年此月此人是否有数据，没有则新增，有则更新
                LearnDataVideoMonthPo monthPo = taskTopListMapper.queryLearnDataVideoMonth(boYear, boMonth, bo.getSalesmanId());
                if (ObjectUtils.isEmpty(monthPo)) {
                    LearnDataVideoMonthPo temp = monthInsertPos.stream().filter(po -> boYear == po.getYear() && boMonth == po.getMonth() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(temp)) {
                        int newDuration = temp.getDuration() + bo.getDuration();
                        temp.setDuration(newDuration);
                    } else {
                        buildLearnDataVideoMonthPos(monthInsertPos, bo, boYear, boMonth);
                    }
                } else {
                    LearnDataVideoMonthPo updateTemp = monthUpdatePos.stream().filter(po -> boYear == po.getYear() && boMonth == po.getMonth() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(updateTemp)) {
                        int newDuration = updateTemp.getDuration() + bo.getDuration();
                        updateTemp.setDuration(newDuration);
                    } else {
                        int duration = monthPo.getDuration() + bo.getDuration();
                        monthPo.setDuration(duration);
                        monthPo.setUpdateTime(DateUtils.getNowDate());
                        monthUpdatePos.add(monthPo);
                    }
                }
                //2.判断年表此年此人是否有数据，没有则新增，有则更新
                LearnDataVideoYearPo yearPo = taskTopListMapper.queryLearnDataVideoYear(boYear, bo.getSalesmanId());
                if (ObjectUtils.isEmpty(yearPo)) {
                    LearnDataVideoYearPo temp = yearInsertPos.stream().filter(po -> boYear == po.getYear() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(temp)) {
                        int newDuration = temp.getDuration() + bo.getDuration();
                        temp.setDuration(newDuration);
                    } else {
                        buildLearnDataVideoYearPos(yearInsertPos, bo, boYear);
                    }
                } else {
                    LearnDataVideoYearPo updateTemp = yearUpdatePos.stream().filter(po -> boYear == po.getYear() && bo.getSalesmanId().equals(po.getSaleNo())).findAny().orElse(null);
                    if (!ObjectUtils.isEmpty(updateTemp)) {
                        int newDuration = updateTemp.getDuration() + bo.getDuration();
                        updateTemp.setDuration(newDuration);
                    } else {
                        int duration = yearPo.getDuration() + bo.getDuration();
                        yearPo.setDuration(duration);
                        yearPo.setUpdateTime(DateUtils.getNowDate());
                        yearUpdatePos.add(yearPo);
                    }
                }
                //3.将明细临时表已处理数据迁移至真正明细表中
                deleteHisBak.add(bo);
            }
            if (!CollectionUtils.isEmpty(monthInsertPos)) {
                taskTopListMapper.insertLearnDataVideoMonth(monthInsertPos);
            }
            if (!CollectionUtils.isEmpty(monthUpdatePos)) {
                taskTopListMapper.updateLearnDataVideoMonthById(monthUpdatePos);
            }
            if (!CollectionUtils.isEmpty(yearInsertPos)) {
                taskTopListMapper.insertLearnDataVideoYear(yearInsertPos);
            }
            if (!CollectionUtils.isEmpty(yearUpdatePos)) {
                taskTopListMapper.updateLearnDataVideoYearById(yearUpdatePos);
            }
            if (!CollectionUtils.isEmpty(deleteHisBak)) {
                taskTopListMapper.deleteVideoBehaviorHisBak(deleteHisBak);
            }
        }
    }

    @Override
    @Transactional
    public void handleExceptionDataBak2Temp(List<VideoBehaviorHisPo> bos) throws Exception {
        if (!CollectionUtils.isEmpty(bos)) {
            taskTopListMapper.insertVideoBehaviorHisTemp(bos);
            taskTopListMapper.deleteVideoBehaviorHisBak(bos);
        }
    }

    private void buildLearnDataVideoMonthPos(List<LearnDataVideoMonthPo> tempPos, VideoBehaviorHisPo bo, int boYear, int boMonth) {
        LearnDataVideoMonthPo po = new LearnDataVideoMonthPo();
        po.setId(UUIDUtils.generateUUID());
        po.setCreateTime(DateUtils.getNowDate());
        po.setDuration(bo.getDuration());
        po.setSaleNo(bo.getSalesmanId());
        po.setMonth(boMonth);
        po.setYear(boYear);
        tempPos.add(po);
    }

    private void buildLearnDataVideoYearPos(List<LearnDataVideoYearPo> tempPos, VideoBehaviorHisPo bo, int boYear) {
        LearnDataVideoYearPo po = new LearnDataVideoYearPo();
        po.setId(UUIDUtils.generateUUID());
        po.setCreateTime(DateUtils.getNowDate());
        po.setDuration(bo.getDuration());
        po.setSaleNo(bo.getSalesmanId());
        po.setYear(boYear);
        tempPos.add(po);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleDayToMonthTopList(int year, int month) throws Exception {
        int start = 0;
        List<LearnDataVideoDayPo> learnDataVideoDayPos = null;
        //1.清空前天的年月的月榜
        taskTopListMapper.deleteMonthTopList(year, month);
        init4Top();
        do {
            LOGGER.error("执行月榜单,从{}到{},任务进度{}", year, month, start);
            learnDataVideoDayPos = taskTopListMapper.getMonthTopListFromLearnDataVideoMonth(year, month, start, deviationValue);
            if (!CollectionUtils.isEmpty(learnDataVideoDayPos)) {
                List<MonthTaskTopListPo> monthTopListPos = new ArrayList<>();
                buildMonthTopListPos(learnDataVideoDayPos, monthTopListPos);
                taskTopListMapper.insertMonthTopList(monthTopListPos);
            }
            start += deviationValue;
        } while (!CollectionUtils.isEmpty(learnDataVideoDayPos));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleDayToYearTopList(int year) throws Exception {
        int start = 0;
        List<LearnDataVideoDayPo> learnDataVideoDayPos = null;
        //1.清空前天的年月的年榜
        taskTopListMapper.deleteYearTopList(year);
        init4Top();
        do {
            LOGGER.error("执行年榜单,年份：{},任务进度{}", year, start);
            learnDataVideoDayPos = taskTopListMapper.getYearTopListFromViewLearnDataVideoYear(start, deviationValue, year);
            if (!CollectionUtils.isEmpty(learnDataVideoDayPos)) {
                List<YearTaskTopListPo> yearTopListPos = new ArrayList<>();
                buildYearTopListPos(learnDataVideoDayPos, yearTopListPos);
                taskTopListMapper.insertYearTopList(yearTopListPos);
            }
            start += deviationValue;
        } while (!CollectionUtils.isEmpty(learnDataVideoDayPos));
    }

    private void buildMonthTopListPos(List<LearnDataVideoDayPo> learnDataVideoDayPos, List<MonthTaskTopListPo> monthTopListPos) {
        if (!CollectionUtils.isEmpty(learnDataVideoDayPos)) {
            for (LearnDataVideoDayPo learnDataVideoDayPo : learnDataVideoDayPos) {
                MonthTaskTopListPo monthTaskTopListPo = new MonthTaskTopListPo();
                monthTaskTopListPo.setId(UUIDUtils.generateUUID());
                monthTaskTopListPo.setSaleNo(learnDataVideoDayPo.getSaleNo());
                monthTaskTopListPo.setDuration(learnDataVideoDayPo.getDuration());
                monthTaskTopListPo.setDurationUnit(DurationUintEnum.MINUTES.getCode());
                monthTaskTopListPo.setMonth(learnDataVideoDayPo.getMonth());
                monthTaskTopListPo.setYear(learnDataVideoDayPo.getYear());
                if (ranking == 0 || durationTotal != learnDataVideoDayPo.getDuration()) {
                    ranking += 1;
                    durationTotal = learnDataVideoDayPo.getDuration();
                }
                monthTaskTopListPo.setRanking(ranking);
                monthTaskTopListPo.setUpdateTime(new Date());
                monthTopListPos.add(monthTaskTopListPo);
            }
        }
    }

    private void buildYearTopListPos(List<LearnDataVideoDayPo> learnDataVideoDayPos, List<YearTaskTopListPo> yearTopListPos) {
        if (!CollectionUtils.isEmpty(learnDataVideoDayPos)) {
            for (LearnDataVideoDayPo learnDataVideoDayPo : learnDataVideoDayPos) {
                YearTaskTopListPo yearTaskTopListPo = new YearTaskTopListPo();
                yearTaskTopListPo.setId(UUIDUtils.generateUUID());
                yearTaskTopListPo.setSaleNo(learnDataVideoDayPo.getSaleNo());
                yearTaskTopListPo.setDuration(learnDataVideoDayPo.getDuration());
                yearTaskTopListPo.setDurationUnit(DurationUintEnum.MINUTES.getCode());
                yearTaskTopListPo.setYear(learnDataVideoDayPo.getYear());
                if (ranking == 0 || durationTotal != learnDataVideoDayPo.getDuration()) {
                    ranking += 1;
                    durationTotal = learnDataVideoDayPo.getDuration();
                }
                yearTaskTopListPo.setRanking(ranking);
                yearTaskTopListPo.setUpdateTime(new Date());
                yearTopListPos.add(yearTaskTopListPo);
            }
        }
    }

    private void init4Top() {
        durationTotal = 0;
        ranking = 0;
    }

    //处理排榜
    private void handleTopList(int year, int month) throws Exception {
        // 2.处理日统计表learn_data_video_day-》月榜单表learn_video_month_top_list
        handleDayToMonthTopList(year, month);
        // 3.处理月表视图view_learn_data_video_month-》年榜单表learn_video_year_top_list
        handleDayToYearTopList(year);
    }

}
