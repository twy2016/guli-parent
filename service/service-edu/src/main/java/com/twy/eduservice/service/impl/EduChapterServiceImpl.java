package com.twy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.twy.eduservice.entity.EduChapter;
import com.twy.eduservice.entity.EduVideo;
import com.twy.eduservice.mapper.EduChapterMapper;
import com.twy.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twy.eduservice.service.EduVideoService;
import com.twy.eduservice.vo.ChapterVo;
import com.twy.eduservice.vo.VideoVo;
import com.twy.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author gongpeng
 * @since 2020-12-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    /**
     * 根据课程Id获取课程章节
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterListByCourseId(String courseId) {
        //最终要的到的数据列表
        List<ChapterVo> chapterVoArrayList = new ArrayList<>();
        //获取章节信息
        List<EduChapter> chapters = this.list(Wrappers.<EduChapter>lambdaQuery().eq(EduChapter::getCourseId, courseId).orderByAsc(EduChapter::getSort));
        //获取课时信息
        List<EduVideo> videos = videoService.list(Wrappers.<EduVideo>lambdaQuery().eq(EduVideo::getCourseId, courseId).orderByAsc(EduVideo::getSort));
        //填充章节vo数据
        for (EduChapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            List<VideoVo> videoVoArrayList = new ArrayList<>();
            for (EduVideo video : videos) {
                if (chapter.getId().equals(video.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
            chapterVoArrayList.add(chapterVo);
        }
        return chapterVoArrayList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if (videoService.getCountByChapterId(id)) {
            throw new GuliException("该分章节下存在视频课程，请先删除视频课程");
        }
        boolean result = this.removeById(id);
        return result;
    }
}
