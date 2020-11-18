package com.xishanqu.redpacket.mapper;

import com.xishanqu.redpacket.common.vo.ImageVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created By BaoNing On 2019年1月22日
 */
@Mapper
public interface ImageMapper {

    /**
     * 新增图片
     *
     * @param imageVo
     * @return
     */
    int insert(ImageVo imageVo);

}
